package com.dgree.service;

import java.util.logging.Logger;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.PetDetails;
import com.dgree.userDAO.PetDetailsDao;
import com.dgree.userDAO.PetDetailsDaoImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class PetDetailsServiceImpl implements PetDetailsService {

	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());

	@Override
	public void insertPetDeails(PetDetails petDetails,MongoClient mongoClient) {
		PetDetailsDao petDetailsdao = new PetDetailsDaoImpl();
		petDetailsdao.insertPetDeails(petDetails,mongoClient);
		
	}

	@Override
	public void updatePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePetDeails(PetDetails petDetails, MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PetDetails loadPetDeails( MongoDatabase mongoDatabase) {
		// TODO Auto-generated method stub
		return null;
	}


}
