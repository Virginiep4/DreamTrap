package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dreamTrap.Screen;
import entities.Character;

public class KeyboardInputs implements KeyListener {
	private Character character;

	public KeyboardInputs(Screen screen) {
		character = screen.getCharacter();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Handle the Keys pressed
	 *
	 * @param e is the KeyEvent detected in the window that have the focus
	 */
	@Override
	
	//we do expression lambda for manage consequence of keypressed in game vs shop ?
	public void keyPressed(KeyEvent e) {
		// whenever a key is pressed we check if it does something for our game
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			character.jump();
			break;
		case KeyEvent.VK_D:
			character.right(true);
			break;
		case KeyEvent.VK_Q:
			character.left(true);
			break;
		case KeyEvent.VK_I:
			// show interface shop
			break;
		}
	
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// when a key is released it's the end of movement
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			character.right(false);
			break;
		case KeyEvent.VK_Q:
			character.left(false);
			break;
		}
	}
}
