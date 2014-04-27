package com.me.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.me.GameObjects.Rocket;
import com.me.GameWorld.GameWorld;

public class InputHandler implements InputProcessor {
	
	private Rocket rocket;
	
	private OrthographicCamera cam;
	
	private GameWorld world;
	
	
	public InputHandler(GameWorld world, Rocket rocket) {
		this.world = world;
		this.rocket = rocket;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, Constants.TRUE_WIDTH, Constants.TRUE_HEIGHT);
		
		
		
		
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (world.isReady()) {
			world.standby();
			
		}
		else if (world.isStandby()){
			
		}
		else if( world.isGameOver()){
			world.restart();
		}
		else {
		
			Vector3 tempPos = new Vector3(screenX, screenY, 0);
			cam.unproject(tempPos);
			System.out.println("Touched " + tempPos.x);
			if (tempPos.x < rocket.getMiddleX()) {
			
				rocket.onLeft();
				rocket.userAtX((int)tempPos.x);
			}
			else
				rocket.onRight();
		    	rocket.userAtX((int)tempPos.x);
		}
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
		
		if (world.isReady()) {
			world.standby();
			
		}
		else if (world.isStandby()){
			
		}
		else {
			Vector3 tempPos = new Vector3(screenX, screenY, 0);
			cam.unproject(tempPos);
			System.out.println("Touched " + tempPos.x);
		
			if (tempPos.x < rocket.getMiddleX()) {
			
				rocket.onLeft();
				rocket.userAtX((int)tempPos.x);
			
			
			}
			else {
				rocket.onRight();
				rocket.userAtX((int)tempPos.x);
			}
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
