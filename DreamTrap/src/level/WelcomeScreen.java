package level;

import java.awt.Graphics;

import dreamTrap.Screen;

public class WelcomeScreen extends LevelNoAnimBackground {

	public WelcomeScreen(Screen screen) {
		super(screen, "/WelcomeScreen.png", null, null, "/WelcomeScreenLevel.png");
		
		xCharacterSpawn = (Screen.BLOCK_PER_WIDTH * Screen.BLOCK_SIZE) / 2 - Screen.BLOCK_SIZE / 2;
		yCharacterSpawn = (Screen.BLOCK_PER_HEIGHT - 5) * Screen.BLOCK_SIZE - Screen.BLOCK_SIZE / 2;
	}

	@Override
	protected void additionalDraw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
	}
}
