package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import game.GameData.Directions;

public class Pacman {

	private Directions direction;
	private int row, column;
	private boolean spriteOpen;

	public Pacman() {
		direction = Directions.STILL;
	//	initSpriteTimer();
	}

	private void initSpriteTimer() {
		new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				spriteOpen = !spriteOpen;
			}
		}).start();
	}

	public void move() {
		switch (direction) {
		case UP:
			row--;
			break;
		case DOWN:
			row++;
			break;
		case LEFT:
			column--;
			break;
		case RIGHT:
			column++;
			break;
		case STILL:
			break;
		}
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		if (!spriteOpen) {
			g.fillOval(column * GameData.TILE_WIDTH, row * GameData.TILE_HEIGHT, GameData.TILE_WIDTH,
					GameData.TILE_HEIGHT);
		}else {
			g.drawImage(GameData.PACMAN_SPRITE, column * GameData.TILE_WIDTH, row * GameData.TILE_HEIGHT, GameData.TILE_WIDTH,
					GameData.TILE_HEIGHT, null);
		}
	}

}
