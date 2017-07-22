package com.dgree.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.DOMOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.PetDetails;
import com.dgree.userDAO.Util;
import com.mongodb.diagnostics.logging.Logger;

public class GoesLocationLatLong {

	private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DBConnectionImpl.class.getName());
    
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
				finalurl1 = finalurl1.concat(country);
			if(StringUtils.isNotBlank(finalurl1))
			finalurl2 = finalurl1.concat(Key);
			logger.info("::::"+finalurl1);
		}		
		try { 
			logger.info(finalurl2+"::::");
			URL url = new URL(finalurl2.replace(" ", ",")); 
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
	        connection.setDoOutput(true); 
	        connection.setInstanceFollowRedirects(false); 
	        connection.setRequestProperty("Content-Type", "application/xml"); 
			BufferedReader br = new BufferedReader(new InputStreamReader(
			(connection.getInputStream())));
			  int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
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
		         for (int i = 0; i < 1; i++) {
		        	 if (nodes.item(i) !=null) {
			        	 NodeList nodeList = nodes.item(i).getChildNodes();
			        	 for (int j = 0; j < nodeList.getLength(); j++) {
			                 Node childNode = nodeList.item(j);
			                 if ("lat".equals(childNode.getNodeName())) 
			                	 petDetails.setLatitude(nodeList.item(j).getTextContent().trim());
			                 if("lng".equals(childNode.getNodeName()))
			                	 petDetails.setLongittude(nodeList.item(j).getTextContent().trim());
			             }
					}	      
		        }	        
		        connection.disconnect(); 	
			}
	    } catch(Exception e) { 
	        throw new RuntimeException(e); 
	    }
		return petDetails; 
	}

	private boolean validteFields(String latLangServiceUrl, String Key ) {
		return StringUtils.isNotBlank(latLangServiceUrl) && StringUtils.isNotBlank(Key);
	}
	
	
}
