/**
 * File: Player.java 
 * Author: William Forte
 * Time: 11:15:16 AM
 * Date: Mar 24, 2016
 * Project: Survival
 * Package: survival.main.entity.creatures
 */
package survival.main.entity.creatures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import backbone.engine.main.BackboneAnimation;
import backbone.engine.main.BackboneMouseManager;
import backbone.engine.main.BackboneVector2f;
import survival.main.Main;
import survival.main.drops.jem.JemCollection;
import survival.main.generation.World;
import survival.main.images.Assets;
import survival.main.light.Light;
import survival.main.particle.ParticleManager;
import survival.main.particle.ParticleManagerType;
import survival.main.ui.GUI;
import survival.main.ui.bar.ExperienceBar;
import survival.main.utils.Util;

/**
 * File: Player.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 24, 2016
 * Time Created: 11:15:16 AM
 * Project: Survival
 * Package: survival.main.entity.creatures
 */

@SuppressWarnings("serial")
public class Player extends Creature {
	
	public BackboneVector2f world_position_fix;
	private ExperienceBar experienceBar;
	private GUI gui;
	private ParticleManager particlemanager;

	protected int currentDamageAmount = 2;

	/**
	 * @param world
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 */
	public Player(World world, float xpos, float ypos, int width, int height) {
		super(world, xpos, ypos, width, height);
		useDefaultSpeed();
		world_position_fix = new BackboneVector2f();
		experienceBar = new ExperienceBar(Main.WIDTH - 80, 75, 20, Main.HEIGHT - 200, 2000);
		particlemanager = new ParticleManager(ParticleManagerType.AMBIENT, this, 30, 1, 6).setGenerating(true).setColor(new Color(0 , 0, 0));
		gui = new GUI(this);
		pos.xpos = Main.WIDTH / 2 - width / 2;
		pos.ypos = Main.HEIGHT / 2 - height / 2;
		jem_collection = new JemCollection(this, experienceBar);
		this.health = 100;
		setAnimations(180, 4);
		world.addLight(new Light(this, 600, 0x000000));
	}
	
	public void setAnimations(int speed, int idol_factor) {
		current_sprite = Assets.zombie_up[0];
		anim_walk_up = new BackboneAnimation(Assets.zombie_up, speed);
		anim_walk_down = new BackboneAnimation(Assets.zombie_down, speed);
		anim_walk_right = new BackboneAnimation(Assets.zombie_right, speed);
		anim_walk_left = new BackboneAnimation(Assets.zombie_left, speed);
		anim_idol_up = new BackboneAnimation(Assets.zombie_idol_up, speed * idol_factor);
		anim_idol_down = new BackboneAnimation(Assets.zombie_idol_down, speed * idol_factor);
		anim_idol_left = new BackboneAnimation(Assets.zombie_idol_left, speed * idol_factor);
		anim_idol_right = new BackboneAnimation(Assets.zombie_idol_right, speed * idol_factor);
		anim_fight_left = new BackboneAnimation(Assets.zombie_fight_left, 100);
		anim_fight_right = new BackboneAnimation(Assets.zombie_fight_right, 100);
		anim_fight_down = new BackboneAnimation(Assets.zombie_fight_down, 100);
		anim_fight_up = new BackboneAnimation(Assets.zombie_fight_up, 100);
		playAllAnimations();
	}
	
	/* (non-Javadoc)
	 * @see survival.main.entity.creatures.Creature#tick()
	 */
	@Override
	public void tick() {
		super.tick();
		experienceBar.tick();
		gui.tick();
		world_position_fix.xpos = pos.xpos + world.worldxpos;
		world_position_fix.ypos = pos.ypos + world.worldypos;
		sort_y = pos.ypos;
		setBlockCollisionBounds(pos.xpos + 15, pos.ypos + height / 2, width / 2, height / 2);
		checkCollisions();
		move();
		smoothMove();
		checkIfDamaged();
		handleAnimation();
		particlemanager.tick();
		collectJems();
	}

