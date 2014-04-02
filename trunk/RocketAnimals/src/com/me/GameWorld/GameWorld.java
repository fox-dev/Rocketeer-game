package com.me.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.Rocket;
import com.me.GameObjects.Scrollable;
import com.me.GameObjects.ScrollableHandler;

public class GameWorld {
	
	private Rocket rocket;
	private ScrollableHandler scroller;
	private Array<AbstractObstacle> scrollObjects;
	
	public GameWorld(int midPointY) {
		rocket = new Rocket((Gdx.graphics.getWidth() / 2) - 15, 400, 30, 30);
		scroller = new ScrollableHandler();
		scrollObjects = new Array<AbstractObstacle>();
	}
	
	public void update(float delta) {
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
	
	public Rocket getRocket() { return rocket; }
	
	public ScrollableHandler getScroller() { return scroller; }
	
}
