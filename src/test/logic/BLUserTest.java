package test.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.financial.Usuarios;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import zen.logic.BLSecurity;
import zen.logic.BLUser;
import zen.persistence.DataBaseSession;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class BLUserTest extends TestCase  {
	
	@Mock  private DataBaseSession sessionDB;
	@InjectMocks private BLUser bLUser;
	

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
    public void registerUserSuccessFull() throws Exception
    {
		boolean expected = true;
		HashMap params = null;
		Usuarios user = new Usuarios("lbolanos", "GokuEsm1Heroe", "Luis Alberto", "Bolaños");
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", params, null )).thenReturn(new ArrayList());
		//Mockito.when(bLUserLog.validateLoginExists(user.getUserName())).thenReturn(new Boolean(true));
		
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());
		
		
		JSONObject json = bLUser.register(user);
		Assert.assertEquals(expected, json.getBoolean("success"));
    }
	
	
	@Test
    public void registerUserWithSecuryPassword() throws Exception
    {
		boolean expected = true;
		Usuarios user = new Usuarios("lbolanos", "S1sc0Coorp", "Luis Alberto", "Bolaños");
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", new HashMap(), null )).thenReturn(new ArrayList());
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());
		
		JSONObject json = bLUser.register(user);
		Assert.assertEquals(expected, json.getBoolean("success"));
    }
	
	
	@Test
    public void registerUserWithPasswordLower() throws Exception
    {
		boolean expected = false;
		Usuarios user = new Usuarios("lbolanos", "s1sc0coorp", "Luis Alberto", "Bolaños");
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", new HashMap(), null )).thenReturn(new ArrayList());
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());
		
		JSONObject json = bLUser.register(user);
		Assert.assertEquals(expected, json.getBoolean("success"));
    }
	
	@Test
    public void registerUserPasswordLWithoutNumbers() throws Exception
    {
		boolean expected = false;
		Usuarios user = new Usuarios("lbolanos", "SiScocoorp", "Luis Alberto", "Bolaños");
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", new HashMap(), null )).thenReturn(new ArrayList());
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());
		
		JSONObject json = bLUser.register(user);
		Assert.assertEquals(expected, json.getBoolean("success"));
    }

	@Test
    public void registerUserPasswordInvalidLength() throws Exception
    {
		boolean expected = false;
		Usuarios user = new Usuarios("lbolanos", "Aaa1234", "Luis Alberto", "Bolaños");
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", new HashMap(), null )).thenReturn(new ArrayList());
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());
		
		JSONObject json = bLUser.register(user);
		Assert.assertEquals(expected, json.getBoolean("success"));
    }
	
	
	@Test
    public void registerUserIncompleteNamesAndLastName() throws Exception
    {
		boolean expected = false;
		Usuarios user = new Usuarios("lbolanos", "Aaa123456+", null, null);
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", new HashMap(), null )).thenReturn(new ArrayList());
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());

		JSONObject json = bLUser.register(user);			
		Assert.assertEquals(expected, json.getBoolean("success"));
    }
	
	@Test
    public void registerUserIncompleteUserName() throws Exception
    {
		boolean expected = false;
		Usuarios user = new Usuarios(null, "Aaa123456+", "Jose", "Sanchez");
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", new HashMap(), null )).thenReturn(new ArrayList());
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());

		JSONObject json = bLUser.register(user);			
		Assert.assertEquals(expected, json.getBoolean("success"));
    }
	
	
	@Test
    public void registerUserAllDatasAreVoid() throws Exception
    {
		boolean expected = false;
		Usuarios user = new Usuarios("", "", "", "");
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", new HashMap(), null )).thenReturn(new ArrayList());
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());

		JSONObject json = bLUser.register(user);			
		Assert.assertEquals(expected, json.getBoolean("success"));
    }
	
	
	@Test
    public void registerUserLoginAlreadyExists() throws Exception
    {
		boolean expected = true;
		String userName = "user"+System.currentTimeMillis();
		Usuarios user = new Usuarios(userName, "GokuEsm1Heroe", "Luis Alberto", "Bolaños");
		
		Mockito.when(this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", new HashMap(), null )).thenReturn(new ArrayList());
		Mockito.when(sessionDB.save(user)).thenReturn(new Object());

		JSONObject json = bLUser.register(user);			
		Assert.assertEquals(expected, json.getBoolean("success"));
    }
	
	
	
	
	
}
