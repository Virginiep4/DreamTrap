package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import entities.Boss;
import entities.Character;
import inputs.KeyboardInputs;
import level.LevelManager;
import level.LevelOne;

public class Screen extends JPanel {
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 29;
	public final static int BLOCK_PER_HEIGHT = 12;
	private final static float SCALE = 1f;

	private Character character;
	private LevelManager levelManager;

	// getters and setters
	public Character getCharacter() {
		return character;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public Screen() {
		setScreenSize();
		character = new Character();
		levelManager = new LevelOne(this);
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		character.update();
		levelManager.update();
	}

	/**
	 * The function that will allow to draw on the created screen It is never called
	 * on the code because it is automatically done
	 *
	 * @param g you don't have to care about this argument because this is created
	 *          by the extensions used
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // constructor of Jpanel clean and allow to draw on the screen

		// paintComponent is called when Jpanel is created
		// could be optimized by loading all sprite on same image and use getSubimage()

		levelManager.draw(g);
		g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
				4 * Screen.BLOCK_SIZE, (BLOCK_PER_HEIGHT - 5) * Screen.BLOCK_SIZE + (int) character.getPosY(), null);
	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension((int) (BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),
				(int) (BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE)); // Screen resolution
		setPreferredSize(size);

	}
}
