/**
 * File: ParticleElement.java 
 * Author: William Forte
 * Time: 12:31:51 AM
 * Date: Apr 29, 2016
 * Project: Survival
 * Package: survival.main.particle
 */
package survival.main.particle;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

/**
 * File: ParticleElement.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 29, 2016
 * Time Created: 12:31:51 AM
 * Project: Survival
 * Package: survival.main.particle
 */

public class ParticleElement extends Particle {
	
	private float floating;
	private float move;
	private float movefactor;
	private float moveAmount;
	private boolean left;
	private boolean right;
	private boolean halfway;

	/**
	 * @param manager
	 * @param x
	 * @param y
	 * @param size
	 * @param life
	 */
	public ParticleElement(ParticleManager manager, int x, int y, int size, int life) {
		super(manager, x, y, size, life);
		floating = 2;
		movefactor = .05f;
		moveAmount = random.nextInt(3) + 3;
		if(random.nextInt(2) == 1) {
			left = true;
			right = false;
		} else {
			left = false;
			right = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see survival.main.particle.Particle#tick()
	 */
	@Override
	protected void tick() {
		super.tick();
		pos.ypos -= floating;
		if(left) {
			if(halfway) {
				move -= movefactor;
			} else {
				move += movefactor;
				if(move > moveAmount / 2) halfway = true;
			}
			if(move < 0) {
				halfway = false;
				right = true;
				left = false;
			}
			pos.xpos -= move;
		}
		if(right) {
			if(halfway) {
				move -= movefactor;
			} else {
				move += movefactor;
				if(move > moveAmount / 2) halfway = true;
			}
			pos.xpos += move;
			if(move < 0) {
				left = true;
				halfway = false;
				right = false;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see survival.main.particle.Particle#render(java.awt.Graphics2D)
	 */
	@Override
	protected void render(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f));
		super.render(g);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
