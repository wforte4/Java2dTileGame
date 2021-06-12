/**
 * File: GUI.java 
 * Author: William Forte
 * Time: 5:27:14 PM
 * Date: Apr 30, 2016
 * Project: Survival
 * Package: survival.main.ui
 */
package survival.main.ui;

import java.awt.Graphics2D;

import survival.main.entity.creatures.Player;

/**
 * File: GUI.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 30, 2016
 * Time Created: 5:27:14 PM
 * Project: Survival
 * Package: survival.main.ui
 */

public class GUI {
	
	private Player player;
	
	public GUI(Player player) {
		this.player = player;
	}
	
	public void render(Graphics2D g) {
		player.getAcerbia_bar().render(g);
	}

}
