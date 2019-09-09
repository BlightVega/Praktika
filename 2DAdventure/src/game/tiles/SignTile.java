package game.tiles;

import java.awt.image.BufferedImage;

import game.gfx.Assets;

public class SignTile extends Tile{

	public SignTile(int id) {
		super(Assets.sign, id);
	}
	
	
	public boolean isSolid() {
		return true;
	}

	

}
