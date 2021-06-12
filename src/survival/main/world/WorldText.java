/**
 * File: WorldText.java 
 * Author: William Forte
 * Time: 8:19:34 AM
 * Date: May 13, 2016
 * Project: Survival
 * Package: survival.main.world
 */
package survival.main.world;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import backbone.engine.main.BackboneVector2f;
import survival.main.entity.EntityManager;

/**
 * File: WorldText.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 13, 2016
 * Time Created: 8:19:34 AM
 * Project: Survival
 * Package: survival.main.world
 */

public class WorldText {
	
	private BackboneVector2f pos;
	private EntityManager manager;
	private Font font;
	private Color color;
	private String string;
	private Random random;
	private float life;
	private float speed;
	private double xx;
	private double yy;
	private boolean inWorld;
	
	public WorldText(EntityManager manager, BackboneVector2f pos, String string, boolean inWorld) {
		this.pos = pos;
		this.string = string;
		this.manager = manager;
		this.font = new Font("Courier", Font.BOLD, 28);
		this.color = new Color(0xb3ffb3);
		this.random = new Random();
		this.xx = random.nextGaussian();
		this.yy = -1;
		this.speed = 2;
		this.life = 1f;
		this.inWorld = inWorld;
	}
	
	public void tick() {
		pos.xpos += xx * speed;
		pos.ypos += yy * speed;
		if(life > .05) {
			life -= .02f;
		} else {
			manager.getWorld_texts().remove(this);
		}
	}
	
	public void render(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, life));
		g.setFont(font);
		if(inWorld) {		
			g.setColor(Color.BLACK);
			g.drawString(string, pos.getWorldLocation().xpos + 2, pos.getWorldLocation().ypos - 2);
			g.setColor(color);
			g.drawString(string, pos.getWorldLocation().xpos, pos.getWorldLocation().ypos);
		} else {
			g.setColor(Color.BLACK);
			g.drawString(string, pos.xpos + 2, pos.ypos - 2);
			g.setColor(color);
			g.drawString(string, pos.xpos, pos.ypos);
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}

}
