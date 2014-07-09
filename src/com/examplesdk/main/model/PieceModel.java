package com.examplesdk.main.model;

import com.game.engine.ActionEvent;
import com.game.engine.BaseModel;
import com.game.engine.Engine;

public class PieceModel extends BaseModel {

	private static final long serialVersionUID = -3364755443936460271L;
	private Integer movementPoints;
	
	public PieceModel(Engine engine) {
		super(engine);
	}
	
	public void initialize() {
		movementPoints = 0;
	}

	public void actionUpdate(ActionEvent event) {

	}
	
	public Integer getMovementPoints() {
		return movementPoints;
	}

	public void setMovementPoints(Integer points) {
		this.movementPoints = points;
	}
}
