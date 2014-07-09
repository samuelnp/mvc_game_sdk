package com.game.engine;

/**
 * Class that manage contexts for State Based Controllers
 */
public class BaseContext implements Context{

	private State current;
	
	public BaseContext() {
		current = null;
	}
	
	/**
	 * Calls next State from current
	 */
	public void goNext() {
		current.goNext(this);
	}

	public void setState(State state) {
		current = state;
	}
	
	public State getCurrent() {
		return current;
	}
	
	/**
	 * Execute current State
	 */
	public void execute() {
		current.execute();
	}

}
