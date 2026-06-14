package entity;

import java.awt.Graphics2D;

public abstract class Entity {
	
	public int x;
	public int y;
	public int maxSpeed;
	public double currentSpeed;
	public Direction lastDirection=Direction.UP;
	public double acceleration;
	
	public Entity(int x, int y, int maxSpeed) {
		super();
		this.x = x;
		this.y = y;
		this.maxSpeed = maxSpeed;
	}
	
	abstract void update();
	abstract void draw(Graphics2D g2);
}

