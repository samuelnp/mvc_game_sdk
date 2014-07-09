package com.game.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements Subject from Observer Pattern to manage execution of concrete game logic components
 */
public class LogicManager implements Manager, LogicSubject{
	
	private List<LogicObserver> observers;
	private List<LogicObserver> auxObservers;
	
	public LogicManager() {
		observers = new ArrayList<LogicObserver>();
		auxObservers = new ArrayList<LogicObserver>();
	}

	public void attachObserver(LogicObserver o) {
		this.observers.add(o);
	}

	public void detachObserver(LogicObserver o) {
		this.observers.remove(o);
	}

	public void notifyObservers() {
				
		synchronizeObservers();
		
		if(!this.observers.isEmpty()) {
			for(LogicObserver o: this.observers) {
				o.logicUpdate();
			}
		}

	}
	
	public void detachAll() {
		this.observers.clear();
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
