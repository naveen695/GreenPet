package com.dgree.userDAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.dgree.model.Image;
import com.dgree.model.PetDetails;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

public class MapDao {
	public static Logger logger= LogManager.getLogger();
	public List<PetDetails> getPetDetails(MongoDatabase mongoDatabase){
		logger.info("  ----> mapDao.getPetDetails");
		List<PetDetails> list=new ArrayList<>();
		MongoCollection<Document> collection = mongoDatabase.getCollection("Dgree_PetDetails");
				FindIterable<Document> cursor = collection.find();
				MongoCursor<Document> iterator = cursor.iterator();
				while(iterator.hasNext()){
					PetDetails details=new PetDetails();
					Image image=new Image();
					Document dbObject = (Document) iterator.next();
					String id =(String) dbObject.get("_id");
					details.setId(id);
					details.setPetname((String) dbObject.get("petName"));
					details.setAddress1((String) dbObject.get("address1"));
					details.setAddress2((String) dbObject.get("address2"));
					details.setCity((String) dbObject.get("city"));
					details.setCounty((String) dbObject.get("county"));
					details.setCountry((String) dbObject.get("country"));
					details.setZip((String) dbObject.get("zip"));
					details.setLatitude((String) dbObject.get("latitude"));
					details.setLongittude((String) dbObject.get("longittude"));
					//details.setInsertedDate((String) dbObject.get("insert_date"));
					details.setPetDesc((String)dbObject.get("petDesc"));
				list.add(details);
				}
		
		return list;
	}
	public Image loadImage( MongoClient mongoClient,String ID){
		Image image=new Image();
		 
		DB db = new DB(mongoClient, "dgree-treepet");
		
		
		GridFS gfsPhoto = new GridFS(db, "images");
		BasicDBObject whereQueryImage = new BasicDBObject();
		whereQueryImage.append("_id",new ObjectId(ID));
		GridFSDBFile imageForOutput = gfsPhoto.findOne(whereQueryImage);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			imageForOutput.writeTo(baos);
			byte[] bytes = baos.toByteArray();
			image.setName(imageForOutput.getFilename());
			image.setContentType(imageForOutput.getContentType());
			image.setFileByte(bytes);
		} catch (IOException e) {
			logger.info("inside load images for user ."+imageForOutput);
		}		return image;
		
	}
	
	 
}
