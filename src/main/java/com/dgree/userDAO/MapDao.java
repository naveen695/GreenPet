package com.dgree.userDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.Image;
import com.dgree.model.PetDetails;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MapDao {
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());
    
	  
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
	
	
	
}
