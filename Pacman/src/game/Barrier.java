package game;

import java.awt.Graphics;
import java.util.ArrayList;

public class Barrier {

	private ArrayList<int[]> barrierPoints;
	
	public Barrier() {
		barrierPoints = new ArrayList<int[]>();
		initBarriers();
	}

	public void initBarriers() {
		//Index 0 = xStart, Index 1 0 = yStart, Index 2 = xEnd, Index 3 = yEnd
		barrierPoints.add(new int[] { ((GameData.GRID_COLUMNS / 2) - 4) * GameData.TILE_WIDTH,
				((GameData.GRID_ROWS / 2) - 1) * GameData.TILE_HEIGHT, (8 * GameData.TILE_WIDTH),
				(2 * GameData.TILE_HEIGHT), 10, 10 });
	}
	
//	public int[] convertToXAndY() {
//		
//	}

	public void render(Graphics g) {
		barrierPoints.forEach((barrier) -> g.drawRoundRect(barrier[0], barrier[1], barrier[2], barrier[3],
				GameData.BARRIER_ROUNDNESS, GameData.BARRIER_ROUNDNESS));
	}

}
