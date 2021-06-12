/**
 * File: BlockType.java 
 * Author: William Forte
 * Time: 8:30:24 AM
 * Date: Mar 23, 2016
 * Project: Survival
 * Package: survival.main.generation
 */
package survival.main.generation;

import backbone.engine.main.BackboneSprite;
import survival.main.images.Assets;

/**
 * File: BlockType.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 23, 2016
 * Time Created: 8:30:24 AM
 * Project: Survival
 * Package: survival.main.generation
 */

public enum BlockType {
	
	GRASS(Assets.block_grass, 0x007F0E, false),
	GRASSROCK(Assets.block_grass_rock, 0x808080, false),
	GRASSFLOWER(Assets.block_grass_flower, 0xFF6666, false),
	SAND(Assets.block_sand, 0xE2D392, false),
	SANDGRASSRIGHT(Assets.block_sand_grass_right, 0xB6FF00, false),
	SANDGRASSLEFT(Assets.block_sand_grass_left, 0x95D100, false),
	SANDGRASSUP(Assets.block_sand_grass_up, 0x7FB200, false),
	SANDGRASSDOWN(Assets.block_sand_grass_down, 0x74A300, false),
	SANDGRASSTOPLEFT(Assets.block_sand_grass_top_left, 0x5F8400, false),
	SANDGRASSTOPRIGHT(Assets.block_sand_grass_top_right, 0x527000, false),
	SANDGRASSBOTTOMRIGHT(Assets.block_sand_grass_bottom_right, 0x486300, false),
	SANDGRASSBOTTOMLEFT(Assets.block_sand_grass_bottom_left, 0x3B5100, false),
	DINERTILE(Assets.block_diner_tile, 0x565656, false),
	DINERWALL(Assets.block_diner_wall, 0xFF7A7A, true),
	DINERFADEUP(Assets.block_diner_fade_up, 0x3F3F3F, true),
	DINERFADEDOWN(Assets.block_diner_fade_down, 0x232323, true),
	DINERFADERIGHT(Assets.block_diner_fade_right, 0x1E1E1E, true),
	DINERFADELEFT(Assets.block_diner_fade_left, 0x161616, true),
	DINERFADETOPLEFT(Assets.block_diner_fade_top_left, 0x111111, true),
	DINERFADETOPRIGHT(Assets.block_diner_fade_top_right, 0x0C0C0C, true),
	DINERFADEBOTTOMRIGHT(Assets.block_diner_fade_bottom_right, 0x070707, true),
	DINERFADEBOTTOMLEFT(Assets.block_diner_fade_bottom_left, 0x020202, true),
	BRICK(Assets.block_brick, 0x000000, true),
	GRASSBUSH(Assets.block_grass_bush, 0xffffff, false);
	
	private BackboneSprite image;
	private int color;
	private boolean solid;
	
	private BlockType(BackboneSprite image, int color, boolean solid) {
		this.image = image;
		this.color = color;
		this.solid = solid;
	}
	
	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * @return the solid
	 */
	public boolean isSolid() {
		return solid;
	}
	
	/**
	 * @return the image
	 */
	public BackboneSprite getImage() {
		return image;
	}

}
