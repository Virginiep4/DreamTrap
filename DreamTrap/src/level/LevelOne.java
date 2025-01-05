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

		// Placées à la main

		stars[54][13] = 0;
		stars[59][29] = 0;

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

		spikes[59][28] = 0;
		spikes[59][30] = 0;

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
