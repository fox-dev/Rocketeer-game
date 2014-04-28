package com.me.GameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.me.helpers.Constants;


public class JetPlane extends AbstractObstacle
{
	protected boolean isSpawned;
	
	public JetPlane(float x, float y, int width, int height, float scrollSpeed)
	{
		super(x, y, width, height, scrollSpeed);
		// collisionRect = new Rectangle(x, y, width, height);
		velocity.x = -200;
		// velocity.y = 80;
	}
	
	public JetPlane(float x, float y, int width, int height, float scrollSpeed, float glideSpeed, Constants.DIRECTION direction) 
	{
		super(x, y, width, height, scrollSpeed, glideSpeed, direction);
		
	}
	
	public void update(float delta)
	{
		super.update(delta);

	}
	
}
