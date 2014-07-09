package com.examplesdk.main.view;

import com.game.engine.ActionEvent;
import com.game.engine.BaseView;
import com.game.engine.Engine;
import com.game.engine.InputEvent;
import com.game.engine.MouseInputEvent;
import com.game.engine.Sprite;
import com.game.engine.Sprite2D;

public class MainMenuView extends BaseView{

	private static final long serialVersionUID = -5782576228040373996L;
	private Sprite background;
	private Sprite logo;
	private Sprite loadGame;
	private Sprite newGame;
	public MainMenuView(Engine engine) {
		super(engine);
		background = null;
		logo = null;
	}
	
	public void actionUpdate(ActionEvent event) {
		
	}

	public void initialize() {
		
		 //Initialize sprites
		 background = new Sprite2D(engine, "/assets/background.png"); 
		 logo = new Sprite2D(engine, "/assets/logo.png");
		 newGame = new Sprite2D(engine, "/assets/new-game.png",1,2);
		 loadGame = new Sprite2D(engine, "/assets/load-game.png",1,2);
		 
		 //Apply entity names to sprites that will be used in actions
		 newGame.setID("NEW_GAME");
		 loadGame.setID("LOAD_SAVEGAME");
		 
		 //Add sprites to render
		 engine.getRenderManager().addSprite(background);
		 engine.getRenderManager().addSprite(logo);
		 engine.getRenderManager().addSprite(newGame);
		 engine.getRenderManager().addSprite(loadGame);
	}

	@Override
	public void render() {
		
		logo.setX((engine.getScreenWidth() / 2) - (logo.getFrameWidth() / 2));
		logo.setY(60);
		
	    newGame.setX((engine.getScreenWidth() / 2) - (newGame.getFrameWidth() / 2));
		newGame.setY((engine.getScreenHeight() / 2) - (newGame.getFrameHeight() / 2));
		
		loadGame.setX((engine.getScreenWidth() / 2) - (loadGame.getFrameWidth() / 2));
		loadGame.setY(((engine.getScreenHeight() / 2) - (loadGame.getFrameHeight() / 2)) + 100);
		
	}

	public void inputUpdate(InputEvent event) {
		if (event.getClass().equals(MouseInputEvent.class)) {
			MouseInputEvent e = (MouseInputEvent) event;
			if(event.getStatus() == "MOUSE_MOVED") {
				over(e.getX(), e.getY());
			}
			
			if(event.getStatus() == "MOUSE_CLICK") {
				click(e.getX(), e.getY());
			}
		}
	}
	
	public void over(Integer x, Integer y) {
		if(newGame.containsCoords(x, y)) {
			newGame.setCurrentYFrame(2);
		} else {
			newGame.setCurrentYFrame(1);
		}
		
		if(loadGame.containsCoords(x, y)) {
			loadGame.setCurrentYFrame(2);
		} else {
			loadGame.setCurrentYFrame(1);
		}
	}
	
	/**
	 * Check which menu element have been clicked and generate action to be listened
	 * by other controller, model or view
	 * @param x
	 * @param y
	 */
	public void click(Integer x, Integer y) {
		if(newGame.containsCoords(x, y)) {
			ActionEvent event = new ActionEvent(newGame, "NEXT_STATE");
			engine.getActionManager().attachActionEvent(event);
		}
		
		if(loadGame.containsCoords(x, y)) {
			ActionEvent event = new ActionEvent(loadGame, "NEXT_STATE");
			engine.getActionManager().attachActionEvent(event);
		}
	}
	

}
