/**
 * File: GUI.java 
 * Author: William Forte
 * Time: 5:27:14 PM
 * Date: Apr 30, 2016
 * Project: Survival
 * Package: survival.main.ui
 */
package survival.main.ui;

import java.awt.*;

import survival.main.Main;
import survival.main.entity.creatures.Player;
import survival.main.utils.Util;

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
	private int healthBarWidth = 800;
	private double healthToWidth = healthBarWidth;
	
	public GUI(Player player) {
		this.player = player;
	}

	public void tick() {
		this.healthToWidth = (player.getHealth() * .01) * healthBarWidth;
	}
	
	public void render(Graphics2D g) {
		player.getExperienceBar().render(g);
		g.setColor(Color.black);
		g.fillRect(((Main.WIDTH / 2) - healthBarWidth / 2) - 2, Main.HEIGHT - 102, healthBarWidth + 4, 14);
		g.setColor(new Color(237,92,80));
		g.fillRect((Main.WIDTH / 2) - healthBarWidth / 2, Main.HEIGHT - 100, (int) healthToWidth, 10);
	}

}
