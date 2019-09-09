package game.input;

import java.awt.event.MouseEvent;


import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.ui.UiManager;



// Mouse Manager ist in etwa wie der KEyManager aufgebaut, allerdings ein wenig Umfangreicher aufgrund des Listener
// Tutorial zu Rate gezogen

public class MouseManager implements MouseListener, MouseMotionListener{
	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UiManager uiManager;
	
	public MouseManager() {
		
	}
	
	public void setUiManager(UiManager uiManager) {
		this.uiManager = uiManager;
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Automatisch generierter Methodenstub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if(uiManager != null) {
			uiManager.onMouseMove(e);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Automatisch generierter Methodenstub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Automatisch generierter Methodenstub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Automatisch generierter Methodenstub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		}else if(e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		}else if(e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = false;
		}		
		if(uiManager != null) {
			uiManager.onMouseRelease(e);
		}
	}

}
