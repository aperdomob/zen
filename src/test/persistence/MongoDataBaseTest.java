package test.persistence;

import org.junit.Assert;
import org.junit.Test;

import persistence.SessionDataBaseMongo;
import resources.Enums.DataBase;

public class MongoDataBaseTest {
	
	private SessionDataBaseMongo sessionDataBaseMongo;
	
	@Test
	public void successConnectClientMongoDB() throws Exception {
		boolean expected = true;
		sessionDataBaseMongo = SessionDataBaseMongo.getInstance(DataBase.AdoptPet);
		Assert.assertSame(expected, sessionDataBaseMongo.isConnect());
		
	}
}
