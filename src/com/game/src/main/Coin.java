package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;


public class Coin extends GameObject implements EntityB{
	
	private Game game;
	private Ground gd;
	private Controller cont;
	
	public Coin(double x, double y, Game game, Controller cont, Ground gd) {
		super(x, y);
		this.game = game;
		this.cont = cont;
		this.gd = gd;
	}
	
	public void tick() {
		x += gd.getVelX();
		y += gd.getVelY();
		for(int i = 0; i < game.ea.size(); i++)
		{
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt)) 
			{
				cont.removeEntity(this);
			}else{
				
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, 16, 16);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
