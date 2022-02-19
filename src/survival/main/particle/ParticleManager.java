/**
 * File: ParticleManager.java 
 * Author: William Forte
 * Time: 11:39:33 PM
 * Date: Apr 28, 2016
 * Project: Survival
 * Package: survival.main.particle
 */
package survival.main.particle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import backbone.engine.main.BackboneVector2f;
import survival.main.entity.Entity;
import survival.main.entity.creatures.Player;
import survival.main.images.Assets;

/**
 * File: ParticleManager.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 28, 2016
 * Time Created: 11:39:33 PM
 * Project: Survival
 * Package: survival.main.particle
 */

public class ParticleManager {
	
	private CopyOnWriteArrayList<Particle> particles;
	private ParticleManagerType type;
	private Entity entity;
	private Random random;
	private Color color;
	private BufferedImage image;
	private boolean generating;
	private int timeBetweenParticles;
	private int generate_particle_counter;
	private int particleLifeTime;
	private int width;
	private int height;
	private int x;
	private int y;
	private int particleSize;
	
	public ParticleManager(ParticleManagerType type, Entity entity, int particleLifeTime, int timeBetweenParticles, int particleSize) {
		this.particles = new CopyOnWriteArrayList<Particle>();
		this.type = type;
		this.entity = entity;
		this.particleLifeTime = particleLifeTime;
		this.timeBetweenParticles = timeBetweenParticles;
		this.x = (int) entity.getPos().xpos;
		this.y = (int) entity.getPos().ypos;
		this.width = entity.getImageWidth() / 2;
		this.height = entity.getImageHeight() / 2;
		this.generating = false;
		this.random = new Random();
		this.particleSize = particleSize;
	}
	
	public ParticleManager(ParticleManagerType type, BackboneVector2f pos, int width, int height, int particleLifeTime, int timeBetweenParticles, int particleSize) {
		this.particles = new CopyOnWriteArrayList<Particle>();
		this.type = type;
		this.particleLifeTime = particleLifeTime;
		this.timeBetweenParticles = timeBetweenParticles;
		this.x = (int) pos.xpos;
		this.y = (int) pos.ypos;
		this.width = width;
		this.height = height;
		this.generating = false;
		this.random = new Random();
		this.particleSize = particleSize;
	}
	
	public void tick() {

		// If there is an entity then get his position and set it to the position of the particle manager
		if(entity != null) {
			if(entity instanceof Player) {
				this.x = (int) ((Player) entity).getElement_pos().xpos + width / 4;
				this.y = (int) ((Player) entity).getElement_pos().ypos + height / 4;
			} else {				
				this.x = (int) (entity.getPos().xpos + width / 4);
				this.y = (int) (entity.getPos().ypos + width / 4);
			}
		}

		// Generation counter of the particles
		// Decides when the particle should be created
		if(generating) {
			generate_particle_counter++;
			if(generate_particle_counter > timeBetweenParticles) {
				if(width > 0 && height > 0) {
					int xx = random.nextInt(width) + (int) x;
					int yy = random.nextInt(height) + (int) y;
					addParticle(xx, yy);
				}
				generate_particle_counter = 0;
			}
		}

		// Tick each particle
		for(Particle particle: particles) {
			particle.tick();
		}
	}
	
	private void addParticle(int x, int y) {
		switch(type) {
		case AMBIENT:
			Particle particle = new ParticleAmbient(
					this,
					x,
					y,
					particleSize,
					particleLifeTime)
			.setInWorld(true)
			.setDarkness(random.nextFloat());
			if(color != null) particle.setColor(color);
			if(image != null) particle.setImage(image);
			particles.add(particle);
			break;
		case RAIN:
			Particle particle_rain = new ParticleRain(this, x, y, particleSize, particleLifeTime)
			.setInWorld(false)
			.setDarkness(0)
			.setImage(Assets.rain_drop.getImage());
			particles.add(particle_rain);
			break;
		default:
			System.out.println("Didn't add a Particle :(");
			break;
		}
	}
	
	public void render(Graphics2D g) {
		for(Particle particle: particles) {
			particle.render(g);
		}
	}
	
	/**
	 * @param color the color to set
	 */
	public ParticleManager setColor(Color color) {
		this.color = color;
		return this;
	}
	
	/**
	 * @param image the image to set
	 */
	public ParticleManager setImage(BufferedImage image) {
		this.image = image;
		return this;
	}
	
	public void removeParticle(Particle particle) {
		particles.remove(particle);
	}
	
	/**
	 * @param generating the generating to set
	 */
	public ParticleManager setGenerating(boolean generating) {
		this.generating = generating;
		return this;
	}

}
