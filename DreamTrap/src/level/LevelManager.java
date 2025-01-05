package level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.Arrays;
import dreamTrap.Screen;
import dreamTrap.Time;
import entities.Boss;
import entities.Character;

import static utils.ImageImporter.importImg;

public abstract class LevelManager {
	protected Character character;
	protected int xCharacterSpawn;
	protected int yCharacterSpawn;

	protected String backgroundPath;

	protected String blocksPath;
	private static final int BLOCKS_SIZE = 32;
	public static int blocksLength;
	protected BufferedImage[] blocks;

	protected String objectsPath;
	private static final int OBJECTS_SIZE = 32;
	public static int objectsLength;
	protected BufferedImage[] objects;

	private String levelPath;
	protected static int[][] level;

	protected static int levelHeight;
	protected static int levelWidth;
	private BufferedImage starImg;
	private BufferedImage spikeImg;
	protected int[][] stars;
	protected int[][] spikes;

	protected LevelManager(Screen screen, String blocksPath, String objectsPath, String levelPath) {
		character = screen.getCharacter();
		this.blocksPath = blocksPath;
		this.objectsPath = objectsPath;
		this.levelPath = levelPath;

		spritesInitializer();
		levelInitializer();
	}

	// getters and setters
	public int[][] getCurrentLevel() {
		return level;
	}

	public static int getLevelHeight() {
		return levelHeight;
	}

	public int getxCharacterSpawn() {
		return xCharacterSpawn;
	}

	public int getyCharacterSpawn() {
		return yCharacterSpawn;
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

	private void spritesInitializer() {
		if (blocksPath != null) {
			blocks = fillArray(BLOCKS_SIZE, blocksPath);
			blocksLength = blocks.length;
		}

		if (objectsPath != null) {
			objects = fillArray(OBJECTS_SIZE, objectsPath);
			objectsLength = objects.length;
		}

		this.starImg = Loadsave.importImg(Loadsave.STAR_IMG);
		this.spikeImg = Loadsave.importImg(Loadsave.SPIKE_IMG);
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
		BufferedImage levelImage = importImg(levelPath);
		levelWidth = levelImage.getWidth();
		levelHeight = levelImage.getHeight();
		level = new int[levelHeight][levelWidth];
		int value;
		starsInitializer();
		spikesInitializer();

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
				if (value > 127) {
					if (value > 127 + objectsLength - 3) {
						spikes[i][j] = value - 128;
					}
					// objects index is always higher than objects and we know blocks length
					level[i][j] = value - 128 + blocksLength;
				}

				value = color.getRed();
				if (value > 127) {
					// objects index is always higher than objects and we know blocks length
					if (value == 128)
						spikes[i][j] = value - 128;
					else if (value == 255)
						stars[i][j] = value - 128;
					else {
						stars[i][j] = -1;
					}
				}
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
		int x = (character.getPosX() - xCharacterSpawn) / Screen.BLOCK_SIZE;

		for (int i = levelHeight - 1; i > levelHeight - Screen.BLOCK_PER_HEIGHT - 1; i--)
			for (int j = 0; j < Screen.BLOCK_PER_WIDTH + 1; j++) {
				int block = level[i][j + x];
				int star = stars[i][j + x];
				int spike = spikes[i][j + x];

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

				if (star != -1) {
					g.drawImage(starImg, j * Screen.BLOCK_SIZE - (character.getPosX() % Screen.BLOCK_SIZE),
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, null);
				}

				if (spike != -1) {
					g.drawImage(spikeImg, j * Screen.BLOCK_SIZE - (character.getPosX() % Screen.BLOCK_SIZE),
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, null);
				}
			}
		additionalDraw(g);
	}

	protected abstract void additionalDraw(Graphics g);

	public abstract void update();

	public abstract void starsInitializer();

	public abstract void spikesInitializer();
}
