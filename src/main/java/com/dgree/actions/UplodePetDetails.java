package com.dgree.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.dgree.model.Image;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.service.GoesLocationLatLong;
import com.dgree.service.PetDetailsService;
import com.dgree.service.PetDetailsServiceImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class UplodePetDetails
 */
public class UplodePetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UplodePetDetails() {
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
		
		PetDetails petDetails=new PetDetails();
		petDetails.setPetname(request.getParameter("petname1"));
		petDetails.setAddress1(request.getParameter("address11"));
		petDetails.setAddress2(request.getParameter("address21"));
		petDetails.setCity(request.getParameter("county1"));
		petDetails.setCity(request.getParameter("city1"));
		petDetails.setCountry(request.getParameter("country1"));
		petDetails.setZip(request.getParameter("zip1"));
		
		
		String stringurl = null ;
		String url = request.getHeader("Referer");
		String[] split = url.split("/");
		for (int i = 0; i <= split.length; i++) {
			if(i==split.length){
				int j=i;
				stringurl = split[--j];
			}
		}
		Image image=new Image();
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	        if (isMultipart) {
	                FileItemFactory factory = new DiskFileItemFactory();
	                ServletFileUpload upload = new ServletFileUpload(factory);
	                try {
	                   List<FileItem> multiparts = upload.parseRequest(request);
	                   for (FileItem item : multiparts) {
	                   if (!item.isFormField()) {
	                	   image.setName(item.getName());
	                	   image.setContentType(item.getContentType());
	                	   image.setFileByte(item.get());
	                   }else if("petname1".equalsIgnoreCase(item.getFieldName().trim())){
	                	   petDetails.setPetname(item.getString().trim());
	                   } else if("address11".equalsIgnoreCase(item.getFieldName().trim())){
	                	   petDetails.setAddress1(item.getString().trim());
	                   } else if("address21".equalsIgnoreCase(item.getFieldName().trim())){
	                	   petDetails.setAddress2(item.getString().trim());
	                   } else if("city1".equalsIgnoreCase(item.getFieldName().trim())){
	                	   petDetails.setCity(item.getString().trim());
	                   } else if("county1".equalsIgnoreCase(item.getFieldName().trim())){
	                	   petDetails.setCounty(item.getString().trim());
	                   } else if("country1".equalsIgnoreCase(item.getFieldName().trim())){
	                	   petDetails.setCountry(item.getString().trim());
	                   } else if("zip1".equalsIgnoreCase(item.getFieldName().trim())){
	                	   petDetails.setZip(item.getString().trim());
	                   }
	                   
	                   }
	                  } 
	                  catch (Exception e){
	                    request.setAttribute("message", "File Upload Failed due to " + e);
	                  }
	        	}
	        petDetails.setImage(image);
	    
	        ServletContext servletContext = request.getServletContext();
      	    MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
      	  MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");

      	 
	        GoesLocationLatLong goesLocationLatLong= new GoesLocationLatLong();
	        PetDetails petDetailsWithLatLong = goesLocationLatLong.findLatitudeLongitude(petDetails);
		
	        String latitude = petDetailsWithLatLong.getLatitude();
	        String longiute = petDetailsWithLatLong.getLongittude();
	        
	        if (StringUtils.isEmpty(latitude) || StringUtils.isEmpty(longiute)){
			 SignUpResponce sresponce=new SignUpResponce();
		    	sresponce.setStatuscode("0");
			  	sresponce.setStatusMessage("not updated sussesfully !"); 
			  	request.setAttribute("signupresponce", sresponce);
			  	if("UploadPetDetails".equals(stringurl)){
	        		request.getRequestDispatcher("/home").include(request, response);
	        	return;
	        	}
	        	getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
	        	return;
	        }else{
	        	
	        	PetDetailsService petDeailsService =new PetDetailsServiceImpl();
	        	petDeailsService.insertPetDeails(petDetailsWithLatLong,mongoClient);
	        	
	        	SignUpResponce sresponce=new SignUpResponce();
	        	sresponce.setStatuscode("0");
	        	sresponce.setStatusMessage("updated sussesfully !"); 
	        	request.setAttribute("signupresponce", sresponce);
	        	if("GreenPet".equals(stringurl) || "UpdatePetDetails".equals(stringurl)){
	        		request.getRequestDispatcher("/home").include(request, response);
	        	return;
	        	}
	        	getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
	        	return;
	        }
	}
}