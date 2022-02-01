/**
 * File: World.java 
 * Author: William Forte
 * Time: 10:38:04 AM
 * Date: Mar 23, 2016
 * Project: Survival
 * Package: survival.main.generation
 */
package survival.main.generation;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

import backbone.engine.main.BackboneGameStateManager;
import backbone.engine.main.BackboneVector2f;
import survival.main.Main;
import survival.main.debug.DebugMenu;
import survival.main.entity.Entity;
import survival.main.entity.EntityManager;
import survival.main.entity.creatures.Player;
import survival.main.light.Light;
import survival.main.light.LightMap;
import survival.main.sound.Sound;
import survival.main.weather.Weather;

/**
 * File: World.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 23, 2016
 * Time Created: 10:38:04 AM
 * Project: Survival
 * Package: survival.main.generation
 */

public abstract class World {
	
	protected BlockManager block_manager;
	protected EntityManager entity_manager;
	protected BackboneGameStateManager gsm;
	public Sound sound = new Sound();
	protected Player player;
	protected LightMap light_map;
	protected DebugMenu menu;
	protected Weather weather;
	protected int block_size = Block.BLOCKSIZE;
	protected int width;
	protected int height;

	protected boolean debug = false;
	protected boolean isLoading = true;

	public float worldxpos;
	public float worldypos;

	protected float loadingPercentage;
	
	public World(BackboneGameStateManager gsm) {
		block_manager = new BlockManager();
		entity_manager = new EntityManager(gsm);
		menu = new DebugMenu(new BackboneVector2f(50, 50));
		playMusic("background");
		sound.setVolume(.2f);
		this.gsm = gsm;
	}
	
	public void loadWorld() {
		
	}
	
	public void tick() {
		block_manager.tick();
		entity_manager.tick();
	}
	
	public void render(Graphics2D g) {	
		block_manager.render(g);
		entity_manager.render(g);
	}
	
	public void renderPlayerGUI(Graphics2D g) {
		if(isLoading) {
			g.setColor(Color.white);
			g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
			g.setColor(new Color(237,92,80));
			g.fillRect(200, 300, (int) (loadingPercentage * 800), 30);
		}
		player.getGui().render(g);
	}
	
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
	public void addEntity(Entity entity) {
		entity_manager.addEntity(entity);
	}
	
	protected void setWorldVariables(float worldxpos, float worldypos) {
		BackboneVector2f.setWorldVariables(worldxpos, worldypos);
	}
	
	public CopyOnWriteArrayList<Block> getBlocks() {
		return block_manager.getBlocks();
	}
	
	public CopyOnWriteArrayList<Block> getSolidBlocks() {
		return block_manager.getSolid_blocks();
	}
	
	public World setPlayer(Player player) {
		this.player = player;
		addEntity(player);
		entity_manager.setPlayer(player);
		return this;
	}
	
	public Block getBlock(float xpos, float ypos) {
		for(Block block: block_manager.getBlocks()) {
			if(xpos == block.getPos().xpos && ypos == block.getPos().ypos) {
				return block;
			}
		}
		return null;
	}
	
	public void addLight(Light light) {
		if(light_map != null) light_map.addLight(light);
	}
	
	public int getIntegerWidth() {
		return width * block_size;
	}
	
	public int getIntegerHeight() {
		return height * block_size;
	}
	
	public Block getBlock(int index) {
		return block_manager.getBlocks().get(index);
	}
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * @return the entity_manager
	 */
	public EntityManager getEntity_manager() {
		return entity_manager;
	}


	/**
	 * @return the menu
	 */
	public DebugMenu getDebugMenu() {
		return menu;
	}
	
	/**
	 * @return the debug
	 */
	public boolean isDebugging() {
		return debug;
	}

	public BackboneGameStateManager getGsm() {
		return gsm;
	}

	public void playMusic(String key) {
		sound.setFile(key);
		sound.play();
		sound.loop();
	}

	public void stopMusic() {
		sound.stop();
	}

	public void playSoundEffect(String key) {
		sound.setFile(key);
		sound.play();
	}
}
