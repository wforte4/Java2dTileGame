/**
 * File: JemType.java 
 * Author: William Forte
 * Time: 11:19:28 AM
 * Date: May 12, 2016
 * Project: Survival
 * Package: survival.main.drops
 */
package survival.main.drops;

import backbone.engine.main.BackboneSprite;
import survival.main.images.Assets;

/**
 * File: JemType.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 12, 2016
 * Time Created: 11:19:28 AM
 * Project: Survival
 * Package: survival.main.drops
 */

public enum JemType {
	
	GREEN(Assets.jem_green),
	BLUE(Assets.jem_blue),
	PINK(Assets.jem_pink),
	GOLD(Assets.jem_gold);
	
	private BackboneSprite sprite;
	
	private JemType(BackboneSprite sprite) {
		this.sprite = sprite;
	}
	
	public BackboneSprite getSprite() {
		return sprite;
	}

}
