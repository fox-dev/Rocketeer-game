package com.me.GameObjects;

import com.badlogic.gdx.math.Rectangle;

public class HotAirBalloon extends AbstractObstacle{
	
	protected Rectangle collisionRect;
	

	public HotAirBalloon(float x, float y, int width, int height,
			float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		
		
		collisionRect = new Rectangle(x,y,width,height);
	}
	
	@Override
	public void update(float delta){
		
		
		position.add(velocity.cpy().scl(delta));
		
		// If the Scrollable object is no longer visible:
        if (position.y > 480) {
            isScrolledDown = true;
        }
		
		collisionRect.setPosition(position);
		
		
	}
	
	public Rectangle getRect() { return collisionRect; }

}
