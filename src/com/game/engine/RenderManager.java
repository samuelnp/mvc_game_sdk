package com.game.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * RenderManager define OpenGL control GL operations to render Graphics
 * Implements GLEventListener
 */
public class RenderManager implements GLEventListener{
    private GL2 gl;
    private GLAutoDrawable drawable; //Drawable context passed from GLJpanel
	private List<List<Sprite>> sprites;
	private Integer screenWidth;
	private Integer screenHeight;
	private Map<String, TextureShape> textureMap;
	private Integer layers;
	public RenderManager() {
		this.sprites = new ArrayList<List<Sprite>>();
		this.screenWidth = 0;
		this.screenHeight = 0;
		this.drawable = null;
		this.setTextureMap(new HashMap<String, TextureShape>());
	}
	
	public RenderManager(Integer screenWidth, Integer screenHeight, Integer numLayers) {
		this();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.layers = numLayers;
		if(numLayers > 0) {
			for(int i = 0; i < layers; i++) {
				List<Sprite> layer = new ArrayList<Sprite>();
				this.sprites.add(layer);
			}
		} else {
			System.err.println("System needs almost one layer.");
			System.exit(1);
		}
	}

	/**
	 * GLJpanel call init al object creation. Initialize OpenGL State Machine
	 */
	public void init(GLAutoDrawable drawable) {
		
		if(drawable != null) {
			if(this.drawable == null) this.drawable = drawable;
			gl = drawable.getGL().getGL2();
			
			//Change canvas to Proyection mode to define view
			gl.glMatrixMode( GL2.GL_PROJECTION );
			//Initialize canvas with identity matrix
	        gl.glLoadIdentity();
	
	        //GLUtilities to configure Orthographic Proyection to represent 2D images
	        GLU glu = new GLU();
	        glu.gluOrtho2D( 0.0f, drawable.getWidth(), 0.0f, drawable.getHeight() );
	
	        //Change canvas to draw operations
	        gl.glMatrixMode( GL2.GL_MODELVIEW );
	        //Initialize canvas with identity matrix
	        gl.glLoadIdentity();
	        //Configure Viewport size
	        gl.glViewport( 0, 0, drawable.getWidth(), drawable.getHeight() );
	
	        //Set canvas color to black
	        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		} else {
			System.err.println("Drawable can't be null. Engine need OpenGL Context.");
			System.exit(1);
		}
        
	}

	/**
	 * Load all initialized sprites and append GL Texture to each
	 */
	public void load() {
		//load all textures defined from sprites from each layer
		if(!this.sprites.isEmpty()) {
        for(List<Sprite> layer: this.sprites) {
			for(Sprite sprite: layer) {
	        	
	        	if(sprite.getTexture() == null && drawable != null) {
	        		if(sprite.getResourceName() != null) {
	        		TextureShape texture = new TextureShape(drawable, sprite.getResourceName(), sprite.getX(),sprite.getY(), screenWidth, screenHeight, sprite);
	        		
		        	sprite.setTexture(texture);
		        	sprite.setWidth(texture.getWidth());
		        	sprite.setHeight(texture.getHeight());
	        		} else {
	        			System.err.println("Resource Name can't be null. Aborting execution");
	        			System.exit(1);
	        		}
	        	}
	        }
        }
		} else {
			System.err.println("Render needs sprites to render something");
			System.exit(1);
		}
	}
	
	/**
	 * This method is used when game is going to exit
	 */
	public void dispose(GLAutoDrawable drawable) {
		
	}

	/**
	 * Execute paint operations for each activated sprite
	 */
	public void display(GLAutoDrawable drawable) {
		//call to load textures if have not been setted earlier
		load();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		if(!this.sprites.isEmpty()) {
			for(List<Sprite> layer : this.sprites) {
				for(Sprite s: layer) {
					
					if(s.getVisible() && s.getTexture() != null) {
						//Layer 0 is used to preload
						if(s.getLayer() > 0) s.getTexture().paint();
					}
				}
			}
		} else {
			System.err.println("Render needs sprites to render something");
			System.exit(1);
		}
		
		gl.glFlush();
	}

	/**
	 * This method is executed when game window is resized
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		gl.glViewport(0, 0, width, height);
	}

	public void addSprite(Sprite sprite) {
		Integer layer = sprite.getLayer();
		this.sprites.get(layer).add(sprite);
	}
	
	public void removeSprite(Sprite sprite) {
		Integer layer = sprite.getLayer();
		this.sprites.get(layer).remove(sprite);
		
	}

	public void detachAll() {
		//this.sprites.clear();
		for(List<Sprite> layer: this.sprites) {
			layer.clear();
		}
	}
	
	public Integer getLayers() {
		return layers;
	}
	
	public void setLayers(Integer layers) {
		this.layers = layers;
	}
	
	public void moveToLayer(Sprite sprite, Integer layer) {
		Integer oldLayer = sprite.getLayer();
		this.sprites.get(oldLayer).remove(sprite);
		this.sprites.get(layer).add(sprite);
	}

	public Map<String, TextureShape> getTextureMap() {
		return textureMap;
	}

	public void setTextureMap(Map<String, TextureShape> textureMap) {
		this.textureMap = textureMap;
	}

	/** 
	 * @uml.property name="engine"
	 * @uml.associationEnd inverse="renderManager:com.game.engine.Engine"
	 */
	private Engine engine;
	/** 
	 * Getter of the property <tt>engine</tt>
	 * @return  Returns the engine.
	 * @uml.property  name="engine"
	 */
	public Engine getEngine() {
		return engine;
	}

	/** 
	 * Setter of the property <tt>engine</tt>
	 * @param engine  The engine to set.
	 * @uml.property  name="engine"
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
}
