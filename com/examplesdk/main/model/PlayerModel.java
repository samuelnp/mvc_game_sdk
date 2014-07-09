package com.examplesdk.main.model;

import java.util.ArrayList;
import java.util.List;

import com.game.engine.ActionEvent;
import com.game.engine.BaseModel;
import com.game.engine.Engine;

public class PlayerModel extends BaseModel {

	private static final long serialVersionUID = 338343813838290371L;
	private List<PieceModel> pieces;
	private Integer totalActionPoints;
	private Integer availableActionPoints;
	
	public PlayerModel(Engine engine) {
		super(engine);
	}
	
	public void initialize() {
		pieces = new ArrayList<PieceModel>();
		totalActionPoints = 0;
		availableActionPoints = 0;
	}

	/**
	 * Listen actions to execute logic in player
	 */
	public void actionUpdate(ActionEvent event) {
		
		if(event.getActionName().equals("NEW_TURN")) {
			availableActionPoints = totalActionPoints;
		}
		
		if(event.getActionName().equals("USE_POINTS")) {
			PieceModel piece = (PieceModel) event.getEntity();
			if(availableActionPoints < piece.getMovementPoints()) {
				ActionEvent movementEvent = new ActionEvent(piece, "NO_MORE_MOVEMENTS");
				engine.getActionManager().attachActionEvent(movementEvent);	
			} else {
				availableActionPoints -= piece.getMovementPoints();
			}	
		}
	}
	
	public Integer getAvailableActionPoints() {
		return availableActionPoints;
	}
	
	public void setAvailableActionPoints(Integer actionPoints) {
		this.availableActionPoints = actionPoints;
	}
	
	public List<PieceModel> getPieces() {
		return pieces;
	}
	
	public void addPiece(PieceModel piece) {
		pieces.add(piece);
		totalActionPoints += piece.getMovementPoints();
	}
	
	public void removePiece(PieceModel piece) {
		pieces.remove(piece);
		totalActionPoints -= piece.getMovementPoints();
	}

}
