package game;

public class GameData {

	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 800;
	public static final int UPDATE_SPEED_MS = 100;
	public static final int GRID_ROWS = 100;
	public static final int GRID_COLUMNS = 100;
	public static final int TILE_WIDTH = (int) ((double) FRAME_WIDTH / GRID_ROWS);
	public static final int TILE_HEIGHT = (int) ((double) FRAME_HEIGHT / GRID_COLUMNS);
	public static final int PACMAN_ROW_LENGTH = 5;
	public static final int PACMAN_COLUMN_LENGTH = 5;
	
	public enum Directions {
		UP, DOWN, LEFT, RIGHT
	}

}
