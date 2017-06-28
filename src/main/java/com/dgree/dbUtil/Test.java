package com.dgree.dbUtil;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dgree.model.PetDetails;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.util.JSON;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class Test
{
  public Test() {}
  
  public static void main(String[] args) throws IOException
  {	
	
	  MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://treepet:treepet@ds131151.mlab.com:31151/dgree-treepet"));
		DB db = new DB(mongoClient, "dgree-treepet");
	  List<PetDetails> petDetails = new ArrayList<>();
	  PetDetails cartMap=new PetDetails();
	  cartMap.setAddress1("hi");
	  petDetails.add(cartMap);
	  
		
		 JSONObject responseDetailsJson = new JSONObject();
		    JSONArray jsonArray = new JSONArray();

		    for(PetDetails p : petDetails) {
		        JSONObject formDetailsJson = new JSONObject();
		        
		        Field[] fields = p.getAddress1().getClass().getFields();
		        
		        formDetailsJson.put("p",p.getAddress1());
		       jsonArray.add(formDetailsJson);
		    }
		    responseDetailsJson.put("perdetails", jsonArray);//Here you can see the data in json format

	  
 /*   MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://treepet:treepet@ds131151.mlab.com:31151/dgree-treepet"));
	DB db = new DB(mongoClient, "dgree-treepet");
	DBCollection collection = db.getCollection("Dgree_PetDetails");
	DBCursor find = collection.find();
	DBObject next = find.next();
	Object object = next.get("image");
	 ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(object);
	    byte[] byteArray = out.toByteArray();
	    InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bImageFromConvert = ImageIO.read(in);
		OutputStream out1 = null;

	GridFS fs=new GridFS(db);
	
	String newFileName = "IMG_20140122_132026.JPG";
	GridFS gfsPhoto = new GridFS(db);
	GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);
	try {
		long inputStream = imageForOutput.writeTo("d://image.jpg");
	} catch (IOException e) {
		e.printStackTrace();
	}

*/  
  }
}