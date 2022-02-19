package survival.main.ui.buttons;

import backbone.engine.main.BackboneGameState;

import java.awt.image.BufferedImage;

/**
 * File: MenuStateButton
 * Language: Java
 * Author: Will 40
 * Date Created: 2/18/2022
 * Time Created: 11:49 PM
 * Project: Survive
 * Package: survival.main.ui.buttons
 */
public class MenuStateButton extends MenuButton {

    BackboneGameState state;

    public MenuStateButton(ButtonManager manager, int x, int y, int width, int height, String name, BufferedImage background, BackboneGameState state) {
        super(manager, x, y, width, height, name, background);
        this.state = state;
    }

    @Override
    public void onClick() {
        super.onClick();
        manager.getGsm().switchState(state);
    }
}
