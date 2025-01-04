package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import dreamTrap.Screen;
import entities.Character;
import entities.Item;
import entities.ShopInt;
import entities.backgroundd;
import level.GameOverScreen;
import level.LevelManager;

public class KeyboardInputs implements KeyListener {
	private Character character;
	private backgroundd backgroundd;
	private Screen screen;
	private boolean unlockEnter = true;

	public KeyboardInputs(Screen screen) {
		character = screen.getCharacter();
		backgroundd = screen.getBackgroundd();
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
			character.moving.jumping(true);
			break;
		case KeyEvent.VK_Z:
			character.moving.up(true);
			if (backgroundd.getCurrentAnimation() == 9) {
				GameOverScreen.ShowZ(true);
				GameOverScreen.ShowS(false);
			}
			break;
		case KeyEvent.VK_D:
			character.moving.right(true);
			break;
		case KeyEvent.VK_Q:
			character.moving.left(true);
			break;
		case KeyEvent.VK_S:
			character.moving.down(true);
			if (backgroundd.getCurrentAnimation() == 9) {
				GameOverScreen.ShowZ(false);
				GameOverScreen.ShowS(true);
			}
			break;
		case KeyEvent.VK_ENTER:
			
			if (unlockEnter) {
				if (backgroundd.getCurrentAnimation() == 1) {
					backgroundd.setCurrentAnimation(2);
				}
				else if (backgroundd.getCurrentAnimation() == 9) {
					if (GameOverScreen.getShowZ()) {
						backgroundd.setCurrentAnimation(3);
					} else if (GameOverScreen.getShowS()) {
						backgroundd.setCurrentAnimation(1);
						screen.getPlayerName();
						unlockEnter = false;
					}
					character.setPosX(Screen.levelManager.getxCharacterSpawn());
					character.setPosY(0);
				} else if (backgroundd.getCurrentAnimation() >= 3) {
					character.click();
					Item.click();
					ShopInt.click();
				}
			}
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
		case KeyEvent.VK_SPACE:
			character.moving.jumping(false);
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
		case KeyEvent.VK_ENTER:
			unlockEnter = true;
			character.setClicked(false);
			Item.setClicked(false);
			ShopInt.setClicked(false);
			break;
		}
	}
}
