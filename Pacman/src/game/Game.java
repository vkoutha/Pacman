package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Game implements ActionListener {

	public static Game game;
	private JFrame frame;
	private Renderer renderer;
	private Pacman pacman;
	private Timer timer;

	public Game() {
		initFrame();
	}

	public void initFrame() {
		frame = new JFrame("Pacman");
		renderer = new Renderer();
		renderer.setPreferredSize(new Dimension(GameData.FRAME_WIDTH, GameData.FRAME_HEIGHT));
		frame.add(renderer);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer = new Timer(GameData.UPDATE_SPEED_MS, this);
	}

	public void initGame() {
		pacman = new Pacman();
	}

	public void render(Graphics g) {
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

	}

}
