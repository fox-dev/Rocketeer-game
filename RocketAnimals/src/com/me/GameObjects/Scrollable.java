package com.me.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Scrollable {
	
	// Protected is similar to private, but allows inheritance by subclasses.
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledDown;
    protected Rectangle collisionRect; // Used for very crude collision detection
    
    private Random r;
    
    
    ////////////////////////////////////////////////////
    
    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, scrollSpeed);
        
        this.width = width;
        this.height = height;
        
        isScrolledDown = false;
        
        collisionRect = new Rectangle(x,y,width,height);
    }
    
    ////////////////////////////////////////////////////
    
    public void update(float delta){
		
		// Move the obstacle using velocity
		position.add(velocity.cpy().scl(delta));
		
		// Move the rectangle to the new position
		collisionRect.setPosition(position);
		
		// If the Scrollable object is no longer visible:
        if (position.y + height > 480 + 30) {
            isScrolledDown = true;
        }
	}
    
    /////////////////////////////////////////////////////
    
    public void resetPosition(float newX, float newY){
		position.x = newX;
		position.y = newY;
		isScrolledDown = false;
	}
    
    public void reset(float newY){
    	position.y = newY;
    	isScrolledDown = false;
    }
    
    public void setRandomX(float x){
    	position.x = x;
    }
    
 // Getter methods
 	public float getX() { return position.x; }
 	public float getY() { return position.y; }
 	public float getWidth() { return width; }
 	public float getHeight() { return height; }
 	public boolean isScrolledDown() { return isScrolledDown; }
 	public Rectangle getRect() { return collisionRect; }
 	
}
