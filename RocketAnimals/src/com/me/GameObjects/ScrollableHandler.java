package com.me.GameObjects;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.me.GameWorld.GameWorld;
import com.me.helpers.Constants;


public class ScrollableHandler 
{
	public static final int SCROLL_SPEED = 150;
	
	private final int LOW_OBSTACLE_NUM = 5;
	private final int MID_OBSTACLE_NUM = 7;
	private final int HI_OBSTACLE_NUM = 9;
	
	private final int FIRST_WAVE_TIME = 15;
	private final int SECOND_WAVE_TIME = 30;
	private final int THIRD_WAVE_TIME = 45;
	
	private int OBSTACLE_LIMIT = LOW_OBSTACLE_NUM;
	
	
	private Background bg; //temp
	

	private Array<AbstractObstacle> obstacleList = new Array<AbstractObstacle>();
 	private Iterator<AbstractObstacle> iterator;
	private int numObstacles = 0;
	private boolean flipObjectX = false; // False => Direction is DOWN_LEFT
	
	private int dodged = 0;
	
	private float runTime = 0;
	
	private Random r;
	
	//To keep track of rocket position
	private Rocket playerRocket;
	
	
	//EventFlags with default - false
	private boolean PLANE_EVENT = false;
	private boolean ALIEN_EVENT = false;
	private boolean CARGO_PLANE_EVENT = false;
	
	//other Alien flags
	private int ALIEN_EVENT_COUNT = 0;
	private boolean firing = false;
	private boolean doneFiring = false;
	private float shootDelay = 0;
	private int x = 0;
	private int bulletCount = 0;
	private int xSpeed = 0;
	private int ySpeed = 100;
	
	//other CargoPlane flags
	private int CARGOPLANE_EVENT_COUNT = 0;
	private boolean spawning = false;
	private boolean doneSpawning = false;
	private int spawnCount = 0;
	
	
	public ScrollableHandler()
	{	
		
		//iterator = new AbstractObstacle(0, 0, 30, 30, SCROLL_SPEED); //using default bird sprite, change sprite and position later
		r = new Random();
			
		bg = new Background(0, Constants.TRUE_HEIGHT - 102, Constants.TRUE_WIDTH, 102, 15);
	}
	                   
