package com.game.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements Subject from Observer Pattern to manage execution of system and player actions 
 */
public class ActionManager implements Manager, ActionSubject{

	private List<ActionObserver> observers;
	private List<ActionObserver> auxObservers;
	private List<ActionEvent> actionEvents;
	
	public ActionManager() {
		observers = new ArrayList<ActionObserver>();
		auxObservers = new ArrayList<ActionObserver>();
		actionEvents = new ArrayList<ActionEvent>();
		
	}

	public void attachObserver(ActionObserver o) {
		this.auxObservers.add(o);
	}
	
	public void attachActionEvent(ActionEvent actionEvent) {
		this.actionEvents.add(actionEvent);
	}


	public void detachObserver(ActionObserver o) {
		this.observers.remove(o);
	}


	/**
	 * Notify all Action Observers with actions queued
	 */
	public void notifyObservers() {
		
		synchronizeObservers();
		
		if(!this.actionEvents.isEmpty()) {

			ActionEvent e = this.actionEvents.remove(0);
			
			if(!this.observers.isEmpty()) {
				for(ActionObserver o: this.observers) {
					o.actionUpdate(e);
				}
			}
			
		}
	}
	
	/**
	 * Left empty all lists
	 */
	public void detachAll() {
		this.observers.clear();
		this.actionEvents.clear();
	}

	public void update() {
		this.notifyObservers();
	}
	
	/**
	 * Synchronize Observers list to avoid ConcurrentCoModification exceptions
	 */
	private void synchronizeObservers() {
		observers.addAll(auxObservers);
		auxObservers.clear();
	}

}
