package com.dgree.userDAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.bson.Document;
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

		basicDBObject.put("address1",petDetails.getAddress1());
		basicDBObject.put("address2", petDetails.getAddress2());
		basicDBObject.put("city", petDetails.getCity());
		basicDBObject.put("county", petDetails.getCounty());
		basicDBObject.put("country", petDetails.getCountry());
		basicDBObject.put("zip", petDetails.getZip());
		basicDBObject.put("image_name", petDetails.getImage().getName());
		//basicDBObject.put("content_type",  petDetails.getImage().getContentType());
		basicDBObject.put("latitude", petDetails.getLatitude());
		basicDBObject.put("longittude", petDetails.getLongittude());
		basicDBObject.put("petDesc", petDetails.getPetDesc());
		
		Date date=new Date();
	//	basicDBObject.put("insert_date", date);
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
		logger.info("**** loadPetDeails user loadPetDeails ****");
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection collection = db.getCollection("Dgree_PetDetails");
		
		List<PetDetails> list=new ArrayList<>();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("user_id",loginUserDetails.getEmail());
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("_id",-1);
				
				DBCursor cursor = collection.find(whereQuery).sort(basicDBObject);
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
				list.add(details);
				}

	return list;
	}

}
