package com.game.engine;

/**
 * Common methods to implement in Action Subject
 */
public interface ActionSubject {
	void attachObserver(ActionObserver o);
	void detachObserver(ActionObserver o);
	void notifyObservers();
}
