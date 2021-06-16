/**
 * File: SurvivalGameLoop.java 
 * Author: William Forte
 * Time: 8:05:47 AM
 * Date: Mar 22, 2016
 * Project: Survival
 * Package: survival.main.gameloop
 */
package survival.main.gameloop;

import java.awt.*;
import java.awt.event.KeyEvent;

import backbone.engine.main.BackboneGameLoop;
import backbone.engine.main.BackboneGameStateManager;
import backbone.engine.main.BackboneMouseManager;
import backbone.engine.main.BackboneWindow;
import survival.main.gamestate.StateGame;
import survival.main.gamestate.StateSplash;
import survival.main.images.Assets;

/**
 * File: SurvivalGameLoop.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 22, 2016
 * Time Created: 8:05:47 AM
 * Project: Survival
 * Package: survival.main.gameloop
 */

@SuppressWarnings("serial")
public class SurvivalGameLoop extends BackboneGameLoop {
	
	private BackboneGameStateManager gsm;

	/**
	 * @param title
	 * @param width
	 * @param height
	 */
	public SurvivalGameLoop(BackboneWindow window, String title, int width, int height) {
		super(title, width, height);
		window.add(this);
		hideRegularMouse(window);
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameLoop#init()
	 */
	@Override
	public void init() {
		Assets.loadImages();
		setMouseImages(Assets.mouse.getImage(), Assets.mouse.getImage());
		setMouseSize(20);
		addMouseListener(new BackboneMouseManager());
		addMouseMotionListener(new BackboneMouseManager());
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		gsm = new BackboneGameStateManager();
		gsm.setOrigState(new StateGame(gsm));

	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameLoop#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameLoop#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameLoop#tick()
	 */
	@Override
	public void tick() {
		BackboneMouseManager.tick();
		gsm.tick();

	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameLoop#render(java.awt.Graphics2D)
	 */
	@Override
	protected void render(Graphics2D g) {
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		gsm.render(g);
		BackboneMouseManager.render(g);
	}


}
