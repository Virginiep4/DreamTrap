package level;

import static utils.ImageImporter.importImg;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dreamTrap.Screen;

public abstract class LevelNoAnimBackground extends LevelManager {
	protected BufferedImage background;

	protected LevelNoAnimBackground(Screen screen, String backgroundPath, String blocksPath, String objectsPath, String levelPath) {
		super(screen, blocksPath, objectsPath, levelPath);
		
		this.backgroundPath = backgroundPath;
		backgroundInitializer();
	}
	

	@Override
	protected void backgroundInitializer() {
		 background = importImg(backgroundPath);
		
	}

	@Override
	protected void drawBackground(Graphics g) {
		g.drawImage(background, 0, 0, Screen.BLOCK_PER_WIDTH * Screen.BLOCK_SIZE, Screen.BLOCK_PER_HEIGHT * Screen.BLOCK_SIZE, null);		
	}
}
