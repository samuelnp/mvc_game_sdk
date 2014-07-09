package com.examplesdk.main;

import com.game.engine.Engine;

public class Main {

    public static void main( String [] args ) {
    	
    	Engine engine = new Engine("EXAMPLE SDK", 1024, 768, 60, 10);
    	engine.initialize();
    	
    	Game game = new Game(engine);
    	game.initialize();
    	game.start();
    }
}