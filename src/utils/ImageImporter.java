package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageImporter {
	/**
	 * Load an image on stream and return it if is gotten
	 *
	 * @param path is the path of the image to load
	 */
	public static BufferedImage importImg(String path) {
		InputStream is = ImageImporter.class.getResourceAsStream(path);

		try {
			BufferedImage img = ImageIO.read(is);
			is.close();
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}