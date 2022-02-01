/**
 * File: Assets.java 
 * Author: William Forte
 * Time: 10:59:54 AM
 * Date: Mar 22, 2016
 * Project: Survival
 * Package: survival.main.images
 */
package survival.main.images;

import backbone.engine.main.BackboneImageLoader;
import backbone.engine.main.BackboneSprite;
import survival.main.images.SpriteSheet;

/**
 * File: Assets.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 22, 2016
 * Time Created: 10:59:54 AM
 * Project: Survival
 * Package: survival.main.images
 */

public class Assets {
	
	// SpriteSheets
	public static SpriteSheet sheet_block,
									  sheet_player,
									  sheet_zombie,
									  sheet_slime;
	
	// Images
	public static BackboneSprite mouse_pressed,
								mouse_released,
								mouse,
								mouse_two,
								shadow,
								background_palm_tree,
								background_loading_one,
								tree_sinister,
								tree_sinister_apple,
								tree_sinister_two,
								tree_sinister_three,
								jukebox,
								particle_fire,
								particle_acid,
								particle_darkness,
								jem_green,
								jem_blue,
								jem_pink,
								jem_gold,
								damage_down,
								damage_up,
								damage_right,
								damage_left,
								rain_drop;
	
	// Blocks
	public static BackboneSprite block_grass,
								block_brick,
								block_grass_rock,
								block_grass_flower,
								block_grass_bush,
								block_sand,
								block_sand_grass_right,
								block_sand_grass_left,
								block_sand_grass_up,
								block_sand_grass_down,
								block_sand_grass_top_right,
								block_sand_grass_top_left,
								block_sand_grass_bottom_left,
								block_sand_grass_bottom_right,
								block_diner_tile,
								block_diner_wall,
								block_diner_fade_up,
								block_diner_fade_down,
								block_diner_fade_right,
								block_diner_fade_left,
								block_diner_fade_top_right,
								block_diner_fade_top_left,
								block_diner_fade_bottom_right,
								block_diner_fade_bottom_left;
	
	//Animation
	public static BackboneSprite[] player_up,
								   player_down,
								   player_right,
								   player_left,
								   zombie_up,
								   zombie_down,
								   zombie_left,
								   zombie_right,
								   zombie_idol_down,
								   zombie_idol_up,
								   zombie_idol_left,
								   zombie_idol_right,
			                       zombie_fight_left,
								   slime_up,
								   slime_down,
								   slime_left,
								   slime_right,
								   slime_idol_down,
								   slime_idol_up,
								   slime_idol_left,
								   slime_idol_right;
	
