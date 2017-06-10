package com.dgree.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import com.dgree.model.PetDetails;
import com.dgree.userDAO.Util;

public class GoesLocationLatLong {

	
	public PetDetails findLatitudeLongitude(PetDetails petDetails){
		
		String latLangServiceUrl = Util.LAT_LANG_SERVICE_URL;
		String address="address=";
		String Key=Util.GOOGLE_KEY;
		String finalurl2=null;
		
		String address1 = petDetails.getAddress1();
		String address2 = petDetails.getAddress2();
		String city = petDetails.getCity();
		String country = petDetails.getCountry();
		String zip = petDetails.getZip();
		String state = petDetails.getState();
		
		
		if (validteFields(latLangServiceUrl, Key)) {
			
			String finalUrl=latLangServiceUrl.concat(address);
			String finalurl1 = null ;
			if (StringUtils.isNotEmpty(address1)) 
				finalurl1 = finalUrl.concat(address1+"+");
			if(StringUtils.isNotEmpty(address2))
				finalurl1 = finalurl1.concat(address2+"+");
			if(StringUtils.isNotEmpty(city))
				finalurl1 = finalurl1.concat(city+"+");
			if(StringUtils.isNotEmpty(state))
				finalurl1 = finalurl1.concat(state+"+");
			if(StringUtils.isNotEmpty(zip))
				finalurl1 = finalurl1.concat(zip+"+");
			if(StringUtils.isNotEmpty(country))
				finalurl1 = finalurl1.concat(country+"+");
			
			finalurl2 = finalurl1.concat(Key);
		}		
		try { 
			  URL url = new URL(finalurl2.replace(" ", "")); 
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
	        connection.setDoOutput(true); 
	        connection.setInstanceFollowRedirects(false); 
	        connection.setRequestProperty("Content-Type", "application/xml"); 


			BufferedReader br = new BufferedReader(new InputStreamReader(
				(connection.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	        int responseCode = connection.getResponseCode();
	        connection.disconnect(); 
	    } catch(Exception e) { 
	        throw new RuntimeException(e); 
	    }
		return petDetails; 
	}

	private boolean validteFields(String latLangServiceUrl, String Key ) {
		return StringUtils.isNotBlank(latLangServiceUrl) && StringUtils.isNotBlank(Key);
	}
	
	
}
