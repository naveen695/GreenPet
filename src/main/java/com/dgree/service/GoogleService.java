package com.dgree.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.DOMOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
		
	//	findLatitudeLongitude.findLatitudeLongitude(petDetails);
		try { 
	        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/xml?&address=773+3rdCrossRdMarathahalliVillage+Bangalore+560037+india&key=AIzaSyA37598NOPeHttg0t75EozEBNF_JpT4vZ0"); 
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
	        connection.setDoOutput(true); 
	        connection.setInstanceFollowRedirects(false); 
	        connection.setRequestProperty("Content-Type", "application/xml"); 


	        BufferedReader br = new BufferedReader(new InputStreamReader(
				(connection.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			StringBuilder sb = new StringBuilder();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			System.out.println(sb.toString());
	        SAXBuilder builder = new SAXBuilder();
	        
	         org.jdom2.Document build = builder.build(new ByteArrayInputStream(sb.toString().getBytes()));
	         Document document = new DOMOutputter().output(build);
	         
	         NodeList nodes = document.getElementsByTagName("location");
	         // print the text content of each child
	         for (int i = 0; i < nodes.getLength(); i++) {
	        	 NodeList nodeList = nodes.item(i).getChildNodes();
	        	 for (int j = 0; j < nodeList.getLength(); j++) {
	                 Node childNode = nodeList.item(j);
	                 if ("lat".equals(childNode.getNodeName())) {
	                     System.out.println(nodeList.item(j).getTextContent()
	                             .trim());
	                 }else if("lng".equals(childNode.getNodeName())){
	                	 System.out.println(nodeList.item(j).getTextContent()
	                             .trim());
	                 }
	             }
	         }	        
	        connection.disconnect(); 
	    } catch(Exception e) { 
	        throw new RuntimeException(e); 
	    } 
	}

}
