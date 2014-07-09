package com.game.engine;

/**
 * Define Model common methods for MVC based game components
 * Models can be modified by system/player actions
 */
public interface Model extends Entity, ActionObserver{

	void initialize();
	
}
