package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import world.World;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize=16; //16*16 tile
	final int scale=3;
	
	public final int tileSize=scale*originalTileSize; //48*48 tile
	public final int maxScreenCol =16;
	public final int maxScreenRow =12;
	final int screenWidth=tileSize*maxScreenCol;   //768 pixels
	final int screenHeight= tileSize*maxScreenRow; //576 pixels
	
	// FPS
	int FPS= 60;
	
	Thread gameThread;	
	
	public Player player;
	public World world;
	
	public GamePanel() {
		player=new Player(this);
		addKeyListener(player.keyH);
		
		world=new World(this);
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawIntervall = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawIntervall;
		
		while(gameThread != null) {
//			System.out.println("Game is running...");
			
			// 1 UPDATE: update information such as character position
			player.update();
			world.update();
			
			// 2 DRAW: draw the screen with the updated information
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime= remainingTime / 1000000;
				
				if(remainingTime < 0) {
					remainingTime=0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawIntervall;
			} catch (InterruptedException e){
				
			}
			
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;

	    world.draw(g2);
	    player.draw(g2);

	    g2.dispose();
	}

}
