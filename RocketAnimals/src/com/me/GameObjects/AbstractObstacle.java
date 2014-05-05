package com.me.GameObjects;

import com.me.helpers.Constants.DIRECTION;

public class AbstractObstacle extends Scrollable{
	
	protected DIRECTION scrollDirection;
	protected float tempXSpeed, tempYSpeed, polygonScaleX; // Place holders just in case we change direction
	
	protected float[] polygonRightVertices, polygonLeftVertices; // Hold the vertices for collision polygons
	
	// Polygon vertices
	protected final float[] jetPlane_polygon_right = {
			0,	47,
			23,	53,
			80, 52,
			82, 56,
			101, 57,
			104, 53,
			137, 53,
			157, 41,
			136, 24,
			43, 26,
			16, 0,
			5,	0,
			13, 35,
			1, 40,
	};
	
	protected final float[] jetPlane_polygon_left = {
			158,	47,
			135,	53,
			78, 52,
			76, 56,
			57, 57,
			54, 53,
			21, 53,
			1, 41,
			22, 24,
			115, 26,
			142, 0,
			153,	0,
	        145, 35,
			157, 40,
	};
	
	protected final float[] hotAirBalloon_polygon = {
			23, 138,
			23, 119,
			26, 99,
			1, 48,
			0, 35,
			1, 30,
			30, 1,
			41, 0,
			52, 1,
			82, 30,
			83, 35,
			81, 48,
			56, 99,
			59, 119,
			59, 138,
	};
	
	protected final float[] meteor_polygon = {
			24, 48,
			6, 41,
			0, 26,
			3, 9,
			25, 0,
			38, 7,
			48, 23,
			49, 28,
			42, 39,
	};
	
	protected final float[] bullet_polygon = {
			1,8,
			3,3,
			7,0,
			13,3,
			15,8,
			12,12,
			9,15,
			2,13
	};
	/*
	protected final float[] bullet_polygon = {
			9, 32,
			11, 28,
			17, 25,
			21, 27,
			23, 32,
			21, 37,
			16, 39,
			11, 36
	};
	*/
	////////////////////////////////////////////
	// When AbstractObstacle's constructor is invoked, invoke the super (Scrollable)
    // constructor
	public AbstractObstacle(float x, float y, int width, int height, float ySpeed){
		super(x, y, width, height, ySpeed);

		polygonRightVertices = new float[]{
				0,0,
				0,height,
				width,height,
				width,0
		};
		polygonLeftVertices = polygonRightVertices;
		
		scrollDirection = DIRECTION.DOWN;
		setDirection(scrollDirection);
	}
	
	public AbstractObstacle(float x, float y, int width, int height, float xSpeed, float ySpeed){
		super(x, y, width, height, xSpeed, ySpeed);

		polygonRightVertices = new float[]{
				0,0,
				0,height,
				width,height,
				width,0
		};
		polygonLeftVertices = polygonRightVertices;
	}
	
	public AbstractObstacle(float x, float y, int width, int height, float ySpeed, float xSpeed, DIRECTION direction) {
		super(x, y, width, height, xSpeed, ySpeed);
		
		polygonRightVertices = new float[]{
				0,0,
				0,height,
				width,height,
				width,0
		};
		polygonLeftVertices = polygonRightVertices;
		
		scrollDirection = direction;
		setDirection(scrollDirection);

	}
	
	@Override
	public void update(float delta) 
	{
		super.update(delta);
	}
	
	public void setDirection(DIRECTION newDirection) 
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
				velocity.x = -velocity.x;
				velocity.y = tempYSpeed;
				hitBox.setVertices(polygonLeftVertices);
				break;
			case DOWN_RIGHT:
				velocity.x = tempXSpeed;
				velocity.y = tempYSpeed;
				hitBox.setVertices(polygonRightVertices);
				break;
			default:
				velocity.x = 0;
				velocity.y = tempYSpeed;
				break;
		}
	}
	public DIRECTION getDirection() { return scrollDirection; }
	
	
}
