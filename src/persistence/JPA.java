package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPA implements Persistencia {
	
private EntityManagerFactory emf;
	
	private EntityManager em;
	
	private EntityTransaction trans;
	
	

	/*
	 * Inserta o actualiza un registro en una entidad de base de datos
	 * params: 
	 * 	Object entity : Objecto serializado ubicados en el paquete model
	 * return:
	 * 	retorna el mismo objecto de parametro, en caso de insercion y tener una
	 *  llave autogenerada, el objecto obtiene dicha llave.
	 */
	@Override
	public Object persist(Object entity) {
		try{
			emf = Persistence.createEntityManagerFactory(persistenceUnit); 
	        em = emf.createEntityManager(); 
	        trans = em.getTransaction(); 
	        
	        trans.begin(); 
	        em.persist(entity); 
	        trans.commit(); 
	        
	        return entity;
	        
		}
		catch(Exception e){
			trans.rollback();
			throw e;
		}
		finally {
			em.close(); 
	        emf.close(); 
		}
	}
}
