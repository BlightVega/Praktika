package game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// Hilfsklasse f�r die Assets
// Simultan wie die fontLoader Klasse aufgebaut

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		try {
			// Gibt das geladene Bild zur�ck
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
