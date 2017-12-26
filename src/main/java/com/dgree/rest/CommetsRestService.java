package com.dgree.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.dgree.model.Comments;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetComments;
import com.mongodb.MongoClient;

@Path(value="/comments")
public class CommetsRestService {
	

    @Context
    private HttpServletRequest request;
    
	@POST
	@Path("/load/{petID}")
    @Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public  PetComments loadPetComments(@PathParam("petID") String petID) {
		
		//if image id null load all for pet else load image wise
		
		if (request !=null) {
			ServletContext servletContext = request.getServletContext();
			MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
			CommentsRestDAO commentsRestDAO= new CommentsRestDAO();
			PetComments petComments=new PetComments();
			petComments.setPetID(petID);
			return  commentsRestDAO.loadComments(mongoClient, petComments);
		}
		return null;
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("save/{petID}/{imageID}")
    @Produces(MediaType.APPLICATION_JSON)
	public  Comments saveComments(@PathParam("petID") String petID,@PathParam("imageID") String imageID,Comments comments) {
		if (request !=null) {
			HttpSession session = request.getSession();
			LoginUserDetails loginUserDetails = (LoginUserDetails)session.getAttribute("loginUserDetails");
			ServletContext servletContext = request.getServletContext();
			MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
			CommentsRestDAO commentsRestDAO= new CommentsRestDAO();
			PetComments petComments=new PetComments();
			petComments.setPetID(petID);
			List<Comments> comList = new ArrayList<>();
			comments.setImageID(imageID);
			comments.setUser(loginUserDetails.getUserFirstName()+" "+loginUserDetails.getUserLastName());
			comList.add(comments);
			petComments.setComments(comList);
			commentsRestDAO.saveCommetns(mongoClient, petComments); 
		return comments;
		}
		return comments;
	}
}

