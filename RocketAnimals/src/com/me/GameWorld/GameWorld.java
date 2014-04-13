package com.me.GameWorld;

import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.Rocket;
import com.me.GameObjects.Scrollable;
import com.me.GameObjects.ScrollableHandler;
import com.me.helpers.AssetLoader;
import com.me.helpers.Constants;

public class GameWorld {
	
	private Rocket rocket;
	private ScrollableHandler scroller;
	private Array<AbstractObstacle> scrollObjects;
	
	private float runTime = 0;
	
	private GameState currentState;
	
	// Game states
	public enum GameState {
		READY, STANDBY, RUNNING, GAMEOVER
	}
	
	public GameWorld(int midPointY) {
		currentState = GameState.READY;
		rocket = new Rocket((Constants.TRUE_WIDTH / 2) - 15, 450, 30, 30);
		scroller = new ScrollableHandler();
		scrollObjects = new Array<AbstractObstacle>();
		
		// Play music???
		AssetLoader.bgm.play();
	}
	
	public void update(float delta) {
		runTime += delta;
		
		switch(currentState) {
		
		case READY:
			
			updateReady(delta);
			break;
		
		case STANDBY:
			
			rocket.moveBird();
			
			if(rocket.getY() == 400){
				start();
				break;
			}
			
			
		case RUNNING:
			
			updateRunning(delta);
			break;
			
		default:
			break;
		}
		
	}
	
	private void updateReady(float delta) {
		
	
	}
	
	private void updateRunning(float delta) {
		rocket.update(delta);
		scroller.update(delta);
		
		// Very crude collision detection
		scrollObjects = scroller.getAbstractObstacles();
		for (Scrollable tempObj : scrollObjects) {
			if (rocket.getRect().overlaps(tempObj.getRect())) {
				scroller.removeObject((AbstractObstacle)tempObj);
				AssetLoader.hit.play();
			}
		}
	}
	
	public void restart() {
		;
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
	
	// Game state getters
	public boolean isReady() { return currentState == GameState.READY; }
	public boolean isRunning() { return currentState == GameState.RUNNING; }
	public boolean isGameOver() { return currentState == GameState.GAMEOVER; }
	public boolean isStandby() { return currentState == GameState.STANDBY; }
	
	public Rocket getRocket() { return rocket; }
	public ScrollableHandler getScroller() { return scroller; }
	
}
