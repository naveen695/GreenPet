package com.dgree.dbUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Test
{
  public Test() {}
  
  public static void main(String[] args)
  {
    List<Document> seedData = new ArrayList();
    
    seedData.add(new Document("decade", "1970s")
      .append("artist", "Debby Boone")
      .append("song", "You Light Up My Life")
      .append("weeksAtOne", Integer.valueOf(10)));
    

    seedData.add(new Document("decade", "1980s")
      .append("artist", "Olivia Newton-John")
      .append("song", "Physical")
      .append("weeksAtOne", Integer.valueOf(10)));
    

    seedData.add(new Document("decade", "1990s")
      .append("artist", "Mariah Carey")
      .append("song", "One Sweet Day")
      .append("weeksAtOne", Integer.valueOf(16)));
    

    DBConnectionImpl instance = DBConnectionImpl.getInstance();
    MongoDatabase dbConnection = instance.getDBConnection();
    
    
    MongoCollection<Document> songs = dbConnection.getCollection("songs");
	BasicDBObject whereQuery = new BasicDBObject();
	whereQuery.put("decade", "1970s");
	FindIterable<Document> cursor = songs.find();
	MongoCursor<Document> iterator = cursor.iterator();
	while(iterator.hasNext()) {
	    System.out.println(iterator.next());
	}
    songs.insertMany(seedData);
    System.out.println(dbConnection);
  }
}