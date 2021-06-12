/**
 * File: BarFade.java 
 * Author: William Forte
 * Time: 5:34:08 PM
 * Date: Apr 30, 2016
 * Project: Survival
 * Package: survival.main.ui.bar
 */
package survival.main.ui.bar;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import backbone.engine.main.BackboneVector2f;

/**
 * File: BarFade.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 30, 2016
 * Time Created: 5:34:08 PM
 * Project: Survival
 * Package: survival.main.ui.bar
 */

public class BarFade {
	
	private BackboneVector2f pos;
	private GrowthBar bar;
	private Color color;
	private int width;
	private int height;
	private int speed;
	private float life = .4f;
	private float xx;
	private float yy;
	
	public BarFade(GrowthBar bar, BackboneVector2f pos, int width, int height, Color color) {
		this.bar = bar;
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = new Random().nextInt(2) + 1;
		this.xx = (float) (new Random().nextGaussian() * speed);
		this.yy = (float) (new Random().nextGaussian() * speed);
	}
	
	public void tick() {
		pos.xpos -= xx;
		pos.ypos -= yy;
		if(life > .02) {			
			life -= .01;
		} else {
			bar.getBars().remove(this);
		}
	}
	
	public void render(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, life));
		g.setColor(color);
		g.fillRect((int) pos.xpos, (int) pos.ypos, width, height);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
