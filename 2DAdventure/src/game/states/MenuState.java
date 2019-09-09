package game.states;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.entities.EntityManager;
import game.entities.creature.Player;
import game.entities.statics.Sign;
import game.gfx.Assets;
import game.gfx.ImageLoader;
import game.ui.ClickListener;
import game.ui.UiImageButton;
import game.ui.UiManager;

// Einstellungen, Anzeigen und Bearbeitung für das Hauptmenü

public class MenuState extends State {
	
	private UiManager uiManager;

	public MenuState(Handler handler) {
		
		super(handler);
		
		uiManager = new UiManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		
		
		uiManager.addObject(new UiImageButton(320, 50, 128, 64, Assets.haupt_menu, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(null);
				State.setState(handler.getGame().gameState);
				
			}}));
		
		uiManager.addObject(new UiImageButton(220, 250, 128, 64, Assets.button_start, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(null);
				State.setState(handler.getGame().gameState);
				
			}}));
		
		uiManager.addObject(new UiImageButton(420, 250, 128, 64, Assets.button_exit, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(null);
				System.exit(1);
				
			}}));
		
	}
	
	
	@Override
	public void tick() {

		uiManager.update();
		
		
//		System.out.println(handler.getMouseManager().getMouseX() + "    " + handler.getMouseManager().getMouseY());
		
	}
	
	@Override
	public void render(Graphics g) {
		
		uiManager.render(g);
		
	}
	
}
