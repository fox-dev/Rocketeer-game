package com.me.GameObjects;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.utils.Array;

public class ScrollableHandler {
	
	
	private Array<AbstractObstacle> obstacleList = new Array<AbstractObstacle>();
 	private Iterator<AbstractObstacle> iterator;
 	private AbstractObstacle tempMet;
	private double RNG;
	private int numObstacles = 0;
	
	public static final int SCROLL_SPEED = 150;
	
	private Random r;
	
	public ScrollableHandler(){
		//iterator = new AbstractObstacle(0, 0, 30, 30, SCROLL_SPEED); //using default bird sprite, change sprite and position later
		r = new Random();
		
		
	}
	
	public void update(float delta){
		RNG = Math.random();
		
		//System.out.println("R = " + numObstacles);
		if(RNG < 0.05 && numObstacles < 15) {
			obstacleList.add(new AbstractObstacle(r.nextInt(305), -30, 30, 30, SCROLL_SPEED));
			numObstacles++;
		}
		
		iterator = obstacleList.iterator();
		while(iterator.hasNext()){
			tempMet = iterator.next();
			tempMet.update(delta);
			
			if(tempMet.isScrolledDown()){
				iterator.remove();
				numObstacles--;
			}
			
			
		}
	}
	
	public Array<AbstractObstacle> getAbstractObstacles(){ return obstacleList;}
	
	public void generateAbstractObstacles(){
		
	}
	
	// Need this to remove objects for collision detection
	public void removeObject(AbstractObstacle obstacle) {
		obstacleList.removeValue(obstacle, true);
		numObstacles--;
	}

}
