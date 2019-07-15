package game;

import java.awt.Graphics;

import game.GameData.Directions;

public class Pacman {

	private Directions direction;
	private int row, column;
	
	public Pacman() {
		
	}
	
	public void move() {
		switch(direction) {
		case UP:
			break;
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}
	}
	
	public void setDirection(Directions direction) {
		this.direction = direction;
	}
	
	public void render(Graphics g) {
		
	}
	
}
