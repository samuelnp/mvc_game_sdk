package com.game.engine;

public interface View extends Entity, InputObserver, ActionObserver{

	void initialize();
	void render();
	
}
