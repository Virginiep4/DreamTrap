package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dreamTrap.Screen;

public class KeyboardInputs implements KeyListener {
	private Screen screen;

	public KeyboardInputs(Screen screen) {
		this.screen = screen;
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
			screen.jump();
			break;
		case KeyEvent.VK_D:
			screen.xMovement(10);
			break;
		case KeyEvent.VK_Q:
			screen.xMovement(-10);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
