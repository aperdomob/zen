package zen.logic;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import model.financial.Usuarios;

import org.json.JSONObject;

import zen.logic.BLSecurity.ValidatesSecurity;
import zen.persistence.DataBaseSession;
import zen.persistence.IDataBasesSession;

public class BLUser implements IBLUser {

	private IDataBasesSession sessionDB;
	
	
	
	public BLUser(){
		this.sessionDB = new DataBaseSession();
	}
	
	private boolean validateDataRequired(Usuarios user){
		boolean success = true;
		try{
			success = user.getUserName() != null && !user.getUserName().trim().equals("");
			success = success ? user.getPassword() != null && !user.getPassword().trim().equals("") : success;
			success = success ? user.getNames() != null && !user.getNames().trim().equals("") : success;
			success = success ? user.getLastName() != null && !user.getLastName().trim().equals("") : success;
			return success;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	private boolean validatePassword(String password){
		
		String expRegPassword = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$";
		try{
			return Pattern.compile(expRegPassword).matcher(password).find();
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public boolean validateLoginExists(String userName) throws Exception{
		
		HashMap params = null;
		List listUsers = null;
		boolean exists = false;
		try{
			params = new HashMap();
			params.put("userName", userName);
			listUsers = this.sessionDB.executeHqlQuery("Usuarios", "getAllUser", params, null );
			
			exists = listUsers!= null && listUsers.size() > 0; 
			return exists;
			
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	@Override
	public JSONObject register(Usuarios user) throws Exception {
		JSONObject json = null;
		try{
			
			if( !validateDataRequired( user ) ){
				throw new Exception("Incomplete User Data");
			}
			
			if( !validatePassword( user.getPassword() ) ){
				throw new Exception("Insecure password");
			}
			
			
			if(validateLoginExists(user.getUserName())){
				throw new Exception("login exists");
			}
			
			
			this.sessionDB.save(user);
			
			json = new JSONObject();
			json.put("success", true);
		}
		catch(Exception e ){
			json = new JSONObject();
			json.put("success", false);
			json.put("message", e.getMessage());
		}
		
		return json;
	}
	
	

	@Override
	public JSONObject activeAccount(String encryptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject update(Usuarios user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject delete(Usuarios user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject logIn(Usuarios user) {
		// TODO Auto-generated method stub
		return null;
	}

}
