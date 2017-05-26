package com.dgree.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class LogOutServlet
 */
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stringurl = null ;
		String url = request.getHeader("Referer");
		if(url != null){
		String[] split = url.split("/");
		for (int i = 0; i <= split.length; i++) {
			if(i==split.length){
				int j=i;
				stringurl = split[--j];
			}
		}
		}
		if(!"LogOutServlet".equals(stringurl)){
		       
		HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			if("GreenPet".equals(stringurl) || "LogOutServlet".equals(stringurl)){
	        	request.getRequestDispatcher("/home").include(request, response);
	        return;
	        }
	        getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
	        return;
		}
		}
}
