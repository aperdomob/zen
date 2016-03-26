package zen.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;
import model.financial.Usuarios;
import model.financial.NameQryUsers.FieldsUser;

import org.junit.Test;

import zen.delegate.DNUser;
import zen.persistence.DataBaseSession;
import zen.persistence.IDataBasesSession;


public class PersistenceTest {

	@Test
	public void Transacciones() throws Exception {
		IDataBasesSession session = null;
		Usuarios user = null;
		Usuarios userPers = null;
		Usuarios userPers02 = null;
		Integer idUser = null;
		String newPasswd = "Aaa123456+";
		String newLastName = "Bolanos Lenis";
		try{
			session = new DataBaseSession();
			user = new Usuarios();
			user.setLastName("Bolaños Lenis");
			user.setNames("Juan Pablo");
			user.setPassword("123");
			user.setUserName("jp");
			idUser = (Integer) session.save(user);
			
			Assert.assertNotNull("Error Save User", idUser);
			
			session = new DataBaseSession();
			userPers = (Usuarios) session.findById(Usuarios.class, idUser);
			
			Assert.assertNotNull("Error find by Id User", userPers);
			
			
			session = new DataBaseSession();
			userPers.setPassword(newPasswd);
			session.merge(userPers);
			
			session = new DataBaseSession();
			userPers = (Usuarios) session.findById(Usuarios.class, idUser);
			
			Assert.assertNotSame("Error Merge Usuario",newPasswd, userPers.getPassword());
			
			
			session = new DataBaseSession();
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			params.put(FieldsUser.LastName.ordinal(), newLastName);
			session.merge(Usuarios.class, idUser, params);
			
			session = new DataBaseSession();
			userPers = (Usuarios) session.findById(Usuarios.class, idUser);
			
			Assert.assertNotSame("No update las name", newLastName, userPers.getLastName());
			
			
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(idUser != null){
				user.setUserId(idUser);
				session = new DataBaseSession();
				session.delete(user);
			}
		}
		
	}
	
	
	@Test
	public void execHql() throws Exception {
		IDataBasesSession session = null;
		Usuarios user01 = null;
		Usuarios user02 = null;
		Usuarios user03 = null;
		HashMap params = null;
		List<Usuarios> listUser;
		try{
			
			
			
			user01 = new Usuarios();
			user01.setLastName("Bolaños Lenis");
			user01.setNames("Juan Pablo");
			user01.setPassword("123");
			user01.setUserName("jp");
			
			user02 = new Usuarios();
			user02.setLastName("Bolaños Salazar");
			user02.setNames("Luis Alberto");
			user02.setPassword("123");
			user02.setUserName("lb");
			
			user03 = new Usuarios();
			user03.setLastName("Lenis Hurtado");
			user03.setNames("Angie Lucia");
			user03.setPassword("123");
			user03.setUserName("al");
			
			session = new DataBaseSession();
			user01.setUserId((Integer)session.save(user01));
			
			session = new DataBaseSession();
			user02.setUserId((Integer)session.save(user02));
			
			session = new DataBaseSession();
			user03.setUserId((Integer)session.save(user03));
			
			params = new HashMap();
			params.put("userName", "jp");
			params.put("names", "Juan Pablo");
			params.put("password", "123");
			params.put("lastName", "Bolaños Lenis");
			session = new DataBaseSession();
			listUser = session.executeHqlQuery("Usuarios", "getAllUser", params, null);
			
			Assert.assertTrue("No se encontro registros", listUser!=null && listUser.size() > 0);
			
			
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(user01.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user01);
			}
			
			if(user02.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user02);
			}
			
			if(user03.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user03);
			}
		}
		
	}
	
	
	@Test
	public void execNameQuery() throws Exception {
		IDataBasesSession session = null;
		Usuarios user01 = null;
		Usuarios user02 = null;
		Usuarios user03 = null;
		HashMap params = null;
		List<Usuarios> listUser;
		try{
			
			
			
			user01 = new Usuarios();
			user01.setLastName("Bolaños Lenis");
			user01.setNames("Juan Pablo");
			user01.setPassword("123");
			user01.setUserName("jp");
			
			user02 = new Usuarios();
			user02.setLastName("Bolaños Salazar");
			user02.setNames("Luis Alberto");
			user02.setPassword("123");
			user02.setUserName("lb");
			
			user03 = new Usuarios();
			user03.setLastName("Lenis Hurtado");
			user03.setNames("Angie Lucia");
			user03.setPassword("123");
			user03.setUserName("al");
			
			session = new DataBaseSession();
			user01.setUserId((Integer)session.save(user01));
			
			session = new DataBaseSession();
			user02.setUserId((Integer)session.save(user02));
			
			session = new DataBaseSession();
			user03.setUserId((Integer)session.save(user03));
			
			params = new HashMap();
			params.put("userName", "jp");
			params.put("names", "Juan Pablo");
			params.put("password", "123");
			params.put("lastName", "Bolaños Lenis");
			session = new DataBaseSession();
			listUser = session.executeNameQuery("findGetAllUser", params);
			
			Assert.assertTrue("No se encontro registros", listUser!=null && listUser.size() > 0);
			
			
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(user01.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user01);
			}
			
			if(user02.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user02);
			}
			
			if(user03.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user03);
			}
		}
		
	}
	
	
	@Test
	public void execNativeQuery() throws Exception {
		IDataBasesSession session = null;
		Usuarios user01 = null;
		Usuarios user02 = null;
		Usuarios user03 = null;
		HashMap params = null;
		List<Usuarios> listUser;
		try{
			
			
			
			user01 = new Usuarios();
			user01.setLastName("Bolaños Lenis");
			user01.setNames("Juan Pablo");
			user01.setPassword("123");
			user01.setUserName("jp");
			
			user02 = new Usuarios();
			user02.setLastName("Bolaños Salazar");
			user02.setNames("Luis Alberto");
			user02.setPassword("123");
			user02.setUserName("lb");
			
			user03 = new Usuarios();
			user03.setLastName("Lenis Hurtado");
			user03.setNames("Angie Lucia");
			user03.setPassword("123");
			user03.setUserName("al");
			
			session = new DataBaseSession();
			user01.setUserId((Integer)session.save(user01));
			
			session = new DataBaseSession();
			user02.setUserId((Integer)session.save(user02));
			
			session = new DataBaseSession();
			user03.setUserId((Integer)session.save(user03));
			
			params = new HashMap();
			params.put("userName", "jp");
			params.put("names", "Juan Pablo");
			params.put("password", "123");
			params.put("lastName", "Bolaños Lenis");
			session = new DataBaseSession();
			listUser = session.executeNameQuery("findGetAllUserNative", params);
			
			Assert.assertTrue("No se encontro registros", listUser!=null && listUser.size() > 0);
			
			
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(user01.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user01);
			}
			
			if(user02.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user02);
			}
			
			if(user03.getUserId() != null){
				session = new DataBaseSession();
				session.delete(user03);
			}
		}
		
	}
	
}
