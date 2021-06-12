package survival.main.light;

import survival.main.entity.Entity;
import survival.main.entity.creatures.Player;

public class Light {
	
	public int x;
	public int y;
	private int radius;
	private int maxradius;
	private int color;
	private float color_value;
	private boolean light_fade_out;
	private boolean light_fade_in = true;
	private Entity entity;
	
	public Light(int x, int y, int radius, int color) {
		this.x = x;
		this.y = y;
		this.maxradius = radius;
		this.radius = 0;
		this.color = color;
		this.color_value = 1;
	}
	
	public Light(Entity entity, int radius, int color) {
		this.entity = entity;
		this.x = (int) entity.getPos().xpos + entity.getImageWidth() / 2;
		this.y = (int) entity.getPos().ypos + entity.getImageHeight() / 2;
		this.maxradius = radius;
		this.radius = 0;
		this.color = color;
		this.color_value = 1;
	}
	
	public void tick() {
		if(entity != null) {
			if(entity instanceof Player) {
				this.x = (int) ((Player) entity).getElement_pos().xpos + ((Player) entity).getImageWidth() / 2;
				this.y = (int) ((Player) entity).getElement_pos().ypos + ((Player) entity).getImageHeight() / 2;
			} else {	
				this.x = (int) entity.getPos().xpos + entity.getImageWidth() / 2;
				this.y = (int) entity.getPos().ypos + entity.getImageHeight() / 2;
			}
		}
		if(light_fade_out) {
			if(radius > 0) {
				radius -= 10;
			} else {
				radius = 0;
			}
		}
		if(light_fade_in) {			
			if(radius < maxradius) {
				radius += 10;
			} else {
				radius = maxradius;
			}
		}
	}
	
	public Light setColorValue(float value) {
		if(value > 1) value = 1;
		if(value < 0) value = 0;
		this.color_value = value;
		return this;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void fadeIn(boolean fade_in) {
		this.light_fade_in = fade_in;
	}
	
	public void fadeOut(boolean fade_out) {
		this.light_fade_out = fade_out;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * @return the color_value
	 */
	public float getColor_value() {
		return color_value;
	}
	
	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

}
