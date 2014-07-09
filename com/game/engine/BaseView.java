package com.game.engine;

import java.io.Serializable;


/**
 * Class to extend by View concrete game data objects
 * It's serializable to able PersistenceManager save game states for each object
 */
public abstract class BaseView extends BaseEntity implements View, Serializable{

	private static final long serialVersionUID = -942854777838476520L; //Serial ID to use in serialization
	transient protected Engine engine; //This object can't be serializable
	
	protected BaseView() {
		super();
		this.engine = null;
	}
	
	protected BaseView(Engine engine) {
		this.engine = engine;
		this.engine.getInputManager().attachObserver(this);
		this.engine.getActionManager().attachObserver(this);
	}
	
	/**
	 * Method to implement all system/player actions in extended controller
	 */
	public abstract void actionUpdate(ActionEvent event);

	/**
	 * Method to implement all keyboard/mouse events in extended controller
	 */
	public abstract void inputUpdate(InputEvent event);
	
	/**
	 * Method to execute all initialization operations of Sprites
	 */
	public abstract void initialize();

	/**
	 * Method to execute all operations to choose which sprites have to be rendered
	 */
	public abstract void render();
	
	/**
	 * Setter Method to add current engine instance to deserialized objects
	 * @param engine
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
}
