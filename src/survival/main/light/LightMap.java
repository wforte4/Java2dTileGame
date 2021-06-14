package survival.main.light;

import java.awt.Graphics2D;
import java.util.ArrayList;

import survival.main.generation.World;

public class LightMap {

	// List of light panels
	private ArrayList<LightPanel> panels;
	private ArrayList<Light> lights;

	// Counting the sun
	protected int sunlightCounter = 0;
	protected int maxSunlightCounter = 86400 / 2;
	protected int daylightSpeed = 2;
	private boolean sunlight = false;
	private boolean rising = true;
	private boolean falling = false;

	// Initialize the light map
	public LightMap(World world, boolean sunlight, int panelSize) {
		this.sunlight = sunlight;
		panels = new ArrayList<LightPanel>();
		lights = new ArrayList<Light>();
		generateLightPanels(world, panelSize);
	}

	// Initialize Light map for random generation world
	public LightMap(int width, int height, boolean sunlight, int panelSize) {
		this.sunlight = sunlight;
		panels = new ArrayList<LightPanel>();
		lights = new ArrayList<Light>();
		generateLightPanels(width, height, panelSize);
	}

	// Load the proper amount of light panels for world
	public void generateLightPanels(World world, int size) {
		for(int y = 0; y < world.getIntegerHeight() / size + 1; y++) {
			for(int x = 0; x < world.getIntegerWidth() / size + 1; x++) {
				panels.add(new LightPanel(this, world, x * size, y * size, size));
			}
		}
	}

	// Load the proper amount of light panels
	public void generateLightPanels(int width, int height, int size) {
		for(int y = 0; y < height / size + 1; y++) {
			for(int x = 0; x < width / size + 1; x++) {
				panels.add(new LightPanel(this,  x * size, y * size, size));
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
		// If the sun is rising
		if(rising) {	
			if(sunlightCounter < maxSunlightCounter) {
				sunlightCounter += daylightSpeed;
			} else {
				sunlightCounter = maxSunlightCounter;
				falling = true;
				rising = false;
			}
		}

		// Past noon (the sun is falling)
		if(falling) {
			if(sunlightCounter > 0) {
				sunlightCounter -= daylightSpeed;
			} else {
				sunlightCounter = 0;
				rising = true;
				falling = false;
			}
		}
		if(sunlightCounter < maxSunlightCounter / 2) {
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
	
	public int getMaxSunlightCounter() {
		return maxSunlightCounter;
	}
	
	public int getSunlightCounter() {
		return sunlightCounter;
	}

}
