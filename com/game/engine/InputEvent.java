package com.game.engine;

/**
 * Encapsulate all input event information to be notified and processed
 */
public class InputEvent {
	
	protected String status; //Save Input Control status such as KEY_PRESSED, MOUSE_MOVED
	
	protected InputEvent() {
		status = null;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
