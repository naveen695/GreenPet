package com.dgree.actions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.detector.SkinToneDetector;
import com.dgree.detector.SkinToneSpanningDetector;
import com.dgree.model.Image;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.userDAO.UploadMultipleImagesDAO;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class UploadMultipleImages
 */
public class UploadMultipleImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(UploadMultipleImages.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadMultipleImages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/home").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("inside upload multtiple pet details");
		PetDetails petDetails=new PetDetails();
		
        ServletContext servletContext = request.getServletContext();
     	//MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
        MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");

		String stringurl = null ;
		String url = request.getHeader("Referer");
		String[] split = url.split("/");
		for (int i = 0; i <= split.length; i++) {
			if(i==split.length){
				int j=i;
				stringurl = split[--j];
			}
		}
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	        if (isMultipart) {
	                FileItemFactory factory = new DiskFileItemFactory();
	                ServletFileUpload upload = new ServletFileUpload(factory);
	                Image image=new Image();
	                try {
	                   List<FileItem> multiparts = upload.parseRequest(request);
	                   for (FileItem item : multiparts) {
	                	   if (!item.isFormField()) {
	                		   image.setName(item.getName());
	                		   image.setContentType(item.getContentType());
	                		   image.setFileByte(item.get());
	                		   image.setValidate(false);
	                	   }else if("petId".equalsIgnoreCase(item.getFieldName().trim())){
	                		   petDetails.setId(item.getString().trim());
	                	   }
	                   }
	                   petDetails.setImage(image);

	                   if (isNotValidImage(image)) {
	      	        	 SignUpResponce sresponce=new SignUpResponce();
	      			    	sresponce.setStatuscode("0");
	      				  	sresponce.setStatusMessage("Sorry ... ! we can't uploaded petdetails, Please upload tree imges ."); 
	      				  	request.setAttribute("signupresponce", sresponce);
	      				  	if("UploadPetDetails".equals(stringurl)){
	      		        		request.getRequestDispatcher("/home").include(request, response);
	      		        	return;
	      		        	}
	      		        	getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
	      		        	return;
	      			}                   
	                   
	                   UploadMultipleImagesDAO uploadMultipleImagesDAO=new UploadMultipleImagesDAO();
	                   uploadMultipleImagesDAO.uploadMultImages(petDetails, mongoClient);
	                   
	                SignUpResponce sresponce=new SignUpResponce();
	   		    	sresponce.setStatuscode("0");
	   			  	sresponce.setStatusMessage("Uploaded Sussesfully !");
	   			  	request.setAttribute("signupresponce", sresponce);
	   			  	if("UploadPetDetails".equals(stringurl) || "UploadMultipleImages".equals(stringurl)){
	   	        		request.getRequestDispatcher("/HomeServlet").include(request, response);
	   	        	return;
	   	        	}
	   	        	getServletContext().getRequestDispatcher("/HomeServlet").include(request, response);
	   	        	return;
	                   
	                }catch (Exception e) {
	                	request.setAttribute("message", "File Upload Failed due to ");
	                	logger.info("inside upload multtiple pet details",e);
					}
	         }else{
	        		request.getRequestDispatcher("/home").include(request, response);

	         }
	 }
	private boolean isNotValidImage(Image image) {
		byte[] fileByte = image.getFileByte();
	    SkinToneDetector skinToneDetector = new SkinToneSpanningDetector();
	    skinToneDetector.setIsDelta(5);
	    InputStream arrayInputStream=new ByteArrayInputStream(fileByte);
		try {
			return skinToneDetector.isNotValid(arrayInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
