package com.game.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Class to load and write information in JSON Format. This format
 * is easy to read by humans. JSON objects are transformed into POJO
 * objects ready to use in java at runtime
 * 
 * JSON representation needs implemented POJO class to do transformation
 * 
 * Gson is a library created by Google to manipulate JSON / POJO Objects.
 * For more information visit http://code.google.com/p/google-gson/
 *
 */
public class ConfigManager {

	private Gson jsonParser;
	
	public ConfigManager() {
		this.jsonParser = new Gson();
	}
	
	/**
	 * Load object from JSON file and return its POJO object
	 * @param className
	 * @param filename
	 * @return Object
	 */
	public Object load(Class<?> className, String filename) {
		
		Object obj = null;
		
		try {
			//Open Read Buffer to get file content
			BufferedReader br = new BufferedReader(new FileReader(filename));
	 
			//convert the json string back to object casted by className
			obj = jsonParser.fromJson(br, (Type) className);
	 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	/**
	 * Save POJO object into JSON object representation in filename
	 * @param obj
	 * @param filename
	 */
	public void save(Object obj, String filename) {
		String json = jsonParser.toJson(obj);
		 
		try {
			//write converted json data to a file
			FileWriter writer = new FileWriter(filename);
			writer.write(json);
			writer.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
