package resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import resources.Enums.PropertiesFile;


public class Settings {
	
	private static Properties settings;
	private final String[] propertyFileName = new String[]{"settings"};
	
	public Settings(){
		
	}
	
	public Properties loadPropertiesFile(PropertiesFile indexProperty) throws Exception {
		InputStream inStream = null;
		Properties properties = null;
		String filename = null;
		String packageName = null;
		String pathFile = null;
		try{
			filename = String.format("%s.%s", propertyFileName[indexProperty.ordinal()], "properties");
			packageName = getClass().getPackage().getName().replace(".", "/");
			pathFile = String.format("%s/%s", packageName, filename);
			inStream = getClass().getClassLoader().getResourceAsStream(pathFile);
			properties = new Properties();
			properties.load(inStream);
			return properties;
		}
		catch(FileNotFoundException e)	{
			throw e;
		}
		catch (IOException e){
			throw e;
		}
		finally{
			if(inStream!=null )
			 inStream.close();
		}
	} 
	
	public void reloadPropertiesFile(PropertiesFile indexProperty) throws Exception{
		try{
			Settings.settings = indexProperty == PropertiesFile.Settings ? null : Settings.settings;
			loadPropertiesFile(indexProperty);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public Properties getSettings() throws Exception{
		Settings.settings =  Settings.settings == null ? 
		 loadPropertiesFile(PropertiesFile.Settings) : Settings.settings;
		return Settings.settings;
	}
	
	
	
}
