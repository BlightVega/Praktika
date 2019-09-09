package game.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.security.auth.x500.X500Principal;

import game.Game;
import game.Handler;
import game.entities.creature.Player;
import game.entities.statics.Tree;
import game.gfx.Assets;
import game.tiles.Tile;
import game.worlds.World;

//Jede State die wir anlegen muss "extends State" haben
public class GameState extends State{
	
	private Player player;
	private World world;
	private World world2;


	
	private boolean active = true;
	public State gameState;
	public State menuState;



	//ToDO: Mehrere welten als Variable Speichern.
	// Wechseln der Welten in einer anderen Klasse (state)
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler,"res/worlds/world1.txt");
		world2 = new World(handler,"res/worlds/world2.txt");
		handler.setWorld(world);

		
		
		
//		game.getGameCamera().move(100, 200);
		
	}
	
	//ToDO: Klasse aktuell nicht nutzbar
	public void switchWorld() {
		handler.setWorld(world2);
	}
	
	@Override
	public void tick() {
		world.update();
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
			active = !active;
//		
//		if(player.checkEntityCollisions((int) 200, (int) 250)) {
//			switchWorld();
//		}
//	
//		if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
//			y += yMove;
//		}else {
//			y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height -1;
//		}
		
		
		
//		game.getGameCamera().move(1, 1);
	}

	@Override
	public void render(Graphics g) {
		
		
		menuState = new MenuState(handler);
		world.render(g);

		if(!active) {
			State.setState(menuState);
			active = true;
		}
			
		else {
			return;
		}
		
		
//		Tile.tiles[0].render(g, 0, 0);
//		Tile.tiles[1].render(g, 128, 0);
//		Tile.tiles[2].render(g, 256, 0);
	
	}


	public World getWorld() {
		return world;
	}


	public void setWorld(World world) {
		this.world = world;
	}


	public World getWorld2() {
		return world2;
	}


	public void setWorld2(World world2) {
		this.world2 = world2;
	}
	

}
