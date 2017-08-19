package com.dgree.userDAO;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

public class UserDao implements User{
	public static Logger logger= LogManager.getLogger();

	@Override
	public ValidateUser validateNewUser(MongoDatabase mongoDatabase,UserBean us) {
		logger.info("**** validating user new / old ****");
		ValidateUser validateUser=new ValidateUser();
		
		MongoCollection<Document> collection = mongoDatabase.getCollection("dgree_UserInfo");
				BasicDBObject whereQuery = new BasicDBObject();
				whereQuery.put("_id",us.getEmail());
				FindIterable<Document> cursor = collection.find(whereQuery);
				MongoCursor<Document> iterator = cursor.iterator();
				while(iterator.hasNext()){

					validateUser.setNewUser(false);
					validateUser.setUserid(0);
					return validateUser;
				}
			long userid = insertNewUser(collection,us);
			
			validateUser.setNewUser(true);
			validateUser.setUserid(userid);
		return validateUser;
	}

	@Override
	public long insertNewUser(MongoCollection<Document> collection,UserBean us) {

		logger.info("**** insert new user records to dgree_UserInfo table ****");
		long count = collection.count();
		Document document=new Document();
		document.append("_id",us.getEmail());
		document.append("user_id", ++count);
		document.append("email_id",us.getEmail());
		document.append("firstname",us.getUserFirstName());
		document.append("lastname",us.getUserLastName());
		document.append("mobile_number",us.getMobilenumber());
		document.append("password",us.getPassword());
		document.append("password",us.getPassword());
		document.append("status", "not_active");
		collection.insertOne(document);
		return count;
	}

	@Override
	public boolean validateUserEmail(MongoDatabase mongoDatabase, UserBean us) {
		
		MongoCollection<Document> collection = mongoDatabase.getCollection("dgree_UserInfo");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("user_id",us.getUserid());
		FindIterable<Document> cursor = collection.find(whereQuery);
		MongoCursor<Document> iterator = cursor.iterator();
		while(iterator.hasNext()){
			Document document = iterator.next();
			String status =(String) document.get("status");
			String _id =(String) document.get("_id");
			if("not_active".equalsIgnoreCase(status)){
				logger.info("**** updating email status active");
				HashMap<String ,Object> map=new  HashMap<>();
				map.put("_id", _id);
				map.put("hash",us.getHash());
				Bson filter = new Document("_id",_id);
				Bson newValue = new Document("status", "active");
				Bson updateOperationDocument = new Document("$set", newValue);
				UpdateResult updateOne = collection.updateOne(filter, updateOperationDocument);
				return true;
			}
			}
		logger.info("**** already updated email status active");
		return false;
	}

	@Override
	public LoginUserDetails updateUserDetails(MongoDatabase mongoDatabase, UserBean us) {
		//update
		LoginUserDetails loginUserDetails=new com.dgree.model.LoginUserDetails();
		MongoCollection<Document> collection = mongoDatabase.getCollection("dgree_UserInfo");
		BasicDBObject bd=new BasicDBObject("firstname",us.getUserFirstName());
		bd.append("lastname",us.getUserLastName());
		bd.append("mobile_number",us.getMobilenumber());
		Bson updateOperationDocument = new Document("$set", bd);
		Bson filter = new Document("_id",us.getEmail());
		collection.updateOne(filter, updateOperationDocument);
		// Retrieve
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id",us.getEmail());
		FindIterable<Document> cursor = collection.find(whereQuery);
		MongoCursor<Document> iterator = cursor.iterator();
		while(iterator.hasNext()){
			Document document = iterator.next();
			loginUserDetails.setEmail((String)document.get("email_id"));
			loginUserDetails.setUserFirstName((String)document.get("firstname"));
			loginUserDetails.setUserLastName((String)document.get("lastname"));
			loginUserDetails.setMobilenumber((String)document.get("mobile_number"));
		}
		return loginUserDetails;
		}
}
		
		
		
		
		