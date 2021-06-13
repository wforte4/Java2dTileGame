/**
 * File: World.java 
 * Author: William Forte
 * Time: 10:38:04 AM
 * Date: Mar 23, 2016
 * Project: Survival
 * Package: survival.main.generation
 */
package survival.main.generation;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

import backbone.engine.main.BackboneVector2f;
import survival.main.debug.DebugMenu;
import survival.main.entity.Entity;
import survival.main.entity.EntityManager;
import survival.main.entity.creatures.Player;
import survival.main.light.Light;
import survival.main.light.LightMap;

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
	protected Player player;
	protected LightMap light_map;
	protected DebugMenu menu;
	protected int block_size = Block.BLOCKSIZE;
	protected int width;
	protected int height;
	protected boolean debug = false;
	public float worldxpos;
	public float worldypos;
	
	public World() {
		block_manager = new BlockManager();
		entity_manager = new EntityManager();
		menu = new DebugMenu(new BackboneVector2f(50, 50));
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

}
