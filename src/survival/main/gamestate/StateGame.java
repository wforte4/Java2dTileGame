/**
 * File: StateGame.java 
 * Author: William Forte
 * Time: 10:45:39 AM
 * Date: Mar 23, 2016
 * Project: Survival
 * Package: survival.main.gamestate
 */
package survival.main.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import backbone.engine.main.BackboneGameState;
import backbone.engine.main.BackboneGameStateManager;
import survival.main.Main;
import survival.main.entity.creatures.Player;
import survival.main.generation.World;
import survival.main.generation.worlds.MapWorld;

/**
 * File: StateGame.java 
 * Language: Java
 * Author: Will 40
 * Data Created: Mar 23, 2016
 * Time Created: 10:45:39 AM
 * Project: Survival
 * Package: survival.main.gamestate
 */

public class StateGame extends BackboneGameState {
	
	private World world;
	private Player player;

	/**
	 * @param gsm
	 */
	public StateGame(BackboneGameStateManager gsm) {
		super(gsm);
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#init()
	 */
	@Override
	public void init() {
		world = new MapWorld(gsm, "/worlds/map_diner.png");
		player = new Player(world, 140, 140, 70, 70);
		world.setPlayer(player);
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		world.keyPressed(k);
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) {
		world.keyReleased(k);
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		world.render(g);
	}

	/* (non-Javadoc)
	 * @see backbone.engine.main.BackboneGameState#tick()
	 */
	@Override
	public void tick() {
		world.tick();
	}

}
