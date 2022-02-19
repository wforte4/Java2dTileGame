/**
 * File: EntityJukeBox.java 
 * Author: William Forte
 * Time: 9:34:46 PM
 * Date: Apr 26, 2016
 * Project: Survival
 * Package: survival.main.entity.still
 */
package survival.main.entity.still;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import survival.main.entity.element.Element;
import survival.main.generation.World;
import survival.main.images.Assets;

/**
 * File: EntityJukeBox.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 26, 2016
 * Time Created: 9:34:46 PM
 * Project: Survival
 * Package: survival.main.entity.still
 */

@SuppressWarnings("serial")
public class EntityJukeBox extends EntityStill {
	
	private BufferedImage image;

	/**
	 * @param world
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 */
	public EntityJukeBox(World world, float xpos, float ypos, int width, int height) {
		super(world, xpos, ypos, width, height);
		image = Assets.jukebox.getImage();
		bounds_width = width / 2 + 30;
		bounds_height = height / 2 + 50;
	}
	
	/* (non-Javadoc)
	 * @see survival.main.entity.still.EntityStill#tick()
	 */
	@Override
	public void tick() {
		sort_y = pos.getWorldLocation().ypos - 30;
		setSolidWorldBounds(65, 65);
	}
	
	/* (non-Javadoc)
	 * @see survival.main.entity.still.EntityStill#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.drawImage(image, (int) (pos.getWorldLocation().xpos), (int) (pos.getWorldLocation().ypos), width, height, null);
	}

}
