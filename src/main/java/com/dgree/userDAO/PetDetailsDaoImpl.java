package com.dgree.userDAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.Image;
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
import com.mongodb.client.result.UpdateResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class PetDetailsDaoImpl implements PetDetailsDao {
	public PetDetailsDaoImpl() {
	}	
	
	public static Logger logger= LogManager.getLogger();


	@Override
	public void insertPetDeails(PetDetails petDetails,MongoClient mongoClient) {
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("Dgree_PetDetails");
		DBCollection dgreeImageMap = db.getCollection("dgree_ImageMap");
			
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
		basicDBObject.put("address1",petDetails.getAddress1());
		basicDBObject.put("address2", petDetails.getAddress2());
		basicDBObject.put("city", petDetails.getCity());
		basicDBObject.put("county", petDetails.getCounty());
		basicDBObject.put("country", petDetails.getCountry());
		basicDBObject.put("zip", petDetails.getZip());
		basicDBObject.put("image_name", petDetails.getImage().getName());
		basicDBObject.put("latitude", petDetails.getLatitude());
		basicDBObject.put("longittude", petDetails.getLongittude());
		basicDBObject.put("petDesc", petDetails.getPetDesc());
		
		collection.insert(basicDBObject);

		BasicDBObject dgreeImageMapObject=new BasicDBObject();
		dgreeImageMapObject.put("petId", id);
		dgreeImageMapObject.put("imageId", id);
		dgreeImageMapObject.put("homePage", 1);
		dgreeImageMap.insert(dgreeImageMapObject);
	
	}		

	@Override
	public void updatePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase) {

		MongoCollection<Document> collection = mongoDatabase.getCollection("Dgree_PetDetails");
			
		 		
		BasicDBObject basicDBObject=new BasicDBObject();
		basicDBObject.put("petDesc", petDetails.getPetDesc());
		basicDBObject.put("petName",petDetails.getPetname());
		basicDBObject.put("address1",petDetails.getAddress1());
		basicDBObject.put("address2", petDetails.getAddress2());
		basicDBObject.put("city", petDetails.getCity());
		basicDBObject.put("county", petDetails.getCounty());
		basicDBObject.put("country", petDetails.getCountry());
		basicDBObject.put("zip", petDetails.getZip());
		basicDBObject.put("latitude", petDetails.getLatitude());
		basicDBObject.put("longittude", petDetails.getLongittude());

		Bson filter = new Document("_id",petDetails.getId());
		Bson updateOperationDocument = new Document("$set", basicDBObject);
		UpdateResult updateOne = collection.updateOne(filter, updateOperationDocument);
				

		 
			
	}

	@Override
	public void deletePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PetDetails> loadPetDeails(Map<String, Object> parameters,LoginUserDetails loginUserDetails, MongoClient mongoClient) {
		logger.info("**** loadPetDeails user loadPetDeails ****");
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("Dgree_PetDetails");
		
		List<PetDetails> list=new ArrayList<>();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("user_id",loginUserDetails.getEmail());
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("_id",-1);
				
				DBCursor cursor = collection.find(whereQuery).skip((Integer)parameters.get("skipRows")).limit((Integer)parameters.get("pageLimit")).sort(basicDBObject);
		try{
				while (cursor.hasNext()) {
				
					PetDetails details=new PetDetails();
					Image image=new Image();
					
					DBObject dbObject = (DBObject) cursor.next();
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
					GridFS gfsPhoto = new GridFS(db, "images");
					BasicDBObject whereQueryImage = new BasicDBObject();
					whereQueryImage.append("_id",new ObjectId(id));
					GridFSDBFile imageForOutput = gfsPhoto.findOne(whereQueryImage);
					try {
					//	byte[] byteArray = IOUtils.toByteArray(imageForOutput.getInputStream());
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						imageForOutput.writeTo(baos);
						byte[] bytes = baos.toByteArray();
						image.setName(imageForOutput.getFilename());
						image.setContentType(imageForOutput.getContentType());
						image.setFileByte(bytes);
			//			String base64DataString = new String(bytes , "UTF-8");
						details.setDate(imageForOutput.getUploadDate());
						details.setImage(image);
						/*byte[] encoded=Base64.encodeBase64(bytes);
						String encodedString = new String(encoded);*/
					//	details.setPetname(encodedString);
					} catch (IOException e) {
						logger.info("inside load images for user ."+imageForOutput);
					}
					details.setTotalLikes(new UploadMultipleImagesDAO().loadTotalLikes(mongoClient, id, loginUserDetails.getEmail()));
					
				list.add(details);
				}
		}finally {
			cursor.close();
		}
			
	return list;
	}

	@Override
	public void updatePetDeails(PetDetails petDetails, MongoClient mongoClient) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Integer findUserPetCount(LoginUserDetails loginUserDetails, MongoDatabase mongoDatabase) {
		MongoCollection<Document> collection = mongoDatabase.getCollection("Dgree_PetDetails");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("user_id",loginUserDetails.getEmail());

		return ((Long)collection.count(whereQuery)).intValue();
	}
	@Override
	public List<PetDetails> loadPetDeailsWithOutImage(LoginUserDetails loginUserDetails, MongoClient mongoClient) {
		logger.info("**** Load PetDeails With OutImage ****");
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("Dgree_PetDetails");
		
		List<PetDetails> list=new ArrayList<>();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("user_id",loginUserDetails.getEmail());
					
				DBCursor cursor = collection.find(whereQuery);
		try{
				while (cursor.hasNext()) {
				
					PetDetails details=new PetDetails();
					
					DBObject dbObject = (DBObject) cursor.next();
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
		}finally {
			cursor.close();
		}
			
	return list;
	}
}
