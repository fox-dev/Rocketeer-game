package com.me.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	
	public static Animation rocketAnimation;
	public static TextureRegion rocket1, rocket2, rocket3;
	
	
	public static void load() {
		
		// Load the texture
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		// Rocket
		rocket1 = new TextureRegion(texture, 153, 0, 17, 12);
		rocket2 = new TextureRegion(texture, 170, 0, 17, 12);
		rocket3 = new TextureRegion(texture, 136, 0, 17, 12);
		
		// Rocket Animation
		TextureRegion[] rockets = {rocket1, rocket2, rocket3};
		rocketAnimation = new Animation(0.01f, rockets);
		rocketAnimation.setPlayMode(Animation.LOOP_PINGPONG);
	}
	
	public static void dipose() {
		// Dipose of the texture when we're done
		texture.dispose();
	}
}
