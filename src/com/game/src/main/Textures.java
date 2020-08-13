package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {
	
	
	public BufferedImage player;
	public BufferedImage coin;

	private SpriteSheet ss = null;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures() {
		player = ss.grabImage(1, 1, 32, 32);
		coin = ss.grabImage(2, 1, 16, 16);
	}
	
}