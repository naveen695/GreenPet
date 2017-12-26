package com.dgree.detector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;
 
public class SkinToneSpanningDetector extends SkinToneDetector {

    @Override
    public boolean isNotValid(final InputStream inputStream) throws IOException {
        final BufferedImage bufferedImage = ImageIO.read(inputStream);
        final Raster raster = bufferedImage.getData();

        final int[] avgRgb = new int[3];
        for(int[] rgb : SKINTONES) {
            avgRgb[0] += rgb[0];
            avgRgb[1] += rgb[1];
            avgRgb[2] += rgb[2];
        }
        avgRgb[0] /= SKINTONES.length;
        avgRgb[1] /= SKINTONES.length;
        avgRgb[2] /= SKINTONES.length;

        final int[] rgb = new int[4];
        final int totalPixels = raster.getHeight() * raster.getWidth();
        int skinTonePixels = 0;
        for(int y = 0; y < raster.getHeight(); y++) {
            for(int x = 0; x < raster.getWidth(); x++) {
                raster.getPixel(x, y, rgb);

                int xs = Math.abs(avgRgb[0] - rgb[0]);
                xs *= xs;
                int ys = Math.abs(avgRgb[1] - rgb[1]);
                ys *= ys;
                int zs = Math.abs(avgRgb[2] - rgb[2]);
                zs *= zs;

                boolean matchesSkinTone = Math.sqrt(xs + ys + zs) <= skinToneDelta;
                if(matchesSkinTone) {
                    skinTonePixels++;
                }
            }
        }
        final long skinTonesThreshold = Math.round(skinTonePixels / (double) totalPixels * 100.0);
        System.out.println(skinTonesThreshold);
        return skinTonesThreshold > isDelta;
    }

}
