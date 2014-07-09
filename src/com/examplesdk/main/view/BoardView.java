package com.examplesdk.main.view;

import java.util.ArrayList;
import java.util.List;

import com.examplesdk.main.model.BoardModel;
import com.examplesdk.main.model.structures.Parcel;
import com.examplesdk.main.model.structures.ParcelStack;
import com.game.engine.ActionEvent;
import com.game.engine.BaseView;
import com.game.engine.Engine;
import com.game.engine.InputEvent;
import com.game.engine.MouseInputEvent;
import com.game.engine.Sprite;
import com.game.engine.Sprite2D;

/**
 * This class read Board Model and converts logic Matrix into Sprite Matrix
 * to paint in Render
 * @author samuelnp
 *
 */
public class BoardView extends BaseView{

	private static final long serialVersionUID = -2989782202158235202L;

	private Integer left;
	private Integer top;
	
	private BoardModel boardModel;
	private List<List<List<Sprite>>> boardSprites;
	
	public BoardView(Engine engine, BoardModel boardModel) {
		super(engine);
		this.boardModel = boardModel;
		boardSprites = new ArrayList<List<List<Sprite>>>();
		left = 0;
		top = 0;
	}
	
	public void inputUpdate(InputEvent event) {
		if(event.getStatus() == "MOUSE_CLICK") {
			MouseInputEvent e = (MouseInputEvent) event;
			click(e.getX(), e.getY());
		}

	}

	public void actionUpdate(ActionEvent event) {

	}

	public void initialize() {
		
		
		for(List<ParcelStack> stackList: boardModel.getBoard()) {
			
			List<List<Sprite>> listSpriteStack = new ArrayList<List<Sprite>>();
			
			for(ParcelStack stack: stackList) {
				
				List<Sprite> spriteStack = new ArrayList<Sprite>();
				
				Integer layer = 1;
				
				for(Parcel parcel: stack.getParcelStack()) {
					
					Sprite sprite = new Sprite2D(engine, "/assets/parcel-" + parcel.getMaterial() + ".png", layer);
					
					spriteStack.add(sprite);
					layer++;
				}

				listSpriteStack.add(spriteStack);

			}
			
			boardSprites.add(listSpriteStack);
		}
		
		//add sprites to render in custom order
		for(List<List<Sprite>> listStack: boardSprites) {
			Integer elements = 1;
			List<Sprite> imparSprites = new ArrayList<Sprite>();
			List<Sprite> parSprites = new ArrayList<Sprite>();
			for(List<Sprite> stack: listStack) {
				for(Sprite sprite: stack) {
					if(elements % 2 != 0) {
						imparSprites.add(sprite);
					} else {
						parSprites.add(sprite);
					}
				}
				elements++;
			}
			
			for(Sprite sprite: imparSprites) {
				engine.getRenderManager().addSprite(sprite);
			}
			
			for(Sprite sprite: parSprites) {
				engine.getRenderManager().addSprite(sprite);
			}
		}
		
	}

	public void render() {
		//Set initial coordinates to paint
		Integer rows = 1;
		Integer cols = 1;
		
		
		for(List<List<Sprite>> stackList: boardSprites) {

			for(List<Sprite> stack: stackList) {
				
				Integer level = 0;
				
				for(Sprite sprite: stack) {

					Integer coordX = left + rows * sprite.getWidth();
					Integer coordY = top + cols * sprite.getHeight();

					coordX = (rows > 1) ? coordX - (sprite.getWidth()/4)*(rows-1) : coordX;
					coordY = (cols > 1) ? coordY - 22*(cols-1): coordY;
					coordY = (rows % 2 == 0) ? coordY + (sprite.getHeight()/2) - 11: coordY;
					coordY = (level > 0) ? coordY - 20*level : coordY;

					sprite.setX(coordX);
					sprite.setY(coordY);
					
					level++;
				}
			
				if(rows.equals(boardModel.getRows()) ) {rows = 1; cols++;}
				else rows++;

			}

		}

	}
	
	public Integer getLeft() {
		return left;
	}
	
	public Integer getTop() {
		return top;
	}
	
	public void setLeft(Integer left) {
		this.left = left;
	}
	
	public void setTop(Integer top) {
		this.top = top;
	}
	
	public List<List<List<Sprite>>> getBoard() {
		return boardSprites;
	}
	
	/**
	 * Search top layer clicked parcel sprite to move player piece
	 * @param x
	 * @param y
	 */
	public void click(Integer x, Integer y) {
		
		Integer row = 0;
		
		Sprite candidate = null;
		Integer[] coords = new Integer[2];
		
		for(List<List<Sprite>> stackList: boardSprites) {

				Integer col = 0;
				for(List<Sprite> stack: stackList) {	

						Sprite sprite = stack.get(stack.size() - 1);
						if(sprite.containsCoords(x, y)) {
							
							if(candidate == null || sprite.getLayer() > candidate.getLayer()) {
								coords[0] = row;
								coords[1] = col;
							    candidate = sprite;
							}

							break;
						}
						
						col++;
				}
					
				row++;
		}
		
		ActionEvent event = new ActionEvent(candidate, "PARCEL_CLICKED", coords );
		engine.getActionManager().attachActionEvent(event);
	}

}
