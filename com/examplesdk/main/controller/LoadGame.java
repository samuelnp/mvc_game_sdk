package com.examplesdk.main.controller;

import com.game.engine.ActionEvent;
import com.game.engine.Context;
import com.game.engine.Engine;
import com.game.engine.State;
import com.game.engine.StateController;

/**
 * Load GameController in new game mode with previously saved board
 * @author samuelnp
 *
 */
public class LoadGame extends StateController {

	private static final long serialVersionUID = 2400342461554585886L;

	private GameController game;
	private State nextState;
	protected LoadGame(Engine engine, Context context) {
		super(engine, context);
		nextState = null;
	}

	@Override
	public void logicUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionUpdate(ActionEvent event) {
		if(event.getActionName().equals("QUIT_GAME")) {
			MainMenu mainMenu = new MainMenu(engine, context);
			mainMenu.initialize();
			nextState = mainMenu;
			goNext(context);
		}
	}

	@Override
	public void initialize() {
		game = new GameController(engine);
		game.loadSaveGame();
		game.initialize();
		
	}

	@Override
	public void goNext(Context context) {
		context.setState(nextState);
	}

}
