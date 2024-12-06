package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;

import entities.Character;

public class Screen extends JPanel {
	private Character character;

	public Screen() {
		setScreenSize();
		character = new Character();
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		character.updateCharacAnimationTick();
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

		// could be optimized by loading all sprite on same image and use getSubimage()
		g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()], 150 + (int) character.getPosX(), 600 + (int) character.getPosY(), null);
	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension(1280, 720); // Screen resolution
		setPreferredSize(size);
	}
}
