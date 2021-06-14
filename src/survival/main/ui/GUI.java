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
	private int healthToWidth = healthBarWidth;
	
	public GUI(Player player) {
		this.player = player;
	}

	public void tick() {
		this.healthToWidth = (int) (player.getHealth() * .01) * healthBarWidth;
		System.out.println(healthToWidth);
		System.out.println(player.getHealth());
	}
	
	public void render(Graphics2D g) {
		player.getAcerbia_bar().render(g);
		g.setColor(Color.black);
		g.fillRect(((Main.WIDTH / 2) - healthBarWidth / 2) - 2, Main.HEIGHT - 52, healthBarWidth + 4, 14);
		g.setColor(Color.red);
		g.fillRect((Main.WIDTH / 2) - healthBarWidth / 2, Main.HEIGHT - 50, healthToWidth, 10);
	}

}
