package game.entities.statics;

import java.awt.Graphics;


import game.Handler;
import game.entities.creature.Player;
import game.gfx.Assets;
import game.items.Item;
import game.tiles.Tile;

public class Sign extends StaticEntity {

	public Sign(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 2f);
	}

	@Override
	public void update() {
		

		
	}
	
//	public void klick(int amt) {
//		health -= amt;
//		if(health <= 5) {
//			active = false;
//			System.out.println("Schild!");
//		}
//	}
	
	@Override
	public void die(){
//		handler.getWorld().getItemManager().addItem(Item.signItem.createNew((int) x, (int) y));

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.sign, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}



}