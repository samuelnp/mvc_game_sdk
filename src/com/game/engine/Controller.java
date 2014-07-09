package com.game.engine;

/**
 * Generic methods that implements concrete Controllers
 */
public interface Controller extends Entity, ActionObserver, LogicObserver{

	void initialize();
	
}
