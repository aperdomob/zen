package zen.logic;

import model.financial.Usuarios;

import org.json.JSONObject;

public interface IBLUser {
	/*
	 * Registra un nuevo usuarios
	 * Params:
	 *  user : informacio del usuario a registrar
	 * Excepciones:
	 * 	1. Requiere los campos obligatorios
	 *  2. Verifica la seguridad de la clave
	 *  3. Verifica la existencia de login
	 */
	public JSONObject register(Usuarios user) throws Exception;
	
	/*
	 * Activa la cuenta de usuario
	 * Params: 
	 *  encryptId : id del usuario encriptado
	 *  Excepciones:
	 *  1. El id de usuario no existe
	 */
	public JSONObject activeAccount(String encryptId);
	
	
	/*
	 * Actualiza la informacion de la cuenta del usuario
	 * Params: 
	 *  user : informacio del usuario a registrar
	 *  Excepciones:
	 *  1. El id de usuario no existe
	 *  2. Longitud de campos no valida
	 *  3. Campos requeridos no especificados
	 */
	public JSONObject update(Usuarios user);
	
	
	/*
	 * Elimina la cuenta del usuario e informacion relacionada 
	 *  Params: 
	 *  user : informacio del usuario a registrar
	 *  Excepciones:
	 *  1. El id de usuario no existe
	 *  2. Error intentar borrar antes de eliminar todas las foraneas
	 */
	public JSONObject delete(Usuarios user);
	
	
	/*
	 * iniciar session  
	 *  Params: 
	 *  user : informacio del usuario a registrar
	 *  Excepciones:
	 *  1. El login y/o password no existen
	 */
	public JSONObject logIn(Usuarios user);
	
}
