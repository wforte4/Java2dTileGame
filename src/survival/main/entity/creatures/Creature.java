/**
 * File: Creature.java 
 * Author: William Forte
 * Time: 10:59:23 AM
 * Date: Mar 24, 2016
 * Project: Survival
 * Package: survival.main.entity.creatures
 */
package survival.main.entity.creatures;

import java.awt.*;

import backbone.engine.main.BackboneAnimation;
import backbone.engine.main.BackboneSprite;
import survival.main.drops.Jem;
import survival.main.entity.Entity;
import survival.main.generation.World;

/**
 * File: Creature.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 24, 2016
 * Time Created: 10:59:23 AM
 * Project: Survival
 * Package: survival.main.entity.creatures
 */

@SuppressWarnings("serial")
public abstract class Creature extends Entity {
	
	public static final int DEFAULT_DIRECTION = 2;
	public static final float START_SPEED = 0;
	public static final float DEFAULT_ACCELERATION = .35f;
	public static final float DEFAULT_MAX_SPEED = 5;
	public static final float DEFAULT_PEACEFUL_MOVEMENT_CHANGE = 30;
	
	protected int direction;
	protected float health;
	protected float fullHealth = 10;
	protected int healthBarWidth = 50;
	protected int strength;
	protected int hurtCounter;

	protected float max_speed;
	protected float acceleration;
	protected float current_speed;
	protected float push_up;
	protected float push_down;
	protected float push_left;
	protected float push_right;
	protected float push_excelerator;
	protected float peaceful_movement_counter;
	protected float peaceful_movement_change;
	protected float healthRatio;

	protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left;
	protected boolean attacking;
	protected boolean isDamaged;
	protected boolean peaceful_movement;
	protected boolean isHurt = false;
	public boolean isDead = false;
	
	protected BackboneAnimation anim_walk_up;
	protected BackboneAnimation anim_walk_down;
	protected BackboneAnimation anim_walk_right;
	protected BackboneAnimation anim_walk_left;
	protected BackboneAnimation anim_idol_up;
	protected BackboneAnimation anim_idol_down;
	protected BackboneAnimation anim_idol_right;
	protected BackboneAnimation anim_idol_left;
	
	protected BackboneSprite current_sprite;

	/**
	 * @param world
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 */
	public Creature(World world, float xpos, float ypos, int width, int height) {
		super(world, xpos, ypos, width, height);
		useDefaultSpeed();
	}
	
	protected void useDefaultSpeed() {
		speed_right = START_SPEED;
		speed_left = START_SPEED;
		speed_up = START_SPEED;
		speed_down = START_SPEED;
		direction = DEFAULT_DIRECTION;
		acceleration = DEFAULT_ACCELERATION;
		max_speed = DEFAULT_MAX_SPEED;
		peaceful_movement_change = DEFAULT_PEACEFUL_MOVEMENT_CHANGE;
		health = fullHealth;
	}
	
	protected void animateEntity() {
		if(direction == 0) {			
			if(up) {
				if(anim_walk_up != null) {					
					anim_walk_up.update(System.currentTimeMillis());
					current_sprite = anim_walk_up.getSprite();
				}
			} else {
				if(anim_idol_up != null) {					
					anim_idol_up.update(System.currentTimeMillis());
					current_sprite = anim_idol_up.getSprite();
				}
			}
		}
		if(direction == 2) {			
			if(down) {
				if(anim_walk_down != null) {				
					anim_walk_down.update(System.currentTimeMillis());
					current_sprite = anim_walk_down.getSprite();
				}
			} else {
				if(anim_idol_down != null) {
					anim_idol_down.update(System.currentTimeMillis());
					current_sprite = anim_idol_down.getSprite();
				}
			}
		}
		if(direction == 1) {			
			if(right) {
				if(anim_walk_right != null) {					
					anim_walk_right.update(System.currentTimeMillis());
					current_sprite = anim_walk_right.getSprite();
				}
			} else {
				if(anim_idol_right != null) {
					anim_idol_right.update(System.currentTimeMillis());
					current_sprite = anim_idol_right.getSprite();
				}
			}
		}
		if(direction == 3) {			
			if(left) {
				if(anim_walk_left != null) {					
					anim_walk_left.update(System.currentTimeMillis());
					current_sprite = anim_walk_left.getSprite();
				}
			} else {
				if(anim_idol_left != null) {
					anim_idol_left.update(System.currentTimeMillis());
					current_sprite = anim_idol_left.getSprite();
				}
			}
		}
	}
	
