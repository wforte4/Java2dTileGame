/**
 * File: Particle.java 
 * Author: William Forte
 * Time: 11:40:07 PM
 * Date: Apr 28, 2016
 * Project: Survival
 * Package: survival.main.particle
 */
package survival.main.particle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import backbone.engine.main.BackboneVector2f;

/**
 * File: Particle.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 28, 2016
 * Time Created: 11:40:07 PM
 * Project: Survival
 * Package: survival.main.particle
 */

public abstract class Particle {
	
	protected ParticleManager manager;
	protected BufferedImage image;
	protected BackboneVector2f pos;
	protected Random random = new Random();
	protected Color color;
	protected float darkness;
	protected boolean inWorld;
	protected int size;
	protected int life;
	protected int life_counter;
	
	public Particle(ParticleManager manager, int x, int y, int size, int life) {
		this.pos = new BackboneVector2f(x, y);
		this.manager = manager;
		this.size = size;
		this.life = life;
	}
	
	public Particle setInWorld(boolean inWorld) {
		this.inWorld = inWorld;
		return this;
	}
	
	public Particle setImage(BufferedImage image) {
		this.image = image;
		return this;
	}
	
	public Particle setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public Particle setDarkness(float darkness) {
		this.darkness = darkness;
		return this;
	}
	
	protected void tick() {
		life_counter++;
		if(life_counter > life) {
			manager.removeParticle(this);
		}
	}
	
	protected void render(Graphics2D g) {
		if(image != null) {
			if(inWorld) {
				g.drawImage(image, (int) pos.getWorldLocation().xpos, (int) pos.getWorldLocation().ypos, size, size, null);
			} else {
				g.drawImage(image, (int) pos.xpos, (int) pos.ypos, size, size, null);
			}
		} else {
			g.setColor(color);
			if(inWorld) {
				g.fillRect((int) pos.getWorldLocation().xpos, (int) pos.getWorldLocation().ypos, size, size);
			} else {
				g.fillRect((int) pos.xpos, (int) pos.ypos, size, size);
			}
			g.setColor(Color.BLACK);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, darkness));
			if(inWorld) {			
				g.fillRect((int) pos.getWorldLocation().xpos, (int) pos.getWorldLocation().ypos, size, size);
			} else {
				g.fillRect((int) pos.xpos, (int) pos.ypos, size, size);
			}
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}

}
