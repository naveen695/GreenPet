package com.dgree.dbUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDBUtil{
  private static  String environment = "dev";
  private static  String dbURI=null;
  private static  String dbName=null;
  
  public PropertiesDBUtil() {}
  
  static{
	  Properties prop = new Properties();
	  InputStream input = null;
		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("environment"));
			System.out.println(prop.getProperty("dbURI"));
			System.out.println(prop.getProperty("dbName"));


		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
  }
}