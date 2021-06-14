/**
 * File: Entity.java 
 * Author: William Forte
 * Time: 8:45:47 AM
 * Date: Mar 24, 2016
 * Project: Survival
 * Package: survival.main.entity
 */
package survival.main.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import backbone.engine.main.BackboneVector2f;
import survival.main.drops.jem.JemCollection;
import survival.main.entity.creatures.Player;
import survival.main.entity.element.Element;
import survival.main.entity.still.EntityStill;
import survival.main.generation.Block;
import survival.main.generation.World;
import survival.main.particle.ParticleManager;
import survival.main.particle.ParticleManagerType;

/**
 * File: Entity.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 24, 2016
 * Time Created: 8:45:47 AM
 * Project: Survival
 * Package: survival.main.entity
 */

@SuppressWarnings("serial")
public abstract class Entity extends Rectangle {
	
	protected BackboneVector2f pos;
	protected Random random;
	protected World world;
	protected Element element;
	protected ParticleManager particle_element_manager;
	protected JemCollection jem_collection;
	protected int width;
	protected int height;
	protected int bounds_width;
	protected int bounds_height;
	protected boolean sortable;
	protected boolean isInsideRenderField;
	protected Point left_up,
					left_down,
					right_up,
					right_down,
					up_left,
					up_right,
					down_left,
					down_right;
	

	protected float speed_right,
					speed_left,
					speed_up,
					speed_down,
					sort_y;
	
	public Entity(World world, float xpos, float ypos, int width, int height) {
		pos = new BackboneVector2f(xpos, ypos);
		random = new Random();
		jem_collection = new JemCollection(this);
		this.world = world;
		this.width = width;
		this.height = height;
		this.bounds_width = width;
		this.bounds_height = height;
		this.up_left = new Point();
		this.up_right = new Point();
		this.right_up = new Point();
		this.right_down = new Point();
		this.left_down = new Point();
		this.left_up = new Point();
		this.down_left = new Point();
		this.down_right = new Point();
		particle_element_manager = new ParticleManager(ParticleManagerType.ELEMENT, this, 40, 2);
	}
	
	public void setBlockCollisionBounds(float xpos, float ypos, float width, float height) {
		float fixAmount = 4;
		up_left = new Point((int) (xpos + fixAmount), (int) (ypos - speed_up));
		up_right = new Point((int) (xpos + width- fixAmount), (int) (ypos - speed_up));
		right_up = new Point((int) (xpos + width + speed_right), (int) (ypos + fixAmount));
		right_down = new Point((int) (xpos + width + speed_right), (int) (ypos + height - fixAmount));
		left_up = new Point((int) (xpos - speed_left), (int) (ypos + fixAmount));
		left_down = new Point((int) (xpos - speed_left), (int) (ypos + height - fixAmount));
		down_left = new Point((int) (xpos + fixAmount), (int) (ypos + height + speed_down));
		down_right = new Point((int) (xpos + width - fixAmount), (int) (ypos + height + speed_down));
	}
	
	public void setSolidWorldBounds(int xOffset, int yOffset) {
		setBounds(
				(int) (pos.getWorldLocation().xpos + xOffset), 
				(int) (pos.getWorldLocation().ypos + yOffset), 
				bounds_width, 
				bounds_height);
	}
	
	public void drawBlockCollision(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawRect(up_right.x, up_right.y, 2, 2);
		g.drawRect(up_left.x, up_left.y, 2, 2);
		g.drawRect(right_up.x, right_up.y, 2, 2);
		g.drawRect(right_down.x, right_down.y, 2, 2);
		g.drawRect(left_up.x, left_up.y, 2, 2);
		g.drawRect(left_down.x, left_down.y, 2, 2);
		g.drawRect(down_left.x, down_left.y, 2, 2);
		g.drawRect(down_right.x, down_right.y, 2, 2);
	}
	
	public void checkCollisions() {
		if(checkBlockCollision(up_right, up_left) || checkSolidEntityCollision(up_right, up_left)) speed_up = 0;
		if(checkBlockCollision(right_up, right_down) || checkSolidEntityCollision(right_up, right_down)) speed_right = 0;
		if(checkBlockCollision(down_left, down_right) || checkSolidEntityCollision(down_left, down_right)) speed_down = 0;
		if(checkBlockCollision(left_up, left_down) || checkSolidEntityCollision(left_up, left_down)) speed_left = 0;
	}
	
	public boolean checkSolidEntityCollision(Point point_one, Point point_two) {
		for(EntityStill still: getSolidEntities()) {
			if(still.getBounds().contains(point_one) || still.getBounds().contains(point_two)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkBlockCollision(Point point_one, Point point_two) {
		for(Block block: getSolidBlocks()) {
			if(block.getBounds().contains(point_one) || block.getBounds().contains(point_two)) {
				return true;
			}
		}
		return false;
	}
	
	public void drawElement(Graphics2D g) {
		particle_element_manager.render(g);
	}
	
	public void drawSorter(Graphics2D g) {
		g.setColor(Color.RED);
		if(this instanceof Player) {
			g.fillRect((int) pos.xpos, (int) sort_y + getImageHeight(), getImageWidth(), 2);
		} else {			
			g.fillRect((int) pos.getWorldLocation().xpos, (int) sort_y + getImageHeight(), getImageWidth(), 2);
		}
	}
	
	public void tickParticleElementManager() {
		if(element != null) {			
			particle_element_manager.setGenerating(true);
			if(element.getImage() == null) {		
				particle_element_manager.setColor(element.getColor());
			} else {
				particle_element_manager.setImage(element.getImage());
			}
		} else {
			particle_element_manager.setGenerating(false);
		}
		particle_element_manager.tick();
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
	
	public CopyOnWriteArrayList<Block> getSolidBlocks() {
		return world.getSolidBlocks();
	}
	
	public CopyOnWriteArrayList<EntityStill> getSolidEntities() {
		return world.getEntity_manager().getStill_entities();
	}
	
	public void drawCollisionBounds(Graphics2D g) {
		g.setColor(Color.RED);
		g.draw(getBounds());
	}
	
	/**
	 * @return the jem_collection
	 */
	public JemCollection getJem_collection() {
		return jem_collection;
	}
	
	/**
	 * @return the sort_y
	 */
	public float getSort_y() {
		return sort_y;
	}
	
	/**
	 * @return the pos
	 */
	public BackboneVector2f getPos() {
		return pos;
	}
	
	/**
	 * @return the height
	 */
	public int getImageHeight() {
		return height;
	}
	
	/**
	 * @return the width
	 */
	public int getImageWidth() {
		return width;
	}
	
	public boolean isSortable() {
		return sortable;
	}
	
	public int getEntityHeight() {
		return height;
	}
	
	public int getEntityWidth() {
		return width;
	}

	public boolean isInsideRenderField() { return isInsideRenderField; }
	public void setInsideRenderField(boolean insideRenderField) {
		this.isInsideRenderField = insideRenderField;
	}
	
	public World getCurrentWorld() {
		return world;
	}

}
