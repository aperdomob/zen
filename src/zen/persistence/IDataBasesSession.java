package zen.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;



public interface IDataBasesSession {
	
	
	/*
	 * Almacena un registro de la entidad recibida como parametro
	 * params :  
	 * 		entity : Objecto mapeado del modelo de datos
	 * return :
	 *  Retorna un Object que contiene la llave primaria para los tipos auto numericos o identity. 
	*/
	public Object save(Serializable entity) throws Exception;
	
	
	/*
	 * Actualiza un registro de la entidad recibida con la informacion de dicho objecto
	 * params :  
	 * 		entity : Objecto mapeado del modelo de datos
	 * Retorna un Object que contiene la llave primaria para los tipos auto numericos o identity.
	*/
	public Object merge(Serializable entity) throws Exception;
	
	
	/*
	 * Actualiza un registro de la entidad recibida con la informacion pasada por los parametros 
	 * params :  
	 * 		entity : Objecto mapeado del modelo de datos
	 * 		entidad : Objecto class del objecto que se va a actualizar
	 * 		id      : id o objecto que representa la llave primaria
	 * 		parameters : parametros que contien los campos que se van a actualizar
	 * Retorna un Object que contiene la llave primaria para los tipos auto numericos o identity.
	*/
	public Object merge(Class entidad, Serializable id, HashMap<Integer, Object> parameters) throws Exception;
	
	
	/*
	 * Almacena o actualiza un registro de la entidad recibida con la informacion de dicho objecto
	 * params :  
	 * 		entity : Objecto mapeado del modelo de datos
	 * Retorna un Object que contiene la llave primaria para los tipos auto numericos o identity.
	*/
	public void saveOrUpdate(Serializable entity) throws Exception;
	
	
	/*
	 * Elimina un registro que coincida con el objecto (set atributes key primary)
	 * params :  
	 * 		entity : Objecto mapeado del modelo de datos
	*/
	public void delete(Serializable entity) throws Exception;
	
	/*
	 * Consulta un registro por su llave primaria
	 * params :  
	 * 		entity : Objecto mapeado del modelo de datos con la informacion de la llave primaria
	 * Retorna el objecto que representa el registro de la base de datos.
	*/
	public Object findById(Class entidad, Serializable id) throws Exception;
	
	/*
	 * Consulta un listado de registros que coincidan con los parametros especificados
	 * params :
	 * entity : Objecto Class de la Entidad a consultar
	 * parameters :  Hashmap que almacena los parametros a buscar asociados a la llave que contribulle armar el where
	 * Retorna un listado de los registros que coinciden con los parametros.
	 */
	//public List findByProperty(Class entity, List<ParamQuery> parameters) throws Exception ;
	
	/*
	 * Ejecuta consulta en leguaje HQL (Hibernate Query Language)
	 * params:
	 * 	nameQuery : nombre del query name que almacena a un archivo xml con diferentes querys
	 *  parameters :  Hashmap que almacena los parametros a buscar asociados a la llave que contribulle armar el where
	 *  Retorna un listado de los registros que coinciden con los parametros.
	 */
	public List executeNameQuery(String nameQuery, HashMap parameters) throws Exception;
	
	/*
	 * Ejecuta consulta en leguaje HQL (Hibernate Query Language)
	 * params:
	 * 	nameQuery : nombre del query name que almacena a un archivo xml con diferentes querys
	 *  parameters :  Hashmap que almacena los parametros a buscar asociados a la llave que contribulle armar el where
	 *  top : indica el numero de registro top de la consulta
	 *  rows : indica el numero de registros a paginar en la consulta
	 *  Retorna un listado de los registros que coinciden con los parametros.
	 */
	public List executeNameQuery(String nameQuery, HashMap parameters, Integer top, Integer rows) throws Exception;
	
