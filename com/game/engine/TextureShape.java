package com.game.engine;

import java.io.IOException;
import java.io.InputStream;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

/**
 * Class that load graphic file into a GL Texture 
 */
public class TextureShape {

	private Texture texture;
	private GLAutoDrawable drawable;
	private GL2 gl2;
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private Integer screenWidth;
	private Integer screenHeight;
	private Sprite sprite;
	
	public TextureShape(GLAutoDrawable drawable, String filename) {
		setX(0);
		setY(0);
		this.drawable = drawable;
		gl2 = drawable.getGL().getGL2();
		setScreenWidth(0);
		screenHeight = 0;
		sprite = null;

	}
	
	public TextureShape(GLAutoDrawable drawable, String filename, Integer x, Integer y, Integer screenWidth, Integer screenHeight, Sprite sprite) {
		this(drawable, filename);
		this.x = x;
		this.y = y;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.setSprite(sprite);
		
		try {
			//Load graphic file into a Data Stream and generate Texture
			InputStream stream = getClass().getResourceAsStream(filename);
			TextureData data = TextureIO.newTextureData(drawable.getGLProfile(), stream, false, filename.substring(filename.length() - 4));
	        texture = TextureIO.newTexture(data);
	        
	        //set sprite width & height
	        this.width = texture.getWidth();
	        this.height = texture.getHeight();
	        this.sprite.setWidth(width);
	        this.sprite.setHeight(height);

		} catch (IOException e) {
			System.err.println("Error trying to load texture file '" + filename + "'");
			e.printStackTrace();
		}
		
	}
	
	public TextureShape(GLAutoDrawable drawable, TextureShape texture, Sprite sprite) {
		
		this(drawable, texture.getSprite().getResourceName());
		this.x = sprite.getX();
		this.y = sprite.getY();
		this.width = texture.getWidth();
		this.height = texture.getHeight();
		this.screenWidth = texture.getScreenWidth();
		this.screenHeight = texture.getScreenHeight();
		this.texture = texture.getTexture();
		this.sprite = sprite;
		this.sprite.setWidth(texture.getWidth());
		this.sprite.setHeight(texture.getHeight());
	}
	
	public void paint() {
		try {
		// set the texture parameters to allow for proper displaying
        texture.setTexParameteri(drawable.getGL(), GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
        texture.setTexParameteri(drawable.getGL(), GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        texture.setTexParameteri(drawable.getGL(), GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
        texture.setTexParameteri(drawable.getGL(), GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        
        //Attach texture to OpenGL
        texture.bind(drawable.getGL());
        //Define multiply function for blending
        gl2.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
        
	    gl2.glEnable(GL2.GL_BLEND); 
		gl2.glBlendFunc(GL2.GL_ONE, GL2.GL_ONE_MINUS_SRC_ALPHA); 
		
		// enable 2D textures
        gl2.glEnable(GL2.GL_TEXTURE_2D);
		//the gl draw mode
		gl2.glBegin(GL2.GL_QUADS);

		Integer frameX = sprite.getCurrentXFrame();
		Integer frameY = sprite.getCurrentYFrame();
		
		//Calculate coeficients for multiple frames in sprite to do animations
		float coefX1 = ((float) (frameX - 1) / sprite.getNumXFrames());
		float coefX2 = ((float) frameX / sprite.getNumXFrames());
		float coefY1 = ((float) (frameY - 1) / sprite.getNumYFrames());
		float coefY2 = ((float) frameY / sprite.getNumYFrames());
		float coefWidth = ((float) getWidth() / sprite.getNumXFrames());
		float coefHeight = ((float) getHeight() / sprite.getNumYFrames());

		//Bind texture vertex coord to quad vertex
		gl2.glTexCoord2f(coefX1, coefY2);
		gl2.glVertex2f(sprite.getX(), screenHeight - (sprite.getY() + coefHeight));
		 
		gl2.glTexCoord2f(coefX2, coefY2);
		gl2.glVertex2f(sprite.getX()  + coefWidth, screenHeight - (sprite.getY() + coefHeight));
		
		gl2.glTexCoord2f(coefX2, coefY1);
		gl2.glVertex2f(sprite.getX() + coefWidth, screenHeight - sprite.getY());
		 
		gl2.glTexCoord2f(coefX1, coefY1);
		gl2.glVertex2f(sprite.getX(), screenHeight - sprite.getY());
		 
		gl2.glEnd();
		 
		//show the back buffer if double buffering was set
		gl2.glFlush();
		
		}catch(Exception e) {
			System.err.println("Something go wrong while painting texture. See trace for more information. Perhaps you have tried to resize windows and this function isn't implemented yet");
		}
	}
	
	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Integer getScreenWidth() {
		return screenWidth;
	}
	
	public Integer getScreenHeight() {
		return screenHeight;
	}

	public void setScreenWidth(Integer screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	public void setScreenHeight(Integer screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	protected Texture getTexture() {
		return texture;
	}
}
