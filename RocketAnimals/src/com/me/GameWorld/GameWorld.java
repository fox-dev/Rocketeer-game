package com.me.GameWorld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.HotAirBalloon;
import com.me.GameObjects.JetPlane;
import com.me.GameObjects.Rocket;
import com.me.GameObjects.ScrollableHandler;
import com.me.helpers.AssetLoader;
import com.me.helpers.Constants;


public class GameWorld {
	
	
	
	
	private Rocket rocket;
	private ScrollableHandler scroller;
	private Array<AbstractObstacle> scrollObjects;
	private HotAirBalloon hab;
	
	
	private float runTime = 0;
	
	private int finalScore = 0;
	
	private GameState currentState;
	
	// Game states
	public enum GameState {
		READY, STANDBY, RUNNING, GAMEOVER
	}
	
	public GameWorld(int midPointY) {
		currentState = GameState.READY;
		rocket = new Rocket(Constants.ROCKET_STARTING_X, Constants.ROCKET_STARTING_Y, Constants.ROCKET_WIDTH, Constants.ROCKET_HEIGHT);
		scroller = new ScrollableHandler();
		scrollObjects = new Array<AbstractObstacle>();
		// hab = new HotAirBalloon(100, -30, 95, 120, 200);
		
		// Play music???
		AssetLoader.bgm.play();
		AssetLoader.bgm.setLooping(true);
	}
	
	public void update(float delta) {
		
		scroller.setRocket(rocket);
		
		//System.out.println(("CurrentState: " + currentState));
		switch(currentState) {
		
		case READY:
			
			updateReady(delta);
			break;
		
		case STANDBY:
			
			rocket.moveBird();
			
			if(rocket.getY() == Constants.ROCKET_LIFTOFF_STOP_AT_Y){
				start();
				break;
			}
			
			break;
			
		case RUNNING:
			
			updateRunning(delta);
			break;
			
		case GAMEOVER:
			
			updateGameOver(delta);
			break;
			
		default:
			break;
		}
		
	}
	
	private void updateReady(float delta) {
	
	}
	
	private void updateRunning(float delta) {
		runTime += delta;
		finalScore = scroller.getDodged();
		//System.out.println("Gameworld runtime is: " + runTime);
		rocket.update(delta);
		scroller.update(delta);
		scroller.scrollBackgroundUpdate(delta);
		
		
		
		// Very crude collision detection
		scrollObjects = scroller.getAbstractObstacles();
		for (AbstractObstacle tempObj : scrollObjects) 
		{
			if (rocket.overlapsWith(tempObj)) {
				
				if (tempObj instanceof JetPlane) {
					scroller.despawnPlane();
				}
				//scroller.removeObject((HotAirBalloon)tempObj);
				AssetLoader.hitSounds.random().play();
				//gameOver();
			}
			// Not needed?
			/*
			if(tempObj instanceof HotAirBalloon){
				if (Intersector.overlapConvexPolygons(tempObj.getPolygon(), rocket.getPolygon())) {
					//scroller.removeObject((HotAirBalloon)tempObj);
					AssetLoader.hitSounds.random().play();
					AssetLoader.hitSounds.random().play();
					//gameOver();	
				}
			}
			
			if(tempObj instanceof JetPlane){
				if (Intersector.overlapConvexPolygons(rocket.getPolygon(), tempObj.getPolygon())) {
					scroller.despawnPlane();
					//scroller.removeObject((JetPlane)tempObj);
					AssetLoader.hitSounds.random().play();
					//gameOver();	
				}
			}
			else if (Intersector.overlapConvexPolygons(rocket.getPolygon(), tempObj.getPolygon()))   //Cutting down code
			{
				//scroller.removeObject(tempObj);
				AssetLoader.hitSounds.random().play();
				//gameOver();
			}
			
			/*if(tempObj instanceof Meteor){
				if (rocket.getRect().overlaps(((tempObj).getRect()))) {
					scroller.removeObject((Meteor)tempObj);
					AssetLoader.hitSounds.random().play();
				}
			}
			if(tempObj instanceof HotAirBalloon){
				if (rocket.getRect().overlaps(((tempObj).getRect()))) {
					scroller.removeObject((HotAirBalloon)tempObj);
					AssetLoader.hitSounds.random().play();
				}
				
			}
			if(tempObj instanceof JetPlane){
				if (rocket.getRect().overlaps(((tempObj).getRect()))) {
					scroller.removeObject((JetPlane)tempObj);
					AssetLoader.hitSounds.random().play();
				}
			}*/
		}
	}
	
	public void updateGameOver(float delta){
		scroller.update(delta);
		
	}
	
	public void restart() {
		scroller.resetObjects();
		rocket.resetRocket();
		runTime = 0;
		finalScore = 0;
		ready();
	}
	
	// Game state setters
	public void ready() {
		currentState = GameState.READY;
	}
	
	public void start() {
		currentState = GameState.RUNNING;
		
	}
	
	public void standby() {
		currentState = GameState.STANDBY;
	}
	
	public void gameOver(){
		currentState = GameState.GAMEOVER;
	}
	
	// Game state getters
	public boolean isReady() { return currentState == GameState.READY; }
	public boolean isRunning() { return currentState == GameState.RUNNING; }
	public boolean isGameOver() { return currentState == GameState.GAMEOVER; }
	public boolean isStandby() { return currentState == GameState.STANDBY; }
	

	
	public Rocket getRocket() { return rocket; }
	public ScrollableHandler getScroller() { return scroller; }
	public HotAirBalloon getHab() { return hab; }

	public int getScore() {
		
		return (int) (scroller.getDodged());
	}
	
	public int getFinalScore(){
		return finalScore;
	}
	
	
	
	
	
}
