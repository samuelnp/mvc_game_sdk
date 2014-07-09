package com.game.engine;

public interface State extends Entity {
	void goNext(Context context);
	void execute();
}
