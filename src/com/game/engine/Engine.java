package com.game.engine;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.JFrame;


/**
 * Engine class manage game life cycle. Control all managers to be executed 
 * in stepped main loop at defined FPS. Uses GLJPanel from AWT for OpenGL render layer. 
 */
public class Engine {
        private GLProfile glprofile;
        private GLCapabilities glcapabilities;
		private GLJPanel gljpanel;
		private JFrame jframe;
		private GL2 gl;
		
		private Context context;
		private State currentState;
		
		private RenderManager renderManager;
		private InputManager inputManager;
		private ActionManager actionManager;
		private LogicManager logicManager;
		private PersistenceManager persistenceManager;
		private ConfigManager configManager;
		private FPSTimer timer;

		private String windowName;
		private Integer screenWidth;
		private Integer screenHeight;
		private Integer fps;
		private Integer numLayers;
		
		private Boolean isRunning;
		
		private String persistencePath;

		public Engine(String windowName, Integer screenWidth, Integer screenHeight, Integer fps, Integer numLayers){
			this.windowName = windowName;
			this.screenWidth = screenWidth;
			this.screenHeight = screenHeight;
			this.isRunning = true;
			this.fps = fps;
			this.numLayers = numLayers;
			this.context = null;
			this.currentState = null;
			this.persistencePath = "persistence";
		}

		public void initialize(){
			
			//Configure GL Options
			glprofile = GLProfile.getDefault();
	        glcapabilities = new GLCapabilities( glprofile );
	        //Set Depth Bits for each color channel
	        glcapabilities.setRedBits(8);
	        glcapabilities.setBlueBits(8);
	        glcapabilities.setGreenBits(8);
	        glcapabilities.setAlphaBits(8);
	        //Create GL Canvas Panel
	        gljpanel = new GLJPanel( glcapabilities ); 
	        
	        //Create Render Instance with Screen x/y size to revert coordinates in OGL
	        renderManager = new RenderManager(getScreenWidth(), getScreenHeight(), numLayers);
	        
	        //Add Render to GL Canvas
	        gljpanel.addGLEventListener(renderManager);

	        //Set 60FPS in game
	        timer = new FPSTimer(fps);
	        
	        //Create new Window
	        jframe = new JFrame(windowName); 
	        //Configure JFrame to close on Quit Window Event
	        jframe.addWindowListener( new WindowAdapter() {
	        	//Window Close Event Listener to close application
	            public void windowClosing( WindowEvent windowevent ) {
	                jframe.dispose();
	                isRunning = false;
	                System.exit( 0 );
	            }
	        });

	        //Add GL Canvas panel to JFrame and configure window params
	        jframe.getContentPane().add( gljpanel, BorderLayout.CENTER );
	        jframe.setSize( getScreenWidth(), getScreenHeight() );
	        jframe.setVisible( true );
	        
	        //Load Input Manager
	        inputManager = new InputManager();
	        
	        //Bind KeyBoard Input Listener 
	        KeyBoard keyboard = new KeyBoard(inputManager);
	        jframe.addKeyListener(keyboard);
	        
	        //Bind Mouse Input Listener
	        Mouse mouse = new Mouse(inputManager);
	        jframe.addMouseListener((MouseListener) mouse);
	        jframe.addMouseMotionListener((MouseMotionListener) mouse);
	        
	        //Load Action Manager
	        actionManager = new ActionManager();
	        //Load Logic Manager
	        logicManager = new LogicManager();
	        //Load Persistence Manager
	        persistenceManager = new PersistenceManager(persistencePath);
	        //Load Config Manager
	        configManager = new ConfigManager();
	       
		}
	
		/**
		 * Control update operation from Manager attached to Engine
		 */
		public void update(){
			inputManager.update();
			actionManager.update();
			logicManager.update();
			gljpanel.display();
		}
	
		/**
		 * Main loop execute iterations at defined frames per second. Control context changes and update process
		 */
		public void loop(){
			while(isRunning) {
				//Check if state have changed in context and prepare Engine to reinitialize managers queues
				contextControl();
				//Execute all update operations in appended managers
				update();
				//Sleep every fps milliseconds
				timer.tick();
			}
		}

		public RenderManager getRenderManager() {
			return renderManager;
		}

		public InputManager getInputManager() {
			return inputManager;
		}

		public ActionManager getActionManager() {
			return actionManager;
		}
		
		public LogicManager getLogicManager() {
			return logicManager;
		}
		
		public PersistenceManager getPersistenceManager() {
			return persistenceManager;
		}
		
		public ConfigManager getConfigManager() {
			return configManager;
		}

		public Context getContext() {
			return context;
		}

		/**
		 * addContext gets current context appended to Engine and execute it
		 * @param context
		 */
		public void addContext(Context context) {
			this.context = context;
			this.currentState = context.getCurrent();
			this.context.execute();
		}
		
		/**
		 * Check if context has changed and reset manager process queues
		 */
		public void contextControl() {
			if (context != null && !context.getCurrent().equals(currentState)) {
				inputManager.detachAll();
				actionManager.detachAll();
				logicManager.detachAll();
				renderManager.detachAll();
				context.execute();
				currentState = context.getCurrent();
			}
		}

		public GL2 getGl() {
			return gl;
		}

		public void setGl(GL2 gl) {
			this.gl = gl;
		}

		public Integer getScreenHeight() {
			return screenHeight;
		}

		public Integer getScreenWidth() {
			return screenWidth;
		}
		
}
