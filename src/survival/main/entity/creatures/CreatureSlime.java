/**
 * File: CreatureSlime.java 
 * Author: William Forte
 * Time: 12:26:48 PM
 * Date: May 13, 2016
 * Project: Survival
 * Package: survival.main.entity.creatures
 */
package survival.main.entity.creatures;

import java.awt.Graphics2D;
import java.util.Random;

import backbone.engine.main.BackboneAnimation;
import survival.main.drops.Jem;
import survival.main.entity.element.Element;
import survival.main.generation.World;
import survival.main.images.Assets;
import survival.main.utils.Util;

/**
 * File: CreatureSlime.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 13, 2016
 * Time Created: 12:26:48 PM
 * Project: Survival
 * Package: survival.main.entity.creatures
 */

@SuppressWarnings("serial")
public class CreatureSlime extends Creature {

	/**
	 * @param world
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 */
	public CreatureSlime(World world, float xpos, float ypos, int width, int height) {
		super(world, xpos, ypos, width, height);
		setSpeed(.25f, 4f);
		setAnimations(180, 4);
		peaceful_movement = true;
		acceleration = .1f;
		strength = 2f;
	}
	
	/* (non-Javadoc)
	 * @see survival.main.entity.creatures.Creature#setAnimations(int, int)
	 */
	@Override
	protected void setAnimations(int speed, int idol_factor) {
		current_sprite = Assets.slime_up[0];
		anim_walk_up = new BackboneAnimation(Assets.slime_up, speed);
		anim_walk_down = new BackboneAnimation(Assets.slime_down, speed);
		anim_walk_right = new BackboneAnimation(Assets.slime_right, speed);
		anim_walk_left = new BackboneAnimation(Assets.slime_left, speed);
		anim_idol_up = new BackboneAnimation(Assets.slime_idol_up, speed);
		anim_idol_right = new BackboneAnimation(Assets.slime_idol_right, speed);
		anim_idol_down = new BackboneAnimation(Assets.slime_idol_down, speed);
		anim_idol_left = new BackboneAnimation(Assets.slime_idol_left, speed);
		anim_fight_left = new BackboneAnimation(Assets.slime_idol_left, speed);
		anim_fight_right = new BackboneAnimation(Assets.slime_idol_right, speed);
		anim_fight_up = new BackboneAnimation(Assets.slime_idol_up, speed);
		anim_fight_down = new BackboneAnimation(Assets.slime_idol_down, speed);
		playAllAnimations();
	}
	
	/* (non-Javadoc)
	 * @see survival.main.entity.creatures.Creature#tick()
	 */
	@Override
	public void tick() {
		world.sound.getVolume();
		defaultSort();
		setSolidWorldBounds(0, 10);
		setBlockCollisionBounds(pos.getWorldLocation().xpos, pos.getWorldLocation().ypos + bounds_height / 2, bounds_width, bounds_height / 2);
		checkCollisions();
		handleAnimation();
		move();
		smoothMove();
		checkIfDamaged();
		if(isInPlayerDistance()) {
			followPlayer();
			attacking = true;
		} else {
			peacefulMovement();
			attacking = false;
		}
	}
	

	/* (non-Javadoc)
	 * @see survival.main.entity.creatures.Creature#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.drawImage(Assets.shadow.getImage(), 
				(int) (pos.getWorldLocation().xpos), 
				(int) (pos.getWorldLocation().ypos + getImageHeight() - 10),
				width, 
				height / 2, 
				null);
		g.drawImage(
				current_sprite.getImage(), 
				(int) (pos.getWorldLocation().xpos), 
				(int) (pos.getWorldLocation().ypos), 
				width, 
				height, 
				null);

		if(showHurtAnim) {
			g.drawImage(Util.setImageOverlayColorRed(current_sprite.getImage()),
					(int) (pos.getWorldLocation().xpos),
					(int) (pos.getWorldLocation().ypos), width, height, null);
		}

	}

	@Override
	public void onHit() {
		super.onHit();
		world.playSoundEffect("hit", .4f);
	}

	@Override
	public void onDeath() {
		int chance = random.nextInt(2);
		if(chance == 1) world.getEntity_manager().dropJem(pos, random.nextInt(100));
		world.getEntity_manager().dropJem(pos, random.nextInt(100));
	}
}
