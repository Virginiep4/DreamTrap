package level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Loadsave {

	public static final String BLOC_IMG="/blockSprites.png";
	public static final String LEVEL_IMG="/LevelTest.png";
	public static final String STAR_IMG="/stars.png";
	public static final String SPIKE_IMG="/spikeV2.png";
	
	
	
	public static  BufferedImage importImg(String path) {
		BufferedImage img=null;
		InputStream is = Loadsave.class.getResourceAsStream(path);

		try {
			 img = ImageIO.read(is);
			is.close();
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return img;
		}
	}
}
