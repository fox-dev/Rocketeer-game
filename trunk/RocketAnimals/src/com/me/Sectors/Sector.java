package com.me.Sectors;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.Meteor;
import com.me.helpers.Constants;

public class Sector 
{
	protected Array<AbstractObstacle> obstacleList;
	Random r;
	public Sector(Array<AbstractObstacle> myList)
	{
		obstacleList = myList;
		Random r = new Random();
	}
	
	public void addObject()
	{
		
	}
	public void addMeteor()
	{
		// Add objects with down direction first
			                      // (x position, y position, width, height, ySpeed, xSpeed, direction)
		obstacleList.add(new Meteor(r.nextInt(305), -30, 30, 30, randInt(Constants.METEOR_MIN_SPEED_Y, Constants.METEOR_MAX_SPEED_Y), 0f, Constants.DIRECTION.DOWN));
	}
	public Array<AbstractObstacle> getList(){return obstacleList;}
	
	
}