	public static void loadImages() {
		
		// SpriteSheets
		sheet_block = new SpriteSheet(BackboneImageLoader.loadImage("/spritesheets/sheet_block.png"), 24);
		sheet_player = new SpriteSheet(BackboneImageLoader.loadImage("/spritesheets/sheet_player.png"), 24);
		sheet_zombie = new SpriteSheet(BackboneImageLoader.loadImage("/spritesheets/sheet_zombie.png"), 24);
		sheet_slime = new SpriteSheet(BackboneImageLoader.loadImage("/spritesheets/sheet_slime.png"), 24);
		
		player_up = new BackboneSprite[2];
		player_down = new BackboneSprite[2]; 
		player_right = new BackboneSprite[3];
		player_left = new BackboneSprite[3];
		zombie_up = new BackboneSprite[2];
		zombie_down = new BackboneSprite[2];
		zombie_right = new BackboneSprite[4];
		zombie_left = new BackboneSprite[4];
		zombie_idol_down = new BackboneSprite[3];
		zombie_idol_up = new BackboneSprite[3];
		zombie_idol_left = new BackboneSprite[3];
		zombie_idol_right = new BackboneSprite[3];
		zombie_fight_left = new BackboneSprite[2];
		slime_up = new BackboneSprite[2];
		slime_down = new BackboneSprite[2];
		slime_left = new BackboneSprite[2];
		slime_right = new BackboneSprite[2];
		slime_idol_left = new BackboneSprite[4];
		slime_idol_right = new BackboneSprite[4];
		slime_idol_up = new BackboneSprite[4];
		slime_idol_down = new BackboneSprite[4];
		
		// Images
		mouse_pressed = new BackboneSprite("Mouse Pressed", BackboneImageLoader.loadImage("/images/image_mouse_pressed.png"));
		mouse_released = new BackboneSprite("Mouse Released", BackboneImageLoader.loadImage("/images/image_mouse_released.png"));
		mouse = new BackboneSprite("Mouse", BackboneImageLoader.loadImage("/images/image_mouse.png"));
		mouse_two = new BackboneSprite("Mouse Two", BackboneImageLoader.loadImage("/images/image_mouse_two.png"));
		shadow = new BackboneSprite("Shadow", BackboneImageLoader.loadImage("/images/shadow.png"));
		background_palm_tree = new BackboneSprite("Background Palm Tree", BackboneImageLoader.loadImage("/images/backgrounds/palm_tree.png"));
		background_loading_one = new BackboneSprite("Background Loading One", BackboneImageLoader.loadImage("/images/backgrounds/background_loading_one.png"));
		tree_sinister = new BackboneSprite("Tree Sinister", BackboneImageLoader.loadImage("/images/tree_sinister.png"));
		tree_sinister_apple = new BackboneSprite("Tree Sinister Apple", BackboneImageLoader.loadImage("/images/tree_sinister_apple.png"));
		tree_sinister_two = new BackboneSprite("Tree Sinister Two", BackboneImageLoader.loadImage("/images/tree_sinister_two.png"));
		tree_sinister_three = new BackboneSprite("Tree Sinister Three", BackboneImageLoader.loadImage("/images/tree_sinister_three.png"));
		jukebox = new BackboneSprite("Juke Box", BackboneImageLoader.loadImage("/images/jukebox_sepia_broken.png"));
		particle_fire = new BackboneSprite("Particle Fire", BackboneImageLoader.loadImage("/images/particle_fire.png"));
		particle_acid = new BackboneSprite("Particle Acid", BackboneImageLoader.loadImage("/images/particle_acid.png"));
		particle_darkness = new BackboneSprite("Particle Darkness", BackboneImageLoader.loadImage("/images/particle_darkness.png"));
		jem_blue = new BackboneSprite("Jem Blue", BackboneImageLoader.loadImage("/images/Drops/Jems/jem_blue.png"));
		jem_gold = new BackboneSprite("Jem Gold", BackboneImageLoader.loadImage("/images/Drops/Jems/jem_gold.png"));
		jem_green = new BackboneSprite("Jem Green", BackboneImageLoader.loadImage("/images/Drops/Jems/jem_green.png"));
		jem_pink = new BackboneSprite("Jem Pink", BackboneImageLoader.loadImage("/images/Drops/Jems/jem_pink.png"));
		damage_down = new BackboneSprite("Damage Down", BackboneImageLoader.loadImage("/images/damage_down.png"));
		damage_left = new BackboneSprite("Damage Left", BackboneImageLoader.loadImage("/images/damage_left.png"));
		damage_right = new BackboneSprite("Damage Right", BackboneImageLoader.loadImage("/images/damage_right.png"));
		damage_up = new BackboneSprite("Damage Up", BackboneImageLoader.loadImage("/images/damage_up.png"));
		rain_drop = new BackboneSprite("Rain Drop", BackboneImageLoader.loadImage("/images/rain_drop.png"));
		
		// Blocks
		block_grass = new BackboneSprite("Block Grass", sheet_block.getSprite("Block Grass", 0, 0));
		block_brick = new BackboneSprite("Block Brick", sheet_block.getSprite("Block Brick", 0, 1));
		block_grass_rock = new BackboneSprite("Block Grass Rock", sheet_block.getSprite("Block Grass Rock", 1, 0));
		block_grass_flower = new BackboneSprite("Block Grass Flower", sheet_block.getSprite("Block Grass Flower", 1, 1));
		block_grass_bush = new BackboneSprite("Block Grass Bush", sheet_block.getSprite("Block Grass Bush", 2, 0));
		block_sand = new BackboneSprite("Block Sand", sheet_block.getSprite("Block Sand", 2, 1));
		block_sand_grass_right = new BackboneSprite("Block Sand Grass Right", sheet_block.getSprite("Block Sand Grass Right", 5, 1));
		block_sand_grass_left = new BackboneSprite("Block Sand Grass Left", sheet_block.getSprite("Block Sand Grass Left", 3, 1));
		block_sand_grass_up = new BackboneSprite("Block Sand Grass Up", sheet_block.getSprite("Block Sand Grass Up", 4, 0));
		block_sand_grass_down = new BackboneSprite("Block Sand Grass Down", sheet_block.getSprite("Block Sand Grass Down", 4, 2));
		block_sand_grass_top_right = new BackboneSprite("Block Sand Grass Top Right", sheet_block.getSprite("Block Sand Grass Top Right", 5, 0));
		block_sand_grass_top_left = new BackboneSprite("Block Sand Grass Top Left", sheet_block.getSprite("Block Sand Grass Top Left", 3, 0));
		block_sand_grass_bottom_left = new BackboneSprite("Block Sand Grass Bottom Left", sheet_block.getSprite("Block Sand Grass Bottom Left", 3, 2));
		block_sand_grass_bottom_right = new BackboneSprite("Block Sand Grass Bottom Right", sheet_block.getSprite("Block Sand Grass Bottom Right", 5, 2));
		block_diner_tile = new BackboneSprite("Block Diner Tile", sheet_block.getSprite("Block Diner Tile", 4, 5));
		block_diner_wall = new BackboneSprite("Block Diner Wall", sheet_block.getSprite("Block Diner Wall", 4, 4));
		block_diner_fade_up = new BackboneSprite("Block Diner Fade Up", sheet_block.getSprite("Block Diner Fade Up", 4, 3));
		block_diner_fade_down = new BackboneSprite("Block Diner Fade Down", sheet_block.getSprite("Block Diner Fade Down", 4, 7));
		block_diner_fade_right = new BackboneSprite("Block Diner Fade Right", sheet_block.getSprite("Block Diner Fade Right", 7, 4));
		block_diner_fade_left = new BackboneSprite("Block Diner Fade Left", sheet_block.getSprite("Block Diner Fade Left", 3, 4));
		block_diner_fade_top_left = new BackboneSprite("Block Diner Fade Top Left", sheet_block.getSprite("Block Diner Fade Top Left", 3, 3));
		block_diner_fade_top_right = new BackboneSprite("Block Diner Fade Top Right", sheet_block.getSprite("Block Diner Fade Top Right", 7, 3));
		block_diner_fade_bottom_right = new BackboneSprite("Block Diner Fade Bottom Right", sheet_block.getSprite("Block Diner Fade Bottom Right", 7, 7));
		block_diner_fade_bottom_left = new BackboneSprite("Block Diner Fade Bottom Left", sheet_block.getSprite("Block Diner Fade Bottom Left", 3, 7));
		
		//Animation
		sheet_player.setSprites("Player Up", player_up, 1, 1);
		sheet_player.setSprites("Player Down", player_down, 1, 0);
		sheet_zombie.setSprites("Zombie Up", zombie_up, 0, 3);
		sheet_zombie.setSprites("Zombie Down", zombie_down, 0, 2);
		sheet_zombie.setSprites("Zombie Left", zombie_left, 0, 6);
		sheet_zombie.setSprites("Zombie Right", zombie_right, 0, 5);
		sheet_zombie.setSprites("Zombie Idol Down", zombie_idol_down, 0, 0);
		sheet_zombie.setSprites("Zombie Idol Up", zombie_idol_up, 0, 1);
		sheet_zombie.setSprites("Zombie Idol Right", zombie_idol_right, 0, 4);
		sheet_zombie.setSprites("Zombie Idol Left", zombie_idol_left, 0, 7);
		sheet_zombie.setSpritesWithSize("Zombie Fight Left", zombie_fight_left, 1, 8, 48, 24);
		sheet_slime.setSprites("Slime Right", slime_right, 0, 2);
		sheet_slime.setSprites("Slime Left", slime_left, 0, 3);
		sheet_slime.setSprites("Slime Up", slime_up, 0, 1);
		sheet_slime.setSprites("Slime Down", slime_down, 0, 0);
		sheet_slime.setSprites("Slime Idol Down", slime_idol_down, 0, 0);
		sheet_slime.setSprites("Slime Idol Right", slime_idol_right, 0, 4);
		sheet_slime.setSprites("Slime Idol Left", slime_idol_left, 0, 5);
		sheet_slime.setSprites("Slime Idol Up", slime_idol_up, 0, 1);
		
	}

}
