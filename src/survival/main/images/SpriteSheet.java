//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package survival.main.images;

import backbone.engine.main.BackboneSprite;
import backbone.engine.main.BackboneSpriteSheet;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;
    private int size;
    private int width;
    private int height;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public SpriteSheet(BufferedImage image, int size) {
        this.image = image;
        this.size = size;
    }

    public SpriteSheet(BufferedImage image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public SpriteSheet setSize(int size) {
        this.size = size;
        return this;
    }

    public SpriteSheet setHeight(int height) {
        this.height = height;
        return this;
    }

    public SpriteSheet setWidth(int width) {
        this.width = width;
        return this;
    }

    public BufferedImage getSprite(String name, int x, int y) {
        if (this.width != 0) {
            this.image.getSubimage(x * this.width, y * this.height, this.width, this.height);
        }

        return this.image.getSubimage(x * this.size, y * this.size, this.size, this.size);
    }

    public BufferedImage getSprite(String name, int x, int y, int width, int height) {
        return this.image.getSubimage(x * width, y * height, width, height);
    }

    public void setSprites(String name, BackboneSprite[] sprites, int xStart, int yStart) {
        for(int x = 0; x < sprites.length; ++x) {
            String sprite_name = name + " " + x;
            sprites[x] = new BackboneSprite(sprite_name, this.getSprite(sprite_name, xStart + x, yStart));
        }

    }

    public void setSpritesWithSize(String name, BackboneSprite[] sprites, int xStart, int yStart, int width, int height) {
        for(int x = 0; x < sprites.length; ++x) {
            String sprite_name = name + " " + x;
            sprites[x] = new BackboneSprite(sprite_name, this.getSprite(sprite_name, xStart + x, yStart, width, height));
        }

    }

    public void setSpritesSecondImage(String name, BackboneSprite[] sprites, int xStart, int yStart) {
        for(int x = 0; x < sprites.length; ++x) {
            String sprite_name = name + " " + x;
            sprites[x].setSecond_image(this.getSprite(sprite_name, xStart + x, yStart));
        }

    }
}
