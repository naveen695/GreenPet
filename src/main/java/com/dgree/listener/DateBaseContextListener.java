package com.dgree.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;

import com.dgree.dbUtil.DBConnectionImpl;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
public class DateBaseContextListener implements ServletContextListener {
	
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());
	
	/*default constractor */
	public DateBaseContextListener() {
    }
	//after deploy application this method will execute
	@SuppressWarnings("resource")
	public void contextInitialized(ServletContextEvent servletContextEvent)  { 
	
		
    	ServletContext servletContext = servletContextEvent.getServletContext();
    	
	/*	InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("conf.properties");
		System.out.println(resourceAsStream);*/
    				Map<String, String> prop=new HashMap<>();
    				
    					File file = new File(System.getProperty("catalina.base"),"conf/conf.properties");
						try {
							FileInputStream fileInputStream = new FileInputStream(file);
							Properties properties= new Properties();
							properties.load(fileInputStream);
							fileInputStream.close();
							Enumeration enuKeys = properties.keys();
							while (enuKeys.hasMoreElements()) {
								String key = (String) enuKeys.nextElement();
								String value = properties.getProperty(key);
								prop.put(key, value);
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						} 

    	String environment =(String) servletContext.getInitParameter("environment");
    	String dbURL = (String)servletContext.getInitParameter("dbURL");
    	String  dbName = (String)servletContext.getInitParameter("dbName");
    	MongoClient mongoClient = null;
    	MongoDatabase db = null;
        if (StringUtils.isNotEmpty(environment)&& StringUtils.isNotEmpty(dbURL)&& StringUtils.isNotEmpty(dbName)) {
            if (environment.trim().equals("dev")) {
                try {
                  mongoClient = new MongoClient();
                  db = mongoClient.getDatabase("test");
                  logger.info("*** Getting Connection Local MongoDatabase ***" + db);
                } catch (Exception e) {
                  logger.info("Exception getDBConnection" + e);
                }
              } else if (environment.trim().equals("prod")){
                
                try {
                  mongoClient = new MongoClient(new MongoClientURI(dbURL.trim()));
                  db = mongoClient.getDatabase(dbName.trim());
                  
                  logger.info("*** Getting Connection from remote MongoDatabase ***" + db);
                } catch (Exception e) {
                  logger.info("Exception getDBConnection" + e);
                }
              }
		}else{
			logger.info("*** DB connection values are null . please check in web.xml. ***");
		}
        servletContext.setAttribute("mongoClient", mongoClient);
        servletContext.setAttribute("prop", prop);
    	servletContext.setAttribute("MongoDatabase", db);
    	}
	//destroy will call before destroy application
    public void contextDestroyed(ServletContextEvent servletContextEvent)  {
    }
    
}
