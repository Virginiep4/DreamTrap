package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import dreamTrap.Screen;
import entities.Character;

public class LevelManager {
	

	private Character character;
	// sprites : img de bloc
	private BufferedImage[] blockSprites;
	private BufferedImage img;
	private final static int SPRITES_WIDTH = 5; // block width of the image for blocsSpr
	private final static int SPRITES_HEIGHT = 2;
	private int[][] level;
	private int levelHeight;
	private int levelWidth;
	
	public LevelManager(Screen screen) {
		character = screen.getCharacter();
		blockSprites=new BufferedImage[SPRITES_WIDTH*SPRITES_HEIGHT];
		spritesInitializer();
		levelInitializer();
	}
	
	private void spritesInitializer() {
		
		blockSprites = new BufferedImage[SPRITES_HEIGHT * SPRITES_WIDTH];
		img=Loadsave.importImg(Loadsave.BLOC_IMG);
		

		for (int i = 0; i < SPRITES_HEIGHT; i++)
			for (int j = 0; j < SPRITES_WIDTH; j++) {
				blockSprites[i * SPRITES_WIDTH + j] = img.getSubimage(j * 64, i * 64, 64, 64);
			}
	}
	
	private void levelInitializer() {
		BufferedImage levelImage=Loadsave.importImg(Loadsave.LEVEL_IMG);
		levelWidth = levelImage.getWidth();
		levelHeight = levelImage.getHeight();
		level = new int[levelHeight][levelWidth];

		for (int i = 0; i < levelHeight; i++)
			for (int j = 0; j < levelWidth; j++) {
				Color color = new Color(levelImage.getRGB(j, i));
				int value = color.getGreen();
				if (value < 128)
					level[i][j] = 0;
				else
					level[i][j] = value - 128;
			}
	}
	
	
	public void draw(Graphics g) {
		int x = character.getPosX() / 64;
		for (int i = levelHeight - 1; i > levelHeight - Screen.BLOCK_PER_HEIGHT; i--)
			for (int j = 0; j < Screen.BLOCK_PER_WIDTH + 2; j++) {
				int block = level[i][j + x];
				if (block != 0) {
					g.drawImage(blockSprites[block], j * Screen.BLOCK_SIZE - (character.getPosX() % 64),
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, Screen.BLOCK_SIZE, null);
				}
			}
		
	}
	
	public void update() {}
	
	public int[][] getCurrentLevel(){
		return level;
	}

	public int getLevelHeight() {
		return levelHeight;
	}

	
	
}
