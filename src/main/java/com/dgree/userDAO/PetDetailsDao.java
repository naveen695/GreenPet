package com.dgree.userDAO;

import java.util.List;
import java.util.Map;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface PetDetailsDao {

public void updatePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase);

public void deletePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase);

public void insertPetDeails(PetDetails petDetails, MongoClient mongoClient);

public void updatePetDeails(PetDetails petDetails, MongoClient mongoClient);

public List<PetDetails> loadPetDeails(Map<String, Object> parameters, LoginUserDetails loginUserDetails,
		MongoClient mongoClient);


public Integer findUserPetCount(LoginUserDetails loginUserDetails, MongoDatabase mongoDatabase);


List<PetDetails> loadPetDeailsWithOutImage(LoginUserDetails loginUserDetails, MongoClient mongoClient);
}
