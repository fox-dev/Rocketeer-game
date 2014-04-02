package com.me.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.Meteor;
import com.me.GameObjects.Rocket;
import com.me.helpers.AssetLoader;

public class GameRenderer {
	
	private GameWorld world;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch spriteBatch;
	
	private int gameHeight;
	private int midPointY;
	
	// Game objects
	Rocket rocket;
	Array<AbstractObstacle> meteor;
	
	
	// Game sprites;
	TextureRegion rocket1, rocket2, rocket3;
	TextureRegion sMeteor;
	Animation rocketAnimation;
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		this.world = world;
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(cam.combined); // Attach spriteBatch to camera
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		// Init the game objects and visuals
		initGameObjects();
		initGameAssets();
	}
	
	public void render(float runTime) {

		// Fill screen with black
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Begin ShapeRenderer
		/*
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(world.getRocket().getX(), world.getRocket().getY(), world.getRocket().getWidth(), world.getRocket().getHeight());
		shapeRenderer.end();
		*/
		
		
		spriteBatch.begin();
		drawMeteors();
		
		if (rocket.isMoving())
			spriteBatch.draw(rocketAnimation.getKeyFrame(runTime), rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
		else
			spriteBatch.draw(rocket1, rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
		spriteBatch.end();
		
	}
	
	public void drawMeteors(){
		for(AbstractObstacle items : world.getScroller().getAbstractObstacles()){
			spriteBatch.draw(sMeteor, items.getX(), items.getY(), items.getWidth(), items.getHeight());
		}
		
	}
	
	public void initGameObjects() {
		rocket = world.getRocket();
		meteor = world.getScroller().getAbstractObstacles();
	}
	
	public void initGameAssets() {
		rocket1 = AssetLoader.rocket1;
		rocket2 = AssetLoader.rocket2;
		rocket3 = AssetLoader.rocket3;
		rocketAnimation = AssetLoader.rocketAnimation;
		sMeteor = AssetLoader.meteor;
		
		
	}
}
