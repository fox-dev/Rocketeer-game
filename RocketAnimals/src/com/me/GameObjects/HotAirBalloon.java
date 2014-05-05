package com.me.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.me.helpers.AssetLoader;
import com.me.helpers.Constants;

public class HotAirBalloon extends AbstractObstacle{
	
	public HotAirBalloon(float x, float y, int width, int height,
			float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
			
	}
	
	public HotAirBalloon(float x, float y, int width, int height,
			float scrollSpeed, float xSpeed, Constants.DIRECTION direction) {
		super(x, y, width, height, scrollSpeed, xSpeed, direction);
		
		hitBox.setVertices(hotAirBalloon_polygon);
		hitBox.setScale((float)((double)width / AssetLoader.hotAirBalloon.getRegionWidth()), (float)((double)height / AssetLoader.hotAirBalloon.getRegionHeight()));
	}
	
	@Override
	public void update(float delta){
		super.update(delta);

	}
	
}
