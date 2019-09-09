package game.states;

import java.awt.Graphics;

import game.Game;
import game.Handler;

public abstract class State {
	
	
	//Gamestate "Manager" 
	private static State currentState = null;
	
	public static void setState (State state) {
		currentState = state;
	}
	public static State getState() {
		return currentState;
	}
	
	//Beide Klassen müssen in jeder neu erstellten State Klasse vorhanden sein und auch geöffnet
	
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	

}
