package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class World {
	public int maxTilesWidth = 9;
	public int maxTilesHeight = 9;
	public HashMap<Point, int[][]> savesPatterns=new HashMap<>(); //view Koordinate, als Schlüsel zu den Mustern
	
	public int viewCol=0;
	public int viewRow=0;

	GamePanel panel;
	private BufferedImage grassBright, grassDark;
	private int numberTiles=2;
	private int counter=0;

	public World(GamePanel panel) {
		this.panel = panel;
		
		maxTilesHeight = panel.maxScreenRow;
		maxTilesWidth = panel.maxScreenCol;
		
		if (maxTilesWidth%2==0) {
			maxTilesWidth++;
		} //damit Schachbrettmuster entsteht
		
		grassBright = loadImage("/grassLight.png");
		grassDark = loadImage("/grassDark.png");
	}

	private void createTilePattern(Point view) {
		int [][] pattern = new int[maxTilesHeight][maxTilesWidth];
		for (int row = 0; row < maxTilesHeight; row++) {
			for (int col = 0; col < maxTilesWidth; col++) {
				
				if (col==maxTilesWidth-1 && maxTilesWidth%2==0) {
					change();
				} //damit Schachbrettmuster entsteht
				
				pattern[row][col] = change();
				
				if (pattern[row][col]==0) {
					Random random=new Random();
					if (random.nextInt(5)==1) {
						pattern[row][col]=1;
					}
				}
				
			}
		}
		savesPatterns.put(view, pattern);
	}

	private int change() {
		counter++;
		if(counter>=numberTiles) {
			counter=0;
		}
		return counter;
	}

	private BufferedImage loadImage(String s) {
		try {
			return ImageIO.read(getClass().getResource(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void draw(Graphics2D g2) {
		Point view = new Point(viewCol, viewRow);
		if (savesPatterns.get(view)==null) {
			createTilePattern(view);
		}
		int[][] pattern = savesPatterns.get(view); //selects the proper pattern
		for (int row = 0; row < maxTilesHeight; row++) {
			for (int col = 0; col < maxTilesWidth; col++) {
				if (pattern[row][col] == 0) {
					g2.drawImage(grassBright, col*panel.tileSize, row*panel.tileSize, panel.tileSize, panel.tileSize, null);
				} else {
					g2.drawImage(grassDark, col*panel.tileSize, row*panel.tileSize, panel.tileSize, panel.tileSize, null);
				}
			}
		}
	}
	
	public void update() {
		outsideView(panel.player);

	}

	private void outsideView(Player player) {
		if (player.x > panel.maxScreenCol*panel.tileSize) {
			player.x=-panel.tileSize;
			viewCol++;
			printViewPosition();
		}
		if (player.x < -panel.tileSize) {
			player.x=panel.maxScreenCol*panel.tileSize;
			viewCol--;
			printViewPosition();
		}
		if (player.y > panel.maxScreenRow*panel.tileSize) {
			player.y=-panel.tileSize;
			viewRow++;
			printViewPosition();
		}
		if (player.y < -panel.tileSize) {
			player.y=panel.maxScreenRow*panel.tileSize;
			viewRow--;
			printViewPosition();
		}
	}

	private void printViewPosition() {
		System.out.println("Current View: "+ viewCol+ " / "+ viewRow);
		
	}

}
