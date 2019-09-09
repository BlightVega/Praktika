package display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

// Erstellung des JFrame. Keine großartigen Codes außer der Nutzung von canvas für Pixelgenaue Anzeige

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
				
	}
	
	private void createDisplay() {
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		// Damit das Spiel auch aus dem RAM Geschlossen wird und nicht nur das optische Fenster
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		//JFrame fokused immer und eine Bewegung ist somit nicht ersichtlich. 
		// Mit diesen Befehl sollte es gehen
		canvas.setFocusable(false);
		
		// "Bild" zum "Rahmen" hinzufügen.
		frame.add(canvas);
		// pack stellt sicher, dass auch alles innerhalb der frame zu sehen sein wird
		frame.pack();
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
