package game;

import java.awt.Color;
import java.awt.Graphics;

import game.GameData.Directions;

public class Ghost {

	private int row, column;
	private Color color;
	private Directions direction;

	public Ghost(int row, int column, Color color) {
		this.row = row;
		this.column = column;
		this.color = color;
		direction = Directions.STILL;
		for(int i = 0; i < Game.game.getFood().size(); i++) {
			if(Game.game.getFood().get(i).getRow() == row && Game.game.getFood().get(i).getColumn() == column) {
				Game.game.getFood().get(i).consume();
			}
		}
	}

	public void move() {
		switch (direction) {
		case UP:
			if (row - 1 >= 0) {
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
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(column * GameData.TILE_WIDTH + GameData.PACMAN_SHRINK_SCALE,
				row * GameData.TILE_HEIGHT + GameData.PACMAN_SHRINK_SCALE,
				GameData.TILE_WIDTH - (GameData.PACMAN_SHRINK_SCALE * 2),
				GameData.TILE_HEIGHT - (GameData.PACMAN_SHRINK_SCALE * 2));
	}
	
}
