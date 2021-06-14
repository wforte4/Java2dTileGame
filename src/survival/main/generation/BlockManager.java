/**
 * File: BlockManager.java 
 * Author: William Forte
 * Time: 10:24:30 AM
 * Date: Mar 23, 2016
 * Project: Survival
 * Package: survival.main.generation
 */
package survival.main.generation;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

import survival.main.Main;

/**
 * File: BlockManager.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 23, 2016
 * Time Created: 10:24:30 AM
 * Project: Survival
 * Package: survival.main.generation
 */

public class BlockManager {
	
	private CopyOnWriteArrayList<Block> blocks;
	private CopyOnWriteArrayList<Block> solid_blocks;
	private CopyOnWriteArrayList<Block> loaded_blocks;
	private Rectangle renderBounds;
	
	public BlockManager() {
		blocks = new CopyOnWriteArrayList<Block>();
		solid_blocks = new CopyOnWriteArrayList<Block>();
		loaded_blocks = new CopyOnWriteArrayList<Block>();
		renderBounds = new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT);
	}
	
	public void tick() {
		for(Block block: blocks) {
			if(renderBounds.intersects(block)) {	
				block.setAlive(true);
				if(!loaded_blocks.contains(block)) {					
					loaded_blocks.add(block);
				}
			} else {
				block.setAlive(false);
				if(loaded_blocks.contains(block)) {					
					loaded_blocks.remove(block);
				}
			}
			block.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for(Block block: loaded_blocks) {
			block.render(g);
		}
	}
	
	public void addBlock(Block block) {
		blocks.add(block);
		if(block.isSolid()) solid_blocks.add(block);
		System.out.println("Added Block: " + block.getBlock_type().name() + 
				" At || xpos: " + block.getPos().xpos +
				" ypos: " + block.getPos().ypos);
	}
	
	public void removeBlock(Block block) {
		blocks.remove(block);
		if(block.isSolid()) solid_blocks.remove(block);
		System.out.println("Removed Block: " + block.getBlock_type().name() + 
				" At || xpos: " + block.getPos().xpos +
				" ypos: " + block.getPos().ypos);
	}
	
	/**
	 * @return the solid_blocks
	 */
	public CopyOnWriteArrayList<Block> getSolid_blocks() {
		return solid_blocks;
	}
	
	/**
	 * @return the blocks
	 */
	public CopyOnWriteArrayList<Block> getBlocks() {
		return blocks;
	}
	
	/**
	 * @return the loaded_blocks
	 */
	public CopyOnWriteArrayList<Block> getLoaded_blocks() {
		return loaded_blocks;
	}

	public Rectangle getRenderBounds() {
		return renderBounds;
	}

	public void setRenderBounds(Rectangle renderBounds) {
		this.renderBounds = renderBounds;
	}
}
