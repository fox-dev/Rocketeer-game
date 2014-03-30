package com.me.GameWorld;

import com.badlogic.gdx.Gdx;
import com.me.GameObjects.Rocket;

public class GameWorld {
	
	private Rocket rocket;
	
	public GameWorld(int midPointY) {
		rocket = new Rocket((Gdx.graphics.getWidth() / 2) - 15, 400, 30, 30);
	}
	
	public void update(float delta) {
		rocket.update(delta);
	}
	
	public Rocket getRocket() { return rocket; }
	
}
