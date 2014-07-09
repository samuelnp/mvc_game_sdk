package com.game.engine;

/**
 * Custom InputEvent to encapsulate Mouse inputs 
 * Add cursor coordinates
 */
public class MouseInputEvent extends InputEvent{
	
	private Integer x;
	private Integer y;
	
	public MouseInputEvent() {
		super();
		x = 0;
		y = 0;
	}
	
	public MouseInputEvent(InputEvent inputEvent) {
		super();
		setStatus(inputEvent.getStatus());
	}
	
	public Integer getX() {
		return x;
	}
	
	public Integer getY() {
		return y;
	}
	
	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}
}
