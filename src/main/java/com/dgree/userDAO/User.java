package com.dgree.userDAO;

import org.bson.Document;

import com.dgree.model.UserBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface User {

public boolean insertNewUser(MongoCollection<Document> collection,UserBean us);
public boolean validateNewUser(MongoDatabase mongoDatabase,UserBean us);
}
