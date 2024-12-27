package level;

import java.awt.Graphics;

import dreamTrap.Screen;
import entities.Boss;

public class LevelOne extends LevelAnimBackground {
	private Boss boss;
	
	public LevelOne(Screen screen) {
		super(screen, "/levelOneBackgrounds.png", "/blockSprites.png", "/objectSprites.png", "/levelOne.png");
		
		xCharacterSpawn = 4 * Screen.BLOCK_SIZE ;
		yCharacterSpawn = (Screen.BLOCK_PER_HEIGHT - 5) * Screen.BLOCK_SIZE;
		boss = new Boss(screen.getCharacter());
	}

	@Override
	protected void additionalDraw(Graphics g) {
		g.drawImage(boss.getBoss()[boss.getCurrentAnimation()][boss.getAniIndex()],
				boss.getxBlock() * Screen.BLOCK_SIZE + boss.getmovingXBlock() - character.getPosX() - Screen.BLOCK_SIZE,
				(Screen.BLOCK_PER_HEIGHT - 5) * Screen.BLOCK_SIZE + boss.getPosY(), Screen.BLOCK_SIZE * 2,
				Screen.BLOCK_SIZE * 2, null);
		
	}

	@Override
	public void update() {
		boss.update();		
		updateBackgroundTick();
	}

}
