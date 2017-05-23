package com.dgree.userDAO;

import org.bson.Document;

import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface User {

public long insertNewUser(MongoCollection<Document> collection,UserBean us);
public ValidateUser validateNewUser(MongoDatabase mongoDatabase,UserBean us);
public boolean validateUserEmail(MongoDatabase mongoDatabase,UserBean us);

}
