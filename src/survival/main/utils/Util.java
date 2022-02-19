package survival.main.utils;

import backbone.engine.main.BackboneImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * File: Utils
 * Language: Java
 * Author: Will 40
 * Date Created: 2/18/2022
 * Time Created: 11:15 PM
 * Project: Survive
 * Package: survival.main.utils
 */

public class Util {

    public static void print(String x) {
        System.out.println(x);
    }

    public static void print(Integer x) {
        System.out.println(x);
    }

    public static void print(Boolean x) {
        System.out.println(x);
    }

    public static void print(Object x) {
        System.out.println(x);
    }

    public static void print(char x) {
        System.out.println(x);
    }

    public static void print(char[] x) {
        System.out.println(x);
    }

    public static void print(double x) {
        System.out.println(x);
    }

    public static void print(float x) {
        System.out.println(x);
    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Util.class.getResourceAsStream(path));
        } catch (IOException var2) {
            var2.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static BufferedImage setImageOverlayColorRed(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        int width = image.getWidth();
        int height = image.getHeight();
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color pixelColor = new Color(image.getRGB(x, y), true);
                if(pixelColor.getAlpha() == 0) {
                    continue;
                } else {
                    newImage.setRGB(x, y, new Color(255, 0, 0).getRGB());
                }
            }
        }
        return newImage;
    }

}
