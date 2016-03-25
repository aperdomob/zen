package persistence;

public interface Persistencia {
	
	final String persistenceUnit = "ProyectoTest";
	
	public Object persist(Object entity);
	
	
}
