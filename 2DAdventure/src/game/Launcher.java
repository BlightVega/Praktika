package game;

import display.Display;

public class Launcher {
	
	// Startanfrage für die Auflösung des Spiels
	// ToDo: Zugriff über menustate -> Optionen -> Auflösung ändern
	public static void main(String[] args) {
		Game game = new Game("Spiel", 800, 600);
		game.start();
	}

}
