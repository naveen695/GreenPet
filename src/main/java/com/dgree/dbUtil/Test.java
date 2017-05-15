package com.dgree.dbUtil;

import com.dgree.userDAO.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

public class Test
{
  public Test() {}
  
  public static void main(String[] args)
  {
	  Util util=new Util();
	  Map<String, String> properties = util.getProperties();
	  System.out.println(properties);
  }
}