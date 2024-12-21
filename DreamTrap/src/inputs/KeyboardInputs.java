package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dreamTrap.Screen;
import entities.Character;
import mouvement.MouvementNormal;

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
	public void keyPressed(KeyEvent e) {
		// whenever a key is pressed we check if it does something for our game
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE :
			if (character.moving instanceof MouvementNormal) {
				character.moving.jumping(true);
			}
			else {
				character.moving.up(true);
			}
			break;
		case KeyEvent.VK_Z:
			character.moving.up(true);
			break;
		case KeyEvent.VK_D:
			character.moving.right(true);
			break;
		case KeyEvent.VK_Q:
			character.moving.left(true);
			break;
		case KeyEvent.VK_S:
			character.moving.down(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// when a key is released it's the end of movement
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if (character.moving instanceof MouvementNormal) {
				character.moving.jumping(false);
				character.moving.releaseJump();
			}
			else {
				character.moving.up(false);
			}
			break;
		case KeyEvent.VK_Z:
			character.moving.up(false);
			break;
		case KeyEvent.VK_D:
			character.moving.right(false);
			break;
		case KeyEvent.VK_Q:
			character.moving.left(false);
			break;
		case KeyEvent.VK_S:
			character.moving.down(false);
			break;
		}
	}
}
