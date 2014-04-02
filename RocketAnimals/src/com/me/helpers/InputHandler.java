package com.me.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.me.GameObjects.Rocket;

public class InputHandler implements InputProcessor {
	
	private Rocket rocket;
	
	public InputHandler(Rocket rocket) {
		this.rocket = rocket;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("Touched " + screenX);
		if (screenX < rocket.getMiddleX()) {
			
			rocket.onLeft();
			rocket.userAtX(screenX);
		}
		else
			rocket.onRight();
		    rocket.userAtX(screenX);
		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		rocket.onNoClick();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		
		
		
		if (screenX < rocket.getMiddleX()) {
			
			rocket.onLeft();
			rocket.userAtX(screenX);
			
			
		}
		else {
			rocket.onRight();
			rocket.userAtX(screenX);
		}
		
		
		
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
