package level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dreamTrap.Screen;

import static utils.ImageImporter.importImg;

public class LevelManager {
	private Character character;
	
	private final static int SPRITES_WIDTH = 5; // block width of the image for blocsSpr
	private final static int SPRITES_HEIGHT = 2;
	private BufferedImage[] blockSprites;
	private int[][] level;
	private int levelHeight;
	private int levelWidth;

	public LevelManager() {
		spritesInitializer();
		levelInitializer();
	}

	private void spritesInitializer() {
		blockSprites = new BufferedImage[SPRITES_HEIGHT * SPRITES_WIDTH];
		BufferedImage img = importImg("/blockSprites.png");

		for (int i = 0; i < SPRITES_HEIGHT; i++)
			for (int j = 0; j < SPRITES_WIDTH; j++) {
				blockSprites[i * SPRITES_WIDTH + j] = img.getSubimage(j * 64, i * 64, 64, 64);
			}
	}

	private void levelInitializer() {
		BufferedImage levelImage = importImg("/LevelTest.png");
		levelWidth = levelImage.getWidth();
		levelHeight = levelImage.getHeight();
		level = new int[levelHeight][levelWidth];

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

	public void update() {

	}

	public void draw(Graphics g) {
		for (int i = levelHeight - 1; i > levelHeight - Screen.BLOCK_PER_HEIGHT - 2; i--)
			for (int j = 0; j < Screen.BLOCK_PER_WIDTH + 2; j++) {
				int block = level[i][j];
				if (block != -1) {
					g.drawImage(blockSprites[block], j * Screen.BLOCK_SIZE,
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, null);
				}
			}
	}
}
