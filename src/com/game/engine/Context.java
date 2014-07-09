package com.game.engine;

/**
 * Interface with methods to be implemented in Context classes
 */
public interface Context {

	void goNext();
	void setState(State state);
	void execute();
	State getCurrent();
}
