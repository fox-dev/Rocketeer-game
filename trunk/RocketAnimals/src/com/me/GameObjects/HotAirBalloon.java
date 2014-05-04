package com.me.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.me.helpers.Constants;

public class HotAirBalloon extends AbstractObstacle{

	protected Circle collisionCirc;
	
	public HotAirBalloon(float x, float y, int width, int height,
			float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
			
		//collisionRect = new Rectangle(x,y,width,height);
		collisionRect.set(position.x + 10, position.y + 60, width - 28, height - 60);
		collisionCirc = new Circle();
	}
	
	public HotAirBalloon(float x, float y, int width, int height,
			float scrollSpeed, float xSpeed, Constants.DIRECTION direction) {
		super(x, y, width, height, scrollSpeed, xSpeed, direction);
		
		//collisionRect = new Rectangle(x,y,width,height);
		collisionRect.set(position.x + 10, position.y + 60, width - 28, height - 60);
		collisionCirc = new Circle();
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		collisionRect.set(position.x + 10, position.y + 60, width - 28, height - 60);
		collisionCirc.set(position.x + (width/2), position.y - 20  + (height/2), 30);
		
		
	}
	
	public Circle getCirc() { return collisionCirc;  }

}
