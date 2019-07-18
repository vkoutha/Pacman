package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.GameData.Directions;

public class Ghost {

	private double ogRow, ogColumn, row, column;
	private boolean isEatable, inTimeout;
	private Thread deathTimeoutThread;
	private BufferedImage spriteToUse;
	private Directions direction, prevDirection;
	int[][] possibleGhostSpawnPoints = new int[][] { { 11, 11 }, { 11, 12 }, { 11, 13 }, { 12, 12 } };


	public Ghost(int row, int column, Color color) {
		ogRow = row;
		ogColumn = column;
		this.row = row;
		this.column = column;
		prevDirection = Directions.UP;
		direction = Directions.UP;
		for (int i = 0; i < Game.game.getFood().size(); i++) {
			if (Game.game.getFood().get(i).getRow() == row && Game.game.getFood().get(i).getColumn() == column) {
				Game.game.getFood().get(i).consume();
			}
		}
		setSpriteToUse(color);
		initTimeoutThread();
	}

	private void initTimeoutThread() {
		deathTimeoutThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				inTimeout = true;
				long startTime = System.currentTimeMillis();
				while (startTime - System.currentTimeMillis() < GameData.DEAD_GHOST_TIMEOUT_TIME_MS)
					;
				inTimeout = false;
				Game.game.getBarrier().openSpawn();
			}
		});
	}

	public void setSpriteToUse(Color color) {
		if (color == Color.GREEN) {
			spriteToUse = GameData.GREEN_GHOST_SPRITE;
		} else if (color == Color.BLUE) {
			spriteToUse = GameData.BLUE_GHOST_SPRITE;
		} else if (color == Color.MAGENTA) {
			spriteToUse = GameData.PURPLE_GHOST_SPRITE;
		} else if (color == Color.RED) {
			spriteToUse = GameData.RED_GHOST_SPRITE;
		}
	}

	public void setAsEatable(boolean isEatable) {
		this.isEatable = isEatable;
	}

	public void move() {
		if (inTimeout) {
			return;
		}
		if (inSpawnArea() && Game.game.getTimePassed() > 2000) {
			row = 10;
			column = 12;
		}
		if (row - (int) row != 0 || column - (int) column != 0) {
			intermediaryMove();
			return;
		}
		if (atIntersection()) {
			prevDirection = direction;
			switchIntersections();
		}
		if (hitPacman()) {
			if (!isEatable()) {
				Game.game.reduceLives();
			} else {
				row = ogRow;
				column = ogColumn;
				startTimeoutTimer();
			}
			return;
		}
		int row = (int) this.row, column = (int) this.column;
		switch (direction) {
		case UP:
			if (row - 1 >= 0 && !Game.game.getTiles()[row - 1][column].isBarrierTile()) {
				this.row -= GameData.PACMAN_VELOCITY;
			}
			break;
		case DOWN:
			if (row + 1 < GameData.GRID_ROWS && !Game.game.getTiles()[row + 1][column].isBarrierTile()) {
				this.row += GameData.PACMAN_VELOCITY;
			}
			break;
		case LEFT:
			if (column - 1 >= 0 && !Game.game.getTiles()[row][column - 1].isBarrierTile()) {
				this.column -= GameData.PACMAN_VELOCITY;
			}
			break;
		case RIGHT:
			if (column + 1 < GameData.GRID_COLUMNS && !Game.game.getTiles()[row][column + 1].isBarrierTile()) {
				this.column += GameData.PACMAN_VELOCITY;
			}
			break;
		case STILL:
			break;
		}
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

	private void switchIntersections() {
		ArrayList<Directions> availableDirections = getAvailableDirections();
		ArrayList<Double> distances = new ArrayList<Double>();
		int row = (int) this.row, column = (int) this.column;
		for (int i = 0; i < availableDirections.size(); i++) {
			switch (availableDirections.get(i)) {
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
		for (int i = 0; i < distances.size(); i++) {
			if (distances.get(i) < lowestDist) {
				lowestDist = distances.get(i);
				lowestIndex = i;
			}
		}
		double chance = Math.random() * 100;
		if (chance <= 70) {
			direction = availableDirections.get(lowestIndex);
		} else {
			direction = availableDirections.get(getRandomNum(availableDirections.size(), -1));
		}
	}

	private void startTimeoutTimer() {
		if (deathTimeoutThread.isAlive()) {
			initTimeoutThread();
		}
		deathTimeoutThread.start();
	}

	private ArrayList<Directions> getAvailableDirections() {
		ArrayList<Directions> availableDirections = new ArrayList<Directions>();
		int row = (int) this.row, column = (int) this.column;
		if (row - 1 > 0 && !Game.game.getTiles()[row - 1][column].isBarrierTile()) {
			availableDirections.add(Directions.UP);
		}
		if (row + 1 < GameData.GRID_ROWS && !Game.game.getTiles()[row + 1][column].isBarrierTile()) {
			availableDirections.add(Directions.DOWN);
		}
		if (column - 1 > 0 && !Game.game.getTiles()[row][column - 1].isBarrierTile()) {
			availableDirections.add(Directions.LEFT);
		}
		if (column + 1 < GameData.GRID_COLUMNS && !Game.game.getTiles()[row][column + 1].isBarrierTile()) {
			availableDirections.add(Directions.RIGHT);
		}
		for (int i = 0; i < availableDirections.size(); i++) {
			if (availableDirections.get(i) == prevDirection) {
				availableDirections.remove(i);
			}
		}
		return availableDirections;
	}

	private int getRandomNum(int size, int indexToExclude) {
		int randomNum = -69;
		do {
			randomNum = (int) Math.random() * size;
		} while (randomNum == indexToExclude);
		return randomNum;
	}

	public int getRow() {
		return (int) row;
	}

	public int getColumn() {
		return (int) column;
	}

	private double getDistanceToPlayer(int row, int column) {
		int base = Math.abs(Game.game.getPacman().getColumn() - column);
		int height = Math.abs(Game.game.getPacman().getRow() - row);
		return Math.hypot(base, height);
	}

	private boolean hitPacman() {
		if ((int) row == Game.game.getPacman().getRow() && (int) column == Game.game.getPacman().getColumn()) {
			return true;
		}
		return false;
	}

	private boolean inSpawnArea() {
		for (int j = 0; j < possibleGhostSpawnPoints.length; j++) {
			if (row == possibleGhostSpawnPoints[j][0] && column == possibleGhostSpawnPoints[j][1]) {
				return true;
			}
		}
		return false;
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

	public boolean isEatable() {
		return isEatable;
	}

	public void render(Graphics g) {
		if (!isEatable) {
			g.drawImage(spriteToUse, (int) (column * GameData.TILE_WIDTH + GameData.PACMAN_SHRINK_SCALE),
					(int) (row * GameData.TILE_HEIGHT + GameData.PACMAN_SHRINK_SCALE),
					GameData.TILE_WIDTH - (GameData.PACMAN_SHRINK_SCALE * 2),
					GameData.TILE_HEIGHT - (GameData.PACMAN_SHRINK_SCALE * 2), null);
		} else {
			g.drawImage(GameData.EATABLE_GHOST_SPRITE,
					(int) (column * GameData.TILE_WIDTH + GameData.PACMAN_SHRINK_SCALE),
					(int) (row * GameData.TILE_HEIGHT + GameData.PACMAN_SHRINK_SCALE),
					GameData.TILE_WIDTH - (GameData.PACMAN_SHRINK_SCALE * 2),
					GameData.TILE_HEIGHT - (GameData.PACMAN_SHRINK_SCALE * 2), null);
		}
	}

}
