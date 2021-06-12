/**
 * File: Condition.java 
 * Author: William Forte
 * Time: 12:54:51 PM
 * Date: May 17, 2016
 * Project: Survival
 * Package: survival.main.weather
 */
package survival.main.weather;

import java.awt.Graphics2D;

import survival.main.particle.ParticleManager;

/**
 * File: Condition.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 17, 2016
 * Time Created: 12:54:51 PM
 * Project: Survival
 * Package: survival.main.weather
 */

public abstract class Condition {
	
	protected ParticleManager particle_manager;
	
	public Condition() {
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);

}
