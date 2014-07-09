package com.game.engine;

import java.io.Serializable;

/**
 * Abstract class to extends by concrete game controllers. This class
 * implement Controller interface to communicate with InputManager, ActionManager
 * and LogicManager.
 * It's serializable to able PersistenceManager save game states for each object
 *
 */
public abstract class BaseController extends BaseEntity implements Controller, Serializable{

	private static final long serialVersionUID = 6044029888590440991L; //Serial ID to use in serialization
	transient protected Engine engine; //This object can't be serializable
	
	protected BaseController() {
		super();
		this.engine = null;
	}
	
	/**
	 * This constructor append observers implemented to their managers
	 * Extended Controllers are automatically appended
	 * @param engine
	 */
	protected BaseController(Engine engine) {
		this.engine = engine;
		this.engine.getActionManager().attachObserver(this);
		this.engine.getLogicManager().attachObserver(this);
	}

	/**
	 * Method to implement all logic operations in extended controller
	 */
	public abstract void logicUpdate();

	/**
	 * Method to implement all system/player actions in extended controller
	 */
	public abstract void actionUpdate(ActionEvent event);

	/**
	 * Method to execute all initialization operations of Controllers, Events, Models and Views
	 */
	public abstract void initialize();
	
	/**
	 * Setter Method to add current engine instance to deserialized objects
	 * @param engine
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
}
