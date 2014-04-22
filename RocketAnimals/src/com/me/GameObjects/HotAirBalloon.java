package com.me.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class HotAirBalloon extends AbstractObstacle{
	
	protected Rectangle collisionRect;
	protected Circle collisionCirc;
	

	public HotAirBalloon(float x, float y, int width, int height,
			float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		
		
		collisionRect = new Rectangle(x,y,width,height);
		collisionCirc = new Circle();
	}
	
	@Override
	public void update(float delta){
		
		
		position.add(velocity.cpy().scl(delta));
		
		// If the Scrollable object is no longer visible:
        if ((position.y - 45 + (height/2) - 30) > 480) {
            isScrolledDown = true;
        }
		
		collisionRect.set(position.x, position.y, width, height - 30);
		collisionCirc.set(position.x + (width/2), position.y - 45 + (height/2), 30);
		
		
	}
	
	public Rectangle getRect() { return collisionRect; }
	public Circle getCirc() { return collisionCirc;  }
	public float getMiddleX() { return (position.x + (width / 2)); }
	public float getMiddleY() { return (position.y + (height / 2)); }

}
