/**
 * File: RandomWorld.java 
 * Author: William Forte
 * Time: 9:52:46 AM
 * Date: Mar 24, 2016
 * Project: Survival
 * Package: survival.main.generation.worlds
 */
package survival.main.generation.worlds;

import java.awt.Graphics2D;
import java.util.Random;

import backbone.engine.main.BackboneGameStateManager;
import survival.main.entity.creatures.Player;
import survival.main.generation.Block;
import survival.main.generation.BlockType;
import survival.main.generation.World;

/**
 * File: RandomWorld.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 24, 2016
 * Time Created: 9:52:46 AM
 * Project: Survival
 * Package: survival.main.generation.worlds
 */

public class RandomWorld extends World {
	
	public RandomWorld(BackboneGameStateManager gsm, int width, int height) {
		super(gsm);
		this.width = width;
		this.height = height;
		loadWorld();
	}
	
	private void loadRandomWorld(int border_size) {
		for(int y = border_size; y < height - border_size; y++) {
			for(int x = border_size; x < width - border_size; x++) {
				BlockType blocktype = BlockType.values()[new Random().nextInt(BlockType.values().length)];
				if(blocktype == BlockType.BRICK) blocktype = BlockType.GRASS;
				if(new Random().nextInt(2) == 0) blocktype = BlockType.GRASS;
				block_manager.addBlock(
						new Block(
								x * block_size, 
								y * block_size, 
								blocktype)
						.setSolid(blocktype.isSolid()));
			}
		}
		loadBorder(border_size);
	}
	
	private void loadBorder(int border_size) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(y >= 0 && y < border_size || y >= height - border_size && y < height) {
					block_manager.addBlock(
							new Block(
									x * block_size, 
									y * block_size, 
									BlockType.BRICK)
							.setSolid(true));
				}
				if(x >= 0 && x < border_size || x >= width - border_size && x < width) {
					block_manager.addBlock(
							new Block(
									x * block_size, 
									y * block_size, 
									BlockType.BRICK)
							.setSolid(true));
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see survival.main.generation.World#loadWorld()
	 */
	@Override
	public void loadWorld() {
		loadRandomWorld(3);
		super.loadWorld();
	}
	
	/* (non-Javadoc)
	 * @see survival.main.generation.World#tick()
	 */
	@Override
	public void tick() {
		setWorldVariables(worldxpos, worldypos);
		super.tick();
	}
	
	/* (non-Javadoc)
	 * @see survival.main.generation.World#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	/* (non-Javadoc)
	 * @see survival.main.generation.World#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		getPlayer().keyPressed(k);
	}

	/* (non-Javadoc)
	 * @see survival.main.generation.World#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) {
		getPlayer().keyReleased(k);
	}

}
