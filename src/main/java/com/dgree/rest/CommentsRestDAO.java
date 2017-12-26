package com.dgree.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.dgree.model.Comments;
import com.dgree.model.PetComments;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CommentsRestDAO {
	
	public PetComments   loadComments(MongoClient mongoClient,PetComments petComments ){
		MongoDatabase database = mongoClient.getDatabase("dgree-treepet");
		MongoCollection<Document> collection = database.getCollection("dgree_image_comments");
		List<Comments> commentsList=new ArrayList<>();
		Document whereQuery = new Document();
		whereQuery.append("pet_id",petComments.getPetID());
		FindIterable<Document> find = collection.find(whereQuery);
		for (Document document : find) {
			List<Document> object = (List<Document>) document.get("comments");
			for (Document document2 : object) {
				Comments comments=new Comments();
				comments.setImageID((String) document.get("image_id"));
				comments.setUser((String)document2.get("user"));
				comments.setComment((String)document2.get("comment"));
				commentsList.add(comments);
			} 
		}
		petComments.setComments(commentsList);
		return petComments;
	}
	
	
	public void saveCommetns(MongoClient mongoClient,PetComments petComments ){
		MongoDatabase database = mongoClient.getDatabase("dgree-treepet");
		MongoCollection<Document> collection = database.getCollection("dgree_image_comments");
		
		List<Comments> commentsList = petComments.getComments();
		Comments comments = commentsList.get(0);

		Document document = new Document();
		document.append("pet_id",petComments.getPetID());

		Document whereQuery = new Document();
		whereQuery.append("pet_id",petComments.getPetID());
		whereQuery.append("_id",new ObjectId(comments.getImageID()));
		whereQuery.append("image_id",comments.getImageID());
		Document findOne = collection.find(whereQuery).first();
			if (findOne == null) {
				 Document entity = new Document();
				  	entity.append("_id",new ObjectId(comments.getImageID()));
				    entity.append("image_id",comments.getImageID());
					entity.append("pet_id",petComments.getPetID());
				    // Create the score nested object
					Document comment = new Document();
					comment.append("user", comments.getUser());
					comment.append("comment",comments.getComment());
					List<Document> list=new ArrayList<>();
					list.add(comment);
					entity.append("comments",list);// append the count array
					collection.insertOne(entity);
			}else{
					Document comment = new Document();
					comment.append("user", comments.getUser());
					comment.append("comment",comments.getComment());
				  	Document comme = new Document().append("comments",comment);
				  	Map<String,Object> map=new HashMap<>();
				  	map.put("$push", comme);
				    Document pushElement = new Document(map);
				    collection.updateOne(whereQuery, pushElement);	
				}
	}
}
