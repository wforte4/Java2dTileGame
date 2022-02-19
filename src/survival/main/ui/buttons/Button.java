package survival.main.ui.buttons;

import backbone.engine.main.BackboneButtonManager;

import java.awt.*;

/**
 * File: Button
 * Language: Java
 * Author: Will 40
 * Date Created: 2/18/2022
 * Time Created: 11:38 PM
 * Project: Survive
 * Package: survival.main.ui.buttons
 */
public class Button extends Rectangle {

    protected boolean heldOver = false;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected ButtonManager manager;

    public Button(ButtonManager manager, int x, int y, int width, int height) {
        this.manager = manager;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setBounds(x, y, width, height);
    }

    public void tick() {
        this.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics2D g) {
    }

    public void onClick() {

    }

    public void showBounds(Graphics2D g) {
        g.setColor(Color.RED);
        g.draw(this.getBounds());
    }
}
