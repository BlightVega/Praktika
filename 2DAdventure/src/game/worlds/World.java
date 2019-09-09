package game.worlds;

import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.entities.EntityManager;
import game.entities.creature.Player;
import game.entities.statics.Rock;
import game.entities.statics.Sign;
import game.entities.statics.Tree;
import game.items.ItemManager;
import game.tiles.Tile;
import game.utils.Utils;

public class World {
	
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	// int array? Tiles sind in IDS gespeichert
	private int[][] tiles;
	
	
	private EntityManager entityManager;
	
	private ItemManager itemManager;
	
	

	
	
	public World(Handler handler, String path) {
		

		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		
		itemManager = new ItemManager(handler);


		//Entities hinzufügen
		
		entityManager.addEntity(new Tree(handler, 100, 250));
		entityManager.addEntity(new Tree(handler, 100, 450));
		entityManager.addEntity(new Tree(handler, 100, 650));
		entityManager.addEntity(new Tree(handler, 100, 850));
		
		
		entityManager.addEntity(new Rock(handler, 200, 250));
		entityManager.addEntity(new Rock(handler, 200, 450));
		entityManager.addEntity(new Rock(handler, 200, 650));
		entityManager.addEntity(new Rock(handler, 200, 850));
		
		entityManager.addEntity(new Sign(handler, 450, 250));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setX(spawnY);
		
	}
	
	public void update() {
		itemManager.update();
		entityManager.update();
		
	}
	
	public void render(Graphics g) {
		
		//Wir wollen nur das die Objekte gerendert werden die für den Spieler auch sichtbar sind
		// am Ende +1 und es wird mehr ersichtlicher
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for (int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				
			}
		}
		
		//ITEMS
		itemManager.render(g);
		
		
		//*
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		// Splittet das file nach jeder Leerstelle
		// Spieler und gesamtgröße der map
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		
		//Worlddata
		tiles = new int[width][height];
		for(int y = 0; y <  height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}
