package com.me.GameObjects;

import com.badlogic.gdx.math.Rectangle;

public class JetPlane extends AbstractObstacle
{
	protected Rectangle collisionRect;
	
	public JetPlane(float x, float y, int width, int height, float scrollSpeed)
	{
		super(x, y, width, height, scrollSpeed);
		collisionRect = new Rectangle(x, y, width, height);
	}
	
	public void update(float delta)
	{
		position.add(velocity.cpy().scl(delta));
		
		if (position.y > 480 || position.x < 0)
		{
			isScrolledDown = true;
		}
		
		collisionRect.setPosition(position);
		
	}
	
	public Rectangle getRect(){return collisionRect;}
	
	
}
