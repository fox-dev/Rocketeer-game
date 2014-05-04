package com.me.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.GameObjects.AbstractObstacle;
import com.me.GameObjects.Background;
import com.me.GameObjects.HotAirBalloon;
import com.me.GameObjects.JetPlane;
import com.me.GameObjects.Meteor;
import com.me.GameObjects.Projectile;
import com.me.GameObjects.Rocket;
import com.me.helpers.AssetLoader;
import com.me.helpers.Constants;
import com.me.helpers.Constants.DIRECTION;

public class GameRenderer {
	
	private GameWorld world;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch spriteBatch;
	private Rectangle viewport;
	
	private int gameHeight;
	private int midPointY;
	
	String score;
	
	// Game objects
	Rocket rocket;
	Array<AbstractObstacle> objectList;
	
	// Game sprites;
	TextureRegion rocketLeft, rocketMid, rocketRight, sMeteor, hotAirBalloon, hotAirBalloon_flipped, 
	 			jetPlane, jetPlane_flipped, fire1, fire2, fire3, gameOver;
	Animation rocketAnimation, rocketFireAnimation;
	
	//temp
	TextureRegion bg;
	private Background background; 
	
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
		Gdx.gl.glClearColor(0.11f, 0.11f, 0.11f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Draw Background color
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
		shapeRenderer.rect(0, 0, Constants.TRUE_WIDTH, Constants.TRUE_HEIGHT);
		shapeRenderer.end();
		
		// Draw sprites
		spriteBatch.begin();
		
		//spriteBatch.draw(bg, 0, Constants.TRUE_HEIGHT - 102, Constants.TRUE_WIDTH, 102);
		spriteBatch.draw(bg, background.getX(), background.getY(), Constants.TRUE_WIDTH, 102);
		
		if(!world.isGameOver()){
			drawPlayer(runTime);
		}
		
		drawObjects(runTime);
		
		 // Convert integer into String
        String score = world.getScore() + "";

        // Draw shadow first
        if(!world.isGameOver()){
        	AssetLoader.shadow.draw(spriteBatch, "" + world.getScore(), (136 / 2)
                - (3 * score.length()), 12);
        	// Draw text
        	AssetLoader.font.draw(spriteBatch, "" + world.getScore(), (136 / 2)
                - (3 * score.length() - 1), 11);
        }
        else{
        	AssetLoader.shadow.draw(spriteBatch, "" + world.getFinalScore(), (136 / 2)
                - (3 * score.length()), 12);
            // Draw text
            AssetLoader.font.draw(spriteBatch, "" + world.getFinalScore(), (136 / 2)
                - (3 * score.length() - 1), 11);
        }
        
        // Draw Game Over
        if(world.isGameOver()){
        	drawGameOver();
        	//AssetLoader.shadow.draw(spriteBatch, "Game Over", 25, 56);
           // AssetLoader.font.draw(spriteBatch, "Game Over", 24, 55);
        }
		spriteBatch.end();
		
		// If DRAW_BOUNDS is enabled
		if (Constants.DRAW_BOUNDS) {
			shapeRenderer.begin(ShapeType.Line);
			drawObjectsBoundaries();
			shapeRenderer.end();
		}
		
	}
	
	private void drawGameOver() {
		
		spriteBatch.draw(AssetLoader.gameOver, (Constants.TRUE_WIDTH/2) - 92/2, (Constants.TRUE_HEIGHT/2 - 14/2), 92, 14);
	}
	
