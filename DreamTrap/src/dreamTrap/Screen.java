package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import level.LevelManager;
import entities.Character;

public class Screen extends JPanel {
	// screen size parameters
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 20;
	public final static int BLOCK_PER_HEIGHT = 11;
	private final static float SCALE = 1f;

	private Character character;
	private LevelManager levelManager;

	public Screen() {
		setScreenSize();
		character = new Character();
		levelManager = new LevelManager();
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		character.update();
		levelManager.update();
	}

	public Character getCharacter() {
		return character;
	}

	/**
	 * The function that will allow to draw on the created screen It is never called
	 * on the code because it is automatically done
	 *
	 * @param g you don't have to care about this argument because this is created
	 *          by the extensions used
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clean and allow to draw on the screen
		
		levelManager.draw(g);
		// could be optimized by loading all sprite on same image and use getSubimage()
		g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
				150 + (int) character.getPosX(), (BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE + (int) character.getPosY(), null);
	}

	/**
	 * Where the screen size is determined
	 */
	private void setScreenSize() {
		Dimension size = new Dimension((int) (BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),
				(int) (BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE)); // Screen resolution
		setPreferredSize(size);
	}
}
