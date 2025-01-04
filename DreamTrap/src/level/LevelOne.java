package level;

import java.awt.Graphics;

import dreamTrap.Screen;
import entities.Boss;

public class LevelOne extends LevelManager {
	private Boss boss;

	public LevelOne(Screen screen) {
		super(screen, "/blockSprites.png", "/objectSprites.png", "/levelOne.png");

		xCharacterSpawn = 4 * Screen.BLOCK_SIZE;
		yCharacterSpawn = (Screen.BLOCK_PER_HEIGHT - 5) * Screen.BLOCK_SIZE;
		screen.getCharacter().loadlvlData(this.getCurrentLevel());
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

		// Placées à la main

		stars[25][7] = 0;
		stars[25][30] = 0;
		stars[27][11] = 0;
		stars[26][22] = 0;

		/*
		 * Mais faire boucle sur Blue for (int i = 0; i < levelHeight; i++) for (int j =
		 * 0; j < levelWidth; j++) { Color color = new Color(levelImage.getRGB(j, i));
		 * int value = color.getBlue(); if (value < 128) level[i][j] = -1; else
		 * level[i][j] = 0; }
		 */
	}

	@Override
	public void spikesInitializer() {
		spikes = new int[levelHeight][levelWidth];

		for (int i = 0; i < spikes.length; i++) {
			for (int j = 0; j < spikes[i].length; j++) {
				spikes[i][j] = -1;
			}
		}

		// Placés à la main

		spikes[29][7] = 0;
		spikes[29][43] = 0;
		spikes[29][44] = 0;
		spikes[29][45] = 0;
		spikes[29][46] = 0;
		spikes[29][47] = 0;
		spikes[29][48] = 0;
		spikes[27][51] = 0;
		spikes[29][56] = 0;
		spikes[29][57] = 0;

		/*
		 * Mais faire boucle sur Red for (int i = 0; i < levelHeight; i++) for (int j =
		 * 0; j < levelWidth; j++) { Color color = new Color(levelImage.getRGB(j, i));
		 * int value = color.getRed(); if (value < 128) level[i][j] = -1; else
		 * level[i][j] = 0; }
		 */
	}

	@Override
	protected void additionalDraw(Graphics g) {
		if (boss != null) {
			g.drawImage(boss.getBoss()[boss.getCurrentAnimation()][boss.getAniIndex()],
					boss.getxBlock() * Screen.BLOCK_SIZE + boss.getmovingXBlock() - character.getPosX(),
					boss.getyBlock() * Screen.BLOCK_SIZE + boss.getmovingYBlock(), Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
					null);
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
