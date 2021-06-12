/**
 * File: StateMenu.java 
 * Author: William Forte
 * Time: 9:22:07 AM
 * Date: Mar 22, 2016
 * Project: Survival
 * Package: survival.main.gamestate
 */
package survival.main.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import backbone.engine.main.BackboneButtonManager;
import backbone.engine.main.BackboneGameState;
import backbone.engine.main.BackboneGameStateManager;
import backbone.engine.main.BackboneMousePulseManager;
import survival.main.Main;
import survival.main.gamestate.menu.MenuButton;
import survival.main.images.Assets;

/**
 * File: StateMenu.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 22, 2016
 * Time Created: 9:22:07 AM
 * Project: Survival
 * Package: survival.main.gamestate
 */

public class StateMenu extends BackboneGameState {
	
	private BackboneButtonManager button_manager;
	private BackboneMousePulseManager pulse_manager;

	/**
	 * @param gsm
	 */
	public StateMenu(BackboneGameStateManager gsm) {
		super(gsm);
		init();
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#init()
	 */
	@Override
	public void init() {
		pulse_manager = new BackboneMousePulseManager();
		button_manager = new BackboneButtonManager(gsm);
		button_manager.addButton(new MenuButton(button_manager, 100, 100, 120, 100 / 16 * 9, "Play").setState(new StateGame(gsm)));
		button_manager.addButton(new MenuButton(button_manager, 100, 175, 120, 100 / 16 * 9, "Options"));
		button_manager.addButton(new MenuButton(button_manager, 100, 250, 120, 100 / 16 * 9, "Quit"));
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int key) {
		
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int key) {
		
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		g.setColor(new Color(0x808080));
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.drawImage(Assets.background_palm_tree.getImage(), 500, 100, 400, 400, null);
		button_manager.render(g);
		pulse_manager.render(g);
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#tick()
	 */
	@Override
	public void tick() {
		button_manager.tick();
		pulse_manager.tick();
	}

}
