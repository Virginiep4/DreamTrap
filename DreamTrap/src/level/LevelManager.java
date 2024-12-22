package level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dreamTrap.Screen;
import entities.Character;
import entities.Boss;

import static utils.ImageImporter.importImg;

public class LevelManager {
	private Character character;
	private Boss boss;

	private static final int BLOCKS_SIZE = 32;
	private static int blocksLength;
	private BufferedImage[] blocks;

	private static final int OBJECTS_SIZE = 32;
	private static int objectsLength;
	private BufferedImage[] objects;

	private int[][] level;
	private int levelHeight;
	private int levelWidth;

	public LevelManager(Screen screen) {
		character = screen.getCharacter();
		boss = screen.getBoss();
		spritesInitializer();
		levelInitializer();
	}

	/**
	 * Puts all blocks in the blockSprites array The levels are built with those
	 * blocks
	 */
	private void spritesInitializer() {
		blocks = fillArray(BLOCKS_SIZE, "/blockSprites.png");
		blocksLength = blocks.length;
		objects = fillArray(OBJECTS_SIZE, "/objectSprites.png");
		objectsLength = objects.length;
	}

	private BufferedImage[] fillArray(int size, String path) {
		BufferedImage img = importImg(path);
		int height = img.getHeight() / size;
		int width = img.getWidth() / size;
		BufferedImage[] array = new BufferedImage[height * width]; // initialize the array according to image size

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				// place each square in right index
				array[i * width + j] = img.getSubimage(j * size, i * size, size, size);
			}

		return array;
	}

	/**
	 * level[][] represents all pixels of a level Initialize the level[][] value
	 * with -1 if no block is in or with the index of the block in blockSprites this
	 * index is the Green RGB value of the pixel in the imported image
	 */
	private void levelInitializer() {
		BufferedImage levelImage = importImg("/LevelOne.png");
		levelWidth = levelImage.getWidth();
		levelHeight = levelImage.getHeight();
		level = new int[levelHeight][levelWidth];
		int value;

		for (int i = 0; i < levelHeight; i++)
			for (int j = 0; j < levelWidth; j++) {
				Color color = new Color(levelImage.getRGB(j, i));

				// block test
				value = color.getGreen();
				if (value < 128)
					level[i][j] = -1;
				else
					level[i][j] = value - 128;

				// object test
				value = color.getBlue();
				if (value > 127)
					// objects index is always higher than objects and we know blocks length
					level[i][j] = value - 128 + blocksLength;

			}
	}

	/**
	 * Draws all the visible blocks from what level[][] contains x is the number of
	 * blocks that character has gone through
	 * 
	 * for drawImage : second argument = is the j-th block from the left and adapt
	 * the the position for smooth movings third argument = is the i-th block from
	 * the bottom
	 *
	 * @param g is given by screen to allow repainting in this function
	 */
	public void draw(Graphics g) {
		int x = character.getPosX() / Screen.BLOCK_SIZE;

		for (int i = levelHeight - 1; i > levelHeight - Screen.BLOCK_PER_HEIGHT - 1; i--)
			for (int j = 0; j < Screen.BLOCK_PER_WIDTH + 1; j++) {
				int block = level[i][j + x];
				if (block != -1) {
					if (block < blocksLength) {
						g.drawImage(blocks[block], j * Screen.BLOCK_SIZE - (character.getPosX() % Screen.BLOCK_SIZE),
								(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
								Screen.BLOCK_SIZE, null);
					} else if (block < blocksLength + objectsLength) {
						g.drawImage(objects[block - blocksLength],
								j * Screen.BLOCK_SIZE - (character.getPosX() % Screen.BLOCK_SIZE),
								(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
								Screen.BLOCK_SIZE, null);
					}
				}
			}
			g.drawImage(boss.getBoss()[boss.getCurrentAnimation()][boss.getAniIndex()],
					boss.getxBlock() * Screen.BLOCK_SIZE + boss.getmovingXBlock() - character.getPosX(),
					boss.getyBlock() * Screen.BLOCK_SIZE + boss.getmovingYBlock(), Screen.BLOCK_SIZE*2,
					Screen.BLOCK_SIZE*3, null);
	}
}
