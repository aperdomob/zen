package zen.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;



public class DataBaseSession implements IDataBasesSession {

	
	
	// Indica si la sesion se mantiena para varias transacciones
	private boolean closeSession;

	// Almacena la sesion de base de datos
	private Session session; 
	
	public DataBaseSession(){
		this.closeSession = true;
		
		// Me permite conectarme a la base de datos definidas 
		this.session = HibernateSession.getSession();
		
	}
	
	
	
	@Override
	public Object save(Serializable entity) throws Exception {
		
        Object object = null;
        try {
        	
        	session.beginTransaction();
        	object = session.save(entity);
        	
        	if(closeSession){
        		session.getTransaction().commit();
        	}
        	
        	return object;
        } 
        catch (RuntimeException re) {
        	
        	if(session.getTransaction().isActive()){
        		session.getTransaction().rollback();
        		session.close();
        	}
        	
        	throw re;
        }
        finally{
        	if(closeSession && session.isConnected()){
        		session.close();
        	}
        }
	}

	
	@Override
	public Object merge(Serializable entity) throws Exception {
		
		Object object = null;
        try {
        	
        	session.beginTransaction();
        	object = session.merge(entity);
        	
        	if(closeSession){
        		session.getTransaction().commit();
        	}
        	
        	return object;
        } 
        catch (RuntimeException re) {
        	
        	if(session.getTransaction().isActive()){
        		session.getTransaction().rollback();
        		session.close();
        	}
        	
        	throw re;
        }
        finally{
        	if(closeSession && session.isConnected()){
        		session.close();
        	}
        }
	}
	
	
	@Override
	public Object merge(Class entidad, Serializable id, HashMap <Integer, Object> parameters) throws Exception {
		Object entity = null;
		Object persistence = null;
		Object object = null;
        try {
        	
        	
        	persistence = (Serializable) findById(entidad, id);
        	entity      = QueryManager.getEntity(parameters, persistence);
        	
        	// Si se cierra la session por la transaccion anterior se abre para la nueva transaccion
        	if(!session.isConnected()){
        		session = HibernateSession.getSession();
        	}
        	
        	session.beginTransaction();
        	object = session.merge(entity);
        	
        	
        	if(closeSession){
        		session.getTransaction().commit();
        	}
        	
        	return object;
        } 
        catch (RuntimeException re) {
        	
        	if(session.getTransaction().isActive()){
        		session.getTransaction().rollback();
        		session.close();
        	}
        	
        	throw re;
        }
        finally{
        	if(closeSession && session.isConnected()){
        		session.close();
        	}
        }
	}

	@Override
	public void saveOrUpdate(Serializable entity) throws Exception {
		
        try {
        	
        	session.beginTransaction();
        	session.saveOrUpdate(entity);
        	
        	if(closeSession){
        		session.getTransaction().commit();
        	}
        	
        } 
        catch (RuntimeException re) {
        	
        	if(session.getTransaction().isActive()){
        		session.getTransaction().rollback();
        		session.close();
        	}
        	
        	throw re;
        }
        finally{
        	if(closeSession && session.isConnected()){
        		session.close();
        	}
        }
	}

	@Override
	public void delete(Serializable entity) throws Exception {
		try {
        	session.beginTransaction();
        	session.delete(entity);
        	
        	if(closeSession){
        		session.getTransaction().commit();
        	}
        } catch (RuntimeException re) {
           
        	if(session.getTransaction().isActive()){
        		session.getTransaction().rollback();
        		session.close();
        	}
            throw re;
        }
        finally{
        	if(closeSession && session.isConnected()){
        		session.close();
        	}
        }
	}

	@Override
	public Object findById(Class entidad, Serializable id) throws Exception {
		Object instance = null;
        try {
        	instance = (Object) session.get(entidad,  id);
        	return instance;
        } catch (RuntimeException re) {
            throw re;
        }
        finally{
        	if(closeSession && session.isConnected()){
        		session.close();
        	}
        }
	}

	

	@Override
	public List executeNameQuery(String nameQuery, HashMap parameters) throws Exception {
		return executeNameQuery(nameQuery, parameters, null, null);
	}

	@Override
	public List executeNameQuery(String nameQuery, HashMap parameters, Integer top, Integer rows) throws Exception{
		Query queryObject = null;
		Iterator vIterator = null;
		Map.Entry mapEnt=null;
		List result = null;
		try {
			
			queryObject =	session.getNamedQuery(nameQuery);
			if(top != null){
				queryObject.setFirstResult(top);
			}
			
			if(rows != null){
				queryObject.setMaxResults( rows);
			}
		

			if (parameters != null) 
			{
				vIterator = parameters.entrySet().iterator();
				while (vIterator.hasNext()) {
					mapEnt = (Map.Entry)vIterator.next();
					queryObject.setParameter(mapEnt.getKey().toString(),mapEnt.getValue());
				}
			}
			
			result = queryObject.list();
			return result;
        
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		finally{
			if(closeSession && session.isConnected()){
        		session.close();
        	}
			
			queryObject = null;
			vIterator = null;
			mapEnt=null;
			result = null;
		}
	}


	@Override
	public List executeNativeQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving) throws Exception {
		return executeNativeQuery(entityName, queryName, paramsWhere, paramsHaving, null, null);		
	}

