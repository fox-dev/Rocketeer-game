package com.me.GameObjects;

import java.util.Random;

public class Meteor extends AbstractObstacle{
	
	private Random r;
	

	public Meteor(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		
	}
	
	/*Not currently needed
	@Override
	public void resetPosition(float newX, float newY){
		super.resetPosition(newX, newY);
		System.out.println("made it!");
		position.x = r.nextInt(305) - 15;
		
	}
	*/


}
