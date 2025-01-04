package level;

import java.awt.Graphics;

import dreamTrap.Screen;
import entities.Boss;

public class LevelOne extends LevelManager {
	private Boss boss;
	
	public LevelOne(Screen screen) {
		super(screen, "/blockSprites.png", "/objectSprites.png", "/levelOne.png");
		
		xCharacterSpawn = 4 * Screen.BLOCK_SIZE ;
		yCharacterSpawn = (Screen.BLOCK_PER_HEIGHT - 5) * Screen.BLOCK_SIZE;
		boss = new Boss(screen.getCharacter());
	}

	@Override
	protected void additionalDraw(Graphics g) {
	}

	@Override
	public void update() {
	}

}
