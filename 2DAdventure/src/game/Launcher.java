package game;

import display.Display;

public class Launcher {
	
	// Startanfrage f�r die Aufl�sung des Spiels
	// ToDo: Zugriff �ber menustate -> Optionen -> Aufl�sung �ndern
	public static void main(String[] args) {
		Game game = new Game("Spiel", 800, 600);
		game.start();
	}

}
