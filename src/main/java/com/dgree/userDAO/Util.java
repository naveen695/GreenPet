package com.dgree.userDAO;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
	public static Logger logger= LogManager.getLogger();
	 
	 
	//  public static final String MAIL_REGISTRATION_SITE_LINK="http://localhost:8000/GreenPet/EmailVerification";
	/*  public static final String MAIL_USERNAME="AKIAJMIHCZXPGNU5WNGA";
	  public static final String MAIL_PASSWORD="Ah3UGADK0m3KAhMTsjrHNo129uw/hGuinabXO3f3qtUd";
*/	  public static final String Sign_up_link="EmailVerification";
	  public static final String LAT_LANG_SERVICE_URL = "https://maps.googleapis.com/maps/api/geocode/xml?&";
	  
	  private static final char[] symbols;
	 
      static {
	    StringBuilder tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	      tmp.append(ch);
	    for (char ch = 'a'; ch <= 'z'; ++ch)
	      tmp.append(ch);
	    symbols = tmp.toString().toCharArray();
	  }   

	  private static final Random random = new Random();

	  public static String prepareRandomString(int len) {
		char[] buf = new char[len];
	    for (int idx = 0; idx < buf.length; ++idx) 
	      buf[idx] = symbols[random.nextInt(symbols.length)];
	    return new String(buf);
	  }
}