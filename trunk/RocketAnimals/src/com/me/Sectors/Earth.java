package com.me.Sectors;

import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.Helicopter;
import com.me.GameObjects.HotAirBalloon;
import com.me.GameObjects.JetPlane;
import com.me.GameObjects.ParaTroop;
import com.me.helpers.Constants;

public class Earth extends Sector
{
	private final int LOW_OBSTACLE_NUM = 5;
	private final int MID_OBSTACLE_NUM = 7;
	private final int HI_OBSTACLE_NUM = 9;
	
	private final int FIRST_WAVE_TIME = 15;
	private final int SECOND_WAVE_TIME = 30;
	private final int THIRD_WAVE_TIME = 45;
	
	private int OBSTACLE_LIMIT = LOW_OBSTACLE_NUM;
	
	public Earth(Array<AbstractObstacle> myList)
	{
		super(myList);
	}
	public void addObject()
	{
		double rPlane = Math.random();
		//Chance of Planes .4%
		if(rPlane < 0.04 && numObstacles < OBSTACLE_LIMIT && runTime >= SECOND_WAVE_TIME
			)
		{
			flipObjectX = ((int)(rPlane * 50)) == 1 ? false : true; // Should alternate often
			
			if (flipObjectX) 
			{
				if(PLANE_EVENT == false && eventInt == 9){
					obstacleList.add(new JetPlane(-100, randInt(0,100), 100, 50, randInt(Constants.PLANE_MIN_Y_SPEED, Constants.PLANE_MAX_Y_SPEED), randInt(Constants.PLANE_MIN_X_SPEED, Constants.PLANE_MAX_X_SPEED), Constants.DIRECTION.DOWN_RIGHT));
					numObstacles++;
					PLANE_EVENT = true;
					System.out.println(PLANE_EVENT);
				}
			} 
			else 
			{
				if(PLANE_EVENT == false && eventInt == 9)
				{
					obstacleList.add(new JetPlane(320, randInt(0,100), 100, 50, randInt(Constants.PLANE_MIN_Y_SPEED, Constants.PLANE_MAX_Y_SPEED), randInt(Constants.PLANE_MIN_X_SPEED, Constants.PLANE_MAX_X_SPEED), Constants.DIRECTION.DOWN_LEFT));
					numObstacles++;
					PLANE_EVENT = true;
					System.out.println(PLANE_EVENT);
				}
				
			}
		}
		
		double rHotAirBalloon = Math.random();
		//Chance of Hot Air Balloons 4%
		if(rHotAirBalloon < 0.04 && numObstacles < OBSTACLE_LIMIT && runTime >= FIRST_WAVE_TIME)
		{
			boolean flipObjectX2 = ((int)(rHotAirBalloon * 50)) == 1 ? false : true; // Should alternate often
			
			if (flipObjectX2) 
			{
				if(runTime >= 0 && runTime <= 100 && (eventInt == 1 ||  eventInt == 5 || eventInt == 7) )
				{
				 	obstacleList.add(new HotAirBalloon(r.nextInt(305), -100, 63, 100, randInt(Constants.BALLOON_MIN_Y_SPEED, Constants.BALLOON_MAX_Y_SPEED), randInt(Constants.BALLOON_MIN_X_SPEED, Constants.BALLOON_MAX_X_SPEED), Constants.DIRECTION.DOWN_RIGHT));
				 	numObstacles++;
				}
			} 
			else 
			{
				if(runTime >= 0 && runTime <= 100){
				 	obstacleList.add(new HotAirBalloon(r.nextInt(305), -100, 63, 100, randInt(Constants.BALLOON_MIN_Y_SPEED,Constants.BALLOON_MAX_Y_SPEED), randInt(Constants.BALLOON_MIN_X_SPEED, Constants.BALLOON_MAX_X_SPEED), Constants.DIRECTION.DOWN_LEFT));
				 	numObstacles++;
				}
			}
		}
		
		double rSkydiver = Math.random();
		if (rSkydiver > 0.95 && numObstacles < OBSTACLE_LIMIT && runTime >= SECOND_WAVE_TIME) 
		{
			boolean flipObjectX3 = ((int)(rSkydiver * 50)) == 1 ? false : true;
			
			if (flipObjectX3) 
			{
				obstacleList.add(new ParaTroop(randInt(0,Constants.TRUE_WIDTH), -30, 30, 30, randInt(150,250), 25, Constants.DIRECTION.DOWN_RIGHT));
				numObstacles++;
			}
			else 
			{
				obstacleList.add(new ParaTroop(randInt(0,Constants.TRUE_WIDTH), -30, 30, 30, randInt(150,250), 25, Constants.DIRECTION.DOWN_LEFT));
				numObstacles++;
			}
		}
		
		// Added Helicopter Spawns here
		double rCopter = Math.random();
		if(rCopter < 0.04 && numObstacles < OBSTACLE_LIMIT && runTime >= SECOND_WAVE_TIME)
		{
			boolean flipObjectX4 = ((int)(rCopter * 50)) == 1 ? false : true; // Should alternate often
			
			if (flipObjectX4) 
			{
				obstacleList.add(new Helicopter(r.nextInt(305), -30, 101, 52, randInt(100, 250), randInt(25, 75), Constants.DIRECTION.DOWN_RIGHT));
				numObstacles++;
			} 
			else 
			{
				obstacleList.add(new Helicopter(r.nextInt(305), -30, 101, 52, randInt(100, 250), randInt(25, 75), Constants.DIRECTION.DOWN_LEFT));
				numObstacles++;
			}
		}
	}
}