	public void move() {
		world.worldypos -= speed_up;
		world.worldypos += speed_down;
		world.worldxpos += speed_right;
		world.worldxpos -= speed_left;
		if(up) {			
			direction = 0;
		}
		if(down) {			
			direction = 2;
		}
		if(right) {			
			direction = 1;
		}
		if(left) {			
			direction = 3;
		}
	}
	
	/* (non-Javadoc)
	 * @see survival.main.entity.creatures.Creature#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		particlemanager.render(g);
		g.drawImage(Assets.shadow.getImage(), (int) (pos.xpos), (int) pos.ypos + height - height / 3, width, height / 2, null);
		if(!attacking) {
			g.drawImage(current_sprite.getImage(), (int) pos.xpos, (int) pos.ypos, width, height, null);
		} else {
			if(direction == 1 || direction == 3) {
				g.drawImage(current_sprite.getImage(), (int) pos.xpos - (width / 2), (int) pos.ypos, width * 2, height, null);
			} else {
				g.drawImage(current_sprite.getImage(), (int) pos.xpos, (int) pos.ypos - (height / 2), width, height * 2, null);
			}
		}
		if(showHurtAnim) {
			g.drawImage(Util.setImageOverlayColorRed(current_sprite.getImage()), (int) pos.xpos, (int) pos.ypos, width, height, null);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_W) {
			up = true;
		}
		if(k == KeyEvent.VK_SPACE) {
			world.getEntity_manager().dropJem(
					new BackboneVector2f(
							world_position_fix.xpos + new Random().nextInt(100),
							world_position_fix.ypos + new Random().nextInt(100)),
					new Random().nextInt(300));
		}
		if(k == KeyEvent.VK_SHIFT || BackboneMouseManager.pressed) {
			attackInDirection(direction, 6, 300);
			world.playSoundEffect("sword", world.settings.getVolume() + .1f);
		}
		if(k == KeyEvent.VK_D) {
			right = true;
		}
		if(k == KeyEvent.VK_S) {
			down = true;
		}
		if(k == KeyEvent.VK_A) {
			left = true;
		}
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_W) {
			up = false;
		}
		if(k == KeyEvent.VK_D) {
			right = false;
		}
		if(k == KeyEvent.VK_S) {
			down = false;
		}
		if(k == KeyEvent.VK_A) {
			left = false;
		}
	}
	
	public void renderMeleeAttacking(Graphics2D g) {
		int fix = 40;
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
		if(attacking) {
			switch(direction) {
			case 0:
				g.drawImage(
						Assets.damage_up.getImage(), 
						(int) (pos.xpos) + 10,
						(int) (pos.ypos) - fix , 64, 64, null);
				break;
			case 1:
				g.drawImage(
						Assets.damage_right.getImage(), 
						(int) (pos.xpos) + fix, 
						(int) (pos.ypos), 64, 64, null);
				break;
			case 2:
				g.drawImage(
						Assets.damage_down.getImage(), 
						(int) (pos.xpos), 
						(int) (pos.ypos) + fix, 64, 64, null);
				break;
			case 3:
				g.drawImage(
						Assets.damage_left.getImage(), 
						(int) (pos.xpos) - fix / 2, 
						(int) (pos.ypos), 64, 64, null);
				break;
			default:
				break;
			}
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	/**
	 * @return the element_pos
	 */
	public BackboneVector2f getElement_pos() {
		return world_position_fix;
	}
	
	/**
	 * @return the gui
	 */
	public GUI getGui() {
		return gui;
	}

	/**
	 * @return the acerbia_bar
	 */
	public ExperienceBar getExperienceBar() {
		return experienceBar;
	}
	
	public World getCurrentWorld() {
		return world;
	}
	
	/**
	 * @return the world_position_fix
	 */
	public BackboneVector2f getWorld_position_fix() {
		return world_position_fix;
	}

	public int getCurrentDamageAmount() {
		return currentDamageAmount;
	}

	public void setCurrentDamageAmount(int currentDamageAmount) {
		this.currentDamageAmount = currentDamageAmount;
	}

}
