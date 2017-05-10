package com.dgree.service;

import com.dgree.model.UserBean;
import com.mongodb.client.MongoDatabase;

public interface SignUp {
public Boolean validateUser(MongoDatabase mongoDatabase,UserBean us);
public void sendMail(UserBean us);
}
