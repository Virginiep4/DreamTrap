package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import levels.LevelManager;
import entities.Character;
//import entities.Platform;

@SuppressWarnings("serial")
public class Screen extends JPanel {
	private LevelManager levelmanager;
	private Character character;
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 20;
	public final static int BLOCK_PER_HEIGHT = 11;

	// private Platform platform=new Platform();

	public Screen() {

		setScreenSize();
		character = new Character();
		levelmanager = new LevelManager(this);
		character.loadlvlData(levelmanager.getCurrentLevel());

		addKeyListener(new KeyboardInputs(this));// focus on this(object screen ) bc of keylistener
	}

	public void updateGame() {

		character.updateCharacAnimationTick();
		character.updatePos();
		character.updateHitbox();
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
		// g.drawImage(platform.getShop()[platform.getCurrentAnimation()][0], 150 , 350
		// , null);

		levelmanager.draw(g);
		g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()], 4 * BLOCK_SIZE,
				(BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE + character.getPosY(), null);

		character.drawHitbox(g);

	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension(BLOCK_PER_WIDTH * BLOCK_SIZE, BLOCK_PER_HEIGHT * BLOCK_SIZE); // Screen resolution
		setPreferredSize(size);

	}

	public Character getCharacter() {
		return character;
	}

}
