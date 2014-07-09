package com.examplesdk.main.view;

import com.game.engine.ActionEvent;
import com.game.engine.BaseView;
import com.game.engine.Engine;
import com.game.engine.InputEvent;
import com.game.engine.MouseInputEvent;
import com.game.engine.Sprite;
import com.game.engine.Sprite2D;

/**
 * Class to render all GUI Elements like change turn button and move legend.
 * @author samuelnp
 *
 */
public class GameGUIView extends BaseView {

	private static final long serialVersionUID = -4586580928741370810L;

	private Sprite endTurn;
	private Sprite clickPiece;
	private Sprite clickParcel;
	private Sprite savegame;
	private Sprite exit;
	
	public GameGUIView (Engine engine) {
		super(engine);
	}
	
	/**
	 * Listen input events an execute button click actions according coordinates
	 */
	public void inputUpdate(InputEvent event) {
		if (event.getClass().equals(MouseInputEvent.class)) {
			MouseInputEvent e = (MouseInputEvent) event;
			
			if(event.getStatus() == "MOUSE_CLICK") {
				if(endTurn.containsCoords(e.getX(), e.getY())) {
					ActionEvent spriteClicked = new ActionEvent(this, "NEW_TURN");
					engine.getActionManager().attachActionEvent(spriteClicked);
				}
				
				if(savegame.containsCoords(e.getX(), e.getY())) {
					ActionEvent spriteClicked = new ActionEvent(this, "SAVE_GAME");
					engine.getActionManager().attachActionEvent(spriteClicked);
				}
				
				if(exit.containsCoords(e.getX(), e.getY())) {
					ActionEvent spriteClicked = new ActionEvent(this, "QUIT_GAME");
					engine.getActionManager().attachActionEvent(spriteClicked);
				}
			}
		}
	}

	/**
	 * Listen actions from controller or models to change visibility piece state
	 */
	public void actionUpdate(ActionEvent event) {
		if(event.getActionName().equals("PIECE_CLICKED")) {
			clickPiece.setVisible(false);
			clickParcel.setVisible(true);
		}
		
		if(event.getActionName().equals("PARCEL_CLICKED")) {
			clickParcel.setVisible(false);
			clickPiece.setVisible(true);
		}
	}

	public void initialize() {
		endTurn = new Sprite2D(engine, "/assets/change-turn.png", 8);
		endTurn.setID("END_TURN");
		endTurn.setX(engine.getScreenWidth() - 300);
		endTurn.setY(100);
		
		engine.getRenderManager().addSprite(endTurn);
		
		clickPiece = new Sprite2D(engine, "/assets/click-piece.png", 8);
		clickPiece.setID("INFO_PIECE");
		clickPiece.setX(engine.getScreenWidth() - 300);
		clickPiece.setY(300);
		
		engine.getRenderManager().addSprite(clickPiece);
		
		clickParcel = new Sprite2D(engine, "/assets/click-block.png", 8);
		clickParcel.setID("INFO_PARCEL");
		clickParcel.setX(engine.getScreenWidth() - 300);
		clickParcel.setY(300);
		clickParcel.setVisible(false);
		
		engine.getRenderManager().addSprite(clickParcel);
		
		savegame = new Sprite2D(engine, "/assets/save.png", 8);
		savegame.setID("SAVEGAME");
		savegame.setX(engine.getScreenWidth() - 300);
		savegame.setY(500);
		
		engine.getRenderManager().addSprite(savegame);
		
		exit = new Sprite2D(engine, "/assets/exit.png", 8);
		exit.setID("EXIT");
		exit.setX(engine.getScreenWidth() - 300);
		exit.setY(700);
		
		engine.getRenderManager().addSprite(exit);
	}

	public void render() {

	}

}