	/*
	 * Ejecuta consulta en leguaje SQL (SQL Nativo para cada base de datos)
	 * params:
	 * 	entity : El nombre del la categoria del query (nivel superior)
	 *  queryName :  El nombre del query 
	 *  paramsWhere : parametros del where
	 *  paramsHaving : parametros del having
	 *  Retorna un listado de los registros que coinciden con los parametros.
	 */
	public List executeNativeQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving) throws Exception;
	
	
	/*
	 * Ejecuta consulta en leguaje SQL (SQL Nativo para cada base de datos)
	 * params:
	 * 	entity : El nombre del la categoria del query (nivel superior)
	 *  queryName :  El nombre del query 
	 *  paramsWhere : parametros del where
	 *  paramsHaving : parametros del having
	 *  Retorna un listado de los registros que coinciden con los parametros.
	 *  top : indica el numero de registro top de la consulta
	 *  rows : indica el numero de registros a paginar en la consulta
	 */
	public List executeNativeQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving, Integer top, Integer rows) throws Exception;
	
	
	/*
	 * Ejecuta consulta de Update, delete, insert en leguaje SQL (SQL Nativo para cada base de datos)
	 * params:
	 * 	nameQuery : nombre del query name que almacena a un archivo xml con diferentes querys
	 *  parameters :  Hashmap que almacena los parametros a buscar asociados a la llave que contribulle armar el where
	 *  Retorna un listado de los registros que coinciden con los parametros.
	 */
	public int executeNativeQueryUpdate(String entityName, String queryName,  HashMap paramsWhere) throws Exception;
	
	
	/*
	 * Ejecuta consulta en leguaje SQL (SQL Nativo)
	 * params:
	 * 	nameQuery : nombre del query name que almacena a un archivo xml con diferentes querys
	 *  parameters :  Hashmap que almacena los parametros a buscar asociados a la llave que contribulle armar el where

	 *  Retorna un listado de los registros que coinciden con los parametros.
	 */
   //public List executeNativeQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving) throws Exception;
	
	/*
	 * Ejecuta consulta en leguaje HQL (Hibernate Query Language)
	 * params:
	 * 	nameQuery : nombre del query name que almacena a un archivo xml con diferentes querys
	 *  parameters :  Hashmap que almacena los parametros a buscar asociados a la llave que contribulle armar el where
	 *  top : indica el numero de registro top de la consulta
	 *  rows : indica el numero de registros a paginar en la consulta
	 *  Retorna un listado de los registros que coinciden con los parametros.
	 */
	//public List executeSqlQuery(String nameQuery, HashMap parameters, Integer top, Integer rows) throws Exception;
	
	/*
	 * Ejecuta consulta en leguaje SQL (SQL Nativo para cada base de datos)
	 * params:
	 * 	entity : El nombre del la categoria del query (nivel superior)
	 *  queryName :  El nombre del query 
	 *  paramsWhere : parametros del where
	 *  paramsHaving : parametros del having
	 *  Retorna un listado de los registros que coinciden con los parametros.
	 */
	public List executeHqlQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving) throws Exception;
	
	
	/*
	 * Ejecuta consulta en leguaje SQL (SQL Nativo para cada base de datos)
	 * params:
	 * 	entity : El nombre del la categoria del query (nivel superior)
	 *  queryName :  El nombre del query 
	 *  paramsWhere : parametros del where
	 *  paramsHaving : parametros del having
	 *  Retorna un listado de los registros que coinciden con los parametros.
	 *  top : indica el numero de registro top de la consulta
	 *  rows : indica el numero de registros a paginar en la consulta
	 */
	public List executeHqlQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving, Integer top, Integer rows) throws Exception;
	
	
	
	/*
	 * Indica que debe mantener la sesion abierta con la base de datos mientras 
	 * se ejecutan varias secuencias (save, update, delete)
	*/
	public void keepSession() throws Exception;
	
	/*
	 * Cierra una sesion que fue dejada abierta para ejecutar varias acciones
	 * Excepcion: si ocurre una excepcion se realiza rollback de todas las operaciones 
	*/
	public void closeSession() throws Exception;
	
	
	
	/*
	 *  Ejecuta procedimientos almacenados llamandolo por el nombre y los parametros
	 *  Params:
	 *  queryString : llamado del procedimiento (exec ProcedimientoName @params = :params1)
	 *  parameters  : Hashmap que almacena los valores de los parametros del procedimiento
	 */
	public List executeNameProcedure(String nameProcedure, HashMap parameters, Integer top, Integer rows) throws Exception;
	
}
