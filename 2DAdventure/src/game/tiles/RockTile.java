package game.tiles;

import java.awt.image.BufferedImage;

import game.gfx.Assets;

public class RockTile extends Tile{

	public RockTile(int id) {
		super(Assets.stone, id);
		// TODO Automatisch generierter Konstruktorstub
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
	

}
