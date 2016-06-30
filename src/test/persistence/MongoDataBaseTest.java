package test.persistence;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoDatabase;

import model.Pet;
import persistence.SessionDataBaseMongo;
import resources.Enums.Database;
import resources.Enums.URIServer;

public class MongoDataBaseTest {
	
	private SessionDataBaseMongo sessionDataBaseMongo;
	
	@Before
	public void init() throws Exception{
		sessionDataBaseMongo = SessionDataBaseMongo.getInstance(URIServer.Local);
	}
	
	@Test
	public void successConnectClientMongoDB() throws Exception {
		boolean isConnect = sessionDataBaseMongo.isConnect();
		Assert.assertTrue(isConnect);
	}
	
	@Test
	public void successGetDatabaseTest(){
		MongoDatabase database = sessionDataBaseMongo.getDatabase(Database.AdopAtPet);
		Assert.assertNotNull(database);
	}
	
	@Test
	public void successInsertPet() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Pet pet = new Pet();
		pet.setName("Lassie");
		pet.setActualHeightCm(8.5f);
		pet.setActualWeightCm(3.2f);
		pet.setActualWidthCm(5.1f);
		pet.setBirthDate(dateFormat.parse("11/07/2010") );
		pet.setColors(new int[]{0x100, 0x1234, 0xDEAF});
		pet.setQuadruped(true);
		
		Assert.assertNotNull(sessionDataBaseMongo.insert(Database.AdopAtPet, pet));
	
	}
	
}
