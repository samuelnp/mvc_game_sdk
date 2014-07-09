package com.game.engine;

public interface InputSubject {
	void attachObserver(InputObserver o);
	void detachObserver(InputObserver o);
	void notifyObservers();
}
