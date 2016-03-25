package zen.delegate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.financial.Usuarios;

import org.json.JSONObject;

import zen.logic.BLSecurity;
import zen.logic.BLSecurity.ValidatesSecurity;
import zen.logic.BLUser;

public class DNUser extends HttpServlet {

	
	private Usuarios user;
	private boolean  success = false; 
	private BLUser bLUser;
	private JSONObject json = null;
	private PrintWriter out 	= null;
	private ValidatesSecurity action = null;
	
	
	/**
	 * The doDelete method of the servlet. <br>
	 *
	 * This method is called when a HTTP delete request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			user = new Usuarios();
			user.setUserId( request.getSession().getAttribute("userId")!=null ? Integer.parseInt(request.getSession().getAttribute("userId").toString()) :null  );
			
			action  =  ValidatesSecurity.LogIn;
			success = new BLSecurity().vaidateUser(user, action);
			
			if(!success){
				throw new Exception("Permission Denied");
			}
			
			bLUser = new BLUser();
			user.setUserId( request.getParameter("userId")!=null ? Integer.parseInt(request.getParameter("userId").toString()) :null  );
			json =bLUser.delete(user);
			
			
		}
		catch(Exception e){
			json = new JSONObject();
			json.put("success", false);
			json.put("message", e.getMessage());
		}
		
		
		out = response.getWriter();
		out.print(json.toString());
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idOperacion = null;
		try{
			user = new Usuarios();
			user.setUserId( request.getSession().getAttribute("userId")!=null ? Integer.parseInt(request.getSession().getAttribute("userId").toString()) :null  );
			user.setLastName( request.getParameter("lastName")!=null ? request.getParameter("lastName").toString() :null  );
			user.setNames( request.getParameter("names")!=null ? request.getParameter("names").toString() :null  );
			user.setPassword( request.getParameter("password")!=null ? request.getParameter("password").toString() :null  );
			user.setUserName( request.getParameter("userName")!=null ? request.getParameter("userName").toString() :null  );
			
			idOperacion = Integer.parseInt(request.getParameter("idOperacion"));
			
			success = new BLSecurity().vaidateOperacion(user, idOperacion);
			
			if(!success){
				throw new Exception("Permission Denied");
			}
			
			bLUser = new BLUser();
			if(action.equals(ValidatesSecurity.LogOut)){
				json = bLUser.register(user);
			}
			
			if(action.equals(ValidatesSecurity.LogIn)){
				json =bLUser.update(user);
			}
			
		}
		catch(Exception e){
			json = new JSONObject();
			json.put("success", false);
			json.put("message", e.getMessage());
		}
		
		
		out = response.getWriter();
		out.print(json.toString());
	}

	/**
	 * The doPut method of the servlet. <br>
	 *
	 * This method is called when a HTTP put request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			user = new Usuarios();
			user.setUserId( request.getSession().getAttribute("userId")!=null ? Integer.parseInt(request.getSession().getAttribute("userId").toString()) :null  );
			user.setLastName( request.getParameter("lastName")!=null ? request.getParameter("lastName").toString() :null  );
			user.setNames( request.getParameter("names")!=null ? request.getParameter("names").toString() :null  );
			user.setPassword( request.getParameter("password")!=null ? request.getParameter("password").toString() :null  );
			user.setUserName( request.getParameter("userName")!=null ? request.getParameter("userName").toString() :null  );
			
			action  =  user.getUserId() != null ? ValidatesSecurity.LogOut : ValidatesSecurity.LogIn;
			success = new BLSecurity().vaidateUser(user, action);
			
			if(!success){
				throw new Exception("Permission Denied");
			}
			
			bLUser = new BLUser();
			if(action.equals(ValidatesSecurity.LogOut)){
				json = bLUser.register(user);
			}
			
			if(action.equals(ValidatesSecurity.LogIn)){
				json =bLUser.update(user);
			}
			
		}
		catch(Exception e){
			json = new JSONObject();
			json.put("success", false);
			json.put("message", e.getMessage());
		}
		
		
		out = response.getWriter();
		out.print(json.toString());
	}
		
			
}
