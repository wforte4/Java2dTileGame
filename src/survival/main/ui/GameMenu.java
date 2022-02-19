package survival.main.ui;

import backbone.engine.main.BackboneGameState;
import backbone.engine.main.BackboneGameStateManager;
import survival.main.ui.buttons.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * File: GameMenu
 * Language: Java
 * Author: Will 40
 * Date Created: 2/18/2022
 * Time Created: 11:28 PM
 * Project: Survive
 * Package: survival.main.ui
 */
public class GameMenu {

    private BufferedImage background;
    private int x, y, width, height;
    ButtonManager manager;

    public GameMenu(int x, int y, int width, int height, BufferedImage background, BackboneGameStateManager gsm) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.background = background;
        manager = new ButtonManager(gsm);
    }

    public void tick() {
        manager.tick();
    }

    public void render(Graphics2D g) {
        g.drawImage(background, x, y, width, height, null);
        manager.render(g);
    }

    public ButtonManager getManager() {
        return manager;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
