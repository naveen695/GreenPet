package com.dgree.service;

import java.util.HashMap;
import java.util.Map;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.mongodb.client.MongoDatabase;

public interface UserDetails {

public ValidateUser validateUser(MongoDatabase mongoDatabase,UserBean us);
public void sendMail(UserBean us,StringBuffer url);
public boolean validateUserMail(MongoDatabase mongoDatabase,UserBean us);
public LoginUserDetails updateUserDetails(MongoDatabase mongoDatabase,UserBean us);

}
