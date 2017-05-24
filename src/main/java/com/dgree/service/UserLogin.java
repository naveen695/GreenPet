package com.dgree.service;

import java.util.logging.Logger;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.dgree.userDAO.LoginUserDao;
import com.dgree.userDAO.User;
import com.dgree.userDAO.UserDao;
import com.mongodb.client.MongoDatabase;

public class UserLogin implements UserDetails {
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());
	
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

}
