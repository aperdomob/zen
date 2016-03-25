package logic;

import model.Usuario;
import persistence.JPA;
import persistence.Persistencia;

public class LGUsuarios {
	
	
	
	
	public void crear(){
		Persistencia persistence = null;
		Usuario user = null; 
		try{
			
			
			user =  new Usuario();
			user.setLastName("Bolaños");
			user.setNames("Luis");
			user.setPassword("12345678");
			user.setUserName("lbolanos");
			
			persistence = new  JPA();
			persistence.persist(user);
			
			
		}
		catch(Exception e){
			throw e;
		}
	}
	
}
