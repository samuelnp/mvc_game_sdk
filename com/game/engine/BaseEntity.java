package com.game.engine;

import java.io.Serializable;

/**
 * BaseEntity is used to add Entity identifier to object. This identity able object
 * interact with others through ActionManager
 */
public class BaseEntity implements Entity, Serializable{

	private static final long serialVersionUID = 3956275832147064885L;
	private String id;
	
	public BaseEntity() {
		this.id = null;
	}
	
	public String getID() {
		return this.id;
	}
	
	/**
	 * Transforms id to Upper Case entity name standarization
	 */
	public void setID(String id) {
		this.id = id.toUpperCase();
	}

}
