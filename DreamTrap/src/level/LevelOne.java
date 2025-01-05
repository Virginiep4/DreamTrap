package level;

import java.awt.Graphics;

import dreamTrap.Screen;
import entities.Boss;

public class LevelOne extends LevelManager {
	private Boss boss;

	public LevelOne(Screen screen) {
		super(screen, "/blockSprites.png", "/objectSprites.png", "/levelOne.png");

		xCharacterSpawn = 4 * Screen.BLOCK_SIZE;
		character.setPosX(xCharacterSpawn);		
		yCharacterSpawn = (Screen.BLOCK_PER_HEIGHT - 6) * Screen.BLOCK_SIZE;
		boss = new Boss(screen.getCharacter());
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
		if (boss != null) {
			g.drawImage(boss.getBoss()[boss.getCurrentAnimation()][boss.getAniIndex()],
					boss.getxBlock() * Screen.BLOCK_SIZE + boss.getmovingXBlock() - character.getPosX() + xCharacterSpawn,
					boss.getyBlock() * Screen.BLOCK_SIZE + boss.getmovingYBlock(), null);
		}
	}

	@Override
	public void update() {
		boss.update();
		HelpMethods.bossHurts(character, boss);
		HelpMethods.OnSpike(character, this);
		HelpMethods.OnStar(character, this);
	}

}
