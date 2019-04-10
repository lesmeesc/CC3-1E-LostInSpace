// Spaceshipparts class.
// May contain a list of tileChanges.
// These tileChanges are used to modify
// the tile map upon collection.

package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Manager.Content;
import TileMap.TileMap;

public class Spaceshipparts extends Entity {
	
	BufferedImage[] sprites;
	
	private ArrayList<int[]> tileChanges;
	
	public Spaceshipparts(TileMap tm) {
		//declares the width, height of the parts
		super(tm);
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		sprites = Content.PARTS[0];//gets the content of the parts
		animation.setFrames(sprites); //animates the parts to change every 10 secs
		animation.setDelay(10);
		
		tileChanges = new ArrayList<int[]>();//declares array to change the color of the parts
		
	}
	
	public void addChange(int[] i) {
		tileChanges.add(i);//adds the changes of the parts
	}
	public ArrayList<int[]> getChanges() {
		return tileChanges; //gets the array list of the parts
	}
	
	public void update() {
		animation.update();//updates the animation
	}
	
	public void draw(Graphics2D g) {
		super.draw(g); //draws the parts
	}
	
}
