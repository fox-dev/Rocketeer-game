package com.me.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class AbstractObstacle extends Scrollable{
	

	
	////////////////////////////////////////////
	// When AbstractObstacle's constructor is invoked, invoke the super (Scrollable)
    // constructor
	public AbstractObstacle(float x, float y, int width, int height, float scrollSpeed){
		super(x, y, width, height, scrollSpeed);
	}
	
	
}
