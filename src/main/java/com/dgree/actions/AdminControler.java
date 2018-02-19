package com.dgree.actions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.dgree.service.UserDetails;
import com.dgree.service.UserLogin;
import com.dgree.userDAO.MapDao;
import com.dgree.userDAO.PetDetailsDaoImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class AdminControler extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Admin Get Method .");
		ServletContext servletContext = request.getServletContext();
		MongoDatabase mongoDatabase = (MongoDatabase) servletContext.getAttribute("MongoDatabase");
		HttpSession session = request.getSession();
		loadImages(mongoDatabase, session);
		request.getRequestDispatcher("/admin").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Admin POST Method");

		LoginUserDetails loginUserDetails = new LoginUserDetails();
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(pwd)) {
			UserBean us = new UserBean();
			us.setEmail(email);
			us.setPassword(pwd);
			ServletContext servletContext = request.getServletContext();
			MongoDatabase mongoDatabase = (MongoDatabase) servletContext.getAttribute("MongoDatabase");
			SignUpResponce sresponce = new SignUpResponce();
			UserDetails userSignUp = new UserLogin();
			ValidateUser validateUser = userSignUp.validateUser(mongoDatabase, us);
			logger.info(validateUser.toString());

			if (validateUser.isLoginValid() == true && validateUser.getLoginStauts().equals("active")
					&& validateUser.isAdmin()) {
				loginUserDetails = validateUser.getLoginUserDetails();
				loginUserDetails.setLogin(true);
				loginUserDetails.setMessage("succes");
				sresponce.setStatuscode("0");
				sresponce.setStatusMessage("Success.");
				HttpSession session = request.getSession();
				session.setAttribute("loginUserDetails", loginUserDetails);
				// validate user
				// MongoClient mongoClient =
				// (MongoClient)servletContext.getAttribute("mongoClient");
				loadImages(mongoDatabase, session);
			} else if (validateUser.isLoginValid() == true && validateUser.getLoginStauts().equals("not_active")) {
				loginUserDetails.setLogin(false);
				loginUserDetails.setMessage("not_activate");
				sresponce.setStatuscode("1");
				sresponce.setStatusMessage("validate your mail !");
			} else if (validateUser.isLoginValid() == false && validateUser.getLoginStauts().equals("not_active")) {
				loginUserDetails.setLogin(false);
				loginUserDetails.setMessage("not_match");
				sresponce.setStatuscode("1");
				sresponce.setStatusMessage("Username/Password not match or no user found .Please SignUp ....");
			} else if (validateUser.isAdmin() == false && validateUser.isLoginValid() == true
					&& validateUser.getLoginStauts().equals("active")) {
				loginUserDetails.setLogin(false);
				loginUserDetails.setMessage("Not_Admin");
				sresponce.setStatuscode("1");
				sresponce.setStatusMessage("You don't have Admin Access , Please Contact Admininistrator !");
			}
			request.setAttribute("signupresponce", sresponce);
		} else {
			logger.info("Admin Get Method .");
			ServletContext servletContext = request.getServletContext();
			MongoDatabase mongoDatabase = (MongoDatabase) servletContext.getAttribute("MongoDatabase");
			HttpSession session = request.getSession();
			loadImages(mongoDatabase, session);
			request.getRequestDispatcher("/admin").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/admin").forward(request, response);

	}

	private void loadImages(MongoDatabase mongoDatabase, HttpSession session) {
		PetDetailsDaoImpl detailsDao = new PetDetailsDaoImpl();
		List<PetDetails> petDetails = detailsDao.loadImagesForVerification(mongoDatabase);
		session.setAttribute("loadImages", petDetails);
	}
}
