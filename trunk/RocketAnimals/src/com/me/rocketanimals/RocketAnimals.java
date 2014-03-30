package com.me.rocketanimals;

import com.badlogic.gdx.Game;
import com.me.helpers.AssetLoader;
import com.me.screens.GameScreen;

public class RocketAnimals extends Game {

	@Override
	public void create() {
		System.out.println("RocketAnimals created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
}