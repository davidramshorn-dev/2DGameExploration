package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	public KeyHandler keyH=new KeyHandler();
	GamePanel panel;
	private BufferedImage sprite;

	public Player(int x, int y, int maxSpeed, GamePanel panel) {
		super(x, y, maxSpeed);
		loadImage();
		this.panel=panel;
		
		acceleration=0.5;
	}
	public Player(GamePanel panel) {
		super(100, 100, 8);
		loadImage();
		this.panel=panel;
		
		acceleration=0.5;
	}
	
	private void loadImage() {
        try {
            sprite = ImageIO.read(getClass().getResource("/player01.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void update() {
	    Direction dir = null;

	    if (keyH.upPressed)    dir = Direction.UP;
	    else if (keyH.downPressed) dir = Direction.DOWN;
	    else if (keyH.leftPressed) dir = Direction.LEFT;
	    else if (keyH.rightPressed) dir = Direction.RIGHT;

	    if (dir != null) {
	        accelerate(dir);
	        // Bewegung basierend auf lastDirection oder dir:
	        switch (lastDirection) {
	            case UP:    y -= currentSpeed; break;
	            case DOWN:  y += currentSpeed; break;
	            case LEFT:  x -= currentSpeed; break;
	            case RIGHT: x += currentSpeed; break;
	        }
	    } else {
	        // keine Taste: langsam abbremsen
	        currentSpeed *= 0.85;
	        if (currentSpeed < 0.1) currentSpeed = 0;
	    }
	}

	private void accelerate(Direction d) {
		
		if (d==lastDirection) {
			currentSpeed+=acceleration;
			if(currentSpeed>=maxSpeed) {
				currentSpeed=maxSpeed;
			}
		}
		else {
			currentSpeed=0.5;
			lastDirection=d;
			System.out.println(lastDirection);
		}
		
	}
	@Override
	public void draw(Graphics2D g2) {
	    g2.drawImage(sprite, x, y, panel.tileSize, panel.tileSize, null);
	}

}
