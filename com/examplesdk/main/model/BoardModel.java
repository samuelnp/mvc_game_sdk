package com.examplesdk.main.model;

import java.util.ArrayList;
import java.util.List;

import com.examplesdk.main.model.structures.Parcel;
import com.examplesdk.main.model.structures.ParcelStack;
import com.game.engine.ActionEvent;
import com.game.engine.BaseModel;
import com.game.engine.Engine;

public class BoardModel extends BaseModel {

	private static final long serialVersionUID = 8731859161908001213L;

	private Integer rows;
	private Integer cols;
	private List<List<ParcelStack>> boardMap;
	
	public BoardModel(Engine engine, Integer rows, Integer cols) {
		super(engine);
		this.rows = rows;
		this.cols = cols;
	}
	
	public void initialize() {
		
		boardMap = new ArrayList<List<ParcelStack>>();
		
		for(Integer i = 1; i <= rows; i++) {
			
			List<ParcelStack> stack = new ArrayList<ParcelStack>();
			
			for(Integer j = 1; j <= cols; j++) {
				
				List<Parcel> list = new ArrayList<Parcel>();
				
				for(Integer k = 1; k <= getRandomStackHeight(); k++) {

					list.add(new Parcel(k));

				}

				stack.add(new ParcelStack(list));
			}

			boardMap.add(stack);
		}
		
	}

	public void actionUpdate(ActionEvent event) {

	}	
	
	private Integer getRandomStackHeight() {
		return  (int) (Math.random() * 5) + 1;
	}

	public List<List<ParcelStack>> getBoard() {
		return boardMap;
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getCols() {
		return cols;
	}
	
	public void setBoardMap(List<List<ParcelStack>> board) {
		this.boardMap = board;
	}

}
