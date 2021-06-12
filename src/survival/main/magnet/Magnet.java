/**
 * File: Magnet.java 
 * Author: William Forte
 * Time: 10:17:54 AM
 * Date: May 12, 2016
 * Project: Survival
 * Package: survival.main.magnet
 */
package survival.main.magnet;

import backbone.engine.main.BackboneVector2f;
import survival.main.entity.Entity;

/**
 * File: Magnet.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 12, 2016
 * Time Created: 10:17:54 AM
 * Project: Survival
 * Package: survival.main.magnet
 */

public class Magnet {
	
	protected Entity entity;
	
	public Magnet(Entity entity) {
		this.entity = entity;
	}
	
	public BackboneVector2f getEntityPos() {
		return getEntity().getPos();
	}
	
	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

}
