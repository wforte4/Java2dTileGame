/**
 * File: Main.java 
 * Author: William Forte
 * Time: 8:03:30 AM
 * Date: Mar 22, 2016
 * Project: Survival
 * Package: survival.main
 */
package survival.main;

import backbone.engine.main.BackboneWindow;
import survival.main.gameloop.SurvivalGameLoop;

/**
 * File: Main.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 22, 2016
 * Time Created: 8:03:30 AM
 * Project: Survival
 * Package: survival.main
 */

public class Main {
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = WIDTH / 16 * 9;
	
	public static void main(String[] args) {
		BackboneWindow window = new BackboneWindow("Survival", WIDTH, HEIGHT);
		window.setResizable(true);
		SurvivalGameLoop game = new SurvivalGameLoop(window, "Survival", WIDTH, HEIGHT);
		game.start();
	}

}
