package com.dgree.service;

import java.util.Map;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface PetDetailsService {

	public void updatePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase);

	public void deletePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase);



	public void insertPetDeails(PetDetails petDetails, MongoClient MongoClient);

	public java.util.List<PetDetails> loadPetDeails(Map<String, Object> pagination,LoginUserDetails loginUserDetails, MongoClient mongoClient);
}
