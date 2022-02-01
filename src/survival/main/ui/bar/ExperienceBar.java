/**
 * File: GrowthBar.java 
 * Author: William Forte
 * Time: 5:07:11 PM
 * Date: Apr 30, 2016
 * Project: Survival
 * Package: survival.main.ui.bar
 */
package survival.main.ui.bar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

import backbone.engine.main.BackboneVector2f;

/**
 * File: GrowthBar.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 30, 2016
 * Time Created: 5:07:11 PM
 * Project: Survival
 * Package: survival.main.ui.bar
 */

public class ExperienceBar {
	
	private BackboneVector2f pos;
	private CopyOnWriteArrayList<BarFade> bars;
	private int total_width;
	private int total_height;
	private int current_amount;
	private int full_amount;
	private int bar_amount;
	private boolean is_Full = false;
	
	public ExperienceBar(int x, int y, int width, int height, int full_amount) {
		pos = new BackboneVector2f(x, y);
		bars = new CopyOnWriteArrayList<BarFade>();
		this.total_width = width;
		this.total_height = height;
		this.full_amount = full_amount;
		this.current_amount = 0;
	}
	
	public void tick() {
		if(bar_amount > current_amount) bar_amount -= 10;
		if(bar_amount < current_amount) bar_amount += 10;
		if(current_amount >= full_amount) is_Full = true;
		for(BarFade bar: bars) {
			bar.tick();
		}
	}
	
	public void render(Graphics2D g) {		
		if(bar_amount > current_amount) {	
			g.setColor(Color.RED);
			g.fillRect(
					(int) (pos.xpos), 
					(int) (pos.ypos + total_height - getPercent(bar_amount, full_amount, total_height)) + 1, 
					total_width, 
					(int) getPercent(bar_amount, full_amount, total_height));
			g.setColor(new Color(0xb3ffb3));
			g.fillRect(
					(int) (pos.xpos), 
					(int) (pos.ypos + total_height - getPercent(current_amount, full_amount, total_height)) + 1, 
					total_width, 
					(int) getPercent(current_amount, full_amount, total_height));

		} else {
			g.setColor(Color.RED);
			g.fillRect(
					(int) (pos.xpos), 
					(int) (pos.ypos + total_height - getPercent(current_amount, full_amount, total_height)) + 1, 
					total_width, 
					(int) getPercent(current_amount, full_amount, total_height));
			g.setColor(new Color(0xb3ffb3));
			g.fillRect(
					(int) (pos.xpos), 
					(int) (pos.ypos + total_height - getPercent(bar_amount, full_amount, total_height)) + 1, 
					total_width, 
					(int) getPercent(bar_amount, full_amount, total_height));
		}
		g.setColor(new Color(0x808080));
		for(int i = 0; i < 5; i++) {			
			g.drawRect((int) pos.xpos - i, (int) pos.ypos - i, total_width + (i * 2), total_height + (i * 2));
		}	
		g.setColor(new Color(0xb3ffb3));
		drawLines(g, 7, 5, Color.BLACK, 0);
		drawLines(g, 7, 5, new Color(0x808080), 2);
		for(BarFade bar: bars) {
			bar.render(g);
		}
		g.setFont(new Font("Courier New", Font.PLAIN, 14));
		g.drawString(current_amount + "/" + full_amount, (int) (pos.xpos - 10), (int) (pos.ypos - 10));
		
	}
	
	public void drawLines(Graphics2D g, int amount, int thickness, Color color, int yOffset) {
		g.setColor(color);
		int sector = total_height / amount;
		for(int i = 0; i < amount; i++) {
			g.fillRect((int) (pos.xpos - 3), (int) (pos.ypos + (sector * i) + sector / 2 + yOffset), total_width + 6, thickness);
		}
	}
	
	public void add(int amount) {
		if(current_amount + amount > full_amount) {
			current_amount = full_amount;
			is_Full = true;
			return;
		}
		this.current_amount += amount;
		for(int i = 0; i < amount / 10; i++) {			
			bars.add(
					new BarFade(
							this, 
							new BackboneVector2f(pos.xpos, (float) (pos.ypos + total_height - getPercent(current_amount, full_amount, total_height))),
							total_width,
							(int) getPercent(current_amount, full_amount, total_height),
							new Color(0xe6ffe6)));
		}
	}
	
	public void setFullAmount(int amount) {
		this.full_amount = amount;
	}
	
	public void empty() {
		is_Full = false;
		current_amount = 0;
	}
	
	public double getPercent(double current_amount, double full_amount, double full_width) {
		return (double) (current_amount * full_width) / full_amount;
	}
	
	public boolean isFull() {
		return is_Full;
	}
	
	/**
	 * @return the bars
	 */
	public CopyOnWriteArrayList<BarFade> getBars() {
		return bars;
	}

}
