package zen.utilities;



public class UtilitiesApp {
	
	public static String getPathResources(Class entity, String nameResource){
		ClassLoader loader = null;
		String path = null;
		try{
			   loader = entity.getClassLoader();
			   path = entity.getName();
			   path = path.replace(".", "/");
			   
			   if(nameResource==null){
				   path+= ".class";
			   }
			   else{
				   
				   path = path.substring(0, path.lastIndexOf("/") + 1 );
				   path+= nameResource;
			   }
			   
			   return loader.getResource(path).getPath();
			   
		}
		catch(Exception e){
			throw e;
		}
	}
}
