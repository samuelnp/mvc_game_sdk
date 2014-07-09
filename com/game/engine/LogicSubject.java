package com.game.engine;

public interface LogicSubject {
	void attachObserver(LogicObserver o);
	void detachObserver(LogicObserver o);
	void notifyObservers();
}
