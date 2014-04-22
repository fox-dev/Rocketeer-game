package com.me.GameObjects;

import com.badlogic.gdx.math.Rectangle;


public class JetPlane extends AbstractObstacle
{
	protected Rectangle collisionRect;
	protected boolean isSpawned;
	
	public JetPlane(float x, float y, int width, int height, float scrollSpeed)
	{
		super(x, y, width, height, scrollSpeed);
		collisionRect = new Rectangle(x, y, width, height);
		velocity.x = -200;
		velocity.y = 80;
	}
	
	public void update(float delta)
	{
		position.add(velocity.cpy().scl(delta));
		
		if (position.y > 480 || position.x + width < 0)
		{
			isScrolledDown = true;
			
		}
		
		collisionRect.setPosition(position);
		
	}
	
	public Rectangle getRect(){return collisionRect;}
	
	
}
