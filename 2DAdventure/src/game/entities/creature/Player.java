package game.entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.gfx.Animation;
import game.Game;
import game.Handler;
import game.entities.Entity;
import game.gfx.Assets;
import game.inventory.Inventory;

// Hauptklasse des Spielers, erweitert durch die Creature Klasse


public class Player extends Creature {

	private Animation animDown, animUp, animLeft, animRight;
	
	// Angriffs cd
	// 800 = cd Timer für 1 Angriff
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	
	//Inventar
	private Inventory inventory;
	
	
	
	public Player(Handler handler, float x, float y) {
		// Die x + y wieder aus der Creature -> Entity
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
		
		//Animatons
		// Kommen aus der Assets Klasse. Simple minimalistische Animation in 500 ms
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		
		
		inventory = new Inventory(handler);
		
	}


	// Aufruf der Animationen in der update() damit sie dauerhaft ablaufen und nicht stocken
	@Override
	public void update() {
		//Animation
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		//Siehe Zeile 38
		getInput();
		//move aus der Creature Klasse
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Angriff
		checkAttacks();	
		
		//Inventar
		inventory.update();
		

		
	
	}
	

	// Erstellung eines rectangle in die Richtung in der der User die Bewegungstaste drückt (Pfeiltasten)
	// Jede Entity innerhalb des Rectangle die nicht isSolid ist wird angegriffen und verletzt
	// attack Timer sorgt lediglich dafür, dass der Spieler nicht mit 60fps Angriffe ausübt (60 hits in der sec)

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown) 
			return;
			
		if(inventory.isActive())
			return;
		
		
		Rectangle collisionBounds = getCollisionBounds(0, 0);
		Rectangle attackRect = new Rectangle();
		// innerhalb des int ein entitie? Dann Angriff (in pixel)
		int attackSize = 20;
		attackRect.width = attackSize;
		attackRect.height = attackSize;
		
		if(handler.getKeyManager().attackUp) {
			attackRect.x = collisionBounds.x + collisionBounds.width / 2 - attackSize / 2;
			attackRect.y = collisionBounds.y - attackSize;
		}else if(handler.getKeyManager().attackDown) {
			attackRect.x = collisionBounds.x + collisionBounds.width / 2 - attackSize / 2;
			attackRect.y = collisionBounds.y + collisionBounds.height;
		}else 	if(handler.getKeyManager().attackLeft) {
			attackRect.x = collisionBounds.x - attackSize;
			attackRect.y = collisionBounds.y + collisionBounds.height / 2 -attackSize / 2;
		}else 	if(handler.getKeyManager().attackRight) {
			attackRect.x = collisionBounds.x + collisionBounds.width;
			attackRect.y = collisionBounds.y + collisionBounds.height / 2 -attackSize / 2;
		}else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			//Wenn der Angriff gegen uns ist, dann weitermachen ohne Schaden
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(attackRect)) {
				//Schaden = 1 int
				e.hurt(1);
				return;
			}
		}
	
}
	
	public void die() {
		System.out.println("Du bist getorben.");
	};

	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(inventory.isActive())
			return;
		
		
		if (handler.getKeyManager().up)		
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;
			
	}
	
	@Override
	public void render(Graphics g) {
		//Zugang zu x + y? Durch die Creature und Entity Klassen Vereerbung
		// Wir müssen die x und y in ein int "casten", da wir sie in der Entity als Float angeben
		
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);

		
		
		
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				  (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				  bounds.width, bounds.height);
	
	}
	
	// Wird gerendert NACHDEM alles andere gerendert wurde
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	

}
