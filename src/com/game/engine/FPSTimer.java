package com.game.engine;

/**
 * Encapulsate Timer controls to get constant frame rate
 */
public class FPSTimer {

	private Integer fps;
	private Integer parentFps;
	private Long time;
	private Integer tickCounter;

    public FPSTimer(Integer fps) {
    	this.fps = fps;
    	time = System.nanoTime(); 
    	tickCounter = 0;
    }
    
    public FPSTimer(Integer fps, Integer parentFps) {
    	this(fps);
    	this.parentFps = parentFps;
    }

    public void tick() {
    	
    	Thread.yield();
    	
    	Long elapsed = 1000000000L / fps + time;
    	Long timeNow = System.nanoTime();
         
    	try {
    		while (elapsed > timeNow) {
    			Thread.sleep((elapsed - timeNow) / 2000000L);
    			timeNow = System.nanoTime();
            }
    	} catch (Exception e) {}
    	time = timeNow;
    	
   }
    
    public Boolean logicTick() {
    	
    	tickCounter++;
    	Boolean tick = (tickCounter == (int)(parentFps/fps)) ? true : false;
    	if(tick) tickCounter = 0;
    	
    	return tick;
    	
   }
}
