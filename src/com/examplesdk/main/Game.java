package com.examplesdk.main;

import com.examplesdk.main.controller.MainMenu;
import com.game.engine.BaseContext;
import com.game.engine.Engine;

/**
 * Main game class to initialize examplesdk
 */
public class Game {

	private Engine engine;
	
	public Game(Engine engine) {
		this.engine = engine;
	}
	
	public void initialize() {
		BaseContext context = new BaseContext();
		MainMenu mainMenu = new MainMenu(engine, context);
		context.setState(mainMenu);
		
		engine.addContext(context);
	}
	
	public void start() {
		engine.loop();
	}
}
