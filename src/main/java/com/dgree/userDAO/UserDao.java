package com.dgree.userDAO;

import java.util.logging.Logger;

import org.bson.Document;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class UserDao implements User{
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());

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
		collection.insertOne(document);
		return count;
	}

}
