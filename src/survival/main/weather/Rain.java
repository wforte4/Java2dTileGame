/**
 * File: Rain.java 
 * Author: William Forte
 * Time: 12:57:00 PM
 * Date: May 17, 2016
 * Project: Survival
 * Package: survival.main.weather
 */
package survival.main.weather;

import java.awt.Graphics2D;

import backbone.engine.main.BackboneVector2f;
import survival.main.Main;
import survival.main.particle.ParticleManager;
import survival.main.particle.ParticleManagerType;

/**
 * File: Rain.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 17, 2016
 * Time Created: 12:57:00 PM
 * Project: Survival
 * Package: survival.main.weather
 */

public class Rain extends Condition {
	
	public Rain() {
		particle_manager = new ParticleManager(
				ParticleManagerType.RAIN, 
				new BackboneVector2f(-40, -40),
				Main.WIDTH, 
				20,
				100,
				2);
	}

	/* (non-Javadoc)
	 * @see survival.main.weather.Condition#tick()
	 */
	@Override
	public void tick() {
		particle_manager.setGenerating(true);
		particle_manager.tick();
	}

	/* (non-Javadoc)
	 * @see survival.main.weather.Condition#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		particle_manager.render(g);
	}

}
