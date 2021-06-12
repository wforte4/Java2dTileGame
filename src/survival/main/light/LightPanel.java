package survival.main.light;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import backbone.engine.main.BackboneVector2f;
import survival.main.Main;
import survival.main.generation.World;

public class LightPanel {

	private int size;
	private int blended_color;
	private float transparency;
	private boolean alive = true;
	private Color color;
	private LightMap map;
	private World world;
	private BackboneVector2f pos;
	
	public LightPanel(LightMap map, World world, int x, int y, int size) {
		pos = new BackboneVector2f(x, y);
		this.size = size;
		this.map = map;
		this.world = world;
		transparency = .4f;
		color = Color.BLACK;
		blended_color = 0x000000;
		checkAlive();
	}
	
	public LightPanel(LightMap map, int x, int y, int size) {
		pos = new BackboneVector2f(x, y);
		this.size = size;
		this.map = map;
		this.world = null;
		transparency = .4f;
		color = Color.BLACK;
		blended_color = 0x000000;
	}
	
	public void tick() {
		if(world != null) checkAlive();
		if(alive) {			
			blended_color = 0x000000;
			float totaltran = 0;
			int totallights = 0;
			transparency = 1;
			for(Light light: map.getLights()) {
				double distance = distance(light.getX(), light.getY());
				if(distance < light.getRadius()) {
					if(totaltran == 0) {						
						totaltran += getPercent(distance, light.getRadius(), 1);
					} else {
						totaltran -= 1 - getPercent(distance, light.getRadius(), 1);
					}
					blended_color = blend(blended_color, light.getColor(), light.getColor_value() - getPercent(distance, light.getRadius(), 1));
					totallights++;
				}
			}
			if(map.hasSunlight()) {
				totaltran -= getPercent(map.getSunlight_counter(), map.getMax_sunlight_counter(), 1);
			}
			if(totallights == 0) totaltran = 1 - getPercent(map.getSunlight_counter(), map.getMax_sunlight_counter(), 1);
			if(totaltran > 1) totaltran = 1;
			if(totaltran < 0) totaltran = 0;
			transparency = totaltran;
		}
	}
	
	public void checkAlive() {
		if(pos.xpos < world.worldxpos - size * 2 ||
		   pos.ypos < world.worldypos - size * 2 ||
		   pos.ypos > world.worldypos + Main.HEIGHT ||
		   pos.xpos > world.worldxpos + Main.WIDTH) {
			alive = false;
		} else {
			alive = true;
		}
	}
	
	public void render(Graphics2D g) {
		if(alive) {			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
			color = new Color(blended_color);
			g.setColor(color);
			if(world != null) g.fillRect((int) (pos.getWorldLocation().xpos), (int) (pos.getWorldLocation().ypos), size, size);
			else g.fillRect((int) (pos.xpos), (int) (pos.ypos), size, size);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		} 
	}
	
	public float getPercent(double curr_percent, float full_percent, float full_width) {
		return (float) (full_width * curr_percent) / full_percent;
	}
	
	private int getCenterPositionX() {
		return (int) (pos.xpos + size / 2);
	}
	
	private int getCenterPositionY() {
		return (int) (pos.ypos + size / 2);
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	
	private double distance(int xGoingTo, int yGoingTo) {
		return Math.sqrt(Math.abs((getCenterPositionX() - xGoingTo) * (getCenterPositionX() - xGoingTo) + (getCenterPositionY() - yGoingTo) * (getCenterPositionY() - yGoingTo)));
	}
	
	private int blend (int a, int b, float ratio) {
	    if (ratio > 1f) {
	        ratio = 1f;
	    } else if (ratio < 0f) {
	        ratio = 0f;
	    }
	    float iRatio = 1.0f - ratio;

	    int aA = (a >> 24 & 0xff);
	    int aR = ((a & 0xff0000) >> 16);
	    int aG = ((a & 0xff00) >> 8);
	    int aB = (a & 0xff);

	    int bA = (b >> 24 & 0xff);
	    int bR = ((b & 0xff0000) >> 16);
	    int bG = ((b & 0xff00) >> 8);
	    int bB = (b & 0xff);

	    int A = ((int)(aA * iRatio) + (int)(bA * ratio));
	    int R = ((int)(aR * iRatio) + (int)(bR * ratio));
	    int G = ((int)(aG * iRatio) + (int)(bG * ratio));
	    int B = ((int)(aB * iRatio) + (int)(bB * ratio));

	    return A << 24 | R << 16 | G << 8 | B;
	}
	
}
