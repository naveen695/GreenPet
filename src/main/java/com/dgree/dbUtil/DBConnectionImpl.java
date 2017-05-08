

package com.dgree.dbUtil;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Logger;






public class DBConnectionImpl
  implements DBConnection
{
  private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());
  private static DBConnectionImpl dbConnectionImpl;
  
  static
  {
    try {
      if (dbConnectionImpl == null)
        dbConnectionImpl = new DBConnectionImpl();
    } catch (Exception e) {
      logger.info("*** Exception occured Creating SingleTon Object ***");
    }
  }
  
  private DBConnectionImpl() { logger.info("*** Creating SingleTon Object for :::: DBConnectionImpl *** "); }
  
  public static DBConnectionImpl getInstance()
  {
    return dbConnectionImpl;
  }
  
  public MongoDatabase getDBConnection() {
    MongoDatabase db = null;
    
    if ("dev".equals("dev")) {
      MongoClient mongoClient = null;
      try {
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("test");
        logger.info("*** Getting Connection MongoDatabase ***" + db);
      } catch (Exception e) {
        logger.info("Exception getDBConnection" + e);
      }
    } else if ("dev".equals("prod"))
    {
      MongoClient mongoClient = null;
      try {
        String dbURI = "mongodb://treepet:treepet@ds131151.mlab.com:31151/dgree-treepet";
        mongoClient = new MongoClient(new MongoClientURI(dbURI));
        db = mongoClient.getDatabase("dgree-treepet");
        logger.info("*** Getting Connection MongoDatabase ***" + db);
      } catch (Exception e) {
        logger.info("Exception getDBConnection" + e);
      }
    }
    
    return db;
  }
}

