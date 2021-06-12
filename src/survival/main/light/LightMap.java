package survival.main.light;

import java.awt.Graphics2D;
import java.util.ArrayList;

import survival.main.generation.World;

public class LightMap {
	
	private ArrayList<LightPanel> panels;
	private ArrayList<Light> lights;
	protected int sunlight_counter = 0;
	protected int max_sunlight_counter = 20000;
	private boolean sunlight = false;
	private boolean rising = true;
	private boolean falling = false;
	
	public LightMap(World world, boolean sunlight, int panelSize) {
		this.sunlight = sunlight;
		panels = new ArrayList<LightPanel>();
		lights = new ArrayList<Light>();
		generateLightPanels(world, panelSize);
	}
	
	public LightMap(int width, int height, boolean sunlight, int panelSize) {
		this.sunlight = sunlight;
		panels = new ArrayList<LightPanel>();
		lights = new ArrayList<Light>();
		generateLightPanels(width, height, panelSize);
	}
	
	public void generateLightPanels(int width, int height, int size) {
		for(int y = 0; y < height / size + 1; y++) {
			for(int x = 0; x < width / size + 1; x++) {
				panels.add(new LightPanel(this,  x * size, y * size, size));
			}
		}
	}
	
	public void generateLightPanels(World world, int size) {
		for(int y = 0; y < world.getIntegerHeight() / size + 1; y++) {
			for(int x = 0; x < world.getIntegerWidth() / size + 1; x++) {
				panels.add(new LightPanel(this, world, x * size, y * size, size));
			}
		}
	}
	
	public void tick() {
		if(sunlight) {
			updateSun();
		}
		for(LightPanel panel: panels) {
			panel.tick();
		}
		for(Light light: lights) {
			light.tick();
		}
	}
	
	public void updateSun() {
		if(rising) {	
			if(sunlight_counter < max_sunlight_counter) {					 
				sunlight_counter+= 2;
			} else {
				sunlight_counter = max_sunlight_counter;
				falling = true;
				rising = false;
			}
		}
		if(falling) {
			if(sunlight_counter > 0) {
				sunlight_counter-= 2;
			} else {
				sunlight_counter = 0;
				rising = true;
				falling = false;
			}
		}
		if(sunlight_counter < max_sunlight_counter / 2) {			
			for(Light light: lights) {
				light.fadeIn(true);
				light.fadeOut(false);
			}
		} else {
			for(Light light: lights) {
				light.fadeIn(false);
				light.fadeOut(true);
			}
		}
	}
	
	public void render(Graphics2D g) {
		for(LightPanel panel: panels) {
			if(panel.isAlive()) {				
				panel.render(g);
			}
		}
	}
	
	public ArrayList<Light> getLights() {
		return lights;
	}
	
	public void removeLight(Light light) {
		lights.remove(light);
	}
	
	public void addLight(Light light) {
		lights.add(light);
	}
	
	public boolean hasSunlight() {
		return sunlight;
	}
	
	public int getMax_sunlight_counter() {
		return max_sunlight_counter;
	}
	
	public int getSunlight_counter() {
		return sunlight_counter;
	}

}
