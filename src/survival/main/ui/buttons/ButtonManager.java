package survival.main.ui.buttons;

import backbone.engine.main.BackboneButton;
import backbone.engine.main.BackboneGameStateManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * File: ButtonManager
 * Language: Java
 * Author: Will 40
 * Date Created: 2/18/2022
 * Time Created: 11:36 PM
 * Project: Survive
 * Package: survival.main.ui.buttons
 */
public class ButtonManager {

    private ArrayList<Button> buttons;
    private BackboneGameStateManager gsm;

    public ButtonManager(BackboneGameStateManager gsm) {
        this.gsm = gsm;
        this.buttons = new ArrayList();
    }

    public void tick() {
        Iterator var2 = this.buttons.iterator();

        while(var2.hasNext()) {
            Button button = (Button) var2.next();
            button.tick();
        }

    }

    public void render(Graphics2D g) {
        Iterator var3 = this.buttons.iterator();

        while(var3.hasNext()) {
            Button button = (Button) var3.next();
            button.render(g);
        }

    }

    public void addButton(Button button) {
        this.buttons.add(button);
    }

    public void removeButton(Button button) {
        this.buttons.remove(button);
    }

    public BackboneGameStateManager getGsm() {
        return this.gsm;
    }
}
