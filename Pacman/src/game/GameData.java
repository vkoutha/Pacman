package game;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GameData {

	public static final int FRAME_WIDTH = 806;
	public static final int FRAME_HEIGHT = 806;
	public static final int RENDERER_UPDATE_SPEED_MS = 60;
	public static final int SPRITE_UPDATE_SPEED_MS = 333;
	public static final int GRID_ROWS = 25;
	public static final int GRID_COLUMNS = 25;
	public static final int TILE_WIDTH = (int) ((double) FRAME_WIDTH / GRID_ROWS);
	public static final int TILE_HEIGHT = (int) ((double) FRAME_HEIGHT / GRID_COLUMNS);
	public static final double PACMAN_VELOCITY = .5;
	public static final double GHOST_VELOCITY = .5;
	public static final int PACMAN_SHRINK_SCALE = 4;
	public static final int MINOR_FOOD_SHIRNK_SCALE = 14;
	public static final int MAJOR_FOOD_SHRINK_SCALE = 9;
	public static final int BARRIER_ROUNDNESS = 10;
	public static final int TIME_TO_BE_EATABLE_GHOST_MS = 5000;
	public static final int DEAD_GHOST_TIMEOUT_TIME_MS = 3000;
	
	private static BufferedImage SPRITE_SHEET;
	public static BufferedImage[] PACMAN_SPRITE;
	public static BufferedImage RED_GHOST_SPRITE;
	public static BufferedImage GREEN_GHOST_SPRITE;
	public static BufferedImage BLUE_GHOST_SPRITE;
	public static BufferedImage PURPLE_GHOST_SPRITE;
	public static BufferedImage EATABLE_GHOST_SPRITE;
	
	static {
		try {
			SPRITE_SHEET = ImageIO.read(GameData.class.getResource("/img/pacman.png"));
			PACMAN_SPRITE = new BufferedImage[2];
			PACMAN_SPRITE[0] = SPRITE_SHEET.getSubimage(1, 1, 23 - 1, 23 - 1);
			PACMAN_SPRITE[1] = SPRITE_SHEET.getSubimage(73, 4, 95 - 73, 23 - 4);
			RED_GHOST_SPRITE = SPRITE_SHEET.getSubimage(2, 98, 21 - 2, 118 - 98);
			GREEN_GHOST_SPRITE = SPRITE_SHEET.getSubimage(2, 123, 21 - 2, 142 - 123);
			BLUE_GHOST_SPRITE = SPRITE_SHEET.getSubimage(3, 147, 21 - 3, 166 - 147);
			PURPLE_GHOST_SPRITE = SPRITE_SHEET.getSubimage(3, 171, 20 - 3, 190 - 171);
			EATABLE_GHOST_SPRITE = SPRITE_SHEET.getSubimage(147, 75, 164 - 147, 94 - 75);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public enum Directions {
		UP, DOWN, LEFT, RIGHT, STILL
	}

}