	protected void checkIfDamaged() {
		if(isHurt) {
			hurtCounter++;
			if(hurtCounter > 120) {
				hurtCounter = 0;
				this.isHurt = false;
			}
		}
		if(isDamaged) {
			if(push_up > 0) {
				speed_up += push_up;
				push_up -= push_excelerator;
			} else {
				push_up = 0;
				isDamaged = false;
			}
			if(push_down > 0) {
				speed_down += push_down;
				push_down -= push_excelerator;
			} else {
				push_down = 0;
				isDamaged = false;
			}
			if(push_right > 0) {
				speed_right += push_right;
				push_right -= push_excelerator;
			} else {
				push_right = 0;
				isDamaged = false;
			}
			if(push_left > 0) {
				speed_left += push_left;
				push_left -= push_excelerator;
			} else {
				push_left = 0;
				isDamaged = false;
			}
		} else {
			if(attacking) attacking = false;
		}
	}

	// Push the entity in a certain direction @useful for damaging an entity
	public void pushInDirection(int direction, float force) {
		isDamaged = true;
		push_excelerator = 2;
		push_up = 0;
		push_right = 0;
		push_down = 0;
		push_left = 0;
		switch(direction) {
		case 0:
			speed_up = 0;
			push_up = force;
			break;
		case 1:
			speed_right = 0;
			push_right = force;
			break;
		case 2:
			speed_down = 0;
			push_down = force;
			break;
		case 3:
			speed_left = 0;
			push_left = force;
			break;
		default:
			System.out.println("Not a Direction!");
			break;
		}
	}
	
	protected void attackInDirection(int direction, float force, int damage) {
		attacking = true;
		pushInDirection(direction, force);
	}

