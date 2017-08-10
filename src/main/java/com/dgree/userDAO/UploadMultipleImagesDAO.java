package com.dgree.userDAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import com.dgree.model.Image;
import com.dgree.model.PetDetails;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class UploadMultipleImagesDAO {
	public static Logger logger= LogManager.getLogger();
	
	public void uploadMultImages(PetDetails petDetails, MongoClient mongoClient){
		
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection dgreeImageMapCollection = db.getCollection("dgree_ImageMap");
			
		GridFS fs=new GridFS(db,"images");
		GridFSInputFile createFile = fs.createFile(petDetails.getImage().getFileByte());
		createFile.setFilename(petDetails.getImage().getName());
		createFile.setContentType(petDetails.getImage().getContentType());
		createFile.save();
		String id = (String)createFile.getId().toString();
		
		
		BasicDBObject dgreeImageMapObject=new BasicDBObject();
		dgreeImageMapObject.put("petId", petDetails.getId());
		dgreeImageMapObject.put("imageId", id);
		dgreeImageMapObject.put("homePage", 0);
		dgreeImageMapCollection.insert(dgreeImageMapObject);
		
	}
	
	public List<String>   loadMultipleImage (String id, MongoClient mongoClient){
		List<Image> imageList = new ArrayList<>();
		
		DB db = new DB(mongoClient, "dgree-treepet");
		DBCollection dgreeImageMapCollection = db.getCollection("dgree_ImageMap");

		BasicDBObject imageMap = new BasicDBObject();
		imageMap.append("petId",id);
		List<String> listImageIds =new ArrayList<>();
		DBCursor dbCursor = null;
		try{
			dbCursor = dgreeImageMapCollection.find(imageMap);
			Iterator<DBObject> find = dbCursor.iterator();
			while (find.hasNext()) {
				DBObject next = find.next();
				listImageIds.add((String)next.get("imageId"));
			}
		}finally{
			if (dbCursor!= null) {
				dbCursor.close();
			}
		}
		/*
		for (String imageId : listImageIds) {
			
			GridFS gfsPhoto = new GridFS(db, "images");
			BasicDBObject whereQueryImage = new BasicDBObject();
			whereQueryImage.append("_id",new ObjectId(imageId));
			GridFSDBFile imageForOutput = gfsPhoto.findOne(whereQueryImage);
			Image image=new Image();
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				imageForOutput.writeTo(baos);
				byte[] bytes = baos.toByteArray();
				baos.flush();
				baos.close();
				image.setName(imageForOutput.getFilename());
				image.setContentType(imageForOutput.getContentType());
				image.setFileByte(bytes);
				imageList.add(image);
			} catch (Exception e) {
				logger.info("inside load images for user in multi imageLodeDAO .",e);
			}			
		}
		*/
		return listImageIds;
		
	}
}
