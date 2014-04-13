package com.me.GameWorld;

import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.Rocket;
import com.me.GameObjects.Scrollable;
import com.me.GameObjects.ScrollableHandler;
import com.me.helpers.Constants;

public class GameWorld {
	
	private Rocket rocket;
	private ScrollableHandler scroller;
	private Array<AbstractObstacle> scrollObjects;
	
	private GameState currentState;
	
	// Game states
	public enum GameState {
		READY, RUNNING, GAMEOVER
	}
	
	public GameWorld(int midPointY) {
		currentState = GameState.READY;
		rocket = new Rocket((Constants.TRUE_WIDTH / 2) - 15, 400, 30, 30);
		scroller = new ScrollableHandler();
		scrollObjects = new Array<AbstractObstacle>();
	}
	
	public void update(float delta) {
		
		switch(currentState) {
		
		case READY:
			updateReady(delta);
			break;
			
		case RUNNING:
			updateRunning(delta);
			break;
			
		default:
			break;
		}
		
	}
	
	private void updateReady(float delta) {
		rocket.update(delta);
	}
	
	private void updateRunning(float delta) {
		rocket.update(delta);
		scroller.update(delta);
		
		// Very crude collision detection
		scrollObjects = scroller.getAbstractObstacles();
		for (Scrollable tempObj : scrollObjects) {
			if (rocket.getRect().overlaps(tempObj.getRect())) {
				scroller.removeObject((AbstractObstacle)tempObj);
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
	
	// Game state getters
	public boolean isReady() { return currentState == GameState.READY; }
	public boolean isRunning() { return currentState == GameState.RUNNING; }
	public boolean isGameOver() { return currentState == GameState.GAMEOVER; }
	
	public Rocket getRocket() { return rocket; }
	public ScrollableHandler getScroller() { return scroller; }
	
}
