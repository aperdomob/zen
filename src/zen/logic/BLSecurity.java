package zen.logic;

import model.financial.Usuarios;

public class BLSecurity {
	
	public enum ValidatesSecurity { LogOut, LogIn};
	
	public enum OperactionSecurity { ActiveAccount, LogIn};
	
	public boolean vaidateOperacion(Usuarios userSession, Integer operaction){
		boolean success = false;
		
		if(operaction == OperactionSecurity.ActiveAccount.ordinal()){
			success = true;
		}
		
		if(operaction == OperactionSecurity.LogIn.ordinal()){
			success = vaidateUser(userSession, ValidatesSecurity.LogIn);
		}
		
		return success;
	}
	
	
	public boolean vaidateUser(Usuarios userSession, ValidatesSecurity action){
		boolean success = false;
		
		if(action.equals(ValidatesSecurity.LogOut)){
			success = true;
		}
		
		if(action.equals(ValidatesSecurity.LogIn)){
			success = userSession != null && userSession.getUserId() != null;
		}
		
		return success;
	}
	
}
