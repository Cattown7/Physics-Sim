package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ground extends GameObject{
	
	private double velX = 0;
	private double velY = 0;
	private double b = 5;
	
	private int bounce = 0;
	private int fall = 0;
	
	private Game game;
	
	public Ground(double x, double y, Game game) {
		super (x,y);
		this.game = game;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 300 && fall == 0) {
			y = 300;
			velY = 0;
			game.setIsOnGround(1);
			if(bounce == 1) {
				b -= 1;
				velY = b;
				if(b <= 0) {
					b = 5;
					bounce = 0;
				}
			}
		}else if(y > 300 && fall == 0) {
			velY -= .5;
			bounce = 1;
			game.setIsOnGround(0);
		}
		if(x > 384) {
			game.setIsOnGround(0);
			fall = 1;
			velY -= .5;
			if(y < -1000) {
				x = -2000;
				y = 700;
				bounce = 1;
				b = 10;
				fall = 0;
			}
		}else if(x < -3616) {
			velY -=.5;
			fall = 1;
			game.setIsOnGround(0);
			if(y < -1000) {
				x = -2000;
				y = 700;
				bounce = 1;
				b = 10;
				fall = 0;
			}
		}
	}

	public void render(Graphics g) {
		g.fillRect((int)x, (int)y, 100, 100);
		g.fillRect((int)x + 200, (int)y, 100, 100);
		g.fillRect((int)x + 400, (int)y, 100, 100);
		g.fillRect((int)x + 600, (int)y, 100, 100);
		g.fillRect((int)x + 800, (int)y, 100, 100);
		g.fillRect((int)x + 1000, (int)y, 100, 100);
		g.fillRect((int)x + 1200, (int)y, 100, 100);
		g.fillRect((int)x + 1400, (int)y, 100, 100);
		g.fillRect((int)x + 1600, (int)y, 100, 100);
		g.fillRect((int)x + 1800, (int)y, 100, 100);
		g.fillRect((int)x + 2000, (int)y, 100, 100);
		g.fillRect((int)x + 2200, (int)y, 100, 100);
		g.fillRect((int)x + 2400, (int)y, 100, 100);
		g.fillRect((int)x + 2600, (int)y, 100, 100);
		g.fillRect((int)x + 2800, (int)y, 100, 100);
		g.fillRect((int)x + 3000, (int)y, 100, 100);
		g.fillRect((int)x + 3200, (int)y, 100, 100);
		g.fillRect((int)x + 3400, (int)y, 100, 100);
		g.fillRect((int)x + 3600, (int)y, 100, 100);
		g.fillRect((int)x + 3800, (int)y, 100, 100);
		g.setColor(Color.white);
		g.fillRect((int)x + 100, (int)y, 100, 100);
		g.fillRect((int)x + 300, (int)y, 100, 100);
		g.fillRect((int)x + 500, (int)y, 100, 100);
		g.fillRect((int)x + 700, (int)y, 100, 100);
		g.fillRect((int)x + 900, (int)y, 100, 100);
		g.fillRect((int)x + 1100, (int)y, 100, 100);
		g.fillRect((int)x + 1300, (int)y, 100, 100);
		g.fillRect((int)x + 1500, (int)y, 100, 100);
		g.fillRect((int)x + 1700, (int)y, 100, 100);
		g.fillRect((int)x + 1900, (int)y, 100, 100);
		g.fillRect((int)x + 2100, (int)y, 100, 100);
		g.fillRect((int)x + 2300, (int)y, 100, 100);
		g.fillRect((int)x + 2500, (int)y, 100, 100);
		g.fillRect((int)x + 2700, (int)y, 100, 100);
		g.fillRect((int)x + 2900, (int)y, 100, 100);
		g.fillRect((int)x + 3100, (int)y, 100, 100);
		g.fillRect((int)x + 3300, (int)y, 100, 100);
		g.fillRect((int)x + 3500, (int)y, 100, 100);
		g.fillRect((int)x + 3700, (int)y, 100, 100);
		g.fillRect((int)x + 3900, (int)y, 100, 100);
	}
	
	public Rectangle getBounds () {
		return new Rectangle((int)x, (int)y, 4000, 100);
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
