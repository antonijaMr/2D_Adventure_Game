package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize = 16; 	
	final int scale = 3;
	
	public final int tileSize = originalTileSize*scale; //48x48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeigth = tileSize * maxScreenRow; 
	
	//FPS - Frames per second
	int FPS = 30;
	
	TileManager tileM = new TileManager(this);
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; //like a clock? needs to implement Runnable
	Player player = new Player(this,keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // for better rendering performace
		
		this.addKeyListener(keyH);//dodali smo keylistener
		this.setFocusable(true);// ????
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start(); // this is calli ng the run method
	}

	public void run() {
		//the core of the game
		double drawInterval =  1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			//UPDATE: update information like character position
			//DRAW: draw the screen with the updated information
			update();
			repaint(); //this is how to call paintComponent mathod
			double remainingTime = nextDrawTime - System.nanoTime();
			remainingTime = remainingTime/1000000;
			if(remainingTime < 0) {
				remainingTime = 0;
			}
			try {
				Thread.sleep((long)remainingTime); //takes miliseconds
				nextDrawTime +=drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {//standard name for drawing
		super.paintComponent(g); //super = Jpanel
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose(); //relese system resources
		
	}
}
















