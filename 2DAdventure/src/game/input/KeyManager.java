package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Grundsätzliche KEyManager Klasse. Größtenteils mit einem Tutorial gebaut

public class KeyManager implements KeyListener {

	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left, right;
	public boolean attackUp, attackDown, attackRight, attackLeft;
	
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
		
	}
	
	public void update() {
		
		for(int i = 0; i < keys.length; i++) {
			if(cantPress[i] && !keys[i]) {
				cantPress[i] = false; 
			}else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false; 
			}
			if(!cantPress[i] && keys[i] ) {
				justPressed[i] = true; 
			}
		}
		

		
		up		 = keys[KeyEvent.VK_W];
		down	 = keys[KeyEvent.VK_S];
		left	= keys[KeyEvent.VK_A];
		right 	= keys[KeyEvent.VK_D];
		
		
		//ToDO: Angriff auf E oder F legen und die Blickrichtung des Players als Richtung nehmen
		// Aufgrund von Zeitmanagment habe ich mich vorab dazu entschieden
		// die Attack auf den Pfeiltasten zu legen
		// ist nicht schön, aber schnell und einfach
		attackUp		 = keys[KeyEvent.VK_UP];
		attackDown	 = keys[KeyEvent.VK_DOWN];
		attackRight	= keys[KeyEvent.VK_RIGHT];
		attackLeft 	= keys[KeyEvent.VK_LEFT];
	}
	
	
	// Standardmäßig die key pressed/released Funktionen
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}


	// Die unteren 3 Funktionen müssen eingefügt werden wenn wir den KeyListener implementieren
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
		System.out.println("drückse grad wa");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
	

}
