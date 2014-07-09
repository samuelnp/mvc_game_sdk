package com.examplesdk.main.view;

import java.util.List;

import com.examplesdk.main.model.PlayerModel;
import com.game.engine.ActionEvent;
import com.game.engine.BaseView;
import com.game.engine.Engine;
import com.game.engine.InputEvent;
import com.game.engine.MouseInputEvent;
import com.game.engine.Sprite;
import com.game.engine.Sprite2D;

/**
 * This class execute piece input and output logic
 * @author samuelnp
 *
 */
public class PieceView extends BaseView {

	private static final long serialVersionUID = -6782977379856508830L;
	private Sprite sprite;
	private BoardView board;
	private PlayerModel player;
	private Integer typePiece;
	private Integer row;
	private Integer col;
	
	public PieceView(Engine engine, BoardView board, PlayerModel player, Integer typePiece) {
		super(engine);
		sprite = null;
		this.board = board;
		this.player = player;
		this.typePiece = typePiece;
		this.row = 0;
		this.col = 0;
		
	}
	
	/**
	 * Listen input mouse or keyboard events to execute custom actions
	 */
	public void inputUpdate(InputEvent event) {
		if (event.getClass().equals(MouseInputEvent.class)) {
			MouseInputEvent e = (MouseInputEvent) event;
			
			if(event.getStatus() == "MOUSE_CLICK") {
				if(sprite.containsCoords(e.getX(), e.getY())) {
					Integer[] coords = new Integer[2];
					coords[0] = e.getX();
					coords[1] = e.getY();
					ActionEvent spriteClicked = new ActionEvent(this, "PIECE_CLICKED", coords);
					engine.getActionManager().attachActionEvent(spriteClicked);
				}
			}
		}
	}

	public void actionUpdate(ActionEvent event) {

	}

	public void initialize() {
		Integer numPiece = player.getPieces().size() + 1;
		sprite = new Sprite2D(engine, "/assets/piece" + typePiece + ".png", 2, 1, 6);
		sprite.setID("PIECE_"+ numPiece + "_" + player.getID());
		
		engine.getRenderManager().addSprite(sprite);
	}

	/**
	 * This function update sprite positions every game frame.
	 */
	public void render() {
		List<Sprite> stack = board.getBoard().get(row).get(col);
		Integer lastParcelPosition = stack.size() - 1;
		Sprite parcel = stack.get(lastParcelPosition);
		
		sprite.setX(parcel.getX() + 10);
		sprite.setY(parcel.getY() - 80);
	}
	
	public void move(Integer row, Integer col) {
		List<Sprite> stack = board.getBoard().get(row).get(col);
		Integer lastParcelPosition = stack.size() - 1;
		Sprite parcel = stack.get(lastParcelPosition);
		sprite.setLayer(parcel.getLayer());
		this.row = row;
		this.col = col;
	}
	
	public void focus(Boolean focus) {
		if(focus) sprite.setCurrentXFrame(0);
		else sprite.setCurrentXFrame(1);
	}

}
