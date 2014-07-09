package com.game.engine;

import java.io.Serializable;

/**
 * Class to extend by Model concrete game data objects
 * It's serializable to able PersistenceManager save game states for each object
 */
public abstract class BaseModel extends BaseEntity implements Model, Serializable {

	private static final long serialVersionUID = 8622001870867489473L; //Serial ID to use in serialization
	transient protected Engine engine; //This object can't be serializable
	
	protected BaseModel() {
		super();
		this.engine = null;
	}
	
	protected BaseModel(Engine engine) {
		this.engine = engine;
		this.engine.getActionManager().attachObserver(this);
	}
	
	/**
	 * Setter Method to add current engine instance to deserialized objects
	 * @param engine
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public abstract void actionUpdate(ActionEvent event);
}
