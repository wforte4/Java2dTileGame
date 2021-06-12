/**
 * File: ParticleRain.java 
 * Author: William Forte
 * Time: 12:59:46 PM
 * Date: May 17, 2016
 * Project: Survival
 * Package: survival.main.particle
 */
package survival.main.particle;

import java.awt.Graphics2D;

/**
 * File: ParticleRain.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 17, 2016
 * Time Created: 12:59:46 PM
 * Project: Survival
 * Package: survival.main.particle
 */

public class ParticleRain extends Particle {
	
	private float floating;

	/**
	 * @param manager
	 * @param x
	 * @param y
	 * @param size
	 * @param life
	 */
	public ParticleRain(ParticleManager manager, int x, int y, int size, int life) {
		super(manager, x, y, size, life);
		floating = 2;
		
	}
	
	public void tick() {
		
	}

}