	@Override
	public List executeNativeQuery(String entityName, String queryName,  HashMap paramsWhere, HashMap paramsHaving, Integer top, Integer rows) throws Exception{
		Query queryObject = null;
		Iterator vIterator = null;
		Map.Entry mapEnt=null;
		List result = null;
		String sql = null;
		try {
			
			sql = QueryManager.getQuery(entityName, queryName, paramsWhere, paramsHaving);
			queryObject = session.createSQLQuery(sql);
			
			if(top != null){
				queryObject.setFirstResult(top);
			}
			
			if(rows != null){
				queryObject.setMaxResults( rows);
			}
			
			if (paramsWhere != null) 
			{
				vIterator = paramsWhere.entrySet().iterator();
				while (vIterator.hasNext()) {
					mapEnt = (Map.Entry)vIterator.next();
					queryObject.setParameter(mapEnt.getKey().toString(),mapEnt.getValue());
				}
			}
			
			if (paramsHaving != null) 
			{
				vIterator = paramsWhere.entrySet().iterator();
				while (vIterator.hasNext()) {
					mapEnt = (Map.Entry)vIterator.next();
					queryObject.setParameter(mapEnt.getKey().toString(),mapEnt.getValue());
				}
			}
			
			result = queryObject.list();
			return result;
		
		} catch (Exception ex) {
			throw ex;
		}
		finally{
			if(closeSession && session.isConnected()){
        		session.close();
        	}
			
			queryObject = null;
			vIterator = null;
			mapEnt=null;
			result = null;
			sql = null;
        }
	}

	@Override
	public int executeNativeQueryUpdate(String entityName, String queryName,  HashMap paramsWhere) throws Exception {
		Query queryObject = null;
		Iterator vIterator = null;
		Map.Entry mapEnt=null;
		int result = -1;
		String sql = null;
		try {
		
			sql = QueryManager.getQuery(entityName, queryName, paramsWhere);
			queryObject = session.createSQLQuery(sql);
			
			if (paramsWhere != null) 
			{
				vIterator = paramsWhere.entrySet().iterator();
				while (vIterator.hasNext()) {
					mapEnt = (Map.Entry)vIterator.next();
					queryObject.setParameter(mapEnt.getKey().toString(),mapEnt.getValue());
				}
			}
			
			result = queryObject.executeUpdate();
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			if(closeSession && session.isConnected()){
        		session.close();
        	}
			
			queryObject = null;
			vIterator = null;
			mapEnt=null;
			result = -1;
			sql = null;
		}
		return result;
	}
	
	@Override
	public List executeHqlQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving) throws Exception {
		return executeHqlQuery(entityName, queryName, paramsWhere, paramsHaving, null, null);
	}



	@Override
	public List executeHqlQuery(String entityName, String queryName, HashMap paramsWhere, HashMap paramsHaving, Integer top, Integer rows) throws Exception {
		Query queryObject = null;
		Iterator vIterator = null;
		Map.Entry mapEnt=null;
		List result = null;
		String hql = null;
		try {
			
			hql = QueryManager.getQuery(entityName, queryName, paramsWhere, paramsHaving);
			queryObject = session.createQuery(hql);
			
			if(top != null){
				queryObject.setFirstResult(top);
			}
			
			if(rows != null){
				queryObject.setMaxResults( rows);
			}
			
			if (paramsWhere != null) 
			{
				vIterator = paramsWhere.entrySet().iterator();
				while (vIterator.hasNext()) {
					mapEnt = (Map.Entry)vIterator.next();
					queryObject.setParameter(mapEnt.getKey().toString(),mapEnt.getValue());
				}
			}
			
			if (paramsHaving != null) 
			{
				vIterator = paramsWhere.entrySet().iterator();
				while (vIterator.hasNext()) {
					mapEnt = (Map.Entry)vIterator.next();
					queryObject.setParameter(mapEnt.getKey().toString(),mapEnt.getValue());
				}
			}
			
			result = queryObject.list();
			return result;
		
		} catch (Exception ex) {
			throw ex;
		}
		finally{
			if(closeSession && session.isConnected()){
        		session.close();
        	}
			
			queryObject = null;
			vIterator = null;
			mapEnt=null;
			result = null;
			hql = null;
        }
	}
	
	


	@Override
	public List executeNameProcedure(String nameProcedure, HashMap parameters, Integer top, Integer rows) throws Exception {
		
		Query queryObject  = null;
		Iterator vIterator = null;
		Map.Entry mapEnt = null;
		List result = null;
		
		try{	
			
			queryObject = session.getNamedQuery(nameProcedure);
			
			if(top != null){
				queryObject.setFirstResult(top);
			}
			
			if(rows != null){
				queryObject.setMaxResults( rows);
			}
	
			if (parameters != null) 
			{
				vIterator = parameters.entrySet().iterator();
				while (vIterator.hasNext()) {
					mapEnt = (Map.Entry)vIterator.next();
					queryObject.setParameter(mapEnt.getKey().toString(),mapEnt.getValue());
				}
			}
			
			result = queryObject.list();
			return result;
		
	} catch (Exception ex) {
		throw ex;
	}
	finally{
		if(closeSession && session.isConnected()){
    		session.close();
    	}
		
		queryObject  = null;
		vIterator = null;
		mapEnt = null;
		result = null;
    }
	
	}
	

	@Override
	public void keepSession() throws Exception {
		this.closeSession = false;
	}

	@Override
	public void closeSession() throws Exception {
		
		try{
			session.getTransaction().commit();
		}
		catch(Exception e){
			session.getTransaction().rollback();
			throw e;
		}
		finally{
			session.close();
			this.closeSession = true;
		}
		
	}

}
