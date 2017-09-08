package com.dgree.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PageListDataModel;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.service.PetDetailsService;
import com.dgree.service.PetDetailsServiceImpl;
import com.dgree.userDAO.PetDetailsDao;
import com.dgree.userDAO.PetDetailsDaoImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final int PAGE_LIMIT = 2;
	private static final long serialVersionUID = 1L;
	public static Logger logger= (Logger) LogManager.getLogger();	
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(" Inside home servlet .");
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Object attribute = session.getAttribute("loginUserDetails");
			LoginUserDetails loginUserDetails=(LoginUserDetails)attribute;
		
 			
		if (loginUserDetails != null && loginUserDetails.isLogin()) {
		
			ServletContext servletContext = request.getServletContext();
			MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
			MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
			PetDetailsDao detailsDao=new PetDetailsDaoImpl();
			
			Integer findUserPetCount = detailsDao.findUserPetCount(loginUserDetails, mongoDatabase);
			
			
			
		Map<String,Object> map=new HashMap<>();
		String pageNumberBack = request.getParameter("pageNumberBack");
		String pageNumberNext = request.getParameter("pageNumberNext");
		
		Integer pageNumber;
		String type="";
		if (StringUtils.isNotBlank(pageNumberNext)) {
			pageNumber = Integer.parseInt(pageNumberNext);
			type="next";
		}else if (StringUtils.isNotEmpty(pageNumberBack)){
			pageNumber = Integer.parseInt(pageNumberBack);
			type="back";
		}else{
			pageNumber=0;
		}
		
		
		
		Integer totalPageSize=0;
		if (findUserPetCount%PAGE_LIMIT==0) {
			totalPageSize=findUserPetCount/PAGE_LIMIT;
		}else{
			Integer total=findUserPetCount/PAGE_LIMIT;
			totalPageSize=++total;
		}
		
		int skipRows = pageNumber*PAGE_LIMIT;
		if ("next".equals(type)) {
				if (totalPageSize>pageNumber) {
					map.put("pageLimit",PAGE_LIMIT);
					map.put("skipRows",skipRows);
					pageNumber++;
				}else{
					map.put("pageLimit",PAGE_LIMIT);
					map.put("skipRows",0);
					pageNumber=1;
				}
		}else if("back".equals(type)){
			if (totalPageSize>pageNumber) {
				if (pageNumber == 1) {
					map.put("pageLimit",PAGE_LIMIT);
					
					map.put("skipRows",(totalPageSize-1)*PAGE_LIMIT);
					pageNumber=totalPageSize;
				}else{
					--pageNumber;
					map.put("pageLimit",PAGE_LIMIT);
					map.put("skipRows",pageNumber*PAGE_LIMIT);
					
				}
			}else{
				--pageNumber;
				map.put("pageLimit",PAGE_LIMIT);
				map.put("skipRows",0);
			}
		}else{
			map.put("pageLimit",PAGE_LIMIT);
			map.put("skipRows",0);
			pageNumber=1;
		}
		
		
			PageListDataModel dataModel=new PageListDataModel();
			dataModel.setPageNumber(pageNumber);
			dataModel.setTotalpageNumber(totalPageSize);
			request.setAttribute("dataModel",dataModel);
		
			PetDetailsService detailsService=new PetDetailsServiceImpl();
			List<PetDetails> loadPetDeails = detailsService.loadPetDeails(map,loginUserDetails,mongoClient);
			session.setAttribute("petDeails",loadPetDeails);	
		}else{
			SignUpResponce responce1=new SignUpResponce();
			responce1.setStatuscode("0");
			responce1.setStatusMessage("Please login !!!!!.");
			request.setAttribute("msg",responce1);	
		}
	}else{
			SignUpResponce responce1=new SignUpResponce();
			responce1.setStatuscode("0");
			responce1.setStatusMessage("Please Login !!!!!.");
			request.setAttribute("msg",responce1);	
			
		}
		request.getServletContext().getRequestDispatcher("/home").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}

}
