package com.dgree.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.dgree.userDAO.LoginUserDao;
import com.dgree.userDAO.User;
import com.mongodb.client.MongoDatabase;

public class UserLogin implements UserDetails {
	public static Logger logger= LogManager.getLogger();
	
	@Override
	public ValidateUser validateUser(MongoDatabase mongoDatabase, UserBean us) {
		logger.info("**** validating login UserMail/pwd ****");
		User user=new LoginUserDao();
		return user.validateNewUser(mongoDatabase, us);
	}

	@Override
	public void sendMail(UserBean us, StringBuffer url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateUserMail(MongoDatabase mongoDatabase, UserBean us) {
		return  false;
	}

	@Override
	public LoginUserDetails  updateUserDetails(MongoDatabase mongoDatabase, UserBean us) {
		
		return null;
	}

}
