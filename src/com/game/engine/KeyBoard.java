package com.game.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Extends AWT Keyboard Event Listener and encapsulate events in custom
 * input events to notify other input listeners
 * This is a producer class
 */
public class KeyBoard implements InputObserver, KeyListener{

	InputManager subject;
	
	public KeyBoard(InputManager subject) {
		this.subject = subject;
		this.subject.attachObserver(this);
	}
	
	public void keyPressed(KeyEvent e) {
		KeyboardInputEvent event = new KeyboardInputEvent();
		event.setStatus("KEY_PRESSED");
		event.setKey(e.getKeyChar());

		subject.attachInputEvent(event);
	}

	public void keyReleased(KeyEvent e) {
		KeyboardInputEvent event = new KeyboardInputEvent();
		event.setStatus("KEY_RELEASED");
		event.setKey(e.getKeyChar());
		
		subject.attachInputEvent(event);
	}

	public void keyTyped(KeyEvent e) {
		KeyboardInputEvent event = new KeyboardInputEvent();
		event.setStatus("KEY_TYPED");
		event.setKey(e.getKeyChar());
		
		subject.attachInputEvent(event);
	}

	/**
	 * This method don't have functionality in this observer
	 */
	public void inputUpdate(InputEvent event) {}

}
