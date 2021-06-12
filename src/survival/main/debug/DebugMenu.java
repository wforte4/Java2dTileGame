/**
 * File: DebugMenu.java 
 * Author: William Forte
 * Time: 10:09:09 AM
 * Date: May 16, 2016
 * Project: Survival
 * Package: survival.main.debug
 */
package survival.main.debug;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

import backbone.engine.main.BackboneVector2f;

/**
 * File: DebugMenu.java 
 * Language: Java
 * Author: Will 40
 * Data Created: May 16, 2016
 * Time Created: 10:09:09 AM
 * Project: Survival
 * Package: survival.main.debug
 */

public class DebugMenu {
	
	private ArrayList<String> lines;
	private ArrayList<String> temp_lines;
//	private Font font;
	private BackboneVector2f pos;
	private Comparator<String> stringSorter = new Comparator<String>() {
		public int compare(String a, String b) {
			if(a.length() > b.length()) return 1;
			if(b.length() > a.length()) return -1;
			return 0;
		};
	};
	
	public DebugMenu(BackboneVector2f pos) {
		lines = new ArrayList<String>();
		temp_lines = new ArrayList<String>();
//		font = new Font("Courier", Font.PLAIN, 24);
		this.pos = pos;
	}
	
	public void addLine(String message) {
		lines.add(message);
	}
	
	public String getBiggestLine() {
		temp_lines.removeAll(temp_lines);
		for(String line: lines) {
			temp_lines.add(line);
		}
		temp_lines.sort(stringSorter);
		return temp_lines.get(temp_lines.size() - 1);
	}
	
	public void render(Graphics2D g) {
		g.setFont(new Font("Courier", Font.PLAIN, 22));
//		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f));
//		g.setColor(Color.BLACK);
//		g.fillRect((int) pos.xpos - 20, (int) pos.ypos - 44, BackboneUtils.stringWidth(getBiggestLine(), new Font("Verdana", Font.PLAIN, 24)) + 40, (lines.size() + 1) * 24);
//		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));		
		g.setColor(Color.BLACK);
		for(String line: lines) {
			g.drawString(line, pos.xpos, pos.ypos + (lines.indexOf(line) * 22));
		}
		g.setColor(new Color(0xffffff));
		for(String line: lines) {
			g.drawString(line, pos.xpos + 1, pos.ypos + (lines.indexOf(line) * 22) + 2);
		}
		lines.removeAll(lines);
	}

}
