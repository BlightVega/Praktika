package game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;
import game.items.Item;

public class Inventory {
	
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	
	// Inventar Alles auf das aktuelle inventar.png zugeschnitten
	// Neues inventar image = neue Daten
	// Hardcoding sollte vermieden werden.
	// Aufgrund von Zeitmanagement dazu entschieden
	
	private int invX = 64, invY = 48,
				invWidth = 512, invHeight = 384,
				invListCenterX = invX + 171,
				invListCenterY = invY + invHeight / 2 + 5,
				invListSpacing = 30;
	
	private int invImageX = 452, invImageY = 82,
				invImageWidth = 64, invImageHeight = 64;
	
	private int invCountX = 484, invCountY = 172;
	
	
	private int selectedItem = 0;
	
	
	
	
	public Inventory (Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		
		// Nur zum Testen
//		addItem(Item.woodItem.createNew(5));
//		addItem(Item.rockItem.createNew(5));
//
//		
	}
	
	
	// Überprüfung von Spieler die E Taste drückt um das Inventar zu öffnen
	// Danach die active deaktivieren, damit man auch wieder aus dem Inventar rauskommt
	
	// W und S für hoch und runter innerhalb des Inventar
	public void update() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		
		if(!active)
			return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem++;
		
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
		selectedItem = 0;
		
		
	}
	
	// Klasse um das Inventar auch anzeigen zu lassen
	// Mit -5 wird begonnen, da das Inventar vom Center aus 5 nach oben geht (ins minus) und 5 nach unten, ins plus
	
	public void render(Graphics g) {
		if(!active)
			return;
		
		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight,null);

		int len = inventoryItems.size();
		if(len == 0)
			return;
		
		for (int i = -5; i < 6; i++) {
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			if(i == 0) {					
			Text.drawString(g," --> " +  inventoryItems.get(selectedItem + i).getName() + " <-- ", invListCenterX, invListCenterY + i * invListSpacing,true, Color.GREEN, Assets.font28);
			}else {
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing,true, Color.WHITE, Assets.font28);

			}
		}
		
		Item item = inventoryItems.get(selectedItem);
		
		g.drawImage(item.getTexture(), invImageX, invImageY,  invImageWidth, invImageHeight,null);
		Text.drawString(g, Integer.toString(item.getCount()) , invCountX, invCountY, true, Color.white, Assets.font28);
		
	}
	
	//Inventar items hinzufügen (manuell)
	
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
	
	
	

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

}
