package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Food {

	private int row, column;
	private boolean isMajorFood;

	public Food(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public void consume() {
		Game.game.getFood().remove(this);
		Game.game.increaseScore();
	}

	public void setAsMajorFood(boolean isMajorFood) {
		this.isMajorFood = isMajorFood;
	}

	public boolean isMajorFood() {
		return isMajorFood;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}

	public void render(Graphics g) {
		g.setColor(Color.PINK);
		if (!isMajorFood) {
			g.fillRect((column * GameData.TILE_WIDTH) + GameData.MINOR_FOOD_SHIRNK_SCALE,
					(row * GameData.TILE_HEIGHT) + GameData.MINOR_FOOD_SHIRNK_SCALE,
					GameData.TILE_WIDTH - (GameData.MINOR_FOOD_SHIRNK_SCALE * 2),
					GameData.TILE_HEIGHT - (GameData.MINOR_FOOD_SHIRNK_SCALE * 2));
		}else {
			g.fillRect((column * GameData.TILE_WIDTH) + GameData.MAJOR_FOOD_SHRINK_SCALE,
					(row * GameData.TILE_HEIGHT) + GameData.MAJOR_FOOD_SHRINK_SCALE,
					GameData.TILE_WIDTH - (GameData.MAJOR_FOOD_SHRINK_SCALE * 2),
					GameData.TILE_HEIGHT - (GameData.MAJOR_FOOD_SHRINK_SCALE * 2));
		}
	}

}
