/**
 * File: SplashScreen.java 
 * Author: William Forte
 * Time: 11:31:55 AM
 * Date: Apr 7, 2016
 * Project: Survival
 * Package: survival.main.gamestate.splash
 */
package survival.main.gamestate.splash;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import survival.main.Main;

/**
 * File: SplashScreen.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 7, 2016
 * Time Created: 11:31:55 AM
 * Project: Survival
 * Package: survival.main.gamestate.splash
 */

public class SplashScreen {
	
	private BufferedImage logo;
	private BufferedImage background_image;
	private Color background_color;
	private int timer;
	private int timer_end;
	private int order;
	private int image_height;
	private int image_width;
	private float fade = 1f;
	private boolean end = false;
	
	public SplashScreen(BufferedImage logo, Color background_color, int timer_end, int order) {
		this.timer_end = timer_end;
		this.order = order;
		this.logo = logo;
		this.background_color = background_color;
		image_height = Main.HEIGHT;
		image_width = Main.WIDTH;
	}
	
	public SplashScreen(BufferedImage background_image, int timer_end, int order) {
		this.timer_end = timer_end;
		this.order = order;
		this.background_image = background_image;
		image_height = Main.HEIGHT;
		image_width = Main.WIDTH;
	}
	
	public SplashScreen setLogoDimension(int width, int height) {
		this.image_width = width;
		this.image_height = height;
		return this;
	}
	
	public void tick() {
		if(!end) {			
			timer++;
		}
		if(timer > timer_end) {
			if(fade > .97) {
				end = true;
			} else {
				fade += .02;
			}
		} else {
			if(fade > .05) {				
				fade -=.02;
			}
		}
	}
	
	public void render(Graphics2D g) {
		if(background_image == null) {			
			g.setColor(background_color);
			g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
			g.drawImage(logo, Main.WIDTH / 2 - image_width / 2, Main.HEIGHT / 2 - image_height / 2, image_width, image_height, null);
		} else {
			g.drawImage(background_image, 0, 0, Main.WIDTH, Main.HEIGHT, null);
		}
		g.setColor(Color.BLACK);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public boolean isOver() {
		return end;
	}
	
	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

}
