/**
 * File: StateSplash.java 
 * Author: William Forte
 * Time: 11:28:55 AM
 * Date: Apr 7, 2016
 * Project: Survival
 * Package: survival.main.gamestate
 */
package survival.main.gamestate;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

import backbone.engine.main.BackboneGameState;
import backbone.engine.main.BackboneGameStateManager;
import survival.main.gamestate.splash.SplashScreen;
import survival.main.images.Assets;

/**
 * File: StateSplash.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 7, 2016
 * Time Created: 11:28:55 AM
 * Project: Survival
 * Package: survival.main.gamestate
 */

public class StateSplash extends BackboneGameState {
	
	private Stack<SplashScreen> screens;
	private ArrayList<SplashScreen> allScreens;

	/**
	 * @param gsm
	 */
	public StateSplash(BackboneGameStateManager gsm) {
		super(gsm);
		screens = new Stack<SplashScreen>();
		allScreens = new ArrayList<SplashScreen>();
		allScreens.add(
				new SplashScreen(
						Assets.background_loading_one.getImage(),
						110, 
						0)
				.setLogoDimension(300, 300));
		screens.push(allScreens.get(0));
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#init()
	 */
	@Override
	public void init() {
		
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int arg0) {

	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int arg0) {

	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		screens.peek().render(g);
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#tick()
	 */
	@Override
	public void tick() {
		screens.peek().tick();
		if(screens.peek().isOver()) {
			if(screens.peek().getOrder() == allScreens.size() - 1) {
				gsm.switchState(new StateMenu(gsm));
			} else {				
				screens.push(allScreens.get(screens.peek().getOrder() + 1));
			}
		}
	}

}