	public void update(float delta){
		System.out.println(numObstacles + " " + spawnCount);
		runTime += delta;
		
		
		if(ALIEN_EVENT == false && CARGO_PLANE_EVENT == false){
			meteorStuff(delta);
		}
		if(runTime >= 7 && CARGOPLANE_EVENT_COUNT == 0){
			cargoPlaneStuff(delta);
		}
		if(runTime >= 30 && ALIEN_EVENT_COUNT == 0){
			alienStuff(delta);
		}
		
		
		System.out.println("Runtime is: " + runTime);
		
		// System.out.println(numObstacles + ", " + OBSTACLE_LIMIT);
		
		// Update obstacle limit as time goes on
		if (runTime <= FIRST_WAVE_TIME) {
			OBSTACLE_LIMIT = LOW_OBSTACLE_NUM;
		}
		
		if (runTime > FIRST_WAVE_TIME && runTime <= SECOND_WAVE_TIME) {
			OBSTACLE_LIMIT = MID_OBSTACLE_NUM;
		}
		
		if (runTime > SECOND_WAVE_TIME && runTime <= THIRD_WAVE_TIME) {
			OBSTACLE_LIMIT = HI_OBSTACLE_NUM;
		}
		
		iterator = obstacleList.iterator();
		while(iterator.hasNext())
		{	
			AbstractObstacle o = iterator.next();
			o.update(delta);
			
			if((o instanceof Alien) && doneFiring == true){
				o.setVelocity(0, 80);
				
			}
			
			if((o instanceof CargoPlane) && doneSpawning == true){
				o.setVelocity(0, -30);
				
			}
			
	
			if(o.isScrolledDown())
			{
				if(o instanceof JetPlane){
					PLANE_EVENT = false;
				}
				if(o instanceof Alien){
					ALIEN_EVENT = false;
					ALIEN_EVENT_COUNT++;
					bulletCount = 0;
					
				}
				if(o instanceof CargoPlane){
					CARGO_PLANE_EVENT = false;
					CARGOPLANE_EVENT_COUNT++;
					spawnCount = 0;
				}
				
				iterator.remove();
				numObstacles--;
				dodged++;
			}
			
		}
		
	}
	
	
	public void cargoPlaneStuff(float delta)
	{
		int xDrop = 0;
		int yDrop = 0;
		if(CARGO_PLANE_EVENT == false){
			CARGO_PLANE_EVENT = true;
			obstacleList.add(new CargoPlane(Constants.TRUE_WIDTH/2 - 150,0-150,300,150,0,25));
			numObstacles++;
			
		}
		
		iterator = obstacleList.iterator();
		while(iterator.hasNext())
		{	
			AbstractObstacle o = iterator.next();
			
			if(o instanceof CargoPlane){
				((CargoPlane) o).stalk(playerRocket);
				xDrop = (int) o.getX() + (int) o.getWidth()/2;
				yDrop = (int) o.getY() + (int) o.getHeight();
				
			}
		}
		
		System.out.println("xdrop is: " + xDrop);
		int eventInt = randInt(0,10);
		//System.out.println(eventInt);
		
		//System.out.println("R = " + numObstacles);
		double rMeteor = Math.random();
		//Chance of Meteors 4%
		if(rMeteor < 0.06 && numObstacles < 8) 
		{	
			// Add objects with down direction first
			                        // (x position, y position, width, height, ySpeed, xSpeed, direction)
			obstacleList.add(new Meteor(xDrop - 15, yDrop, 30, 30, randInt(Constants.METEOR_MIN_SPEED_Y/2, Constants.METEOR_MAX_SPEED_Y), 0f, Constants.DIRECTION.DOWN));
			numObstacles++;
			spawnCount++;
	
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
				 	obstacleList.add(new HotAirBalloon(Constants.TRUE_WIDTH/2 - 31, -100, 63, 100, randInt(Constants.BALLOON_MIN_Y_SPEED, Constants.BALLOON_MAX_Y_SPEED), randInt(Constants.BALLOON_MIN_X_SPEED, Constants.BALLOON_MAX_X_SPEED), Constants.DIRECTION.DOWN));
				 	numObstacles++;
				}
			} 
			else 
			{
				if(runTime >= 0 && runTime <= 100){
				 	obstacleList.add(new HotAirBalloon(Constants.TRUE_WIDTH/2 - 31, -100, 63, 100, randInt(Constants.BALLOON_MIN_Y_SPEED,Constants.BALLOON_MAX_Y_SPEED), randInt(Constants.BALLOON_MIN_X_SPEED, Constants.BALLOON_MAX_X_SPEED), Constants.DIRECTION.DOWN));
				 	numObstacles++;
				 	spawnCount++;
				}
			}
		}
		
		if(spawnCount == 30){
			doneSpawning = true;
			spawning = false;	
		}
		
		
	}
	public void alienStuff(float delta){
		
		if(ALIEN_EVENT == false){
			ALIEN_EVENT = true;
			obstacleList.add(new Alien(0 - 50,0,50,50,50,0));
		 	numObstacles++;
		 	
		}
		
		x++;
		xSpeed = (int) (40*Math.sin(Math.toDegrees(x/4)));
		
		iterator = obstacleList.iterator();
		while(iterator.hasNext())
		{	
			AbstractObstacle o = iterator.next();
		
			
			if(o instanceof Alien){
				if(((Alien) o).inPosition()){
					firing  = true;
				}
				
			}
		}
		
		
		if(firing == true && bulletCount < 100){
			shootDelay -= delta;
			if(shootDelay <= 0){
				obstacleList.add(new Projectile(Constants.TRUE_WIDTH/2 ,0,16,16,xSpeed,100));
				numObstacles++;
				bulletCount++;
				shootDelay += 0.1;
			}
		}

		if(bulletCount == 100){
			doneFiring = true;
			firing = false;	
		}	
	}
	
	public void meteorStuff(float delta)
	
	{
		
		int eventInt = randInt(0,10);
		//System.out.println(eventInt);
		
		//System.out.println("R = " + numObstacles);
		double rMeteor = Math.random();
		//Chance of Meteors 4%
		if(rMeteor < 0.04 && numObstacles < OBSTACLE_LIMIT) 
		{	
			// Add objects with down direction first
			                        // (x position, y position, width, height, ySpeed, xSpeed, direction)
			obstacleList.add(new Meteor(r.nextInt(305), -30, 30, 30, randInt(Constants.METEOR_MIN_SPEED_Y, Constants.METEOR_MAX_SPEED_Y), 0f, Constants.DIRECTION.DOWN));
			numObstacles++;
	
		}
		
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
	}
	
	public void scrollBackgroundUpdate(float delta){
		bg.update(delta);
	}
	
	
	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    return rand.nextInt((max - min) + 1) + min;
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
			
			runTime = 0;
			PLANE_EVENT = false;
			
			//bg = new Background(0, Constants.TRUE_HEIGHT - 102, Constants.TRUE_WIDTH, 102, 15);
			
			bg.position.x = 0;
			bg.position.y = Constants.TRUE_HEIGHT - 102;
			
			dodged = 0;
			
		}
	
	public boolean planeEvent(){return PLANE_EVENT;}
	public void spawnPlane(){ PLANE_EVENT = true;}
	public void despawnPlane(){ PLANE_EVENT = false;}
	public int getDodged() { return dodged;};
	public Background getFrontBackground(){return bg;}
	
	//Set rocket
	public void setRocket(Rocket rocket){
		playerRocket = rocket;
	}

}
