package survival.main.ui.buttons;

import backbone.engine.main.BackboneMouseManager;
import backbone.engine.main.BackboneUtils;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * File: MenuButton
 * Language: Java
 * Author: Will 40
 * Date Created: 2/18/2022
 * Time Created: 11:37 PM
 * Project: Survive
 * Package: survival.main.ui.buttons
 */
public class MenuButton extends Button {

    private String name;
    private Font font;
    private RoundRectangle2D bounds;
    private BufferedImage background;
    private int recEdges = 15;

    public MenuButton(ButtonManager manager, int x, int y, int width, int height, String name, BufferedImage background) {
        super(manager, x, y, width, height);
        this.name = name;
        this.background = background;
        font = new Font("Arial", Font.PLAIN, 18);
        bounds = new RoundRectangle2D.Float(x, y, width, height, recEdges, recEdges);
    }

    @Override
    public void tick() {
        super.tick();
        if(getBounds().contains(BackboneMouseManager.mouse)) {
            heldOver = true;
            bounds = new RoundRectangle2D.Float(x + 3, y - 3, width, height, recEdges, recEdges);
            if(BackboneMouseManager.pressed) {
                onClick();
            }
        } else {
            heldOver = false;
            bounds = new RoundRectangle2D.Float(x, y, width, height, recEdges, recEdges);
        }
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.drawImage(background, (int) bounds.getX(), (int) bounds.getY(), width, height, null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.setFont(font);
        if(heldOver) {
            g.drawString(name, x + 3 + width / 2 - BackboneUtils.stringWidth(name, font) / 2, y - 3  + height / 2 + 8);
        } else {
            g.drawString(name, x + width / 2 - BackboneUtils.stringWidth(name, font) / 2, y  + height / 2 + 8);
        }
    }
}
