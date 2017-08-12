package com.dgree.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.dgree.model.Image;
import com.dgree.userDAO.MapDao;
import com.dgree.userDAO.UploadMultipleImagesDAO;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class LoadMultipleImages
 */
public class LoadMultipleImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadMultipleImages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");

		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
		List<String> loadMultipleImage = new UploadMultipleImagesDAO().loadMultipleImage(id,mongoClient);
	if (!loadMultipleImage.isEmpty()) {
	
		StringBuilder outputResponce=new StringBuilder();
		StringBuilder html= new StringBuilder("<div class='jumbotron'>");
		
		String nextNum = request.getParameter("nextNum");
		String backNum = request.getParameter("backNum");
		
		int next = 0 ;
		int back = 0 ;

		if (StringUtils.isNotBlank(nextNum) || StringUtils.isNotBlank(backNum)) {
		try{
				next = Integer.parseInt(nextNum);
				back = Integer.parseInt(backNum);
		}catch(Exception e){
				
		}
		if ("next".equals(type)) {
			if (loadMultipleImage.size()>next) {
				back=next;
				next = next+1;
				if (next==loadMultipleImage.size()) {
					next=0;
				}
			}else{
				//load
			}
		}else if("back".equals(type)){
			if (loadMultipleImage.size()>back) {
			next=back;
			if(back==0)
				//do load im
				back=loadMultipleImage.size()-1;
			else
				//2
				back=back-1;
			}else{
				//load
			}
		}
	
		

		}
		
				

			
		for (String idString : loadMultipleImage) {
			outputResponce.append("<img src=\"LoadAjaxImage/"+idString+"\" style=\"width : 100px\" ><br/></br>"
					+ "<br>  <input type=\"hidden\" id=\"nextNum\" value=\""+next+"\"> </br>"
							+ " <input type=\"hidden\" id=\"backNum\" value=\""+back+"\">  "
							+ "<input type=\"submit\" value=\"back\" onclick=\"loadajaximages(\'"+id+"\','back')\"> "
									+ "<input type=\"submit\" value=\"next\" onclick=\"loadajaximages(\'"+id+"\','next')\"> </form>");
			break;
		}
	
		response.getWriter().write(html.append(outputResponce.toString()).append("</div>").toString());
	}
	}

}















