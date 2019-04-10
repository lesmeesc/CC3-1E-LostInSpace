// Simple class that plays animation
// once and is removed.

package Entity;

import java.awt.Graphics2D;

import Manager.Content;
import TileMap.TileMap;

public class Sparkle extends Entity {
	
	private boolean remove;
	
	public Sparkle(TileMap tm) {
		super(tm);
		animation.setFrames(Content.SPARKLE[0]);//gets the sprite sparkle
		animation.setDelay(5); // animates the parts to sparkle every 5 sec
		width = height = 16; //sets the width and height of the sparkle
	}
	
	public boolean shouldRemove() {
		return remove; //if the sparkle is to be removed
	}
	
	public void update() {
		animation.update(); // updates the animation
		if(animation.hasPlayedOnce()) remove = true;//if the part sparkles once then it won't again
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);//draws the sparkle to the parts
	}
	
}
