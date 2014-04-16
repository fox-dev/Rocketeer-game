package com.me.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

public class Meteor extends AbstractObstacle{
	
	// protected Rectangle collisionRect;

	public Meteor(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		
		//collisionRect = new Rectangle(x,y,width,height);
		rotation = 0;
		
	}
	
	/*Not currently needed
	@Override
	public void resetPosition(float newX, float newY){
		super.resetPosition(newX, newY);
		System.out.println("made it!");
		position.x = r.nextInt(305) - 15;
		
	}
	*/
	
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		rotation += 5;
		//collisionRect.setPosition(position);
		
		
	}
	
	// public Rectangle getRect() { return collisionRect; }
	


}
