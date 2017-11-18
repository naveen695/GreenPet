package com.dgree.userDAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.dgree.model.Image;
import com.dgree.model.PetDetails;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class UploadMultipleImagesDAO {
	public static Logger logger= LogManager.getLogger();
	
	public void uploadMultImages(PetDetails petDetails, MongoClient mongoClient){
		logger.info("inside uploadMultImages .");
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection dgreeImageMapCollection = db.getCollection("dgree_ImageMap");
			
		GridFS fs=new GridFS(db,"images");
		GridFSInputFile createFile = fs.createFile(petDetails.getImage().getFileByte());
		createFile.setFilename(petDetails.getImage().getName());
		createFile.setContentType(petDetails.getImage().getContentType());
		createFile.save();
		String id = (String)createFile.getId().toString();
		
		
		BasicDBObject dgreeImageMapObject=new BasicDBObject();
		dgreeImageMapObject.put("petId", petDetails.getId());
		dgreeImageMapObject.put("imageId", id);
		dgreeImageMapObject.put("homePage", 0);
		dgreeImageMapCollection.insert(dgreeImageMapObject);
		
	}
	
	public List<String>   loadMultipleImage (String id, MongoClient mongoClient){
		logger.info("inside load multiple images .");
		
		List<Image> imageList = new ArrayList<>();
		
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection dgreeImageMapCollection = db.getCollection("dgree_ImageMap");

		BasicDBObject imageMap = new BasicDBObject();
		imageMap.append("petId",id);
		List<String> listImageIds =new ArrayList<>();
		DBCursor dbCursor = null;
		try{
			dbCursor = dgreeImageMapCollection.find(imageMap);
			Iterator<DBObject> find = dbCursor.iterator();
			while (find.hasNext()) {
				DBObject next = find.next();
				listImageIds.add((String)next.get("imageId"));
			}
		}finally{
			if (dbCursor!= null) {
				dbCursor.close();
			}
		}
		/*
		for (String imageId : listImageIds) {
			
			GridFS gfsPhoto = new GridFS(db, "images");
			BasicDBObject whereQueryImage = new BasicDBObject();
			whereQueryImage.append("_id",new ObjectId(imageId));
			GridFSDBFile imageForOutput = gfsPhoto.findOne(whereQueryImage);
			Image image=new Image();
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				imageForOutput.writeTo(baos);
				byte[] bytes = baos.toByteArray();
				baos.flush();
				baos.close();
				image.setName(imageForOutput.getFilename());
				image.setContentType(imageForOutput.getContentType());
				image.setFileByte(bytes);
				imageList.add(image);
			} catch (Exception e) {
				logger.info("inside load images for user in multi imageLodeDAO .",e);
			}			
		}
		*/
		return listImageIds;
		
	}
	
	
	public PetDetails loadlikesandDislikes(MongoClient mongoClient,String ID,String uid){
		logger.info("inside likes and dislikes .");
		List<Image> imageList = new ArrayList<>();
		PetDetails details=new PetDetails();
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("dgree_image_likes_dislikes");
				
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("pet_id",ID);
		DBCursor find = collection.find(whereQuery);
			while (find.hasNext()) { 
					Image image=new Image();
					DBObject dbObject = (DBObject) find.next();
					image.setIds((String) dbObject.get("image_id"));
					Object object = dbObject.get("likes");
					Integer integer = Integer.parseInt(object.toString());
					image.setLikes(integer);
					BasicDBList users = (BasicDBList) dbObject.get("users");
					if (users != null) {
						image.setLiked(users.contains(uid));
					}else{
						image.setLiked(false);
					}
					//loadimagecomments
					imageList.add(image);
			}
			details.setImageList(imageList);
		return details;	
	}
	public Integer loadTotalLikes(MongoClient mongoClient,String ID,String uid){
		logger.info("inside likes and dislikes .");
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("dgree_image_likes_dislikes");
		Integer ttl = 0;
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("pet_id",ID);
		DBCursor find = collection.find(whereQuery);
			while (find.hasNext()) { 
					DBObject dbObject = (DBObject) find.next();
					Object object = dbObject.get("likes");
					Integer integer = Integer.parseInt(object.toString());
					ttl += integer; 
			}
		return ttl;	
	}
	public void insertLikesDislikes(MongoClient mongoClient,String petID,String imageID,String uid,Integer likes,String type){
		MongoDatabase database = mongoClient.getDatabase("dgree-treepet");
		MongoCollection<Document> collection = database.getCollection("dgree_image_likes_dislikes");
				
		Document whereQuery = new Document();
		whereQuery.append("pet_id",petID);
		whereQuery.append("_id",new ObjectId(imageID));
		whereQuery.append("image_id",imageID);
		
		
		Document findOne = collection.find(whereQuery).first();
			if (findOne == null) {
				 Document entity = new Document();
				    entity.append("_id",new ObjectId(imageID));
				    entity.append("image_id",imageID);
					entity.append("pet_id",petID);
					entity.append("likes",likes);
				    // Create the score nested object
					List<String> asList = Arrays.asList(uid);
					String[] countArray = { uid }; 
					entity.append("users",asList);// append the count array
					collection.insertOne(entity);
			}else{
				if ("dislikes".equalsIgnoreCase(type)) {
					ArrayList<Document> list =  (ArrayList<Document>) findOne.get("users");
					if (list.remove(uid)) {
						Document likes1 = new Document().append("likes", likes);
					  	Document users1 = new Document().append("users",list);
					  	Map<String,Object> map=new HashMap<>();
					  	map.put("$set",likes1);
					    Document pushElement = new Document(map);
					    collection.updateOne(whereQuery, pushElement);	
					    
						Map<String,Object> map1=new HashMap<>();
					  	map1.put("$set",users1);
					    Document pushElement1 = new Document(map1);
					    collection.updateOne(whereQuery, pushElement1);	
					}
				}else if ("likes".equalsIgnoreCase(type)) {
					Document likes1 = new Document().append("likes", likes);
				  	Document users1 = new Document().append("users",uid);
				  	Map<String,Object> map=new HashMap<>();
				  	map.put("$set",likes1);
				  	map.put("$push", users1);
				    Document pushElement = new Document(map);
				    collection.updateOne(whereQuery, pushElement);	
				}
				  	
			}
		
		
		
	}
}

