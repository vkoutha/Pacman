package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Barrier {

	private ArrayList<int[]> barrierPoints, polygonBarrierPoints;
	private Tile[][] tiles;

	public Barrier(Tile[][] tiles) {
		barrierPoints = new ArrayList<int[]>();
		polygonBarrierPoints = new ArrayList<int[]>();
		this.tiles = tiles;
		initBarriers();
	}

	// Index 0 = xStart, Index 1 0 = yStart, Index 2 = xEnd, Index 3 = yEnd
	public void initBarriers() {
		barrierPoints.add(convertToXAndY(GameData.GRID_COLUMNS / 2 - 4, GameData.GRID_ROWS / 2 - 1, 9, 3, true));
		barrierPoints.add(convertToXAndY(1, 1, 3, 2, true));
		barrierPoints.add(convertToXAndY(5, 1, 4, 2, true));
		barrierPoints.add(convertToXAndY(10, 1, 1, 3, true));
		barrierPoints.add(convertToXAndY(14, 1, 1, 3, true));
		barrierPoints.add(convertToXAndY(21, 1, 3, 2, true));
		barrierPoints.add(convertToXAndY(16, 1, 4, 2, true));
		barrierPoints.add(convertToXAndY(GameData.GRID_COLUMNS / 2, 0, 1, 5, true));
		setBarrierTiles();
		setBarrierPolygonTiles();
	}

	public void setBarrierTiles() {
		for (int i = 0; i < barrierPoints.size(); i++) {
			int[] rowColCoords = { barrierPoints.get(i)[1] / GameData.TILE_HEIGHT,
					barrierPoints.get(i)[3] / GameData.TILE_HEIGHT, barrierPoints.get(i)[0] / GameData.TILE_WIDTH,
					barrierPoints.get(i)[2] / GameData.TILE_WIDTH };
			for (int row = rowColCoords[0]; row < rowColCoords[0] + rowColCoords[1]; row++) {
				for (int col = rowColCoords[2]; col < rowColCoords[2] + rowColCoords[3]; col++) {
					tiles[row][col].setAsBarrierTile(true);
				}
			}
		}
	}
	
	public void setBarrierPolygonTiles() {
		
	}
	
	public int[] convertToXAndY(int colStart, int rowStart, int width, int height, boolean usingWidthHeight) {
		if (usingWidthHeight) {
			return new int[] { colStart * GameData.TILE_WIDTH, rowStart * GameData.TILE_HEIGHT,
					width * GameData.TILE_WIDTH, height * GameData.TILE_HEIGHT };
		} else {
			return new int[] { colStart * GameData.TILE_WIDTH, rowStart * GameData.TILE_HEIGHT,
					(width - colStart) * GameData.TILE_WIDTH, (height - rowStart) * GameData.TILE_HEIGHT };
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		barrierPoints.forEach((barrier) -> g.drawRoundRect(barrier[0], barrier[1], barrier[2], barrier[3],
				GameData.BARRIER_ROUNDNESS, GameData.BARRIER_ROUNDNESS));
		int[] yPoints = new int[] {6, 7, 7, 10, 10, 7, 7, 6};
		int[] xPoints = new int[] {9, 9, 12, 12, 13, 13, 16, 16};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] *= GameData.TILE_WIDTH;
			yPoints[i] *= GameData.TILE_HEIGHT;
		}
//		g.setColor(Color.BLACK);
//		g.fillPolygon(xPoints, yPoints, xPoints.length);
		g.setColor(Color.BLUE);
		g.drawPolygon(xPoints, yPoints, xPoints.length);
	}

}
