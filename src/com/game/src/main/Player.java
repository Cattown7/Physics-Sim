package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;


public class Player extends GameObject implements EntityA{

	private double velX = 0;
	private double velY = 0;
	//private double b = -8;
	
	//private int bounce = 0;
	
	private Game game;
	private Textures tex;
	
	public Player(double x, double y, Textures tex, Game game) {
		super (x, y);
		this.tex = tex;
		this.game = game;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y >= 548) {
			y = 548;
			velY = 0;
			game.setIsOnGround(1);
			/*if(bounce == 1) {
				b += 1;
				velY = b;
				if(b >= 0) {
					b = -5;
					bounce = 0;
				} */
			}
		}/*else if (y < 548){
			velY += .5;
			bounce = 1;
			game.setIsOnGround(0);
		} */
		
	//}
	
	public void render(Graphics g) {
		g.drawImage(tex.player, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
}
