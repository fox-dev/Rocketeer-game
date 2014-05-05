package com.me.GameObjects;

import com.me.helpers.AssetLoader;
import com.me.helpers.Constants.DIRECTION;


public class JetPlane extends AbstractObstacle
{
	protected boolean isSpawned;
	
	public JetPlane(float x, float y, int width, int height, float scrollSpeed, float glideSpeed, DIRECTION direction) 
	{
		super(x, y, width, height, scrollSpeed, glideSpeed, direction);
		
		polygonRightVertices = jetPlane_polygon_right;
		polygonLeftVertices = jetPlane_polygon_left;
		
		// Face the hitbox according to direction
		if (direction == DIRECTION.DOWN_LEFT) {
			hitBox.setVertices(jetPlane_polygon_left);
		}
		else {
			hitBox.setVertices(jetPlane_polygon_right);
		}

		
		hitBox.setScale((float)((double)width / AssetLoader.jetPlane.getRegionWidth()), (float)((double)height / AssetLoader.jetPlane.getRegionHeight()));
	}
	
	public void update(float delta)
	{
		super.update(delta);
	}
	
}
