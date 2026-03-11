package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static String path="config/config.properties";  //path of file
	private static Properties prop = new Properties();   //so that object creates only once , not on every call 
	private static String env;
	
	
	static {	
		
	
		env = System.getProperty("env","qa");  // it will return string 
		env=env.toLowerCase().trim();   	// select the environment to run 

		
		
		switch(env) {   // using -> operator it will automatically take care of break .. it is part of java 14
		
		case "dev" -> path = "config/config.DEV.properties";
		case "qa" -> path="config/config.QA.properties";	
		case "beta" -> path="config/config.BETA.properties";
		default -> path="config/config.QA.properties";
		
		}
		
		
		
		
		InputStream input= Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		if(input==null) {
			throw new RuntimeException("Cannot find file path at "+path);
		}
		try {
			
			prop.load(input);
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ConfigManager() {   //so that no one can create the object of config manager class 
		
	}
	public static String getPropertyFromFile(String key) {
		return prop.getProperty(key);
	}
}
