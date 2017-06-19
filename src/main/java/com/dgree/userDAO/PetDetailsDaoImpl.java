package com.dgree.userDAO;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class PetDetailsDaoImpl implements PetDetailsDao {
	public PetDetailsDaoImpl() {
	}	
	
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());


	@Override
	public void insertPetDeails(PetDetails petDetails,MongoClient mongoClient) {
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("Dgree_PetDetails");
			
		GridFS fs=new GridFS(db,"images");
		GridFSInputFile createFile = fs.createFile(petDetails.getImage().getFileByte());
		createFile.setFilename(petDetails.getImage().getName());
		createFile.setContentType(petDetails.getImage().getContentType());
		createFile.save();
		String id = (String)createFile.getId().toString();
		
		BasicDBObject basicDBObject=new BasicDBObject();
		basicDBObject.put("_id", id);
		basicDBObject.put("user_id",petDetails.getLoginUserDetails().getEmail());
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
		Date date=new Date();
		basicDBObject.put("insert_date", date);
		collection.insert(basicDBObject);
	
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
	public List<PetDetails> loadPetDeails(LoginUserDetails loginUserDetails, MongoClient mongoClient) {
		logger.info("**** validating user validateUserEmail ****");
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("Dgree_PetDetails");
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("user_id",loginUserDetails.getEmail());
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("_id",-1);
				DBCursor cursor = collection.find(whereQuery).sort(basicDBObject);
				while (cursor.hasNext()) {
					DBObject dbObject = (DBObject) cursor.next();
					String id =(String) dbObject.get("_id");
					GridFS gfsPhoto = new GridFS(db, "images");

					BasicDBObject whereQueryImage = new BasicDBObject();
					whereQueryImage.append("_id",new ObjectId(id));
					GridFSDBFile imageForOutput = gfsPhoto.findOne(whereQueryImage);
					// save it into a new image file
					try {
						imageForOutput.writeTo("d://test1.png");
					} catch (IOException e) {

						logger.info("inside load images for user ."+imageForOutput);
					}
				}

	return null;
	}

}
