package test.persistence;

import org.junit.Assert;
import org.junit.Test;

import persistence.SessionDataBaseMongo;
import persistence.SessionDatabase;

public class MongoDataBaseTest {
	
	private SessionDatabase sesionMongoDB;
	
	@Test
	public void getSession() throws Exception {
		SessionDataBaseMongo mongodb = SessionDataBaseMongo.getSession(null, null, "test");
		Assert.assertNotNull(mongodb.getDatabase());
		
	}
}
