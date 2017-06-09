package com.dgree.userDAO;

import java.util.logging.Logger;

import org.bson.Document;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class LoginUserDao implements User {
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());

	@Override
	public long insertNewUser(MongoCollection<Document> collection, UserBean us) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ValidateUser validateNewUser(MongoDatabase mongoDatabase, UserBean us) {
		ValidateUser vs=new ValidateUser();	
		logger.info("**** validating user validateUserEmail ****");
		MongoCollection<Document> collection = mongoDatabase.getCollection("dgree_UserInfo");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id",us.getEmail());
		whereQuery.append("password",us.getPassword());
				FindIterable<Document> cursor = collection.find(whereQuery);
				MongoCursor<Document> iterator = cursor.iterator();
				while(iterator.hasNext()){
					logger.info("**** mail/pwd is matched in our records ****");
					Document document = iterator.next();
					String status =(String) document.get("status");
				if (status.equals("active")) {
					vs.setLoginStauts("active");
					vs.setLoginValid(true);
					LoginUserDetails loginUserDetails=new com.dgree.model.LoginUserDetails();
					loginUserDetails.setEmail((String)document.get("email_id"));
					loginUserDetails.setUserFirstName((String)document.get("firstname"));
					loginUserDetails.setUserLastName((String)document.get("lastname"));
					loginUserDetails.setMobilenumber((String)document.get("mobile_number"));
					vs.setLoginUserDetails(loginUserDetails);
				}else{
					vs.setLoginStauts("not_active");
					vs.setLoginValid(true);
				}
				return vs;
				}
		logger.info("**** mail/pwd is not matched in our records with active status****");
		
		vs.setLoginStauts("not_active");
		vs.setLoginValid(false);
		return vs;
	}

	@Override
	public boolean validateUserEmail(MongoDatabase mongoDatabase, UserBean us) {
		return false;
	}

	@Override
	public LoginUserDetails updateUserDetails(MongoDatabase mongoDatabase, UserBean us) {
		// TODO Auto-generated method stub
		return null;
	}

}