	private void drawPlayer(float runTime){
		// Draw player first
				/*
				if (rocket.isMoving())
					spriteBatch.draw(rocketAnimation.getKeyFrame(runTime), rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
				else
					spriteBatch.draw(rocket1, rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
				*/
			
				if(!rocket.isMoving()){
					spriteBatch.draw(rocketFireAnimation.getKeyFrame(runTime), rocket.getX(), rocket.getY() + 25, Constants.ROCKET_FIRE_WIDTH, Constants.ROCKET_FIRE_HEIGHT);
					spriteBatch.draw(rocketMid, rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
				}
				else if (rocket.isMovingLeft()) {
					spriteBatch.draw(rocketFireAnimation.getKeyFrame(runTime), rocket.getX(), rocket.getY() + 25, Constants.ROCKET_FIRE_WIDTH, Constants.ROCKET_FIRE_HEIGHT);
					spriteBatch.draw(rocketLeft, rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
				}
				else if (rocket.isMovingRight()) {
					spriteBatch.draw(rocketFireAnimation.getKeyFrame(runTime), rocket.getX(), rocket.getY() + 25, Constants.ROCKET_FIRE_WIDTH, Constants.ROCKET_FIRE_HEIGHT);
					spriteBatch.draw(rocketRight, rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
				}
				
		
	}
	private void drawObjects(float runTime){

		
		
		// Draw all obstacles
		for(AbstractObstacle items : world.getScroller().getAbstractObstacles())
		{
			if(items instanceof Meteor)
			{
				
				// spriteBatch.draw(sMeteor, items.getX(), items.getY(), items.getWidth(), items.getHeight());
				spriteBatch.draw(sMeteor, items.getX(), items.getY(), items.getMiddleX(), items.getMiddleY(), items.getWidth(), items.getHeight(), 1f, 1f, items.getRotation());
			}
			else if(items instanceof HotAirBalloon)
			{
				if (items.getDirection().equals(DIRECTION.DOWN_RIGHT))
					spriteBatch.draw(hotAirBalloon_flipped, items.getX(), items.getY(), items.getWidth(), items.getHeight());
				else
					spriteBatch.draw(hotAirBalloon, items.getX(), items.getY(), items.getWidth(), items.getHeight());
				
			}
			else if(items instanceof JetPlane)
			{
				if (items.getDirection().equals(DIRECTION.DOWN_RIGHT))
					spriteBatch.draw(jetPlane_flipped, items.getX(), items.getY(), items.getWidth(), items.getHeight());
				else
					spriteBatch.draw(jetPlane, items.getX(), items.getY(), items.getWidth(), items.getHeight());
			}
		}
	}
	
	public void drawObjectsBoundaries() {
		
		// Draw boundary for player
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(world.getRocket().getX(), world.getRocket().getY(), world.getRocket().getWidth(), world.getRocket().getHeight());
		
		// Now draw boundaries for all obstacles
		for (AbstractObstacle items : world.getScroller().getAbstractObstacles()) {
			if(items instanceof HotAirBalloon){
				shapeRenderer.circle(((HotAirBalloon) items).getCirc().x, ((HotAirBalloon) items).getCirc().y, ((HotAirBalloon) items).getCirc().radius);
				shapeRenderer.rect(items.getRect().x, items.getRect().y, items.getRect().width, items.getRect().height);
			}
			if(items instanceof Projectile){
				shapeRenderer.circle(((Projectile) items).getCirc().x, ((Projectile) items).getCirc().y, ((Projectile) items).getCirc().radius);
			}
			else shapeRenderer.rect(items.getRect().x, items.getRect().y, items.getRect().width, items.getRect().height);
	
		}
	}
	
	public void initGameObjects() {
		rocket = world.getRocket();
		objectList = world.getScroller().getAbstractObstacles();
		background = world.getScroller().getFrontBackground();
		
	}
	
	public void initGameAssets() {
		rocketLeft = AssetLoader.rocketLeft;
		rocketMid = AssetLoader.rocket;
		rocketRight = AssetLoader.rocketRight;
		rocketAnimation = AssetLoader.rocketAnimation;
		rocketFireAnimation = AssetLoader.rocketFireAnimation;
		sMeteor = AssetLoader.meteor;
		hotAirBalloon = AssetLoader.hotAirBalloon;
		hotAirBalloon_flipped = AssetLoader.hotAirBalloon_flipped;
		jetPlane = AssetLoader.jetPlane;
		jetPlane_flipped = AssetLoader.jetPlane_flipped;
		
		gameOver = AssetLoader.gameOver;
		
		bg = AssetLoader.bg;
		
		
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
