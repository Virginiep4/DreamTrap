package level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.awt.Color;
import dreamTrap.Screen;
import dreamTrap.Time;
import entities.Boss;
import entities.Character;

public class LevelManager {

	private Character character;
	private Boss boss;
	// sprites : img de bloc
	private BufferedImage[] blockSprites;
	private BufferedImage img;
	private final static int SPRITES_WIDTH = 5; // block width of the image for blocsSpr
	private final static int SPRITES_HEIGHT = 2;
	private int[][] level;
	private static int levelHeight;
	private int levelWidth;
	private BufferedImage[] starSprites;
	private BufferedImage starImg;
	private BufferedImage spikeImg;
	private int[][] stars;
	private int[][] spikes;
	
	public LevelManager(Screen screen) {
		character = screen.getCharacter();
		boss = new Boss(character);
		blockSprites = new BufferedImage[SPRITES_WIDTH * SPRITES_HEIGHT];
		spritesInitializer();
		levelInitializer();
		System.out.println("Coeurs :" + character.getNbCoeurs());
	}

	// getters and setters
	public int[][] getCurrentLevel() {
		return level;
	}

	public static int getLevelHeight() {
		return levelHeight;
	}

	private void spritesInitializer() {

		blockSprites = new BufferedImage[SPRITES_HEIGHT * SPRITES_WIDTH];
		img = Loadsave.importImg(Loadsave.BLOC_IMG);

		for (int i = 0; i < SPRITES_HEIGHT; i++)
			for (int j = 0; j < SPRITES_WIDTH; j++) {
				blockSprites[i * SPRITES_WIDTH + j] = img.getSubimage(j * 64, i * 64, 64, 64);
			}

		this.starImg = Loadsave.importImg(Loadsave.STAR_IMG);
		this.spikeImg = Loadsave.importImg(Loadsave.SPIKE_IMG);
	}

	private void levelInitializer() {
		BufferedImage levelImage = Loadsave.importImg(Loadsave.LEVEL_IMG);
		levelWidth = levelImage.getWidth();
		levelHeight = levelImage.getHeight();
		level = new int[levelHeight][levelWidth];
		starsInitializer();
		spikesInitializer();

		for (int i = 0; i < levelHeight; i++)
			for (int j = 0; j < levelWidth; j++) {
				Color color = new Color(levelImage.getRGB(j, i));
				int value = color.getGreen();
				if (value < 128)
					level[i][j] = -1;
				else
					level[i][j] = value - 128;
			}
	}

	public void draw(Graphics g) {
		int x = character.getPosX() / Screen.BLOCK_SIZE;
		for (int i = levelHeight - 1; i > levelHeight - Screen.BLOCK_PER_HEIGHT; i--)
			for (int j = 0; j < Screen.BLOCK_PER_WIDTH + 2; j++) {
				int block = level[i][j + x];
				int star = stars[i][j + x];
				int spike = spikes[i][j + x];
				
				if (block != -1) {
					g.drawImage(blockSprites[block], j * Screen.BLOCK_SIZE - (character.getPosX() % 64),
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, null);
				}
				if (star != -1) {
					g.drawImage(starImg, j * Screen.BLOCK_SIZE - (character.getPosX() % 64),
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, null);
				}
				if (spike != -1) {
					g.drawImage(spikeImg, j * Screen.BLOCK_SIZE - (character.getPosX() % 64),
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, null);
				}
			}
		if (boss != null) {
			g.drawImage(boss.getBoss()[boss.getCurrentAnimation()][boss.getAniIndex()],
					boss.getxBlock() * Screen.BLOCK_SIZE + boss.getmovingXBlock() - character.getPosX(),
					boss.getyBlock() * Screen.BLOCK_SIZE + boss.getmovingYBlock(), Screen.BLOCK_SIZE,
					Screen.BLOCK_SIZE, null);

	    }
	}

	public void update() {
		boss.update();
		HelpMethods.bossHurts(character, boss);
		HelpMethods.OnSpike(character, this);
		HelpMethods.OnStar(character, this);
	}

	// Stars

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
		
		/* Mais faire boucle sur Blue
		 * for (int i = 0; i < levelHeight; i++)
			for (int j = 0; j < levelWidth; j++) {
				Color color = new Color(levelImage.getRGB(j, i));
				int value = color.getBlue();
				if (value < 128)
					level[i][j] = -1;
				else
					level[i][j] = 0;
			}
		 */
	}
	
	public void spikesInitializer() {
		spikes = new int[levelHeight][levelWidth];

		for (int i = 0; i < spikes.length; i++) {
			for (int j = 0; j < spikes[i].length; j++) {
				spikes[i][j] = -1;
			}
		}
		
		// Placés à la main
		
		spikes[29][7] = 0;
		spikes[29][43] = 0;spikes[29][44] = 0;spikes[29][45] = 0;spikes[29][46] = 0;
		spikes[29][47] = 0;spikes[29][48] = 0;
		spikes[27][51] = 0;
		spikes[29][56] = 0;
		spikes[29][57] = 0;
		
		/* Mais faire boucle sur Red
		 * for (int i = 0; i < levelHeight; i++)
			for (int j = 0; j < levelWidth; j++) {
				Color color = new Color(levelImage.getRGB(j, i));
				int value = color.getRed();
				if (value < 128)
					level[i][j] = -1;
				else
					level[i][j] = 0;
			}
		 */
	}

	public int[][] getStars() {
		return stars;
	}

	public void setStars(int[][] stars) {
		this.stars = stars;
	}
	
	public int[][] getSpikes() {
		return spikes;
	}
}
