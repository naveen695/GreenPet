package com.dgree.userDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class Util {
	  public static final String MAIL_SMTP_ENABLE= "true";
	  public static final String MAIL_SMTP_ATHU="true";
	  public static final String MAIL_SMTP_HOST = "smtp.gmail.com";
	  public static final String MAIL_SMTP_PORT="587";
	  public static final String MAIL_REGISTRATION_SITE_LINK="http://localhost:8000/GreenPet/EmailVerification";
	  public static final String MAIL_USERNAME="knnaveen695@gmail.com";
	  public static final String MAIL_PASSWORD="9494931508";
	  public static final String Sign_up_link="EmailVerification";
	  public static final String LAT_LANG_SERVICE_URL = "https://maps.googleapis.com/maps/api/geocode/json?&";
	  public static final String GOOGLE_KEY="&key=AIzaSyA37598NOPeHttg0t75EozEBNF_JpT4vZ0";
	  
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