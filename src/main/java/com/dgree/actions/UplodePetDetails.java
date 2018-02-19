package com.dgree.actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.detector.SkinToneDetector;
import com.dgree.detector.SkinToneSpanningDetector;
import com.dgree.model.Image;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.model.UserBean;
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
    private static Logger logger = Logger.getLogger(UplodePetDetails.class.getName());

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/home").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("inside -> UplodePetDetails.doPpost() for uploading pet details with image  initilly.");
		PetDetails petDetails=new PetDetails();
		
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
	                	   image.setValidate(false);
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
	                   } else if("petDesc".equalsIgnoreCase(item.getFieldName().trim())){
	                	   petDetails.setPetDesc(item.getString().trim());
	                   }
	                   
	                   }
	                  } 
	                  catch (Exception e){
	                    request.setAttribute("message", "File Upload Failed due to ");
	                  }
	        	
	        petDetails.setImage(image);
	        if (isNotValidImage(image)) {
	        	 SignUpResponce sresponce=new SignUpResponce();
			    	sresponce.setStatuscode("0");
				  	sresponce.setStatusMessage("We can't uploaded PetDetails, Please upload tree images ."); 
				  	request.setAttribute("signupresponce", sresponce);
				  	if("UploadPetDetails".equals(stringurl)){
		        		request.getRequestDispatcher("/home").include(request, response);
		        	return;
		        	}
		        	getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
		        	return;
			}
	        ServletContext servletContext = request.getServletContext();
      	    MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");

	        GoesLocationLatLong goesLocationLatLong= new GoesLocationLatLong();
	        goesLocationLatLong.setProp((Map<String, String>) getServletContext().getAttribute("prop"));
	        PetDetails petDetailsWithLatLong = goesLocationLatLong.findLatitudeLongitude(petDetails);
	        HttpSession session = request.getSession();
	        if (session==null) {
	        	request.getRequestDispatcher("/home").include(request, response);
        	 	return;
	        }
	        	LoginUserDetails loginUserDetails = (LoginUserDetails)session.getAttribute("loginUserDetails");
	        	petDetailsWithLatLong.setLoginUserDetails(loginUserDetails);
	        String latitude = petDetailsWithLatLong.getLatitude();
	        String longiute = petDetailsWithLatLong.getLongittude();
	        
	        if (StringUtils.isEmpty(latitude) || StringUtils.isEmpty(longiute)){
			 SignUpResponce sresponce=new SignUpResponce();
		    	sresponce.setStatuscode("0");
			  	sresponce.setStatusMessage("not uploaded PetDetails, please provide valid address ."); 
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
	        	sresponce.setStatusMessage("Uploaded sussesfully !"); 
	        	request.setAttribute("signupresponce", sresponce);
	        	if("GreenPet".equals(stringurl) || "UpdatePetDetails".equals(stringurl) || "UplodePetDetails".equals(stringurl)){
	        		request.getRequestDispatcher("/HomeServlet").include(request, response);
	        	return;
	        	}
	        	getServletContext().getRequestDispatcher("/HomeServlet").include(request, response);
	        	return;
	        }
	        }else{
	        	
		        		request.getRequestDispatcher("/HomeServlet").include(request, response);
		        	 	return;
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