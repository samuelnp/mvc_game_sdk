package com.examplesdk.main.controller;

import com.game.engine.ActionEvent;
import com.game.engine.Context;
import com.game.engine.Engine;
import com.game.engine.State;
import com.game.engine.StateController;

public class NewGame extends StateController {

	private static final long serialVersionUID = 7638836564843500705L;
	private GameController game;
	State nextState;
	
	protected NewGame(Engine engine, Context context) {
		super(engine, context);
		game = null;
		nextState = null;
	}

	public void logicUpdate() {

	}

	public void actionUpdate(ActionEvent event) {
		if(event.getActionName().equals("QUIT_GAME")) {
			MainMenu mainMenu = new MainMenu(engine, context);
			mainMenu.initialize();
			nextState = mainMenu;
			goNext(context);
		}
		
	}

	public void initialize() {
		game = new GameController(engine);
		game.initialize();
	}

	public void goNext(Context context) {
		context.setState(nextState);
	}

}
