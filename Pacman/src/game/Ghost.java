package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.GameData.Directions;

public class Ghost {

	private int row, column;
	private Color color;
	private Directions direction, prevDirection;

	public Ghost(int row, int column, Color color) {
		this.row = row;
		this.column = column;
		this.color = color;
		prevDirection = Directions.STILL;
		direction = Directions.UP;
		for (int i = 0; i < Game.game.getFood().size(); i++) {
			if (Game.game.getFood().get(i).getRow() == row && Game.game.getFood().get(i).getColumn() == column) {
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
		if (atIntersection()) {
			prevDirection = direction;
			switchIntersections();
		}
	}
	
	private void switchIntersections() {
		ArrayList<Directions> availableDirections = getAvailableDirections();
		ArrayList<Double> distances = new ArrayList<Double>();
		for(int i = 0; i < availableDirections.size(); i++) {
			switch(availableDirections.get(i)) {
			case UP:
				distances.add(getDistanceToPlayer(row - 1, column));
				break;
			case DOWN:
				distances.add(getDistanceToPlayer(row + 1, column));
				break;
			case LEFT:
				distances.add(getDistanceToPlayer(row, column - 1));
				break;
			case RIGHT:
				distances.add(getDistanceToPlayer(row, column + 1));
				break;
			case STILL:
				break;
			}
		}
		int lowestIndex = -1;
		double lowestDist = 69420;
		for(int i = 0; i < distances.size(); i++) {
			if(distances.get(i) < lowestDist) {
				lowestDist = distances.get(i);
				lowestIndex = i;
			}
		}
		double chance = Math.random() * 100;
		if(chance <= 30) {
			direction = availableDirections.get(lowestIndex);
		}else {
			direction = availableDirections.get(getRandomNum(availableDirections.size(), -1));
		}
	}
	
	private ArrayList<Directions> getAvailableDirections() {
		ArrayList<Directions> availableDirections = new ArrayList<Directions>();
		if(row - 1 > 0 && !Game.game.getTiles()[row - 1][column].isBarrierTile()) {
			availableDirections.add(Directions.UP);
		}
		if(row + 1 < GameData.GRID_ROWS && !Game.game.getTiles()[row + 1][column].isBarrierTile()) {
			availableDirections.add(Directions.DOWN);
		}
		if(column - 1 > 0 && !Game.game.getTiles()[row][column - 1].isBarrierTile()) {
			availableDirections.add(Directions.LEFT);
		}
		if(column + 1 < GameData.GRID_COLUMNS && !Game.game.getTiles()[row][column + 1].isBarrierTile()) {
			availableDirections.add(Directions.RIGHT);
		}
		for(int i = 0; i < availableDirections.size(); i++) {
			if(availableDirections.get(i) == prevDirection) {
				availableDirections.remove(i);
			}
		}
		return availableDirections;
	}
	
	private int getRandomNum(int size, int indexToExclude) {
		int randomNum = -69;
		do {
			System.out.println(size);
			randomNum = (int) Math.random() * size;
		}while (randomNum == indexToExclude);
		 return randomNum;
	}
	
	private double getDistanceToPlayer(int row, int column) {
		int base = Math.abs(Game.game.getPacman().getColumn() - column);
		int height = Math.abs(Game.game.getPacman().getRow() - row);
		return Math.hypot(base, height);
	}

	private boolean atIntersection() {
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

	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(column * GameData.TILE_WIDTH + GameData.PACMAN_SHRINK_SCALE,
				row * GameData.TILE_HEIGHT + GameData.PACMAN_SHRINK_SCALE,
				GameData.TILE_WIDTH - (GameData.PACMAN_SHRINK_SCALE * 2),
				GameData.TILE_HEIGHT - (GameData.PACMAN_SHRINK_SCALE * 2));
	}

}
