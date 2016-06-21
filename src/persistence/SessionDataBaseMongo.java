package persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import resources.Enums.DataBase;
import resources.Settings;

public class SessionDataBaseMongo{
	
	private static SessionDataBaseMongo instance = new SessionDataBaseMongo();
	private MongoClient mongoClient;
	private Settings setting;
	private final String keyPropertyDatase = "URI_DATABASE_INDEX_";
	private boolean isConnect = false;
	private DataBase indexDatabase;
	
	private SessionDataBaseMongo(){
		setting = new Settings();
	}
	
	public static SessionDataBaseMongo getInstance(DataBase database) throws Exception{
		try{
			instance = instance.isConnect ? instance : new SessionDataBaseMongo().connectClient(database);
			return instance;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	private SessionDataBaseMongo connectClient(DataBase database) throws Exception{
		String keyDatabase;
		String uriDataBase = null;
		try{
			
			indexDatabase = database;
			keyDatabase = String.format("%s%s", keyPropertyDatase, indexDatabase.ordinal() );
			uriDataBase = setting.getSettings().getProperty(keyDatabase);
			mongoClient = new MongoClient(new MongoClientURI(uriDataBase));
			isConnect = mongoClient.getAddress() != null;
			
			return this;
		}
		catch(Exception e){
			isConnect = false;
			throw e;
		}
	}

	public boolean isConnect() {
		return isConnect;
	} 
}
