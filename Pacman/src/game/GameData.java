package game;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GameData {

	public static final int FRAME_WIDTH = 806;
	public static final int FRAME_HEIGHT = 806;
	public static final int RENDERER_UPDATE_SPEED_MS = 100;
	public static final int SPRITE_UPDATE_SPEED_MS = 12;
	public static final int GRID_ROWS = 25;
	public static final int GRID_COLUMNS = 25;
	public static final int TILE_WIDTH = (int) ((double) FRAME_WIDTH / GRID_ROWS);
	public static final int TILE_HEIGHT = (int) ((double) FRAME_HEIGHT / GRID_COLUMNS);
	public static final int PACMAN_SHRINK_SCALE = 4;
	public static final int MINOR_FOOD_SHIRNK_SCALE = 14;
	public static final int MAJOR_FOOD_SHRINK_SCALE = 9;
	public static final int BARRIER_ROUNDNESS = 10;
	
	public static BufferedImage PACMAN_SPRITE;
	
	static {
		try {
			PACMAN_SPRITE = ImageIO.read(GameData.class.getResource("/img/pacmanOpen.png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public enum Directions {
		UP, DOWN, LEFT, RIGHT, STILL
	}

}
