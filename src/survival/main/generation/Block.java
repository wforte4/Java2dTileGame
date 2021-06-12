/**
 * File: Block.java 
 * Author: William Forte
 * Time: 8:10:03 AM
 * Date: Mar 23, 2016
 * Project: Survival
 * Package: survival.main.generation
 */
package survival.main.generation;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import backbone.engine.main.BackboneVector2f;

/**
 * File: Block.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 23, 2016
 * Time Created: 8:10:03 AM
 * Project: Survival
 * Package: survival.main.generation
 */

@SuppressWarnings("serial")
public class Block extends Rectangle {
	
	public static int BLOCKSIZE = 64;
	
	private BackboneVector2f pos;
	private BlockType block_type;
	private boolean solid;
	private boolean alive;
	
	public Block(int x, int y, BlockType block_type) {
		pos = new BackboneVector2f(x, y);
		this.block_type = block_type;
	}
	
	public Block setSolid(boolean isSolid) {
		this.solid = isSolid;
		return this;
	}
	
	public void tick() {	
		setBounds((int) pos.getWorldLocation().xpos, (int) pos.getWorldLocation().ypos, Block.BLOCKSIZE, Block.BLOCKSIZE);
	}
	
	public void render(Graphics2D g) {
		g.drawImage(block_type.getImage().getImage(),
				   (int) pos.getWorldLocation().xpos, 
				   (int) pos.getWorldLocation().ypos,
				   BLOCKSIZE,
				   BLOCKSIZE,
				   null);
	}
	
	/**
	 * @return the block_type
	 */
	public BlockType getBlock_type() {
		return block_type;
	}
	
	/**
	 * @return the pos
	 */
	public BackboneVector2f getPos() {
		return pos;
	}
	
	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * @return the solid
	 */
	public boolean isSolid() {
		return solid;
	}

}
