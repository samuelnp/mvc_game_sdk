package com.game.engine;

/**
 * Interface for commons methods to implement differents Sprites (2D, 3D, etc)
 */
public interface Sprite extends Entity{

	Integer getX();
	void setX(Integer x);
	Integer getY();
	void setY(Integer y);
	Integer getWidth();
	Integer getFrameWidth();
	void setWidth(Integer width);
	Integer getHeight();
	Integer getFrameHeight();
	void setHeight(Integer height);
	String getResourceName();
	void setResourceName(String name);
	Boolean containsCoords(Integer x, Integer y);
	Boolean getVisible();
	void setVisible(Boolean visible);
	TextureShape getTexture();
	void setTexture(TextureShape texture);
	Integer getNumXFrames();
	void setNumXFrames(Integer numXFrames);
	Integer getNumYFrames();
	void setNumYFrames(Integer numYFrames);
	Integer getCurrentXFrame();
	Integer getCurrentYFrame();
	void setCurrentXFrame(Integer frame);
	void setCurrentYFrame(Integer frame);
	Integer nextRowFrame();
	Integer nextColFrame();
	void animate(FPSTimer timer);
	Integer getLayer();
	void setLayer(Integer layer);
}
