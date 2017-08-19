package com.dgree.userDAO;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface PetDetailsDao {

public void updatePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase);

public void deletePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase);



public java.util.List<PetDetails> loadPetDeails(LoginUserDetails loginUserDetails, MongoClient mongoClient);

void insertPetDeails(PetDetails petDetails, MongoClient mongoClient);

void updatePetDeails(PetDetails petDetails, MongoClient mongoClient);
}
