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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.HotAirBalloon;
import com.me.GameObjects.Meteor;
import com.me.GameObjects.Rocket;
import com.me.helpers.AssetLoader;
import com.me.helpers.Constants;

public class GameRenderer {
	
	private GameWorld world;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch spriteBatch;
	private Rectangle viewport;
	
	private int gameHeight;
	private int midPointY;
	
	
	
	// Game objects
	Rocket rocket;
	Array<AbstractObstacle> stuff;
	
	
	
	// Game sprites;
	TextureRegion rocket1, rocket2, rocket3;
	TextureRegion sMeteor;
	Animation rocketAnimation;
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		this.world = world;
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, Constants.TRUE_WIDTH, Constants.TRUE_HEIGHT);
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(cam.combined); // Attach spriteBatch to camera
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		// Init the game objects and visuals
		initGameObjects();
		initGameAssets();
	}
	
	public void render(float runTime) {

		// Update camera
		cam.update();
		cam.apply(Gdx.gl10);
		
		// Set viewport
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
		
		// Fill screen with black
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Begin ShapeRenderer
		/*
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(world.getRocket().getX(), world.getRocket().getY(), world.getRocket().getWidth(), world.getRocket().getHeight());
		shapeRenderer.end();
		*/
		
		
		
		spriteBatch.begin();
		drawStuff();
		
		if (rocket.isMoving())
			spriteBatch.draw(rocketAnimation.getKeyFrame(runTime), rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
		else
			spriteBatch.draw(rocket1, rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
		spriteBatch.end();
		
	}
	
	
	
	public void drawStuff(){
		for(AbstractObstacle items : world.getScroller().getAbstractObstacles()){
			if(items instanceof Meteor){
				
				spriteBatch.draw(sMeteor, items.getX(), items.getY(), items.getWidth(), items.getHeight());
			}
			if(items instanceof HotAirBalloon){
				
				spriteBatch.draw(rocket1, items.getX(), items.getY(), items.getWidth(), items.getHeight());
				
				
			}
		}
		
	}
	
	public void initGameObjects() {
		rocket = world.getRocket();
		stuff = world.getScroller().getAbstractObstacles();
		
	}
	
	public void initGameAssets() {
		rocket1 = AssetLoader.rocket1;
		rocket2 = AssetLoader.rocket2;
		rocket3 = AssetLoader.rocket3;
		rocketAnimation = AssetLoader.rocketAnimation;
		sMeteor = AssetLoader.meteor;
		
		
	}
	
	public void resize(int width, int height) {
		float aspectRatio = (float)width / (float)height;
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);
		
		if(aspectRatio > Constants.ASPECT_RATIO)
        {
            scale = (float)height/(float)Constants.TRUE_HEIGHT;
            crop.x = (width - Constants.TRUE_WIDTH*scale)/2f;
        }
        else if(aspectRatio < Constants.ASPECT_RATIO)
        {
            scale = (float)width/(float)Constants.TRUE_WIDTH;
            crop.y = (height - Constants.TRUE_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)Constants.TRUE_WIDTH;
        }

        float w = (float)Constants.TRUE_WIDTH*scale;
        float h = (float)Constants.TRUE_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
	}
}
