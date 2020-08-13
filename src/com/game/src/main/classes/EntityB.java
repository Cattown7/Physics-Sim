package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

//import javafx.geometry.Bounds;
//import javafx.scene.shape.Circle;

public interface EntityB {
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
}