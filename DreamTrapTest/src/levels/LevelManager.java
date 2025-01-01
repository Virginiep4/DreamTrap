package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
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
	private static int levelHeight;
	private int levelWidth;
	private BufferedImage[] starSprites;
	private BufferedImage starImg;
	private int[][] stars;
	
	public LevelManager(Screen screen) {
		character = screen.getCharacter();
		blockSprites=new BufferedImage[SPRITES_WIDTH*SPRITES_HEIGHT];
		spritesInitializer();
		levelInitializer();
	}
	
	//getters and setters
	public int[][] getCurrentLevel(){
		return level;
	}
	public static int getLevelHeight() {
		return levelHeight;
	}
	
	private void spritesInitializer() {
		
		blockSprites = new BufferedImage[SPRITES_HEIGHT * SPRITES_WIDTH];
		img=Loadsave.importImg(Loadsave.BLOC_IMG);		

		for (int i = 0; i < SPRITES_HEIGHT; i++)
			for (int j = 0; j < SPRITES_WIDTH; j++) {
				blockSprites[i * SPRITES_WIDTH + j] = img.getSubimage(j * 64, i * 64, 64, 64);
			}
		
		this.starImg=Loadsave.importImg(Loadsave.STAR_IMG);

	}
	
	private void levelInitializer() {
		BufferedImage levelImage=Loadsave.importImg(Loadsave.LEVEL_IMG);
		levelWidth = levelImage.getWidth();
		levelHeight = levelImage.getHeight();
		level = new int[levelHeight][levelWidth];
		starsInitializer();

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
				if (block != -1) {
					g.drawImage(blockSprites[block], j * Screen.BLOCK_SIZE - (character.getPosX() % 64),
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, Screen.BLOCK_SIZE, null);
				}
				if (star != -1) {
					g.drawImage(starImg, j * Screen.BLOCK_SIZE - (character.getPosX() % 64),
							(Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE,
							Screen.BLOCK_SIZE, Screen.BLOCK_SIZE, null);
				}
			}
	}
	
	public void update() {
		if (HelpMethods.IsStar(character)) {
			HelpMethods.gotStar(character, this);
		}
	}
	
	// Stars
	
	public void starsInitializer() {
		stars = new int[levelHeight][levelWidth];
		
		for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[i].length; j++) {
                stars[i][j] = -1;
            }
        }
		
		stars[25][7]=0;stars[25][30]=0;stars[27][11]=0;stars[26][22]=0;
	}

	public int[][] getStars() {
		return stars;
	}

	public void setStars(int[][] stars) {
		this.stars = stars;
	}
}
