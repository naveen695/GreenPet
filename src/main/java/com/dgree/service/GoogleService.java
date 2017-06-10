package com.dgree.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.dgree.model.PetDetails;

public class GoogleService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GoesLocationLatLong findLatitudeLongitude =new GoesLocationLatLong();
		PetDetails petDetails = new PetDetails();
		petDetails.setAddress1("773");
		petDetails.setAddress2("3rd Cross Rd Marathahalli Village");
		petDetails.setCity("bangalore");
		petDetails.setState("KA");
		
		findLatitudeLongitude.findLatitudeLongitude(petDetails);
		try { 
	        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?&address=1600%20Amphitheatre%20Parkway%2C%20Mountain%20View%2C%20CA"); 
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
	}

}
