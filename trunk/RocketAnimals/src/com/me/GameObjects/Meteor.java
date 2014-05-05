package com.me.GameObjects;

import com.me.helpers.AssetLoader;
import com.me.helpers.Constants;

public class Meteor extends AbstractObstacle{

	private int rotateNum;
	
	public Meteor(float x, float y, int width, int height, float scrollSpeed) {
		
		super(x, y, width, height, scrollSpeed);

		rotation = 0;
		rotateNum = (int)(Math.random() * Constants.METEOR_MAX_ROTATE);
	}
	
	public Meteor(float x, float y, int width, int height, float scrollSpeed, float xSpeed, Constants.DIRECTION direction) {
		super(x, y, width, height, scrollSpeed, xSpeed, direction);
		
		rotation = 0;
		rotateNum = (int)(Math.random() * Constants.METEOR_MAX_ROTATE);
		
		hitBox.setVertices(meteor_polygon);
		hitBox.setScale((float)((double)width / AssetLoader.meteor.getRegionWidth()), (float)((double)height / AssetLoader.meteor.getRegionHeight()));
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		rotation += rotateNum;
		
	}
}
