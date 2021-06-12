/**
 * File: Element.java 
 * Author: William Forte
 * Time: 11:37:09 PM
 * Date: Apr 28, 2016
 * Project: Survival
 * Package: survival.main.entity.element
 */
package survival.main.entity.element;

import java.awt.Color;
import java.awt.image.BufferedImage;

import survival.main.images.Assets;

/**
 * File: Element.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 28, 2016
 * Time Created: 11:37:09 PM
 * Project: Survival
 * Package: survival.main.entity.element
 */

public enum Element {
	
	FIRE(new Color(0xff0000), Assets.particle_fire.getImage()),
	ACID(new Color(0x00ff00), Assets.particle_acid.getImage()),
	APHOTICACERBIA(new Color(0x000000), Assets.particle_darkness.getImage());
	
	private Color color;
	private BufferedImage image;
	
	private Element(Color color, BufferedImage image) {
		this.color = color;
		this.image = image;
	}
	
	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

}
