package com.me.GameObjects;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.me.helpers.Constants;


public class ScrollableHandler 
{
	

	private Array<AbstractObstacle> obstacleList = new Array<AbstractObstacle>();
 	private Iterator<AbstractObstacle> iterator;
	private double RNG;
	private int numObstacles = 0;
	private boolean flipObjectX = false; // False => Direction is DOWN_LEFT
	
	public static final int SCROLL_SPEED = 150;
	
	private float runTime = 0;
	
	private Random r;
	
	//EventFlags with default - false
	private boolean PLANE_EVENT = false;
	
	public ScrollableHandler(){
		
		//iterator = new AbstractObstacle(0, 0, 30, 30, SCROLL_SPEED); //using default bird sprite, change sprite and position later
		r = new Random();
		
		
	}
	
	public void update(float delta){
		runTime += delta;
		meteorStuff(delta);
		//System.out.println("Runtime is: " + runTime);
		
	}
	
	public void meteorStuff(float delta)
	{
		
		RNG = Math.random();
		
		int eventInt = randInt(0,10);
		//System.out.println(eventInt);
		
		//System.out.println("R = " + numObstacles);
		if(RNG < 0.05 && numObstacles < 15) {
			
			flipObjectX = ((int)(RNG * 50)) == 1 ? false : true; // Should alternate often
			
			// Add objects with down direction first
			obstacleList.add(new Meteor(r.nextInt(305), -30, 30, 30, randInt(Constants.METEOR_MIN_SPEED_Y, Constants.METEOR_MAX_SPEED_Y), 0f, Constants.DIRECTION.DOWN));
			numObstacles++;
			
			if (flipObjectX) {
				if(PLANE_EVENT == false && eventInt == 5){
					obstacleList.add(new JetPlane(-50, randInt(0,280), 50, 50, randInt(Constants.PLANE_MIN_Y_SPEED, Constants.PLANE_MAX_Y_SPEED), randInt(Constants.PLANE_MIN_X_SPEED, Constants.PLANE_MAX_X_SPEED), Constants.DIRECTION.DOWN_RIGHT));
					numObstacles++;
					PLANE_EVENT = true;
					System.out.println(PLANE_EVENT);
				}
				
				if(runTime >= 0 && runTime <= 100){
				 	obstacleList.add(new HotAirBalloon(r.nextInt(305), -100, 63, 100, randInt(Constants.BALLOON_MIN_Y_SPEED,Constants.BALLOON_MAX_Y_SPEED), randInt(Constants.BALLOON_MIN_X_SPEED, Constants.BALLOON_MAX_X_SPEED), Constants.DIRECTION.DOWN_RIGHT));
				 	numObstacles++;
				}
			} else {
				if(PLANE_EVENT == false && eventInt == 5){
					obstacleList.add(new JetPlane(320, randInt(0,280), 50, 50, randInt(Constants.PLANE_MIN_Y_SPEED, Constants.PLANE_MAX_Y_SPEED), randInt(Constants.PLANE_MIN_X_SPEED, Constants.PLANE_MAX_X_SPEED), Constants.DIRECTION.DOWN_LEFT));
					numObstacles++;
					PLANE_EVENT = true;
					System.out.println(PLANE_EVENT);
				}
				
				if(runTime >= 0 && runTime <= 100){
				 	obstacleList.add(new HotAirBalloon(r.nextInt(305), -100, 63, 100, randInt(Constants.BALLOON_MIN_Y_SPEED,Constants.BALLOON_MAX_Y_SPEED), randInt(Constants.BALLOON_MIN_X_SPEED, Constants.BALLOON_MAX_X_SPEED), Constants.DIRECTION.DOWN_LEFT));
				 	numObstacles++;
				}
			}
			
			
		}
		
		iterator = obstacleList.iterator();
		while(iterator.hasNext())
		{	
			AbstractObstacle o = iterator.next();
			o.update(delta);
			
	
			if(o.isScrolledDown())
			{
				if(o instanceof JetPlane){
					PLANE_EVENT = false;
				}
				iterator.remove();
				numObstacles--;
			}
			
		}
		
	}
	
	
	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public Array<AbstractObstacle> getAbstractObstacles(){ return obstacleList;}
	
	public void generateAbstractObstacles()
	{
		
	}
	
	// Need this to remove objects for collision detection
	public void removeObject(AbstractObstacle obstacle) 
	{
		obstacleList.removeValue(obstacle, true);
		numObstacles--;
	}
	
	
	//Remove all objects when restarting game
		public void resetObjects(){
			obstacleList = new Array<AbstractObstacle>();
			numObstacles = 0;
			
		}
	
	public boolean planeEvent(){return PLANE_EVENT;}
	public void spawnPlane(){ PLANE_EVENT = true;}
	public void despawnPlane(){ PLANE_EVENT = false;}

}
