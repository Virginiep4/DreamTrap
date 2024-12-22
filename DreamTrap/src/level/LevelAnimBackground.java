package level;

import static utils.ImageImporter.importImg;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dreamTrap.Screen;

public abstract class LevelAnimBackground extends LevelManager {
	private int aniBgTick, aniBgIndex = 0, aniBgSpeed = 100;
	protected BufferedImage[] background;

	protected LevelAnimBackground(Screen screen, String backgroundPath, String blocksPath, String objectsPath, String levelPath) {
		super(screen, blocksPath, objectsPath, levelPath);
		
		this.backgroundPath = backgroundPath;
		backgroundInitializer();
	}
	
	/**
	 * Puts all the background of the level in an BufferedImage array
	 */
	protected void backgroundInitializer() {
		BufferedImage backgrounds = importImg(backgroundPath);
		int backgroundsAmount = backgrounds.getWidth() / 1920;
		background = new BufferedImage[backgroundsAmount];
		
		for (int i = 0; i < backgroundsAmount; i++) {
			background[i] = backgrounds.getSubimage(1920 * i, 0, 1920, 1080);
		}
		
	}
	
	@Override
	protected void drawBackground(Graphics g) {
		g.drawImage(background[aniBgIndex], 0, 0, Screen.BLOCK_PER_WIDTH * Screen.BLOCK_SIZE, Screen.BLOCK_PER_HEIGHT * Screen.BLOCK_SIZE, null);
		
	}
	
	protected void updateBackgroundTick() {
		aniBgTick++;
		
		if (aniBgTick >= aniBgSpeed) {
			aniBgIndex = ++aniBgIndex % background.length;
			aniBgTick = 0;
		}
	}
}
