package persistence;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import resources.Enums.Database;
import resources.Enums.URIServer;
import resources.Settings;

public class SessionDataBaseMongo{
	
	private static SessionDataBaseMongo instance = new SessionDataBaseMongo();
	private MongoClient mongoClient;
	private Settings setting;
	private boolean isConnect = false;
	private URIServer uriServer;
	private MongoDatabase currentDatabase;
	private Database currentEnumDatabase;
	
	private SessionDataBaseMongo(){
		setting = new Settings();
	}
	
	public static SessionDataBaseMongo getInstance(URIServer uriServer) throws Exception{
		try{
			instance = instance.isConnect ? instance : new SessionDataBaseMongo().connectClient(uriServer);
			return instance;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	private SessionDataBaseMongo connectClient(URIServer uriServer) throws Exception{
		String keyDatabase;
		String uriDataBase = null;
		try{
			this.uriServer = uriServer;
			keyDatabase = String.format("%s%s", setting.getKeyPropertyUri(), this.uriServer.ordinal() );
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
	
	private void setDatabase(Database databaseEnum){
		String databaseName;
		boolean sameDatabase;
		try{
			sameDatabase     = currentEnumDatabase == databaseEnum;
			databaseName     = sameDatabase ? null : String.format("%s%s", setting.getKeyPropertyDatabase(), databaseEnum.ordinal());
			this.currentDatabase  = sameDatabase ? this.currentDatabase : (MongoDatabase) this.mongoClient.getDatabase(databaseName);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public Object insert(Database databaseEnum, Object document){
		MongoDatabase database = null;
		MongoCollection collection;
		WriteResult result = null;
	
		try{
			database = getDatabase(databaseEnum);
			collection = (MongoCollection) database.getCollection(document.getClass().getSimpleName());
			collection.insertOne(document);
			return document;
		}
		catch(Exception e){
			document = null;
			throw e;
		}
	}

	public boolean isConnect() {
		return isConnect;
	}

	public MongoDatabase getDatabase(Database databaseEnum) {
		this.setDatabase(databaseEnum);
		return this.currentDatabase;
	}


}
