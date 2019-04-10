// The main playing GameState.
// Contains everything you need for gameplay:
// Player, TileMap, Diamonds, etc.
// Updates and draws all game objects.

package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Spaceshipparts;
import Entity.Item;
import Entity.Player;
import Entity.Sparkle;
import HUD.Hud;
import Main.GamePanel;
import Manager.Content;
import Manager.Data;
import Manager.GameStateManager;
import Manager.Keys;
import TileMap.TileMap;

public class PlayState extends GameState {
	
	// player
	private Player player;
	
	
	// tilemap
	private TileMap tileMap;
	
	// spaceshipparts
	private ArrayList<Spaceshipparts> spaceshipparts;
	
	// items
	private ArrayList<Item> items;
	
	// sparkles
	private ArrayList<Sparkle> sparkles;
	
	// camera position
	private int xsector;
	private int ysector;
	private int sectorSize; 
	
	// hud
	private Hud hud;
	
	// events
	private boolean blockInput;
	private boolean eventStart;
	private boolean eventFinish;
	private int eventTick;
	
	// transition box
	private ArrayList<Rectangle> boxes;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		// create lists
		spaceshipparts = new ArrayList<Spaceshipparts>();
		sparkles = new ArrayList<Sparkle>();
		items = new ArrayList<Item>();
		
		// load map
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Resources/Tilesets/testtileset.gif");
		tileMap.loadMap("/Resources/Maps/testmap.map");
		
		// create player
		player = new Player(tileMap);
		
		// fill lists
		populateParts();
		populateItems();
		
		// initialize player
		player.setTilePosition(17, 17);
		player.setTotalParts(spaceshipparts.size());
		
		// set up camera position
		sectorSize = GamePanel.WIDTH;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);
		
		// load hud
		hud = new Hud(player, spaceshipparts);
		
		// start event
		boxes = new ArrayList<Rectangle>();
		eventStart = true;
		eventStart();
			
	}
	
	private void populateParts() {
		//positions the parts to the tile map
		Spaceshipparts d;
		
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(20, 20);
		d.addChange(new int[] { 23, 19, 1 });
		d.addChange(new int[] { 23, 20, 1 });
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(12, 36);
		d.addChange(new int[] { 31, 17, 1 });
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(28, 4);
		d.addChange(new int[] {27, 7, 1});
		d.addChange(new int[] {28, 7, 1});
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(4, 34);
		d.addChange(new int[] { 31, 21, 1 });
		spaceshipparts.add(d);
		
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(28, 19);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(35, 26);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(38, 36);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(27, 28);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(20, 30);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(14, 25);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(4, 21);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(9, 14);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(4, 3);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(20, 14);
		spaceshipparts.add(d);
		d = new Spaceshipparts(tileMap);
		d.setTilePosition(13, 20);
		spaceshipparts.add(d);
		
	}
	
	private void populateItems() {

		if(Content.getItemMap() == ""){ //sets the items to the map
			placeItem(Item.AXE, 26, 37);
			placeItem(Item.BOAT, 12, 4);
		}else{
		String currLine;
		String[] data = new String[3];
		int item, x, y;
		
		try{ //places the item in accordance to the array
			FileReader input = new FileReader(Content.getItemMap());
			BufferedReader reader = new BufferedReader(input);
			
			while((currLine = reader.readLine())!= null){
				data = currLine.split(",");
				item = Integer.parseInt(data[0]);
				
				y = Integer.parseInt(data[1]);
				x = Integer.parseInt(data[2]);
				
				placeItem(item,x,y);
			}
				
			
			reader.close();
		}catch (IOException e){ //if there is an error
			e.printStackTrace();
		}
		
		}

		
	}
	
	private void placeItem(int type, int x, int y){
		Item item;
		item = new Item(tileMap);
		item.setType(type);
		item.setTilePosition(x, y);
		items.add(item);
	}
	
	
	public void update() {
		
		// check keys
		handleInput();
		
		// check events
		if(eventStart) eventStart();
		if(eventFinish) eventFinish();
		
		if(player.numParts() == player.getTotalParts()) {
			eventFinish = blockInput = true;
		}
		
		// update camera
		int oldxs = xsector;
		int oldys = ysector;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();
		
		if(oldxs != xsector || oldys != ysector) {
			//JukeBox.play("mapmove");
		}
		
		if(tileMap.isMoving()) return;
		
		// update player
		player.update();
		
		// update spaceshipparts
		for(int i = 0; i < spaceshipparts.size(); i++) {
			
			Spaceshipparts d = spaceshipparts.get(i);
			d.update();
			
			// player collects diamond
			if(player.intersects(d)) {
				
				// remove from list
				spaceshipparts.remove(i);
				i--;
				
				// increment amount of collected spaceshipparts
				player.collectedparts();
				
				// play collect sound
				//JukeBox.play("collect");
				
				// add new sparkle
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(d.getx(), d.gety());
				sparkles.add(s);
				
				// make any changes to tile map
				ArrayList<int[]> ali = d.getChanges();
				for(int[] j : ali) {
					tileMap.setTile(j[0], j[1], j[2]);
				}
				if(ali.size() != 0) {
				}
				
			}
		}
		
		// update sparkles
		for(int i = 0; i < sparkles.size(); i++) {
			Sparkle s = sparkles.get(i);
			s.update();
			if(s.shouldRemove()) {
				sparkles.remove(i);
				i--;
			}
		}
		
		// update items
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if(player.intersects(item)) {
				items.remove(i);
				i--;
				item.collected(player);
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(item.getx(), item.gety());
				sparkles.add(s);
			}
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw spaceshipparts
		for(Spaceshipparts d : spaceshipparts) {
			d.draw(g);
		}
		
		// draw sparkles
		for(Sparkle s : sparkles) {
			s.draw(g);
		}
		
		// draw items
		for(Item i : items) {
			i.draw(g);
		}
		
		// draw hud
		hud.draw(g);
		
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < boxes.size(); i++) {
			g.fill(boxes.get(i));
		}
		
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);//if escape is pressed it will pause
		}
		if(blockInput) return;
                //sets the movement of the player
		if(Keys.isDown(Keys.LEFT)) player.setLeft();
		if(Keys.isDown(Keys.RIGHT)) player.setRight();
		if(Keys.isDown(Keys.UP)) player.setUp();
		if(Keys.isDown(Keys.DOWN)) player.setDown();
		if(Keys.isPressed(Keys.SPACE)) player.setAction();
	}
	
	//===============================================
	
	private void eventStart() {
		eventTick++; //when the game starts
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				boxes.add(new Rectangle(0, i * 16, GamePanel.WIDTH, 16));
			}
		}
		if(eventTick > 1 && eventTick < 32) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					r.x -= 4;
				}
				else {
					r.x += 4;
				}
			}
		}
		if(eventTick == 33) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}
	
	private void eventFinish() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				if(i % 2 == 0) boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
				else boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
			}
		}
		if(eventTick > 1) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					if(r.x < 0) r.x += 4;
				}
				else {
					if(r.x > 0) r.x -= 4;
				}
			}
		}
		if(eventTick > 33) {
				Data.setTime(player.getTicks());
				gsm.setState(GameStateManager.GAMEOVER);
			}
	}
	
}
