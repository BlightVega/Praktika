package game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

import game.entities.creature.Player;

// Hauptklasse der Assets, hier werden ALLE Assets/Sprites/images erstellt, geladen, zugeordnet und
// an der Stelle des spritesheets ausgewählt

// WICHTIG: Aufgrund von Zeitmangel habe ich auf ein Spritesheet aus dem Internet zurückgegriffen
// Exit und Hauptmenü habe ich selbstständig hinzugefügt und erstellt


public class Assets {
	
	private static final int width = 32, height = 32;
	
	// 28 damit ich weiß, welche Schriftgröße die font hat
	public static Font font28;
	
	// hier die Namen der einzelnen Bilder eines sheets eintragen
	public static BufferedImage dirt, grass, stone, tree, rock;
	public static BufferedImage wood;
	public static BufferedImage sign;
	public static BufferedImage[] haupt_menu;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] button_start;
	public static BufferedImage[] button_exit; 
	public static BufferedImage inventoryScreen;
	
	// init die einmalig alles einlädt und nicht im ständigen loop von update hängt
	public static void init() {
		
		font28 =FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		
		haupt_menu = new BufferedImage[2];
		haupt_menu[0] = sheet.crop(width * 2, height * 4, width * 4, height);
		haupt_menu[1] = sheet.crop(width * 2, height * 4, width * 4, height);
		
		button_start = new BufferedImage[2];
		button_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		button_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		button_exit = new BufferedImage[2];
		button_exit[0] = sheet.crop(width * 6, height * 6, width * 2, height);
		button_exit[1] = sheet.crop(width * 6, height * 7, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		
		
		//ToDO: Gegner implementieren
//		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
//		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
//		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
//		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
//		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
//		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
//		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
//		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
		
		
		wood = sheet.crop(width,  height,  width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
		sign = sheet.crop(width * 2, height, width, height);
		
		
		
		
	}

}
