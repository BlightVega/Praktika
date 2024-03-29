package game.entities.creature;

import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

// Erweitert durch die Entity Klasse
public abstract class Creature extends Entity {
	
	public static final float DEFAULT_SPEED = 3.0f;
	
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	
	

	protected float speed;
	protected float xMove;
	protected float yMove;
	
	
	
	
	
	public Creature(Handler handler ,float x, float y, int width, int height) {
		//super = die x + y aus entity() nehmen/geben
		super(handler, x, y, width, height);

		speed = DEFAULT_SPEED;	
		xMove = 0;
		yMove = 0;
	}


	// Zur besseren Lesbarkeit habe ich die move() so klein wie m�glich gehalten und �ber moveX / moveY definiert
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f)) {
			moveX();
		}
		
		if(!checkEntityCollisions(0f, yMove)) {
			moveY();
		}
		
		
	}

	
	// move x und move y
	// Abfrage der Bewegungnen in Verbindung mit der Kollisionsabfrage
	// collisionWithTile habe ich �ber ein isSolid boolean erstellt um einfacher auf "durchgehbar" oder "nicht durchgehbar" zuzugreifen
	
	public void moveX() {
		if(xMove > 0 ) { // Bewegung rechts
			
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH; 
			if(!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width -1;
			}
			
		}else if(xMove < 0) { // Bewegung links
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH; 
			if(!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() {// Bewegung hoch
		if(yMove < 0) {
			int ty = (int) (y + yMove + bounds.y)/ Tile.TILEHEIGHT; 
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
			
			
		}else if(yMove > 0) { //Bewegung runter
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT; 
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height -1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
	//ToDo: Weltenwechsel
//	protected boolean switchWorld(int x, int y) {
//		handler.setWorld();
//	}
//	
	

	public int getHealth() {
		return health;
	}





	public void setHealth(int health) {
		this.health = health;
	}





	public float getSpeed() {
		return speed;
	}





	public void setSpeed(float speed) {
		this.speed = speed;
	}





	public float getxMove() {
		return xMove;
	}





	public void setxMove(float xMove) {
		this.xMove = xMove;
	}





	public float getyMove() {
		return yMove;
	}





	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
	


}
