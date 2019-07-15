package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import game.GameData.Directions;

public class Game implements ActionListener, KeyListener{

	public static Game game;
	private JFrame frame;
	private Renderer renderer;
	private Pacman pacman;
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
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initGame() {
		food = new ArrayList<Food>();
		for(int row = 0; row < GameData.GRID_ROWS; row++) {
			for(int col = 0; col < GameData.GRID_COLUMNS; col++) {
				food.add(new Food(row, col));
			}
		}
		pacman = new Pacman();
		timer = new Timer(GameData.RENDERER_UPDATE_SPEED_MS, this);
		timer.start(); 
	}

	public void render(Graphics g) {
		food.forEach((f) -> f.render(g));
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
		switch(e.getKeyCode()) {
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

}
