package com.me.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AssetLoader {
	
	public static Texture texture;
	
	public static Texture texture2; //temporary
	
	public static TextureRegion bg; //temp
	
	public static TextureRegion rocketLeft, rocket, rocketRight, meteor, hotAirBalloon, hotAirBalloon_flipped, 
	 							jetPlane, jetPlane_flipped, rocketFire1, rocketFire2, rocketFire3, gameOver;
	public static Animation rocketAnimation, rocketFireAnimation;
	
	public static Sound hit1, hit2;
	public static Array<Sound> hitSounds;
	
	public static Music bgm; // change names later!
	
	public static BitmapFont font, shadow;
	
	public static void load() {
		
		hitSounds = new Array<Sound>();
		
		
		// Load the texture
		texture = new Texture(Gdx.files.internal("data/spritesheet.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		texture2 = new Texture(Gdx.files.internal("data/texture.png")); //temporary
		
		//Bg temp
		bg = new TextureRegion(texture2, 0, 0, 136, 43);
		bg.flip(false, true);
		
		
		// Rocket
		rocketLeft = new TextureRegion(texture, 0, 0, 45, 48);
		rocket = new TextureRegion(texture, 58, 0, 45, 48); // Spritesheet may need fixing
		rocketRight = new TextureRegion(texture, 115, 0, 45, 48	);
		
		rocketLeft.flip(false, true);
		rocket.flip(false, true);
		rocketRight.flip(false, true);
		
		// Rocket Animation
		TextureRegion[] rockets = {rocketLeft, rocket, rocketRight};
		rocketAnimation = new Animation(0.01f, rockets);
		rocketAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
		// Rocket fire
		rocketFire1 = new TextureRegion(texture, 3, 48, 39, 30);
		rocketFire2 = new TextureRegion(texture, 61, 48, 39, 30);
		rocketFire3 = new TextureRegion(texture, 118, 48, 39, 30);
		
		rocketFire1.flip(false, true);
		rocketFire2.flip(false, true);
		rocketFire3.flip(false, true);
		
		// Rocket fire Animation
		TextureRegion[] fire = {rocketFire1, rocketFire2, rocketFire3};
		rocketFireAnimation = new Animation(0.05f, fire);
		rocketFireAnimation.setPlayMode(Animation.LOOP);
		
		// Meteor
		meteor = new TextureRegion(texture, 192, 6, 49, 48); //change sprite later
		
		//HotAirBalloon
		hotAirBalloon = new TextureRegion(texture, 7, 90, 83, 139);
		hotAirBalloon.flip(false, true);
		hotAirBalloon_flipped = new TextureRegion(texture, 7, 90, 83, 139);
		hotAirBalloon_flipped.flip(true, true);
		
		// JetPlane
		jetPlane = new TextureRegion(texture, 97, 90, 158, 58);
		jetPlane.flip(false, true);
		jetPlane_flipped = new TextureRegion(texture, 97, 90, 158, 58);
		jetPlane_flipped.flip(true, true);
		
		
		// Load audio
		hit1 = Gdx.audio.newSound(Gdx.files.internal("data/hit1.wav"));
		hit2 = Gdx.audio.newSound(Gdx.files.internal("data/hit2.wav"));
		hitSounds.add(hit1);
		hitSounds.add(hit2);
		
		// Load music
		bgm = Gdx.audio.newMusic(Gdx.files.internal("data/themoon.mp3"));
		
		//Font
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);
        
        //Gameover
        gameOver = new TextureRegion(texture2, 59, 92, 46, 7);
		gameOver.flip(false, true);

	}
	
	public static void dipose() {
		// Dipose of the texture when we're done
		texture.dispose();
		
		// Also dispose of audio
		hit1.dispose();
		bgm.dispose();
		
		//Fonts
		font.dispose();
		shadow.dispose();
	}
}
