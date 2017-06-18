package com.dgree.userDAO;

import java.util.logging.Logger;

import org.bson.Document;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class PetDetailsDaoImpl implements PetDetailsDao {
	public PetDetailsDaoImpl() {
	}	
	
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());


	@Override
	public void insertPetDeails(PetDetails petDetails,MongoClient mongoClient) {
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("Dgree_PetDetails");
		long count = collection.count();
		long countNumber = count++;
		BasicDBObject basicDBObject=new BasicDBObject();
		basicDBObject.put("_id", countNumber);
		basicDBObject.put("petName",petDetails.getPetname());
		basicDBObject.put("Address1",petDetails.getAddress1());
		basicDBObject.put("address2", petDetails.getAddress2());
		basicDBObject.put("city", petDetails.getCity());
		basicDBObject.put("couny", petDetails.getCounty());
		basicDBObject.put("country", petDetails.getCountry());
		basicDBObject.put("zip", petDetails.getZip());
		basicDBObject.put("image_name", petDetails.getImage().getName());
		basicDBObject.put("content_type",  petDetails.getImage().getContentType());
		basicDBObject.put("latitude", petDetails.getLatitude());
		basicDBObject.put("longittude", petDetails.getLongittude());
		collection.insert(basicDBObject);
		
		GridFS fs=new GridFS(db,"images");
		GridFSInputFile createFile = fs.createFile(petDetails.getImage().getFileByte());
		createFile.setId(countNumber);
		createFile.setFilename(petDetails.getImage().getName());
		createFile.setContentType(petDetails.getImage().getContentType());
		createFile.save();
	}		

	@Override
	public void updatePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PetDetails loadPetDeails( MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		return null;
	}

}
