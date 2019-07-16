package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import game.GameData.Directions;

public class Pacman {

	private Directions direction, prevDirection;
	private int row, column;
	private boolean spriteOpen;

	public Pacman() {
		prevDirection = Directions.STILL;
		direction = Directions.STILL;
		// initSpriteTimer();
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
			if (row - 1 >= 0 && !Game.game.getTiles()[row - 1][column].isBarrierTile()) {
				row--;
			}
			break;
		case DOWN:
			if (row + 1 < GameData.GRID_ROWS && !Game.game.getTiles()[row + 1][column].isBarrierTile()) {
				row++;
			}
			break;
		case LEFT:
			if (column - 1 >= 0 && !Game.game.getTiles()[row][column - 1].isBarrierTile()) {
				column--;
			}
			break;
		case RIGHT:
			if (column + 1 < GameData.GRID_COLUMNS && !Game.game.getTiles()[row][column + 1].isBarrierTile()) {
				column++;
			}
			break;
		case STILL:
			break;
		}
		ArrayList<Food> food = Game.game.getFood();
		for(int i = 0; i < food.size(); i++) {
			if(row == food.get(i).getRow() && column == food.get(i).getColumn()) {
				food.get(i).consume();
			}
		}
	}

	public void setDirection(Directions direction) {
		switch (direction) {
		case UP:
			if (row - 1 < 0 || Game.game.getTiles()[row - 1][column].isBarrierTile())
				return;
			break;
		case DOWN:
			if (row + 1 >= GameData.GRID_ROWS || Game.game.getTiles()[row + 1][column].isBarrierTile())
				return;
			break;
		case LEFT:
			if (column - 1 < 0 || Game.game.getTiles()[row][column - 1].isBarrierTile())
				return;
			break;
		case RIGHT:
			if (column + 1 >= GameData.GRID_COLUMNS || Game.game.getTiles()[row][column + 1].isBarrierTile())
				return;
			break;
		case STILL:
			break;
		}
		this.direction = direction;
		System.out.println("Direction set to: " + direction);
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		if (!spriteOpen) {
			g.fillOval(column * GameData.TILE_WIDTH + GameData.PACMAN_SHRINK_SCALE,
					row * GameData.TILE_HEIGHT + GameData.PACMAN_SHRINK_SCALE,
					GameData.TILE_WIDTH - (GameData.PACMAN_SHRINK_SCALE * 2),
					GameData.TILE_HEIGHT - (GameData.PACMAN_SHRINK_SCALE * 2));
		} else {
			g.drawImage(GameData.PACMAN_SPRITE, column * GameData.TILE_WIDTH, row * GameData.TILE_HEIGHT,
					GameData.TILE_WIDTH, GameData.TILE_HEIGHT, null);
		}
	}

}
