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
	private double x, y, row, column;
	private boolean spriteOpen;

	public Pacman() {
		prevDirection = Directions.STILL;
		direction = Directions.STILL;
		// initSpriteTimer();
		row = 5;
		column = 9;
		center();
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

	private boolean atIntersection() {
		int row = (int) this.row, column = (int) this.column;
		switch (direction) {
		case DOWN:
		case UP:
			if (column - 1 >= 0 && !Game.game.getTiles()[row][column - 1].isBarrierTile()) {
				return true;
			}
			if (column + 1 < GameData.GRID_COLUMNS && !Game.game.getTiles()[row][column + 1].isBarrierTile()) {
				return true;
			}
			break;
		case LEFT:
		case RIGHT:
			if (row - 1 >= 0 && !Game.game.getTiles()[row - 1][column].isBarrierTile()) {
				return true;
			}
			if (row + 1 < GameData.GRID_ROWS && !Game.game.getTiles()[row + 1][column].isBarrierTile()) {
				return true;
			}
			break;
		case STILL:
			return true;
		}
		return false;
	}

	public void move() {
		if (row - (int) row != 0 || column - (int) column != 0) {
			intermediaryMove();
			return;
		}
		int row = (int) this.row, column = (int) this.column;
		switch (direction) {
		case UP:
			if (row - 1 >= 0 && !Game.game.getTiles()[row - 1][column].isBarrierTile()) {
				this.row -= GameData.PACMAN_VELOCITY;
				// y -= GameData.PACMAN_VELOCITY;
			}
			break;
		case DOWN:
			if (row + 1 < GameData.GRID_ROWS && !Game.game.getTiles()[row + 1][column].isBarrierTile()) {
				this.row += GameData.PACMAN_VELOCITY;
				// y += GameData.PACMAN_VELOCITY;
			}
			break;
		case LEFT:
			if (column - 1 >= 0 && !Game.game.getTiles()[row][column - 1].isBarrierTile()) {
				this.column -= GameData.PACMAN_VELOCITY;
				// x -= GameData.PACMAN_VELOCITY;
			}
			break;
		case RIGHT:
			if (column + 1 < GameData.GRID_COLUMNS && !Game.game.getTiles()[row][column + 1].isBarrierTile()) {
				this.column += GameData.PACMAN_VELOCITY;
				// x += GameData.PACMAN_VELOCITY;
			}
			break;
		case STILL:
			break;
		}
		checkForFood();
	}

	private void intermediaryMove() {
		switch (direction) {
		case UP:
			row -= GameData.PACMAN_VELOCITY;
			break;
		case DOWN:
			row += GameData.PACMAN_VELOCITY;
			break;
		case LEFT:
			column -= GameData.PACMAN_VELOCITY;
			break;
		case RIGHT:
			column += GameData.PACMAN_VELOCITY;
			break;
		case STILL:
			break;
		}
	}

	private void checkForFood() {
		for (int i = 0; i < Game.game.getFood().size(); i++) {
			if ((int) row == Game.game.getFood().get(i).getRow() && (int) column == Game.game.getFood().get(i).getColumn()) {
				Game.game.getFood().get(i).consume();
			}
		}
	}

	private void setRowAndColumn() {
		row = y / (GameData.TILE_HEIGHT);
		column = x / (GameData.TILE_WIDTH);
	}

	private void center() {
//		y = row * (GameData.TILE_HEIGHT);
//		x = column * (GameData.TILE_WIDTH);
		row = (int) row;
		column = (int) column;
	}

	public void setDirection(Directions direction) {
		if (this.direction == direction) {
			return;
		}
		int row = (int) this.row, column = (int) this.column;
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
		if ((this.direction == Directions.UP || this.direction == Directions.DOWN)
				&& (direction == Directions.LEFT || direction == Directions.RIGHT)) {
			center();
		} else if ((this.direction == Directions.LEFT || this.direction == Directions.RIGHT)
				&& (direction == Directions.UP || direction == Directions.DOWN)) {
			center();
		}
		this.direction = direction;
	}

	public int getRow() {
		return (int) row;
	}

	public int getColumn() {
		return (int) column;
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		if (!spriteOpen) {
			g.fillOval((int) (column * GameData.TILE_WIDTH + GameData.PACMAN_SHRINK_SCALE),
					(int) (row * GameData.TILE_HEIGHT + GameData.PACMAN_SHRINK_SCALE),
					GameData.TILE_WIDTH - (GameData.PACMAN_SHRINK_SCALE * 2),
					GameData.TILE_HEIGHT - (GameData.PACMAN_SHRINK_SCALE * 2));
//			g.fillOval(x + GameData.PACMAN_SHRINK_SCALE, y + GameData.PACMAN_SHRINK_SCALE, GameData.TILE_WIDTH - (GameData.PACMAN_SHRINK_SCALE * 2),
//					GameData.TILE_HEIGHT - (GameData.PACMAN_SHRINK_SCALE * 2));
		} else {
//			g.drawImage(GameData.PACMAN_SPRITE, column * GameData.TILE_WIDTH, row * GameData.TILE_HEIGHT,
//					GameData.TILE_WIDTH, GameData.TILE_HEIGHT, null);
		}
	}

}
