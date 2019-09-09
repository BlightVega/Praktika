package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Observer;

import display.Display;
import game.gfx.Assets;
import game.gfx.GameCamera;
import game.gfx.ImageLoader;
import game.gfx.SpriteSheet;
import game.input.KeyManager;
import game.input.MouseManager;
import game.states.GameState;
import game.states.MenuState;
import game.states.State;




/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+	"just a simple 2D Adventure"																				+
+																												+
+	Ich habe versucht in diesem Projekt weitesgehend oop zu arbeiten.											+
+   Desweiteren habe ich an mehreren Stellen unterschiedliche Tutorials zur Hilfe genommen.						+
+   Die Verteilung in diversen package war von mir bewusst gemacht. 											+
+   Ich möchte damit lernen, Klassen/Funktionen logisch anzuordnen damit ein späterer Zugriff einfacher wird.	+
+   																											+
+   Die Assets habe ich größtenteils der Google Bildersuche entnommen, ich besitze keine Rechte daran.			+
+   Für eine eigene Assets Entwicklung fehlte mir die Zeit und meiner Meinung auch die Notwendigkeit.			+
+																												+
+  Wenn nicht anders ausgezeichnet ist der Code von mir selbst geschrieben mit Inspiration und Lösungshilfen	+
+  unterschiedlicher Tutorials. Kein Code wurde "copy&paste" eingefügt, selbst code der,						+
+  schriftlich erwähnt von einem Tutorial übernommen wurde habe ich selbst geschrieben und zum Teil				+
+  Klassen anders benannt um sie in mein Projekt logischer aufzuzeigen. 										+
+																												+
+		06.09.2019 Rene Treml Version 0.01 Alpha																+
+																												+
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

// runnable für eine Thread Erstellung
// Diverse Technische Einstellungen wie fps und SystemTime und Aufruf aller Listener

public class Game implements Runnable {
	
	private Display display;
	
	public String title;
	private int width;
	private int height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public State gameState;
	public State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	

	
	
	public Game(String title, int width, int height) {
		
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
		
		
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		
		//Alle states müssen wir hier auch initiieren
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);

	}
	
	
	
	private void update() {
		keyManager.update();
		// Wenn wir eine Gamestate haben dann öffne tick()
		if(State.getState() != null) {
			State.getState().tick();
		}
		
	}
	
	//Anzeigen aufs Frame bringen
	private void render() {
		// Alles was angezeigt werden soll wird erst in ein Buffer geladen
		// Damit umgeht man "Flickern/Flimmern" 
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			// Wenn es noch kein buffer gibt, erstell 3 Stück bevor es ein wirkliches Anzeigebild gibt
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, 800, 600);
		// Anzeige anfang

		// Wenn wir eine Gamestate haben, dann bau es im jframe
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		
		
		
		//g.fillRect(0, 0, width, height);
		
//		g.setColor(Color.red);
//		g.drawRect(10, 50, 50, 70);
//		g.setColor(Color.green);
//		g.fillRect(0, 0, 50, 70);
		

		
		// Anzeige ende
		bs.show();
		g.dispose();
	}
	
	// run() muss in der Klasse existieren die implements Runnable besitzt
	public void run() {
		
		
		init();
		
		int fps = 60;
		// In einer Sekunde befinden sich 1 billionen Nanosec
		// Wenn wir in Nanosec rechnen ist es viel präziser als in sekunden
		// timePerTick = Die maximale Anzahl an Sekunden in der unsere update / render sich wiederholen darf
		// System.nanoTime(); Gibt die aktuelle Zeit des Computers zurück, in nanosec
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		// GAME LOOP = so lange while = true, wird update + render immer wiederholt.
		while(running) {
			now = System.nanoTime();
			// now - lastTime gibt die Zeitdauer aus, die verstrichen ist seitdem wir die Zeile delta +=
			// das letzte mal ausgeführt haben
			// GIbt uns die Zeit raus die wir haben um update/render erneut auszuführen
			// delta gibt als aus, wann und wann nicht update / render ausgeführt wird
			delta += (now - lastTime)/ timePerTick; 
			timer += now - lastTime;
			lastTime = now;
			
			// wenn delta größer oder gleich 1 ist dann los
			if(delta >= 1) {
				
				update();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
	
		}
		
		stop();
	
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	// Wenn ich ein Thread starte oder stoppe brauche ich die start() / stop() synchronized
	public synchronized void start() {
		if(running) {
			return;
		}else {
			running = true;
			thread = new Thread(this);
			//start() startet die run()
			thread.start();
		}

		
	}
	
	public synchronized void stop() {
		if(!running) {
			return;
		}else {			
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
		}


		
	}

}
