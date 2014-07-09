package com.game.engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.game.engine.MouseInputEvent;

/**
 * Extends AWT Mouse Event Listener and encapsulate events in custom
 * input events to notify other input listeners
 * This is a producer class
 */
public class Mouse implements InputObserver, MouseListener, MouseMotionListener {

	private InputManager subject;
	
	public Mouse(InputManager subject) {
		this.subject = subject;
		this.subject.attachObserver(this);
	}
	

	public void mouseClicked(MouseEvent e) {
		MouseInputEvent event = new MouseInputEvent();
		event.setStatus("MOUSE_CLICK");
		event.setX(e.getX());
		event.setY(e.getY());
		
		subject.attachInputEvent(event);
	}

	public void mouseEntered(MouseEvent e) {
		MouseInputEvent event = new MouseInputEvent();
		event.setStatus("MOUSE_ENTERED");
		event.setX(e.getX());
		event.setY(e.getY());
		
		subject.attachInputEvent(event);
	}

	public void mouseExited(MouseEvent e) {
		MouseInputEvent event = new MouseInputEvent();
		event.setStatus("MOUSE_EXITED");
		event.setX(e.getX());
		event.setY(e.getY());
		
		subject.attachInputEvent(event);
	}

	public void mousePressed(MouseEvent e) {
		MouseInputEvent event = new MouseInputEvent();
		event.setStatus("MOUSE_PRESSED");
		event.setX(e.getX());
		event.setY(e.getY());
		
		subject.attachInputEvent(event);
	}

	public void mouseReleased(MouseEvent e) {
		MouseInputEvent event = new MouseInputEvent();
		event.setStatus("MOUSE_RELEASED");
		event.setX(e.getX());
		event.setY(e.getY());
		
		subject.attachInputEvent(event);
	}

	public void mouseDragged(MouseEvent e) {
		MouseInputEvent event = new MouseInputEvent();
		event.setStatus("MOUSE_DRAGGED");
		event.setX(e.getX());
		event.setY(e.getY());
		
		subject.attachInputEvent(event);
	}

	public void mouseMoved(MouseEvent e) {
		MouseInputEvent event = new MouseInputEvent();
		event.setStatus("MOUSE_MOVED");
		event.setX(e.getX());
		event.setY(e.getY());
		
		subject.attachInputEvent(event);
	}

	public void inputUpdate(InputEvent e) {}

}
