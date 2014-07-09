package com.game.engine;

/**
 * Custom InputEvent to encapsulate Keyboard inputs 
 * Add keyboard key activated
 */
public class KeyboardInputEvent extends InputEvent{

	private String key;
	
	public KeyboardInputEvent() {
		super();
		key = null;
	}
	
	public KeyboardInputEvent(InputEvent inputEvent) {
		super();
		setStatus(inputEvent.getStatus());
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = new StringBuffer().append(key).toString();
	}
	
}
