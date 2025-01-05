package utils;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class ImageFliper {
	
	/**
     * Flips the supplied BufferedImage vertically. This is often a necessary
     * conversion step to display a Java2D image correctly with OpenGL and vice
     * versa.
     * 
     * @param theImage the image to flip
     */
    public static void flipImageVertically(BufferedImage theImage) {
        WritableRaster raster = theImage.getRaster();
        Object scanline1 = null;
        Object scanline2 = null;

        for (int i = 0; i < theImage.getHeight() / 2; i++) {
            scanline1 = raster.getDataElements(0, i, theImage.getWidth(),
                    1, scanline1);
            scanline2 = raster.getDataElements(0, theImage.getHeight() - i
                    - 1, theImage.getWidth(), 1, scanline2);
            raster.setDataElements(0, i, theImage.getWidth(), 1, scanline2);
            raster.setDataElements(0, theImage.getHeight() - i - 1,
                    theImage.getWidth(), 1, scanline1);
        }
    }
}
