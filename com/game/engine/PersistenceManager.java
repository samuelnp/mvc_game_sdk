package com.game.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * PersistenceManager control serialization/deserialization in objects that have been saved in savegames
 * to restore later games.
 */
public class PersistenceManager {

	private String persistencePath;
	private String extension;
	
	public PersistenceManager(String path) {
		this.persistencePath = path;
		this.extension = "obj";
	}
	
	/**
	 * Serialize object in persistence/file.obj
	 * @param obj
	 * @return
	 */
	private String saveObject (Object obj) {
		
		String filename = obj.toString() + "." + extension;
		try {
			FileOutputStream fileOut = new FileOutputStream(persistencePath + "/" + filename);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(obj);
	        out.close();
	        fileOut.close();

	    }catch(IOException i)
	    {
	    	System.err.println("Error trying to write file '" + filename + "'");
	    	i.printStackTrace();
	    }
		
		return filename;
	}
	
	public String save(Object object) {
		String filename = saveObject(object);
		return filename;
	}
	
	public String save(Object object, String extension) {
		String oldExtension = this.extension;
		this.extension = extension;
		String filename = saveObject(object);
		this.extension = oldExtension;
		return filename;
	}
	
	public String save(Object object, String extension, String path) {
		String oldPersistencePath = persistencePath;
		this.persistencePath = path;
		String filename = save(object, extension);
		persistencePath = oldPersistencePath;
		return filename;
	}
	
	/**
	 * Restore serialized object from file
	 * @param filename
	 * @return
	 */
	public Object load(String filename) {
		String file = persistencePath + "/" + filename;
		Object obj;
		try {
			
          FileInputStream fileIn = new FileInputStream(file);
          ObjectInputStream in = new ObjectInputStream(fileIn);
          obj = in.readObject();
          
          in.close();
          fileIn.close();
          
        }catch(IOException i)
        {
        	System.err.println("Error trying to load file '" + filename + "'");
        	i.printStackTrace();
        	return null;
        }catch(ClassNotFoundException c)
        {
        	System.err.println("Object class not found by PersistenceManager");
        	c.printStackTrace();
        	return null;
        }
		
		return obj;
	}
	
	public Object load(String filename, String path) {
		String oldPersistencePath = persistencePath;
		persistencePath = path;
		Object obj = load(filename);
		persistencePath = oldPersistencePath;
		return obj;
	}
}
