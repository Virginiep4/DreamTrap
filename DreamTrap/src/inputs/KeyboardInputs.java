package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dreamTrap.Screen;
import entities.Character;
import entities.Item;
import entities.ShopInt;

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
		case KeyEvent.VK_SPACE:
			character.jump();
			break;
		case KeyEvent.VK_D:
			character.right(true);
			break;
		case KeyEvent.VK_Q:
			character.left(true);
			break;
		case KeyEvent.VK_ENTER:
			character.click();
			Item.click();
			ShopInt.click();
			break;
		case KeyEvent.VK_LEFT:
			ShopInt.left();
			break;
		case KeyEvent.VK_RIGHT:
			ShopInt.right();
			break;
		case KeyEvent.VK_DOWN:
			ShopInt.down();
			break;
		case KeyEvent.VK_UP:
			ShopInt.up();
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