package zen.persistence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.financial.NameQryUsers.FieldsUser;
import model.financial.Usuarios;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import zen.utilities.UtilitiesApp;

public class QueryManager {
	
	
	
	private static final String OR  = "or";
	
	
	
	private Integer idField;
	
	private Object value;
	
	public QueryManager(){
		
	}
	
	public QueryManager(Integer idField, Object value){
		this.idField = idField;
		this.value = value;
	}
	
	public static String getQuery(String entityName, String queryName) throws Exception{
		return getQuery(entityName, queryName, null,null);
	}
	
	
	public static String getQuery(String entityName, String queryName, HashMap paramsWhere) throws Exception{
		return getQuery(entityName, queryName, paramsWhere, null);
	}
	
	public static String getQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving) throws Exception{
		SAXReader reader = null;
		Document document = null;
		String sql  = null;
        Iterator iterator = null;
        Map.Entry keyParam   =null;
        String lastOr = null;
        boolean firts= true;
        String operador = null;
        Element root = null;
	    Element entidad = null;
	    Element query = null;
	    Element filter = null;
	    Element element = null;
	    String key = null;
	    String condicions = "";
	    
	    
	    int idx = 0;
		try{
			
			// Obtiene los elementos del xml (query)
			reader = new SAXReader();
		    document = reader.read(UtilitiesApp.getPathResources(QueryManager.class, "Queries.xml"));
		    root = document.getRootElement();
		    entidad = (Element) root.element(entityName);
		    query = (Element) entidad.element(queryName);
		    
		    // Reemplaza por cada sesion del query (select, from, groupby, order)
		    sql = "select from where groupBy orderBy having";
		    sql = sql.replace("select", query.element("select").getTextTrim());
		    sql = sql.replace("from", query.element("from").getTextTrim());
		    sql = sql.replace("groupBy", query.element("groupBy")!=null ? query.element("groupBy").getTextTrim() : "");
		    sql = sql.replace("orderBy", query.element("orderBy")!=null ? query.element("orderBy").getTextTrim() : "");
		    
		    
		    // Obtiene los filtros del where y/o having que fueron enviados como parametros
		    do{
		    	switch (idx) {
					case 0:
						iterator = paramsWhere !=null && !paramsWhere.isEmpty() ? paramsWhere.entrySet().iterator() : null;
						key = "where";
					break;
					
					case 1:
						iterator = paramsHaving!=null && !paramsHaving.isEmpty() ? paramsHaving.entrySet().iterator() : null;
						key = "having";
					break;
					
					default:
						iterator = null;
					break;
				}
		    	
		    	
		    	filter = (Element) query.element(key);
		    	condicions = "";
		    	
		    	// Obtiene los filtros para Where o Having
		    	if(iterator != null && filter!= null){
			    	lastOr = "";
			    	firts = true;
			    	
		    		while (iterator.hasNext()) {
						keyParam = (Map.Entry)iterator.next();
						element  = filter.element(keyParam.getKey().toString());
						if(element!=null){
							operador = element.attribute("condition").getText();  
							operador = firts && !operador.trim().toLowerCase().equals(OR) ? "" : operador; 
							lastOr   = firts && operador.trim().toLowerCase().equals(OR) ? (" " +operador  + " " +  element.getTextTrim()) : lastOr ;
							condicions+= (" " + operador + " " + element.getTextTrim());
							firts = false;
						}
		    		}

		    		// En caso de que existan filtros se concatena la cadena sql (Where param = :param1)
		    		condicions =  (condicions.equals("") ? "" : filter.attributeValue("condition") +condicions) ;
		    		
		    	}
		    	
		    	sql = sql.replace(key, condicions);
		    	idx++;
		    
		    } while(iterator != null);
		   
		    return sql;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			reader = null;
			document = null;
			sql  = null;
	        iterator = null;
	        keyParam   =null;
	        lastOr = null;
	        firts= true;
	        operador = null;
	        root = null;
		    entidad = null;
		    query = null;
		    filter = null;
		    element = null;
		    key = null;
		    condicions = null;
		}
	}
	
	
	public static Object getEntity(HashMap<Integer, Object> parameters, Object persistence) throws Exception{
		
		try{
			if(persistence.getClass().getName().equals(Usuarios.class.getName())){
				return new QueryManager().getUsuarios(parameters, persistence);
			}
			
			return null;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	private  Object getUsuarios(HashMap<Integer, Object> parameters, Object persistence ) throws Exception{
		Usuarios oldUsuario = null;
		Usuarios usuario = null;
		try{
			
			oldUsuario = persistence != null ? (Usuarios)persistence : new Usuarios();
			usuario =  (Usuarios) Usuarios.class.getConstructor(String.class, String.class, String.class, String.class).newInstance(
				parameters.containsKey(FieldsUser.Name) ? parameters.get(FieldsUser.Name) : oldUsuario.getUserName(),
				parameters.containsKey(FieldsUser.Password) ? parameters.get(FieldsUser.Password) : oldUsuario.getPassword(),
				parameters.containsKey(FieldsUser.Names) ? parameters.get(FieldsUser.Names) : oldUsuario.getNames(),
				parameters.containsKey(FieldsUser.LastName) ? parameters.get(FieldsUser.LastName) : oldUsuario.getLastName()
			);	
			
			usuario.setUserId( oldUsuario.getUserId());
			
			return usuario;
		}
		catch(Exception e){
			throw e;
		}
	}
}