	// Controls the movement of Creatures
	// Pos.xpos represents the x position on screen
	// Pos.ypos represents the y position on screen
	protected void move() {	
		pos.ypos -= speed_up;
		pos.ypos += speed_down;
		pos.xpos += speed_right;
		pos.xpos -= speed_left;
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

	// Controls acceleration and deceleration of Entity movement
	protected void smoothMove() {
		if(up) {
			if(speed_up < max_speed) {
				speed_up += acceleration;
			} else {
				speed_up = max_speed;
			}
		} else {
			if(speed_up > 0) {
				speed_up -= acceleration;
			} else {
				speed_up = 0;
			}
		}
		if(down) {
			if(speed_down < max_speed) {
				speed_down += acceleration;
			} else {
				speed_down = max_speed;
			}
		} else {
			if(speed_down > 0) {
				speed_down -= acceleration;
			} else {
				speed_down = 0;
			}
		}
		if(right) {
			if(speed_right < max_speed) {
				speed_right += acceleration;
			} else {
				speed_right = max_speed;
			}
		} else {
			if(speed_right > 0) {
				speed_right -= acceleration;
			} else {
				speed_right = 0;
			}
		}
		if(left) {
			if(speed_left < max_speed) {
				speed_left += acceleration;
			} else {
				speed_left = max_speed;
			}
		} else {
			if(speed_left > 0) {
				speed_left -= acceleration;
			} else {
				speed_left = 0;
			}
		}
	}

	// Generates random control movement of peaceful entities
	protected void peacefulMovement() {
		if(!isDamaged) {
			if(peaceful_movement) {
				peaceful_movement_counter++;
				if(peaceful_movement_counter > peaceful_movement_change + random.nextInt((int) peaceful_movement_change)) {
					int direction = random.nextInt(4);
					int stay = random.nextInt(3);
					if(stay == 0) {
						setDirection(true, direction);
					} else {
						setDirection(false, direction);
					}
					peaceful_movement_counter = 0;
				}
			}
		}
	}

	// Set the direction to move in
	protected void setDirection(boolean move, int direction) {
		up = false;
		down = false;
		right = false;
		left = false;
		this.direction = direction;
		if(move) {			
			switch(direction) {
			case 0:
				up = true;
				break;
			case 1:
				right = true;
				break;
			case 2:
				down = true;
				break;
			case 3:
				left = true;
				break;
			default:
				up = false;
				down = false;
				right = false;
				left = false;
				break;
			}
		}
	}

	// Retrieve jems from the world for experience
	protected void collectJems() {
		for(Jem jem: world.getEntity_manager().getJems()) {			
			if(getBounds().intersects(jem.getBounds())) {
				getJem_collection().addJem(jem);
			}
		}
	}

	// Get the default sort position in the world
	protected void defaultSort() {
		sort_y = pos.getWorldLocation().ypos;
	}

	// Starting the animation playing state
	protected void playAllAnimations() {
		anim_walk_up.play();
		anim_walk_down.play();
		anim_walk_left.play();
		anim_walk_right.play();
		anim_idol_up.play();
		anim_idol_right.play();
		anim_idol_down.play();
		anim_idol_left.play();
	}

	// Setting animations for the entity
	protected abstract void setAnimations(int speed, int idol_factor);

	/* (non-Javadoc)
	 * @see survival.main.entity.Entity#tick()
	 */
	@Override
	public void tick() {
		setBounds((int) pos.xpos, (int) pos.ypos, bounds_width, bounds_height);
	}

	/* (non-Javadoc)
	 * @see survival.main.entity.Entity#render(java.awt.Graphics2D)
	 *
	 * Length of health bar is 50
	 */
	@Override
	public void render(Graphics2D g) {
		if(this instanceof Player == false) {

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.60));
			g.setColor(Color.BLACK);
			g.fillRect((int) pos.getWorldLocation().xpos, (int) pos.getWorldLocation().ypos - 20, 50, 25);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			g.fillRect((int) pos.getWorldLocation().xpos - 2, (int) pos.getWorldLocation().ypos - 2, 54, 8);
			g.setColor(new Color(237,92,80));
			g.fillRect((int) pos.getWorldLocation().xpos , (int) pos.getWorldLocation().ypos, healthBarWidth, 4);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
			g.setColor(Color.white);
			g.drawString((int) health + " / " + (int) fullHealth, (int) pos.getWorldLocation().xpos - 2, (int) pos.getWorldLocation().ypos - 8);
		}

	}

	public boolean isAttacking() {
		return attacking;
	}

	public void damageEntity(int amount, int direction, int force) {
			if((health - amount) > 0) {
				this.health = (health - amount);
				this.isHurt = true;
				pushInDirection(direction, force);
			} else {
				this.health = 0;
				this.isDead = true;
			}
			System.out.println(health);
			System.out.println(fullHealth);
			this.healthBarWidth = (int) ((health / fullHealth ) * 50);
			this.healthRatio = 8 % 10;
			System.out.println(healthRatio);
	}

	/**
	 * @return the speed_right
	 */
	public float getSpeed_right() {
		return speed_right;
	}

	/**
	 * @return the speed_left
	 */
	public float getSpeed_left() {
		return speed_left;
	}

	/**
	 * @return the speed_down
	 */
	public float getSpeed_down() {
		return speed_down;
	}

	/**
	 * @return the speed_up
	 */
	public float getSpeed_up() {
		return speed_up;
	}
	
	/**
	 * @return the health
	 */
	public float getHealth() {
		return health;
	}
	
	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isHurt() {
		return isHurt;
	}

	public void setHurt(boolean hurt) {
		isHurt = hurt;
	}
}
