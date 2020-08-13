package com.game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	
	private double x;
	private double y;
	
	private int c;

	@SuppressWarnings("unused")
	private Game game;
	
	
	public void tick(Game game) {
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg1) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg2) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg3) {
		x = arg3.getX();
		y = arg3.getY();
		c = 1;
	}

	@Override
	public void mouseReleased(MouseEvent arg4) {
		c = 0;
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

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
}
