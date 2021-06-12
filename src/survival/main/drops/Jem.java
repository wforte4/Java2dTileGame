/**
 * File: Jem.java 
 * Author: William Forte
 * Time: 2:08:27 PM
 * Date: May 10, 2016
 * Project: Survival
 * Package: survival.main.drops
 */
package survival.main.drops;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import backbone.engine.main.BackboneVector2f;
import survival.main.entity.Entity;
import survival.main.entity.creatures.Player;
import survival.main.images.Assets;

/**
 * File: Jem.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 10, 2016
 * Time Created: 2:08:27 PM
 * Project: Survival
 * Package: survival.main.drops
 */

@SuppressWarnings("serial")
public class Jem extends Rectangle {
	
	private BackboneVector2f pos;
	private Random random;
	private JemType jem_type;
	private int width;
	private int height;
	private int value;
	private float initialdrop_speed;
	private double xx;
	private double yy;
	
	public Jem(BackboneVector2f pos, int width, int height, int value) {
		this.pos = new BackboneVector2f(pos.xpos -200, pos.ypos);
		this.random = new Random();
		this.width = width;
		this.height = height;
		this.value = value;
		this.jem_type = determineType(value);
		this.initialdrop_speed = 4f;
		this.xx = random.nextGaussian();
		this.yy = random.nextGaussian();
		setBounds((int) (pos.getWorldLocation().xpos - 200), (int) pos.getWorldLocation().ypos, width, height);
	}
	
	public void tick() {
		if(initialdrop_speed > 0) {			
			pos.xpos += xx * initialdrop_speed;
			pos.ypos += yy * initialdrop_speed;
			initialdrop_speed -= .08f;
		}
		setBounds((int) (pos.getWorldLocation().xpos), (int) pos.getWorldLocation().ypos, width, height);
	}
	
	public void render(Graphics2D g) {
		g.drawImage(Assets.shadow.getImage(), (int) (pos.getWorldLocation().xpos - width / 2), (int) (pos.getWorldLocation().ypos - height / 2), width * 2, height * 2, null);
		g.drawImage(jem_type.getSprite().getImage(), (int) (pos.getWorldLocation().xpos), (int) (pos.getWorldLocation().ypos), width, height, null);
	}
	
	private JemType determineType(int value) {
		if(value < 10) return JemType.GOLD;
		if(value < 50) return JemType.BLUE;
		if(value < 200) return JemType.PINK;
		if(value >= 200) return JemType.GREEN;
		return null;
	}
	
	public void resetInitalDrop() {
		initialdrop_speed = 4f;
	}
	
	public void setToFollow(Entity entity, int speed) {
		double dx = entity.getPos().getWorldLocation().xpos - pos.getWorldLocation().xpos;
		double dy = entity.getPos().getWorldLocation().ypos - pos.getWorldLocation().ypos;
		if(entity instanceof Player) {
			dx = ((Player) entity).getPos().xpos - pos.getWorldLocation().xpos;
			dy = ((Player) entity).getPos().ypos - pos.getWorldLocation().ypos;
		}
		double direction = Math.atan2(dy, dx);
		double nx = speed * Math.cos(direction);
		double ny = speed * Math.sin(direction);
		pos.xpos += nx;
		pos.ypos += ny;
	}
	
	/**
	 * @return the jem_type
	 */
	public JemType getJem_type() {
		return jem_type;
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @return the pos
	 */
	public BackboneVector2f getPos() {
		return pos;
	}

}
