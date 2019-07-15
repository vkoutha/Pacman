package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import game.GameData.Directions;

public class Game implements ActionListener, KeyListener, MouseListener {

	public static Game game;
	private JFrame frame;
	private Renderer renderer;
	private Barrier barrier;
	private Pacman pacman;
	private Tile[][] tiles;
	private ArrayList<Food> food;
	private Timer timer;

	public Game() {
		initFrame();
		initGame();
	}

	public void initFrame() {
		frame = new JFrame("Pacman");
		renderer = new Renderer();
		renderer.setPreferredSize(new Dimension(GameData.FRAME_WIDTH, GameData.FRAME_HEIGHT));
		renderer.setBackground(Color.BLACK);
		frame.add(renderer);
		frame.addKeyListener(this);
		frame.getContentPane().addMouseListener(this);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initGame() {
		tiles = new Tile[GameData.GRID_ROWS][GameData.GRID_COLUMNS];
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles[row].length; col++) {
				tiles[row][col] = new Tile(row, col);
			}
		} 
		barrier = new Barrier(tiles);
		food = new ArrayList<Food>();
		for (int row = 0; row < GameData.GRID_ROWS; row++) {
			for (int col = 0; col < GameData.GRID_COLUMNS; col++) {
				if (!tiles[row][col].isBarrierTile()) {
					food.add(new Food(row, col));
				}
			}
		}
		pacman = new Pacman();
		timer = new Timer(GameData.RENDERER_UPDATE_SPEED_MS, this);
		timer.start();
	}

	public void render(Graphics g) {
		food.forEach((food) -> food.render(g));
		barrier.render(g);
		pacman.render(g);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				game = new Game();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		renderer.repaint();
		pacman.move();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			pacman.setDirection(Directions.UP);
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			pacman.setDirection(Directions.DOWN);
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			pacman.setDirection(Directions.RIGHT);
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			pacman.setDirection(Directions.LEFT);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = e.getY() / GameData.TILE_HEIGHT;
		int col = e.getX() / GameData.TILE_WIDTH;
		System.out.println("[" + row + ", " + col + "]");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public Tile[][] getTiles() {
		return tiles;
	}

}
