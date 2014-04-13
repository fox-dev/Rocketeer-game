package com.me.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	
	public static TextureRegion rocket1, rocket2, rocket3, meteor;
	public static Animation rocketAnimation;
	
	public static Sound hit;
	
	public static Music bgm; // change names later!
	
	public static void load() {
		
		// Load the texture
		texture = new Texture(Gdx.files.internal("data/spritesheet.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		// Rocket
		rocket1 = new TextureRegion(texture, 0, 0, 67, 74);
		rocket2 = new TextureRegion(texture, 103, 0, 67, 74);
		rocket3 = new TextureRegion(texture, 222, 0, 67, 74);
		
		rocket1.flip(false, true);
		rocket2.flip(false, true);
		rocket3.flip(false, true);
		
		// Meteor
		meteor = new TextureRegion(texture, 337, 0, 81, 80); //change sprite later
		
		// Rocket Animation
		TextureRegion[] rockets = {rocket1, rocket2, rocket3};
		rocketAnimation = new Animation(0.01f, rockets);
		rocketAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
		// Load audio
		hit = Gdx.audio.newSound(Gdx.files.internal("data/hit.wav"));
		bgm = Gdx.audio.newMusic(Gdx.files.internal("data/themoon.mp3"));
	}
	
	public static void dipose() {
		// Dipose of the texture when we're done
		texture.dispose();
		
		// Also dispose of audio
		hit.dispose();
		bgm.dispose();
	}
}
