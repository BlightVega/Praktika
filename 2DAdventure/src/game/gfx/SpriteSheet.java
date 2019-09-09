package game.gfx;

import java.awt.image.BufferedImage;

// Hilfsklasse für die Assets
// Simultan wie die fontLoader aufgebaut
// mit dem Zusatz, dass ich ein Sheet zerschneiden (crop) kann, um nur ein Spritesheet nutzen zu können (Ressourcensparend)

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
		
	}

}
