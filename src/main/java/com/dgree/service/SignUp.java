package com.dgree.service;

import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.mongodb.client.MongoDatabase;

public interface SignUp {
public ValidateUser validateUser(MongoDatabase mongoDatabase,UserBean us);
public void sendMail(UserBean us,StringBuffer url);
public boolean validateUserMail(MongoDatabase mongoDatabase,UserBean us);
}
