package com.me.GameWorld;

import com.badlogic.gdx.Gdx;
import com.me.GameObjects.Rocket;
import com.me.GameObjects.ScrollableHandler;

public class GameWorld {
	
	private Rocket rocket;
	private ScrollableHandler scroller;
	
	public GameWorld(int midPointY) {
		rocket = new Rocket((Gdx.graphics.getWidth() / 2) - 15, 400, 30, 30);
		scroller = new ScrollableHandler();
	}
	
	public void update(float delta) {
		rocket.update(delta);
		scroller.update(delta);
	}
	
	public Rocket getRocket() { return rocket; }
	
	public ScrollableHandler getScroller() { return scroller; }
	
}
