package com.dgree.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.dgree.userDAO.PetDetailsDao;
import com.dgree.userDAO.PetDetailsDaoImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class PetDetailsServiceImpl implements PetDetailsService {

	public static Logger logger= LogManager.getLogger();

	@Override
	public void insertPetDeails(PetDetails petDetails,MongoClient mongoClient) {
		PetDetailsDao petDetailsdao = new PetDetailsDaoImpl();
		petDetailsdao.insertPetDeails(petDetails,mongoClient);
		
	}

	@Override
	public void updatePetDeails(PetDetails petDetails, MongoDatabase mongoClient) {
		PetDetailsDao petDetailsdao = new PetDetailsDaoImpl();
		petDetailsdao.updatePetDeails(petDetails,mongoClient);
				
	}

	@Override
	public void deletePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		
	}

 
	@Override
	public List<PetDetails> loadPetDeails(LoginUserDetails loginUserDetails, MongoClient mongoClient) {
		PetDetailsDao detailsDao=new PetDetailsDaoImpl();
		return detailsDao.loadPetDeails(loginUserDetails, mongoClient);
	}


}
