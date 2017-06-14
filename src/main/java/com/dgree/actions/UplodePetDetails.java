package com.dgree.actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.dgree.model.Image;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.service.GoesLocationLatLong;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

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
//	    	// create a "photo" namespace
//			GridFS gfsPhoto = new GridFS(db, "photo");
//
//			// get image file from local drive
//			GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
//
//			// set a new filename for identify purpose
//			gfsFile.setFilename(newFileName);
//
//			// save the image file into mongoDB
//			gfsFile.save();

		
		GoesLocationLatLong goesLocationLatLong= new GoesLocationLatLong();
		PetDetails petDetailsWithLatLong = goesLocationLatLong.findLatitudeLongitude(petDetails);
		
		String latitude = petDetails.getLatitude();
		String longiute = petDetails.getLongiute();
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
		}
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
