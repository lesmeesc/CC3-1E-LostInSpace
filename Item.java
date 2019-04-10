// There are two types of items: Axe and boat.
// Upon collection, informs the Player
// that the Player does indeed have the item.
// makes use of the items, axe and boat
package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Item extends Entity{
	
	private BufferedImage sprite;//uses the sprite method
	private int type;
	public static final int BOAT = 0;
	public static final int AXE = 1;
	
	public Item(TileMap tm) { //gets the sprite from the image in its width and height
		super(tm);
		type = -1;
		width = height = 16;
		cwidth = cheight = 12;
	}
	
	public void setType(int i) {
		type = i;
		if(type == BOAT) {
			sprite = Content.ITEMS[1][0];//sets the boat to the item got
		}
		else if(type == AXE) {
			sprite = Content.ITEMS[1][1];//sets the axe to the item got
		}
	}
	
	public void collected(Player p) {
		if(type == BOAT) { //if the boat is collected go to method
			p.gotBoat();
		}
		if(type == AXE) { // if axe is colleted go to method
			p.gotAxe();
		}
	}
	
	public void draw(Graphics2D g) {
		setMapPosition(); //sets the position of the axe and boat to the map
		g.drawImage(sprite, x + xmap - width / 2, y + ymap - height / 2, null);
	}
	
}
