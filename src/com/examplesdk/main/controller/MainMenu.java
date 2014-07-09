package com.examplesdk.main.controller;

import com.examplesdk.main.view.MainMenuView;
import com.game.engine.ActionEvent;
import com.game.engine.Context;
import com.game.engine.Engine;
import com.game.engine.State;
import com.game.engine.StateController;

/**
 * Main Menu controller to show logo and game options.
 * Extends from StateController to change context.
 */
public class MainMenu extends StateController{

	private static final long serialVersionUID = -8448899597461120869L;
	MainMenuView mainView;
	State nextState;
	
	public MainMenu(Engine engine, Context context) {
		super(engine, context);
		this.mainView = null;
		this.nextState = null;

	}

	public void logicUpdate() {
		mainView.render();
	}

	public void actionUpdate(ActionEvent event) {
		
		if(event.getActionName().equals("NEXT_STATE")) {
				
				if(event.getEntity().getID().equals("NEW_GAME")) {
					NewGame newGame = new NewGame(engine, context);
					newGame.initialize();
					nextState = newGame;
				}
				
				if(event.getEntity().getID().equals("LOAD_SAVEGAME")) {
					LoadGame loadGame = new LoadGame(engine, context);
					loadGame.initialize();
					nextState = loadGame;
				}
				
				goNext(context);
				
		}
	}

	public void initialize() {
		
        mainView = new MainMenuView(engine);
		mainView.initialize();
		
	}

	public void goNext(Context context) {
		context.setState(nextState);	
	}

}
