/**
 * File: MagnetJem.java 
 * Author: William Forte
 * Time: 10:19:35 AM
 * Date: May 12, 2016
 * Project: Survival
 * Package: survival.main.magnet
 */
package survival.main.magnet;

import java.awt.Color;
import java.awt.Graphics2D;

import backbone.engine.main.BackboneVector2f;
import survival.main.drops.Jem;
import survival.main.entity.Entity;
import survival.main.entity.creatures.Player;

/**
 * File: MagnetJem.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 12, 2016
 * Time Created: 10:19:35 AM
 * Project: Survival
 * Package: survival.main.magnet
 */

public class MagnetJem extends Magnet {

	/**
	 * @param entity
	 */
	public MagnetJem(Entity entity) {
		super(entity);
	}
	
	public void tick() {
		for(Jem jem: entity.getCurrentWorld().getEntity_manager().getJems()) {
			if(entity instanceof Player) {
				if(BackboneVector2f.distanceBetweenVectors(((Player) entity).getPos(), jem.getPos().getWorldLocation()) < 300) {
					jem.setToFollow(entity, 2);
				}
			} else {
				if(BackboneVector2f.distanceBetweenVectors(entity.getPos().getWorldLocation(), jem.getPos().getWorldLocation()) < 300) {
					jem.setToFollow(entity, 2);
				}
			}
		}
	}
	
	public void render(Graphics2D g) {
		for(Jem jem: entity.getCurrentWorld().getEntity_manager().getJems()) {
			g.setColor(Color.RED);
			if(entity instanceof Player) {				
				g.drawLine(
						(int) (jem.getPos().getWorldLocation().xpos + jem.getWidth() / 2), 
						(int) (jem.getPos().getWorldLocation().ypos + jem.getHeight() / 2), 
						(int) ((Player) entity).getPos().xpos + entity.getImageWidth() / 2, 
						(int) ((Player) entity).getPos().ypos + entity.getImageHeight() / 2) ;
			} else {
				g.drawLine(
						(int) jem.getPos().getWorldLocation().xpos, 
						(int) jem.getPos().getWorldLocation().ypos, 
						(int) entity.getPos().getWorldLocation().xpos + entity.getImageWidth() / 2, 
						(int) entity.getPos().getWorldLocation().ypos + entity.getImageHeight() / 2) ;
			}
		}
	}

}
