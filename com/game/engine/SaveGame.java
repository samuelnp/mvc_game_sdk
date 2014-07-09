package com.game.engine;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SaveGame class save file names of serialized objects to restore them at savegame load
 */
public class SaveGame implements Serializable {

	private static final long serialVersionUID = -2002988476327190938L;
	private List<String> serializedObjects;
	private String name;
	
	public SaveGame() {
		this.serializedObjects = new ArrayList<String>();
		this.name = getGeneratedName();
		
	}
	
	public SaveGame(String name) {
		this();
		this.name = name;
	}
	
	public void add(String filename) {
		this.serializedObjects.add(filename);
	}
	
	public List<String> getAll() {
		return this.serializedObjects;
	}
	
	private String getGeneratedName() {
		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
		return dateFormat.format(time);
	}
	public String toString() {
		return name;
	}
}
