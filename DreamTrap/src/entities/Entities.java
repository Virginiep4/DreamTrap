package entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public abstract class Entities {
	protected int posX = 0;
	protected int posY = 0;
	
	public Entities() {
		importEntity();
	}
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Puts all the frame of character animation in each character array
	 */
	abstract void importEntity();

	/**
	 * Load an image on stream and return it if is gotten
	 *
	 * @param path is the path of the image to load
	 */
	protected BufferedImage importImg(String path) {
		InputStream is = getClass().getResourceAsStream(path);

		try {
			BufferedImage img = ImageIO.read(is);
			is.close();
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Where the animation of the character are handled
	 */
	abstract void updateCharacAnimationTick();
}
