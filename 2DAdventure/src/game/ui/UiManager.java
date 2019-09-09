package game.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import game.Handler;

public class UiManager {

	private Handler handler;
	private ArrayList<UiObject> objects; 
	
	
	public UiManager (Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UiObject>();
		
	}
	
	
	public void update() {
		for(UiObject o : objects) {
			o.update();
		}
	}
	
	public void render(Graphics g) {
		for(UiObject o : objects) {
			o.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		for(UiObject o : objects) {
			o.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		for(UiObject o : objects) {
			o.onMouseRelease(e);
		}
	}
	
	public void addObject(UiObject o) {
		objects.add(o);
	}
	
	public void removeObject(UiObject o) {
		objects.remove(o);
	}


	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public ArrayList<UiObject> getObjects() {
		return objects;
	}


	public void setObjects(ArrayList<UiObject> objects) {
		this.objects = objects;
	}
	
}
