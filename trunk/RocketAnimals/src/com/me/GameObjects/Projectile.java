package com.me.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.me.helpers.AssetLoader;

public class Projectile extends AbstractObstacle{
	
	protected Circle collisionCirc;
	
	public Projectile(float x, float y, int width, int height, float ySpeed) {
		super(x, y, width, height, ySpeed);
		
		collisionCirc = new Circle();
		
	}
	
	public Projectile(float x, float y, int width, int height, float xSpeed, float ySpeed) {
		super(x, y, width, height, ySpeed);
		
		this.velocity.x = xSpeed;
		
		collisionCirc = new Circle();
		
		hitBox.setVertices(bullet_polygon);
		hitBox.setScale((float)((double)width / AssetLoader.bullets.getRegionWidth()), (float)((double)height / AssetLoader.bullets.getRegionHeight()));
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		collisionCirc.set(position.x + (width/2), position.y + (height/2), 8);
	}
	
	
	public Circle getCirc(){ return collisionCirc; }

	
	
	

}
