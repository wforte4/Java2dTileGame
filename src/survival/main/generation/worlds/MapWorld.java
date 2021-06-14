/**
 * File: MapWorld.java 
 * Author: William Forte
 * Time: 10:36:53 AM
 * Date: Apr 26, 2016
 * Project: Survival
 * Package: survival.main.generation.worlds
 */
package survival.main.generation.worlds;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Date;

import backbone.engine.main.BackboneGameStateManager;
import backbone.engine.main.BackboneImageLoader;
import survival.main.drops.Jem;
import survival.main.entity.Entity;
import survival.main.entity.creatures.CreatureSlime;
import survival.main.entity.still.EntityJukeBox;
import survival.main.entity.still.EntityTree;
import survival.main.generation.Block;
import survival.main.generation.BlockType;
import survival.main.generation.World;
import survival.main.light.Light;
import survival.main.light.LightMap;

/**
 * File: MapWorld.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 26, 2016
 * Time Created: 10:36:53 AM
 * Project: Survival
 * Package: survival.main.generation.worlds
 */

public class MapWorld extends World {
	
	public MapWorld(BackboneGameStateManager gsm, String path) {
		super(gsm);
		loadMap(path);
		for(int i = 0; i < 10; i++) {
			entity_manager.addEntity(new CreatureSlime(this, 200, 200, 48, 48));
		}
	}

	public void loadMap(String path) {
		BufferedImage map = BackboneImageLoader.loadImage(path);
		this.width = map.getWidth();
		this.height = map.getHeight();
		light_map = new LightMap(this, true, 30);
		light_map.addLight(new Light(400, 400, 400, 0x000000));
		
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = 0; y < map.getHeight(); y++) {
				
				int current_color = map.getRGB(x, y);
				
				if((current_color & 0x7F3300) == 0x7F3300) {
					block_manager.addBlock(
							new Block(
									x * block_size,
									y * block_size,
									BlockType.GRASS));
					entity_manager.addEntity(
							new EntityTree(
									this, 
									x * block_size,
									y * block_size,
									300,
									300));
					continue;
				}
				
				if(current_color == new Color(0xFFD8D8).getRGB()) {
					block_manager.addBlock(
							new Block(
									x * block_size,
									y * block_size,
									BlockType.DINERTILE));
					entity_manager.addEntity(
							new EntityJukeBox(
									this, 
									x * block_size,
									y * block_size - 170,
									300,
									300));
				}
				
				for(int i = 0; i < BlockType.values().length; i++) {
					BlockType blocktype = BlockType.values()[i];
					if(current_color == new Color(blocktype.getColor()).getRGB()) {
						block_manager.addBlock(
								new Block(
										x * block_size, 
										y * block_size,
										blocktype)
								.setSolid(blocktype.isSolid()));
						System.out.println("Added Block: " + blocktype.name());
						break;
					}
				}
				
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see survival.main.generation.World#tick()
	 */
	@Override
	public void tick() {
		setWorldVariables(worldxpos, worldypos);
		super.tick();
		light_map.tick();
	}
	
	/* (non-Javadoc)
	 * @see survival.main.generation.World#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		light_map.render(g);
		renderPlayerGUI(g);
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
			getPlayer().getJem_magnet().render(g);
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
	}
	
	/* (non-Javadoc)
	 * @see survival.main.generation.World#loadWorld()
	 */
	@Override
	public void loadWorld() {
		super.loadWorld();
	}

	/* (non-Javadoc)
	 * @see survival.main.generation.World#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		getPlayer().keyPressed(k);
		if(k == KeyEvent.VK_F10) {
			debug = !debug;
		}
	}

	/* (non-Javadoc)
	 * @see survival.main.generation.World#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) {
		getPlayer().keyReleased(k);
	}

}
