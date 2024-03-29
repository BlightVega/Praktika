package game.entities.statics;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.items.Item;
import game.tiles.Tile;

public class Tree extends StaticEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
		
	}

	@Override
	public void update() {
		// TODO Automatisch generierter Methodenstub
		
	}

	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width,height,null);
		
	}
	


}
