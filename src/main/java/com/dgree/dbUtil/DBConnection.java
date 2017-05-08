package com.dgree.dbUtil;

import com.mongodb.client.MongoDatabase;

public abstract interface DBConnection
{
  public abstract MongoDatabase getDBConnection();
}