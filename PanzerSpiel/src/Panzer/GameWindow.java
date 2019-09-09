package Panzer;

import java.awt.Dimension;
import java.awt.event.ActionEvent; //Erkennung von Dr�cken der Tasten
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter; // Erkennung von Bewegungen / Aktionen im Spielfenster
import java.awt.event.WindowEvent;

import javax.swing.JFrame; // GFrafische Oberfl�che
import javax.swing.JMenu; // Men� 
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GameWindow extends JFrame{

	private final GamePanel panzerGamePanel; // Er�ffnung des Spielfenster samt seiner Objekte und Eintragungen sowie Pr�ferenzen
	private Button button1;
	private Button button2;
	
	public GameWindow() {
		this.panzerGamePanel = new GamePanel();
		
	//JPanel testPanel = new JPanel();
	//testPanel.setPreferredSize(new Dimension(600, 400));
	
	registerWindowListener(); // Aktivieren der Klasse in Zeile 42
	createMenu(); // Aufrufen des Men�s im Spielfenster
	
	add(panzerGamePanel);
	pack(); // Sorgt daf�r, dass die Dimension in Zeile 25 auch wirklich gesetzt wird
	
	setTitle("PanzerHQ");
	setLocation(50, 50);
	setResizable(false);
	
	setVisible(true);
	

	

	}
	
	
	private void registerWindowListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) { // Wenn das Spielfenster �ber das X geschlossen wird funktioneirts auch
				System.exit(0);
			}
			@Override
			public void windowDeactivated(WindowEvent e) { // Spiel Pausieren
				panzerGamePanel.pauseGame();
			}
			@Override
			public void windowActivated(WindowEvent e) { // Spiel fortsetzen
				panzerGamePanel.continueGame();
			}
		});
	}
	
	
	
	
	private void createMenu() { // Hier erstelle ich ein Optionsmen�, erst einmal willk�rlich. In der GameWindow Klasse wirds ge�ffnet
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		
		JMenu fileMenu = new JMenu("Datei");
		JMenu gameMenu = new JMenu("Spielen");
		JMenu prefMenu = new JMenu("Einstellungen");
		
	    menuBar.add(fileMenu);        
	    menuBar.add(gameMenu);        
	    menuBar.add(prefMenu);
	    
	    addFileMenuItems(fileMenu);
	    addGameMenuItems(gameMenu);
	    addPrefMenuItems(prefMenu);
	   
	}
	
	private void addFileMenuItems(JMenu fileMenu) { // Hier erstelle ich ein Untermen� im Hauptmen�
		
		JMenuItem quitItem = new JMenuItem("Quit"); // Wir nennen das Untermen� exit
		fileMenu.add(quitItem);
		
		
		quitItem.addActionListener(new ActionListener() { // Hier befehlen wir beim dr�cken ein beenden des Spiels
			 @Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void addGameMenuItems(JMenu gameMenu) {
	    JMenuItem pauseItem = new JMenuItem("Pause");
	    gameMenu.add(pauseItem);        
	    pauseItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            panzerGamePanel.pauseGame();
	        }
	    });
	        
	    JMenuItem continuetItem = new JMenuItem("Continue");
	    gameMenu.add(continuetItem);       
	    continuetItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {                
	            panzerGamePanel.continueGame();
	        }
	    });
	        
	    gameMenu.addSeparator();
	    JMenuItem restartItem = new JMenuItem("Restart");
	    gameMenu.add(restartItem);       
	    restartItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {                
	            panzerGamePanel.restartGame();
	        }
	    });
	}
	
	private void addPrefMenuItems(JMenu prefMenu) {
        
	    JMenu submenu = new JMenu("Level W�hlen");
	    prefMenu.add(submenu);

	    JMenuItem menuItem = new JMenuItem("Stein Gebirge");
	    submenu.add(menuItem);
	    menuItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            panzerGamePanel.setBackgroundImage(0);
	            repaint();
	        }
	    });

	    menuItem = new JMenuItem("Schneelandschaft");
	    submenu.add(menuItem);
	    menuItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            panzerGamePanel.setBackgroundImage(1);
	            repaint();
	        }
	    });
	        
	    menuItem = new JMenuItem("W�ste");
	    submenu.add(menuItem);
	    menuItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            panzerGamePanel.setBackgroundImage(2);
	            repaint();
	        }
	    });
	}
	

	
	
}
