package com.me.GameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Rocket {

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private float rotation; // Used to rotate this bird
	private int width;
	private int height;
	
	protected Rectangle collisionRect; // Used for very crude collision detection
	
	
	//Used to stop the rocket when it reaches the finger/mouse
	private int stopper;
	
	
	public Rocket(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		
		collisionRect = new Rectangle(x, y, width, height);
	}
	
	// Update method
	public void update(float delta) {
		
		// Move the rocket using velocity
		position.add(velocity.cpy().scl(delta));
		
		// Move the rectangle to the rocket's new position
		collisionRect.setPosition(position);
		
		
		//check if rocket has reached the finger/mouse's location.
		if( (int) getMiddleX() == stopper){
	
			velocity.x = 0;
		}
		
	}
	
	//Input Control Methods to track user's current X location
	public void userAtX(int userLocationX){
		this.stopper = userLocationX;
	}
	
	// Control methods
	public void onLeft() {
		velocity.x -= 50;
		if(velocity.x < -50){
			velocity.x = -50 ;
		}
	}
	public void onRight() {
		velocity.x += 50;
		if(velocity.x > 50){
			velocity.x = 50 ;
		}
	}
	public void onNoClick() {velocity.x = 0; }
	
	// Getter methods
	public float getX() { return position.x; }
	public float getY() { return position.y; }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	public float getMiddleX() { return (position.x + (width / 2)); }
	public float getMiddleY() { return (position.x + (height / 2)); }
	public boolean isMoving() { return (velocity.x != 0); }
	public Rectangle getRect() { return collisionRect; }
	
}
