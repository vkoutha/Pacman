package game;

public class Tile {

	private int row, column;
	private boolean isBarrierTile;
	
	public Tile(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public void setAsBarrierTile(boolean isBarrierTile) {
		this.isBarrierTile = isBarrierTile;
	}
	
	public boolean isBarrierTile() {
		return isBarrierTile;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
}
