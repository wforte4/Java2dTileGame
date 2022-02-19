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
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;

import backbone.engine.main.BackboneGameStateManager;
import backbone.engine.main.BackboneVector2f;
import survival.main.Main;
import survival.main.debug.DebugMenu;
import survival.main.drops.Jem;
import survival.main.entity.Entity;
import survival.main.entity.EntityManager;
import survival.main.entity.creatures.Player;
import survival.main.gamestate.StateMenu;
import survival.main.images.Assets;
import survival.main.light.Light;
import survival.main.light.LightMap;
import survival.main.sound.Sound;
import survival.main.ui.GameMenu;
import survival.main.ui.buttons.MenuStateButton;
import survival.main.utils.Settings;
import survival.main.utils.Util;
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
	public Settings settings;
	protected Player player;
	protected LightMap light_map;
	protected DebugMenu menu;
	protected GameMenu gameMenu;
	protected Weather weather;
	protected int block_size = Block.BLOCKSIZE;
	protected int width;
	protected int height;

	protected boolean debug = false;
	protected boolean isLoading = true;
	protected boolean isPaused = false;

	public float worldxpos;
	public float worldypos;

	protected float loadingPercentage;
	
	public World(BackboneGameStateManager gsm) {
		this.gsm = gsm;
		block_manager = new BlockManager();
		entity_manager = new EntityManager(gsm);
		settings = new Settings();
		menu = new DebugMenu(new BackboneVector2f(50, 50));
		gameMenu = new GameMenu((Main.WIDTH / 2) - 300, (Main.HEIGHT / 2) - 300, 600, 600, Assets.menuBackground, gsm);
		gameMenu.getManager().addButton(new MenuStateButton(gameMenu.getManager(), gameMenu.getX() + 300 - 64, gameMenu.getY() + 400, 128, 64, "Quit Game", Assets.menuButton, new StateMenu(gsm)));
		playMusic("background", .2f);
	}
	
	public void loadWorld() {}
	
	public void tick() {
		if(!isPaused) {
			block_manager.tick();
			entity_manager.tick();
			light_map.tick();
			sound.setVolume(settings.getVolume());
		} else {
			sound.setVolume(settings.getVolume() / 4);
		}
		gameMenu.tick();
	}
	
	public void render(Graphics2D g) {	
		block_manager.render(g);
		entity_manager.render(g);
		light_map.render(g);
		if(debug) {
			g.setColor(Color.RED);
			for(Entity entity: entity_manager.getEntities()) {
				entity.drawCollisionBounds(g);
				entity.drawSorter(g);
				entity.drawBlockCollision(g);
			}
			for(Jem jem: entity_manager.getJems()) {
				g.draw(jem.getBounds());
			}
			menu.addLine("World X Position: " + worldxpos);
			menu.addLine("World Y Position: " + worldypos);
			menu.addLine("Player X: " + player.getWorld_position_fix().xpos);
			menu.addLine("Player Y: " + player.getWorld_position_fix().ypos);
			menu.addLine("World Block Amount: " + block_manager.getBlocks().size());
			menu.addLine("Loaded Blocks: " + block_manager.getLoaded_blocks().size());
			menu.addLine("Entities: " + entity_manager.getEntities().size());
			menu.addLine("Time of Day: " + light_map.getSunlightCounter() + " / " + light_map.getMaxSunlightCounter());
			menu.render(g);
		}
		renderPlayerGUI(g);
		if(isPaused) {
			gameMenu.render(g);
		}
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
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_P || k == KeyEvent.VK_ESCAPE) {
			if(isPaused) {
				isPaused = false;
				Util.print("Unpaused game");
			} else {
				isPaused = true;
				Util.print("Paused game");
			}
		}
	}
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

	public BackboneVector2f getPlayerWorldPos() {
		return new BackboneVector2f(
				worldxpos + (Main.WIDTH / 2) - 20,
				worldypos + (Main.HEIGHT / 2) - 20
		);
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

	public void playMusic(String key, float volume) {
		sound.setFile(key);
		sound.setVolume(volume);
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

	public void playSoundEffect(String key, float volume) {
		sound.setFile(key);
		sound.setVolume(volume);
		sound.play();
	}

	public boolean isPaused() {
		return isPaused;
	}
}
