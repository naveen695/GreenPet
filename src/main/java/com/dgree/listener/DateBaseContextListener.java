package com.dgree.listener;

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
	public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	ServletContext servletContext = servletContextEvent.getServletContext();
    	String environment =(String) servletContext.getInitParameter("environment");
    	String dbURL = (String)servletContext.getInitParameter("dbURL");
    	String  dbName = (String)servletContext.getInitParameter("dbName");
    	
    	MongoDatabase db = null;
        if (StringUtils.isNotEmpty(environment)&& StringUtils.isNotEmpty(dbURL)&& StringUtils.isNotEmpty(dbName)) {
            if (environment.trim().equals("dev")) {
                MongoClient mongoClient = null;
                try {
                  mongoClient = new MongoClient();
                  db = mongoClient.getDatabase("test");
                  logger.info("*** Getting Connection Local MongoDatabase ***" + db);
                } catch (Exception e) {
                  logger.info("Exception getDBConnection" + e);
                }
              } else if (environment.trim().equals("prod")){
                MongoClient mongoClient = null;
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
    	servletContext.setAttribute("MongoDatabase", db);
    	
    }
	//destroy will call before destroy application
    public void contextDestroyed(ServletContextEvent servletContextEvent)  {
    }
    
}
