package com.me.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.helpers.Constants;

public class ParaTroop extends AbstractObstacle
{
	private final int maxXSpeed = 200 , minXSpeed = -200, maxYSpeed = 250, minYSpeed = 150;
	private float xMax, xMin;
	private Random r = new Random();
	
	public ParaTroop()
	{
		position = new Vector2(r.nextInt(305),-30);
		velocity = new Vector2(randInt(minXSpeed, maxXSpeed), randInt(minYSpeed, maxYSpeed));
	
		width = 30;
		height = 30;
	
		isScrolledDown = false;
	
		collisionRect = new Rectangle(position.x,position.y,width,height);
	
		rotation = 0;
		
		if(velocity.x > 0)
		{
			scrollDirection = Constants.DIRECTION.DOWN_RIGHT;
		}
		else if(velocity.x < 0)
		{
			scrollDirection = Constants.DIRECTION.DOWN_LEFT;
		}
		else
		{
			scrollDirection = Constants.DIRECTION.DOWN;
		}
		
		if(position.x > (Constants.TRUE_WIDTH - position.x))
		{
			xMax = Constants.TRUE_WIDTH - position.x; 
		}
		else
		{
			xMax = position.x;
		}
		xMin = position.x - xMax;
		xMax = position.x + xMax;
	}
	
	public ParaTroop(float x, float y, int width, int height, float scrollSpeed)
	{
		super(x, y, width, height, scrollSpeed);
		if(x > (Constants.TRUE_WIDTH - x))
		{
			xMax = Constants.TRUE_WIDTH - x; 
		}
		else
		{
			xMax = x;
		}
		xMin = x - xMax;
	}
	
	public ParaTroop(float x, float y, int width, int height, float scrollSpeed, float glideSpeed, Constants.DIRECTION direction) 
	{
		super(x, y, width, height, scrollSpeed, glideSpeed, direction);
		if(x > (Constants.TRUE_WIDTH - x))
		{
			xMax = Constants.TRUE_WIDTH - x; 
		}
		else
		{
			xMax = x;
		}
		xMin = x - xMax;
	}
	
	public void update(float delta)
	{
		// Move the obstacle using velocity
		position.add(velocity.cpy().scl(delta));
				
		// Move the rectangle to the new position
		collisionRect.setPosition(position);
		
		if (position.x + width >= xMax)
		{
			setDirection(Constants.DIRECTION.DOWN_LEFT);
			System.out.println("im moving left!");
		}
		else if(position.x <= xMin)
		{
			setDirection(Constants.DIRECTION.DOWN_RIGHT);
			System.out.println("im movin right!");
		}
		
		// If the Scrollable object is no longer visible:
		if (((position.y + height) > 480 + height) || (((position.x + width < 0) || position.x - width > Constants.TRUE_WIDTH))) 
		{
			isScrolledDown = true;
		}
	}
	
	public int randInt(int min, int max) 
	{
	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    return rand.nextInt((max - min) + 1) + min;
	}
	
}
