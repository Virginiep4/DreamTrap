package level;

import java.awt.Graphics;

import dreamTrap.Screen;
import entities.Boss;

public class LevelOne extends LevelManager {

	public LevelOne(Screen screen) {
		super(screen, "/blockSprites.png", "/objectSprites.png", "/levelOne.png");

		xCharacterSpawn = 4 * Screen.BLOCK_SIZE;
		character.setPosX(xCharacterSpawn);		
		yCharacterSpawn = (Screen.BLOCK_PER_HEIGHT - 6) * Screen.BLOCK_SIZE;
	}

	@Override
	public void starsInitializer() {
		stars = new int[levelHeight][levelWidth];

		for (int i = 0; i < stars.length; i++) {
			for (int j = 0; j < stars[i].length; j++) {
				stars[i][j] = -1;
			}
		}
	}

	@Override
	public void spikesInitializer() {
		spikes = new int[levelHeight][levelWidth];

		for (int i = 0; i < spikes.length; i++) {
			for (int j = 0; j < spikes[i].length; j++) {
				spikes[i][j] = -1;
			}
		}
	}

	@Override
	protected void additionalDraw(Graphics g) {
	}

	@Override
	public void update() {
		HelpMethods.OnSpike(character, this);
		HelpMethods.OnStar(character, this);
	}

}