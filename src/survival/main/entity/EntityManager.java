/**
 * File: EntityManager.java 
 * Author: William Forte
 * Time: 9:45:10 AM
 * Date: Mar 24, 2016
 * Project: Survival
 * Package: survival.main.entity
 */
package survival.main.entity;

import java.awt.*;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

import backbone.engine.main.BackboneGameStateManager;
import backbone.engine.main.BackboneVector2f;
import survival.main.Main;
import survival.main.drops.Jem;
import survival.main.entity.creatures.Creature;
import survival.main.entity.creatures.Player;
import survival.main.entity.still.EntityStill;
import survival.main.world.WorldText;

/**
 * File: EntityManager.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 24, 2016
 * Time Created: 9:45:10 AM
 * Project: Survival
 * Package: survival.main.entity
 * Details:
 * 	This file contains the core of management of entities. It will be checking to see if entities are on screen and if to load them
 */

public class EntityManager {
	
	private CopyOnWriteArrayList<Entity> entities;
	private CopyOnWriteArrayList<Entity> loaded_entities;
	private CopyOnWriteArrayList<EntityStill> still_entities;
	private CopyOnWriteArrayList<Jem> jems;
	private CopyOnWriteArrayList<WorldText> world_texts;
	private Comparator<Entity> renderSorter = (a, b) -> {
		float f1 = (float) (a.getSort_y() + a.getImageHeight());
		float f2 = (float) (b.getSort_y() + b.getImageHeight());
		return Float.compare(f1, f2);
	};
	private Rectangle renderBounds;

	private Player player;
	private BackboneGameStateManager gsm;
	
	public EntityManager(BackboneGameStateManager gsm) {
		entities = new CopyOnWriteArrayList<Entity>();
		loaded_entities = new CopyOnWriteArrayList<Entity>();
		still_entities = new CopyOnWriteArrayList<EntityStill>();
		jems = new CopyOnWriteArrayList<Jem>();
		world_texts = new CopyOnWriteArrayList<WorldText>();
		renderBounds = new Rectangle(0, 0, Main.WIDTH + 300, Main.HEIGHT + 300);
		this.gsm = gsm;

	}
	
	public void tick() {
		for(Entity entity: entities) {
			if(renderBounds.intersects(entity)) {
				entity.setInsideRenderField(true);
				if(!loaded_entities.contains(entity)) {
					loaded_entities.add(entity);
				}
			} else {
				entity.setInsideRenderField(false);
				if(loaded_entities.contains(entity)) {
					loaded_entities.remove(entity);
				}
			}
			/* @Creature type Entities Only */
			if(entity instanceof Creature) {
				// Check if the player is attacking enemies
				if(player.isAttacking() && player.getBounds().intersects(entity)) {
					if(entity instanceof Player == false ) {
						if(!((Creature) entity).isHurt()) {
							((Creature) entity).damageEntity(player.getCurrentDamageAmount(), player.getDirection(), 5);
						}
					}
				}
				if(((Creature) entity).isDead == true) {
					if(entity instanceof Player) {

					} else {
						if(loaded_entities.contains(entity)) { loaded_entities.remove(entity); }
						if(entities.contains(entity)) { entities.remove(entity); }
					}
				}
			}
			entity.tick();
		}
		for(Jem jem: jems) {
			jem.tick();
		}
		for(WorldText text: world_texts) {
			text.tick();
		}
	}
	
	public void render(Graphics2D g) {
		loaded_entities.sort(renderSorter);
		for(Jem jem: jems) {
			jem.render(g);
		}
		for(Entity entity: loaded_entities) {
			entity.render(g);
		}
		for(WorldText text: world_texts) {
			text.render(g);
		}
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
		if(entity instanceof EntityStill) {
			still_entities.add((EntityStill) entity);
		}
	}
	
	public void addText(BackboneVector2f pos, String string, boolean inWorld) {
		world_texts.add(new WorldText(this, pos, string, inWorld));
	}
	
	public void dropJem(BackboneVector2f pos, int value) {
		jems.add(new Jem(pos, 32, 32, value));
	}
	
	/**
	 * @return the world_texts
	 */
	public CopyOnWriteArrayList<WorldText> getWorld_texts() {
		return world_texts;
	}
	
	/**
	 * @return the jems
	 */
	public CopyOnWriteArrayList<Jem> getJems() {
		return jems;
	}
	
	/**
	 * @return the still_entities
	 */
	public CopyOnWriteArrayList<EntityStill> getStill_entities() {
		return still_entities;
	}
	
	/**
	 * @return the entities
	 */
	public CopyOnWriteArrayList<Entity> getEntities() {
		return entities;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
