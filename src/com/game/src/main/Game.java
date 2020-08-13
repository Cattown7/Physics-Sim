package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Physics Sim";
	
	private boolean running = false;
	private int isOnGround = 1;
	private int momentum = 0;
	private int negMomentum = 0;
	private int acceleration = 0;
	private int negAcceleration = 0;
	private int coin_count = 10;
	private double speed = 0;
	private double s = 8;
	private Thread thread;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	private Player p;
	private Textures tex;
	private Ground gd;
	private Controller cont;
	
	private double gravity = .5;
	
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			
			spriteSheet = loader.loadImage("/256x256grid_spritesheet.png");
			//background = loader.loadImage("/Background.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		p = new Player(384, 268, tex, this);
		gd = new Ground(-2000,300, this);
		cont = new Controller(this, gd);
		
		ea = cont.getEntityA();
		eb = cont.getEntityB();
		
		addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		cont.createCoin(coin_count);
	}
	
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames= 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		gd.render(g);
		p.render(g);
		cont.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void tick() {
		cont.tick();
		p.tick();
		gd.tick();
		if(momentum == 1) {
			speed -= .075;
			gd.setVelX(speed);
			if(speed <= 0) {
				momentum = 0;
				gd.setVelX(0);
			}
		}else if(negMomentum == 1) {
			speed += .075;
			gd.setVelX(speed);
			if(speed >= 0) {
				negMomentum = 0;
				gd.setVelX(0);
			}
		}
		if(acceleration == 1) {
			speed += .1;
			gd.setVelX(speed);
			if(speed >= s) {
				acceleration = 0;
				speed = s;
				gd.setVelX(speed);
			}
		}
		if(negAcceleration == 1) {
			speed -= .1;
			gd.setVelX(speed);
			if(speed <= -s) {
				negAcceleration = 0;
				speed = -s;
				gd.setVelX(speed);
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			if(isOnGround == 1) {
				gd.setVelY(10);
			}else if(isOnGround == 0) {
				
			}
		}else if(key == KeyEvent.VK_A) {
			momentum = 0;
			negMomentum = 0;
			acceleration = 1;
		}else if(key == KeyEvent.VK_D) {
			negMomentum = 0;
			momentum = 0;
			negAcceleration = 1;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A) {
			acceleration = 0;
			if(speed <= 0) {
				negMomentum = 1;
			}else if(speed >= 0) {
				momentum = 1;
			}
		}else if(key == KeyEvent.VK_D) {
			negAcceleration = 0;
			if(speed >= 0) {
				momentum = 1;
			}else if(speed <= 0) {
				negMomentum = 1;
			}
		}
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public int getIsOnGround() {
		return isOnGround;
	}

	public void setIsOnGround(int isOnGround) {
		this.isOnGround = isOnGround;
	}
	
}