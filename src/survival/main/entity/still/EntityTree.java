/**
 * File: EntityTree.java 
 * Author: William Forte
 * Time: 4:29:55 PM
 * Date: Apr 26, 2016
 * Project: Survival
 * Package: survival.main.entity.still
 */
package survival.main.entity.still;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import survival.main.generation.World;
import survival.main.images.Assets;
import survival.main.light.Light;

/**
 * File: EntityTree.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 26, 2016
 * Time Created: 4:29:55 PM
 * Project: Survival
 * Package: survival.main.entity.still
 */

@SuppressWarnings("serial")
public class EntityTree extends EntityStill {
	
	private BufferedImage image;
	private ArrayList<BufferedImage> tree_images;

	/**
	 * @param world
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 */
	public EntityTree(World world, float xpos, float ypos, int width, int height) {
		super(world, xpos, ypos, width, height);
		tree_images = new ArrayList<BufferedImage>();
		tree_images.add(Assets.tree_sinister.getImage());
		tree_images.add(Assets.tree_sinister_apple.getImage());
		tree_images.add(Assets.tree_sinister_two.getImage());
		tree_images.add(Assets.tree_sinister_three.getImage());
		image = tree_images.get(new Random().nextInt(tree_images.size()));
		bounds_width = width / 2;
		bounds_height = height / 2 - 50;
		world.addLight(new Light(this, 200, new Random().nextInt(0xffffff)).setColorValue(.7f));
	}

	/* (non-Javadoc)
	 * @see survival.main.entity.Entity#tick()
	 */
	@Override
	public void tick() {
		sort_y = pos.getWorldLocation().ypos;
		setSolidWorldBounds(width / 2 - 82, height / 2 + 45);
	}

	/* (non-Javadoc)
	 * @see survival.main.entity.Entity#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, (int) pos.getWorldLocation().xpos, (int) pos.getWorldLocation().ypos, width, height, null);
	}

}
