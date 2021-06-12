/**
 * File: JemCollection.java 
 * Author: William Forte
 * Time: 11:18:53 AM
 * Date: May 12, 2016
 * Project: Survival
 * Package: survival.main.drops.jem
 */
package survival.main.drops.jem;

import java.util.Random;

import backbone.engine.main.BackboneVector2f;
import survival.main.drops.Jem;
import survival.main.entity.Entity;
import survival.main.ui.bar.GrowthBar;

/**
 * File: JemCollection.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 12, 2016
 * Time Created: 11:18:53 AM
 * Project: Survival
 * Package: survival.main.drops.jem
 */

public class JemCollection {
	
	private Entity entity;
	private Random random;
	private GrowthBar bar;
	private int current_value;
	
	public JemCollection(Entity entity) {
		this.entity = entity;
		this.random = new Random();
	}
	
	public JemCollection(Entity entity, GrowthBar bar) {
		this.entity = entity;
		this.bar = bar;
		this.random = new Random();
	}
	
	public void addJem(Jem jem) {
		if(bar != null) bar.add(jem.getValue());
		current_value += jem.getValue();
		entity.getCurrentWorld().getEntity_manager().addText(jem.getPos(), "+" + jem.getValue(), true);
		entity.getCurrentWorld().getEntity_manager().getJems().remove(jem);
	}
	
	public void dropRandomJemsInWorld(BackboneVector2f pos) {
		while(current_value > 0) {
			if(current_value == 1) {
				entity.getCurrentWorld().getEntity_manager().dropJem(pos, 1);
				current_value = 0;
				break;
			}
			int value = random.nextInt(current_value);
			entity.getCurrentWorld().getEntity_manager().dropJem(pos, value);
			current_value -= value;
		}
		bar.empty();
	}
	
	public int getTotalJemValue() {
		return current_value;
	}

}
