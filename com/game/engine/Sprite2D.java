package com.game.engine;

import java.io.Serializable;


/**
 * Sprite implementation with methods to manipulate size and coordinates of graphic resources
 */
public class Sprite2D extends BaseEntity implements Sprite, Serializable{

	private static final long serialVersionUID = 2428613550720321845L;
	transient private Engine engine;
	private String resourceName;
	transient private TextureShape texture;
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private Boolean visible;
	private Integer numXFrames;
	private Integer numYFrames;
	private Integer currentXFrame;
	private Integer currentYFrame;
	private Integer layer;
	
	public Sprite2D(Engine engine, String resourceName) {
		super();
		
		this.engine = engine;
		this.resourceName = resourceName;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.layer = 1;
		this.visible = true;
		this.numXFrames = 1;
		this.numYFrames = 1;
		this.currentXFrame = 1;
		this.currentYFrame = 1;
		
	}
	
	public Sprite2D(Engine engine, String resourceName, Integer layer) {
		super();
		
		this.engine = engine;
		this.resourceName = resourceName;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.layer = layer;
		this.visible = true;
		this.numXFrames = 1;
		this.numYFrames = 1;
		this.currentXFrame = 1;
		this.currentYFrame = 1;
		
	}
	
	public Sprite2D(Engine engine, String resourceName, Integer rowFrames, Integer colFrames) {
		this(engine, resourceName);
		this.setNumXFrames(rowFrames);
		this.setNumYFrames(colFrames);
	}
	
	public Sprite2D(Engine engine, String resourceName, Integer rowFrames, Integer colFrames, Integer layer) {
		super();
		
		this.engine = engine;
		this.resourceName = resourceName;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.layer = layer;
		this.visible = true;
		this.numXFrames = 1;
		this.numYFrames = 1;
		this.currentXFrame = 1;
		this.currentYFrame = 1;
		this.setNumXFrames(rowFrames);
		this.setNumYFrames(colFrames);
		
	}
	
	/**
	 * Copy constructor to share same texture
	 * @param engine
	 * @param sprite
	 */
	public Sprite2D(Engine engine, Sprite sprite) {
		this.engine = engine;
		this.resourceName = sprite.getResourceName();
		this.x = sprite.getX();
		this.y = sprite.getY();
		this.width = 0;
		this.height = 0;
		this.engine.getRenderManager().addSprite(this);
		this.visible = true;
		this.texture = sprite.getTexture();
	}
	
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String name) {
		this.resourceName = name;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getFrameWidth() {
		return width / numXFrames;
	}
	public Integer getHeight() {
		return height;
	}
	public Integer getFrameHeight() {
		return height / numYFrames;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}

	public Boolean containsCoords(Integer x, Integer y) {
		Boolean coordX = x > getX() && x < getX() + getFrameWidth();
		Boolean coordY = y > getY() && y < getY() + getFrameHeight();
		return (coordX && coordY);
	}

	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public TextureShape getTexture() {
		return texture;
	}

	public void setTexture(TextureShape texture) {
		this.texture = texture;
	}

	public Integer getNumXFrames() {
		return numXFrames;
	}

	public void setNumXFrames(Integer numXFrames) {
		this.numXFrames = numXFrames;
	}

	public Integer getNumYFrames() {
		return numYFrames;
	}

	public void setNumYFrames(Integer numYFrames) {
		this.numYFrames = numYFrames;
	}

	public Integer getCurrentXFrame() {
		return currentXFrame;
	}

	public void setCurrentXFrame(Integer frame) {
		this.currentXFrame = frame;
	}
	
	public Integer getCurrentYFrame() {
		return currentYFrame;
	}

	public void setCurrentYFrame(Integer frame) {
		this.currentYFrame = frame;
	}
	
	public Integer nextRowFrame() {
		if(this.currentXFrame >= numXFrames) this.currentXFrame = 1;
		else this.currentXFrame++;
		return this.currentXFrame;
	}
	
	public Integer nextColFrame() {
		if(this.currentYFrame >= numYFrames) this.currentYFrame = 1;
		else this.currentYFrame++;
		return this.currentYFrame;
	}
	
	public void animate(FPSTimer timer) {
		if(timer.logicTick()) {
		
			if(currentXFrame == numXFrames) {
				nextRowFrame();
				nextColFrame();
			} else {
				nextRowFrame();
			}
		}
	}
	
	public Integer getLayer() {
		return layer;
	}
	
	public void setLayer(Integer layer) {
		this.layer = layer;
		engine.getRenderManager().moveToLayer(this, layer);
	}

}
