package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Barrier {

	private ArrayList<int[]> barrierPoints;
	private ArrayList<int[][]> polygonBarrierPoints;
	private Tile[][] tiles;

	public Barrier(Tile[][] tiles) {
		barrierPoints = new ArrayList<int[]>();
		polygonBarrierPoints = new ArrayList<int[][]>();
		this.tiles = tiles;
		initBarriers();
	}

	// Index 0 = xStart, Index 1 0 = yStart, Index 2 = xEnd, Index 3 = yEnd
	public void initBarriers() {
		barrierPoints.add(convertToXAndY(GameData.GRID_ROWS / 2 - 1, GameData.GRID_COLUMNS / 2 - 4, 3, 9, true));
		barrierPoints.add(convertToXAndY(1, 1, 2, 3, true));
		barrierPoints.add(convertToXAndY(1, 5, 2, 4, true));
		barrierPoints.add(convertToXAndY(1, 10, 4, 1, true));
		barrierPoints.add(convertToXAndY(1, 14, 4, 1, true));
		barrierPoints.add(convertToXAndY(1, 21, 2, 3, true));
		barrierPoints.add(convertToXAndY(1, 16, 2, 4, true));
		barrierPoints.add(convertToXAndY(0, GameData.GRID_COLUMNS / 2, 5, 1, true));
		barrierPoints.add(convertToXAndY(4, 1, 5, 9, false));
		barrierPoints.add(convertToXAndY(4, 16, 5, 24, false));
		barrierPoints.add(convertToXAndY(6, 1, 9, 2, false));
		barrierPoints.add(convertToXAndY(10, 1, 13, 2, false));
		barrierPoints.add(convertToXAndY(6, 3, 9, 5, false));
		barrierPoints.add(convertToXAndY(10, 3, 13, 5, false));
		barrierPoints.add(convertToXAndY(6, 23, 9, 24, false));
		barrierPoints.add(convertToXAndY(10, 23, 13, 24, false));
		barrierPoints.add(convertToXAndY(6, 20, 9, 22, false));
		barrierPoints.add(convertToXAndY(10, 20, 13, 22, false));
		barrierPoints.add(convertToXAndY(14, 0, 16, 3, false));
		barrierPoints.add(convertToXAndY(14, 4, 16, 7, false));
		barrierPoints.add(convertToXAndY(14, 18, 16, 21, false));
		barrierPoints.add(convertToXAndY(14, 22, 16, 25, false));
		barrierPoints.add(convertToXAndY(15, 11, 16, 14, false));
		barrierPoints.add(convertToXAndY(23, 11, 24, 14, false));
		setBarrierTiles();
		setBarrierPolygonTiles();
		manualBarrierTileSelection();
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
		polygonBarrierPoints.add(new int[][] { { 6, 7, 7, 10, 10, 7, 7, 6 }, { 8, 8, 12, 12, 13, 13, 17, 17 } });
		polygonBarrierPoints.add(new int[][] { { 6, 6, 8, 8, 10, 10, 13, 13 }, { 6, 7, 7, 11, 11, 7, 7, 6 } });
		polygonBarrierPoints.add(new int[][] { { 6, 8, 8, 10, 10, 13, 13, 6 }, { 18, 18, 14, 14, 18, 18, 19, 19 } });

		polygonBarrierPoints.add(new int[][] { { 15, 15, 17, 17, 19, 19 }, { 8, 10, 10, 12, 12, 8 } });

		polygonBarrierPoints.add(new int[][] { { 20, 20, 24, 24, 22, 22 }, { 12, 8, 8, 10, 10, 12 } });

		polygonBarrierPoints.add(new int[][] { { 15, 15, 17, 17, 19, 19 }, { 17, 15, 15, 13, 13, 17 } });

		polygonBarrierPoints.add(new int[][] { { 20, 20, 24, 24, 22, 22 }, { 13, 17, 17, 15, 15, 13 } });

		polygonBarrierPoints.forEach((coord) -> {
			for (int i = 0; i < coord[0].length; i++) {
				coord[0][i] *= GameData.TILE_HEIGHT;
				coord[1][i] *= GameData.TILE_WIDTH;
			}
		});
	}

	private void manualBarrierTileSelection() {
		ArrayList<int[][]> barriers = new ArrayList<int[][]>();
		barriers.add(new int[][] { { 6, 8 }, { 6, 9 }, { 6, 10 }, { 6, 11 }, { 6, 12 }, { 6, 13 }, { 6, 14 }, { 6, 15 },
				{ 6, 16 }, { 7, 12 }, { 8, 12 }, { 9, 12 } });
		barriers.add(new int[][] { { 6, 6 }, { 7, 6 }, { 8, 6 }, { 8, 7 }, { 8, 8 }, { 8, 9 }, { 8, 10 }, { 9, 10 },
				{ 9, 9 }, { 9, 8 }, { 9, 7 }, { 9, 6 }, { 10, 6 }, { 11, 6 }, { 12, 6 }, });
		barriers.add(new int[][] { { 6, 18 }, { 7, 18 }, { 8, 18 }, { 9, 18 }, { 10, 18 }, { 11, 18 }, { 12, 18 },
				{ 9, 17 }, { 8, 17 }, { 8, 16 }, { 9, 16 }, { 9, 15 }, { 8, 15 }, { 8, 14 }, { 9, 14 }, });
		barriers.add(new int[][] { { 15, 8 }, { 15, 9 }, { 16, 9 }, { 16, 8 }, { 17, 8 }, { 17, 9 }, { 17, 10 },
				{ 17, 11 }, { 18, 11 }, { 18, 10 }, { 18, 9 }, { 18, 8 }, });
		barriers.add(new int[][] { { 20, 8 }, { 20, 9 }, { 20, 10 }, { 20, 11 }, { 21, 11 }, { 21, 10 }, { 21, 9 },
				{ 21, 8 }, { 22, 8 }, { 22, 9 }, { 23, 9 }, { 23, 8 }, });
		barriers.add(new int[][] { { 15, 15 }, { 15, 16 }, { 16, 16 }, { 16, 15 }, { 17, 16 }, { 17, 15 }, { 17, 14 },
				{ 17, 13 }, { 18, 13 }, { 18, 14 }, { 18, 15 }, { 18, 16 }, });
		barriers.add(new int[][] { { 20, 13 }, { 20, 14 }, { 20, 15 }, { 20, 16 }, { 21, 16 }, { 21, 15 }, { 21, 14 },
				{ 21, 13 }, { 22, 15 }, { 22, 16 }, { 23, 16 }, { 23, 15 }, });
		barriers.forEach((polygonBarrier) -> {
			for (int i = 0; i < polygonBarrier.length; i++) {
				tiles[polygonBarrier[i][0]][polygonBarrier[i][1]].setAsBarrierTile(true);
			}
		});
	}

	public int[] convertToXAndY(int rowStart, int colStart, int height, int width, boolean usingWidthHeight) {
		if (usingWidthHeight) {
			return new int[] { colStart * GameData.TILE_WIDTH, rowStart * GameData.TILE_HEIGHT,
					width * GameData.TILE_WIDTH, height * GameData.TILE_HEIGHT };
		} else {
			return new int[] { colStart * GameData.TILE_WIDTH, rowStart * GameData.TILE_HEIGHT,
					(width - colStart) * GameData.TILE_WIDTH, (height - rowStart) * GameData.TILE_HEIGHT };
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(2f));
		barrierPoints.forEach((barrier) -> g2.drawRoundRect(barrier[0], barrier[1], barrier[2], barrier[3],
				GameData.BARRIER_ROUNDNESS, GameData.BARRIER_ROUNDNESS));
		polygonBarrierPoints.forEach((coord) -> g2.drawPolygon(coord[1], coord[0], coord[0].length));
	}

}
