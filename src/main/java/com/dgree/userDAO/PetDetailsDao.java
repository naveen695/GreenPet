package com.dgree.userDAO;

import com.dgree.model.PetDetails;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface PetDetailsDao {

public void updatePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase);

public void deletePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase);


public PetDetails loadPetDeails( MongoDatabase mongoDatabase);

void insertPetDeails(PetDetails petDetails, MongoClient mongoClient);
}
