/**
 * File: EntityStill.java 
 * Author: William Forte
 * Time: 8:15:40 PM
 * Date: Apr 26, 2016
 * Project: Survival
 * Package: survival.main.entity.still
 */
package survival.main.entity.still;

import java.awt.Graphics2D;

import survival.main.entity.Entity;
import survival.main.generation.World;

/**
 * File: EntityStill.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 26, 2016
 * Time Created: 8:15:40 PM
 * Project: Survival
 * Package: survival.main.entity.still
 */

@SuppressWarnings("serial")
public abstract class EntityStill extends Entity {

	/**
	 * @param world
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 */
	public EntityStill(World world, float xpos, float ypos, int width, int height) {
		super(world, xpos, ypos, width, height);
	}

	/* (non-Javadoc)
	 * @see survival.main.entity.Entity#tick()
	 */
	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see survival.main.entity.Entity#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
