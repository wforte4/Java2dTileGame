/**
 * File: MenuButton.java 
 * Author: William Forte
 * Time: 12:45:53 PM
 * Date: Apr 4, 2016
 * Project: Survival
 * Package: survival.main.gamestate.menu
 */
package survival.main.gamestate.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import backbone.engine.main.BackboneButton;
import backbone.engine.main.BackboneButtonManager;
import backbone.engine.main.BackboneGameState;
import backbone.engine.main.BackboneMouseManager;
import backbone.engine.main.BackboneUtils;

/**
 * File: MenuButton.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Apr 4, 2016
 * Time Created: 12:45:53 PM
 * Project: Survival
 * Package: survival.main.gamestate.menu
 */

@SuppressWarnings("serial")
public class MenuButton extends BackboneButton {
	
	private String name;
	private Font font;
	private BackboneGameState state;
	private RoundRectangle2D recone;
	private RoundRectangle2D rectwo;
	private int recEdges = 15;

	/**
	 * @param manager
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public MenuButton(BackboneButtonManager manager, int x, int y, int width, int height, String name) {
		super(manager, x, y, width, height);
		this.name = name;
		font = new Font("Arial", Font.PLAIN, 24);
		recone = new RoundRectangle2D.Float(x, y, width, height, recEdges, recEdges);
		rectwo = new RoundRectangle2D.Float(x + 5, y - 5, width, height, recEdges, recEdges);
	}
	
	/**
	 * @param state the state to set
	 */
	public MenuButton setState(BackboneGameState state) {
		this.state = state;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneButton#tick()
	 */
	@Override
	public void tick() {
		super.tick();
		if(getBounds().contains(BackboneMouseManager.mouse)) {
			heldOver = true;
			recone = new RoundRectangle2D.Float(x + 3, y - 3, width, height, recEdges, recEdges);
			if(BackboneMouseManager.pressed) {
				if(state != null) {			
					manager.getGsm().switchState(state);
				} else {
					System.exit(0);
				}
			}
		} else {
			heldOver = false;
			recone = new RoundRectangle2D.Float(x, y, width, height, recEdges, recEdges);
		}
	}
	
	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneButton#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(0xFFFFFF));
		g.fill(rectwo);
		g.setColor(new Color(0x77de75));
		g.fill(recone);
		g.setColor(new Color(0xFFFFFF));
		g.setFont(font);
		if(heldOver) {	
			g.drawString(name, x + 3 + width / 2 - BackboneUtils.stringWidth(name, font) / 2, y - 3  + height / 2 + 8);	
		} else {
			g.drawString(name, x + width / 2 - BackboneUtils.stringWidth(name, font) / 2, y  + height / 2 + 8);
		}
	}

}
