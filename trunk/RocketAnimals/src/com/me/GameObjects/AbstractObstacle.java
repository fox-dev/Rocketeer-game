package com.me.GameObjects;

import com.me.helpers.Constants;

public class AbstractObstacle extends Scrollable{
	
	protected Constants.DIRECTION scrollDirection;
	protected float tempXSpeed, tempYSpeed; // Place holders just in case we change direction
	
	////////////////////////////////////////////
	// When AbstractObstacle's constructor is invoked, invoke the super (Scrollable)
    // constructor
	public AbstractObstacle(float x, float y, int width, int height, float ySpeed){
		super(x, y, width, height, ySpeed);

		scrollDirection = Constants.DIRECTION.DOWN;
		setDirection(scrollDirection);
	}
	
	public AbstractObstacle(float x, float y, int width, int height, float xSpeed, float ySpeed){
		super(x, y, width, height, xSpeed, ySpeed);

		//scrollDirection = Constants.DIRECTION.DOWN;
		//setDirection(scrollDirection);
	}
	
	public AbstractObstacle(float x, float y, int width, int height, float ySpeed, float xSpeed, Constants.DIRECTION direction) {
		super(x, y, width, height, xSpeed, ySpeed);
		
		scrollDirection = direction;
		setDirection(scrollDirection);
	}
	
	@Override
	public void update(float delta) 
	{
		super.update(delta);
	}
	
	public void setDirection(Constants.DIRECTION newDirection) 
	{ 
		scrollDirection = newDirection;
		tempXSpeed = velocity.x;
		tempYSpeed = velocity.y;
		// Direction logic
		switch (scrollDirection) 
		{
	
			case DOWN:
				velocity.x = 0;
				velocity.y = tempYSpeed;
				break;
			case DOWN_LEFT:
				velocity.x = -tempXSpeed;
				velocity.y = tempYSpeed;
				break;
			case DOWN_RIGHT:
				velocity.x = tempXSpeed;
				velocity.y = tempYSpeed;
				break;
			default:
				velocity.x = 0;
				velocity.y = tempYSpeed;
				break;
		}
	}
	public Constants.DIRECTION getDirection() { return scrollDirection; }
	
	
}
