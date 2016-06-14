package persistence;

import java.util.HashMap;
import java.util.Optional;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class SessionDataBaseMongo implements SessionDatabase {
	
	private String host;
	
	private Integer port;
	
	private String nameDatabase;
	
	private MongoDatabase database;
	
	private static HashMap<String, SessionDataBaseMongo> sessions = new HashMap<>();
	
	private final String HOST_LOCAL = "127.0.0.1";
	
	private final Integer PORT_DEFAULT = 27017;

	
	private SessionDataBaseMongo(String host, Integer port, String nameDatabase){
		this.host = host!=null ? host : HOST_LOCAL;
		this.port = port!=null ? port : PORT_DEFAULT;
		this.nameDatabase = nameDatabase;
		this.database = connectToDatabase();
		this.sessions.put(this.nameDatabase, this );
		
	}
	
	public static SessionDataBaseMongo getSession(String host, Integer port, String nameDatabase){
		SessionDataBaseMongo session = sessions.containsKey(nameDatabase) ? sessions.get(nameDatabase) 
		 : new SessionDataBaseMongo(host, port, nameDatabase);
		return session;
	}
	
	
	@SuppressWarnings("resource")
	private MongoDatabase connectToDatabase(){
		String uriDataBase = null;
		MongoClient instanceDataBase  = null;
		try{
			uriDataBase = String.format("mongodb://%s:%s", this.host, this.port);
			instanceDataBase = new MongoClient(new MongoClientURI(uriDataBase));
			return instanceDataBase.getDatabase(this.nameDatabase);
		}
		catch(Exception e){
			throw e;
		}
		
	}

	public MongoDatabase getDatabase() {
		return database;
	}
}
