// The only subclass the fully utilizes the
// Entity superclass (no other class requires
// movement in a tile based map).
// Contains all the gameplay associated with
// the Player.
package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Manager.Content;
import TileMap.TileMap;

public class Player extends Entity {
	
	// sets the player's sprite to its appropriate title
	private BufferedImage[] downSprites; //when going down
	private BufferedImage[] leftSprites; // when going left
	private BufferedImage[] rightSprites; // when going right
	private BufferedImage[] upSprites; // when going up
	private BufferedImage[] downBoatSprites; // when player has a boat and goes down
	private BufferedImage[] leftBoatSprites; // when players has a boat and goes left
	private BufferedImage[] rightBoatSprites; // when players has a boat and goes right
	private BufferedImage[] upBoatSprites; // when players has a boat and goes up
	
	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWNBOAT = 4;
	private final int LEFTBOAT = 5;
	private final int RIGHTBOAT = 6;
	private final int UPBOAT = 7;
	
	// gameplay
	private int numparts;
	private int totalparts;
	private boolean hasBoat;
	private boolean hasAxe;
	private boolean onWater;
	private long ticks;
	
	public Player(TileMap tm) { //sets the images declared above to the sprite image's position and location
		
		super(tm);
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		moveSpeed = 2;
		
		numparts = 0;
		
		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];
		downBoatSprites = Content.PLAYER[4];
		leftBoatSprites = Content.PLAYER[5];
		rightBoatSprites = Content.PLAYER[6];
		upBoatSprites = Content.PLAYER[7];
		
		animation.setFrames(downSprites);
		animation.setDelay(10);
		
	}
	
	private void setAnimation(int i, BufferedImage[] bi, int d) {//sets the animation in place
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}
	
	public void collectedparts() { numparts++; }//when the parts are collected the num adds one
	public int numParts() { return numparts; }//returns the num of parts
	public int getTotalParts() { return totalparts; } //returns toatal parts collected
	public void setTotalParts(int i) { totalparts = i; }//declares i to totalparts
	
	public void gotBoat() { hasBoat = true; tileMap.replace(22, 4); }//when boat is collected replaced by normal(land) tile
	public void gotAxe() { hasAxe = true; } //when axe is collected, item axe can now be used
	public boolean hasBoat() { return hasBoat; }//returns boolean if players has collected the boat
	public boolean hasAxe() { return hasAxe; }//returns boolean if players has collected the axe
	
	// Used to update time.
	public long getTicks() { return ticks; }
	
	// Keyboard input. Moves the player.
	public void setDown() {
		super.setDown();
	}
	public void setLeft() {
		super.setLeft();
	}
	public void setRight() {
		super.setRight();
	}
	public void setUp() {
		super.setUp();
	}
	
	// Keyboard input.
	// If Player has axe, dead trees in front
	// of the Player will be chopped down.
	public void setAction() {
		if(hasAxe) {
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 21) {
				tileMap.setTile(rowTile - 1, colTile, 1);
				//sets the action if it has axe, then the player can now chop it down
			}
			if(currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
				tileMap.setTile(rowTile + 1, colTile, 1);
			
			}
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
				tileMap.setTile(rowTile, colTile - 1, 1);
				
			}
			if(currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
				tileMap.setTile(rowTile, colTile + 1, 1);
				
			}
		}
	}
	
	public void update() {
		
		ticks++;
		
		// check if on water 
		boolean current = onWater;
		if(tileMap.getIndex(ydest / tileSize, xdest / tileSize) == 4) {
			onWater = true;
		}
		else {
			onWater = false;
		}
		// if going from land to water
		if(!current && onWater) {
		}
		
		// set animation
		if(down) {
			if(onWater && currentAnimation != DOWNBOAT) {
				setAnimation(DOWNBOAT, downBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, 10);
			}
		}
		if(left) {
			if(onWater && currentAnimation != LEFTBOAT) {
				setAnimation(LEFTBOAT, leftBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, 10);
			}
		}
		if(right) {
			if(onWater && currentAnimation != RIGHTBOAT) {
				setAnimation(RIGHTBOAT, rightBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, 10);
			}
		}
		if(up) {
			if(onWater && currentAnimation != UPBOAT) {
				setAnimation(UPBOAT, upBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != UP) {
				setAnimation(UP, upSprites, 10);
			}
		}
		
		// update position
		super.update();
		
	}
	
	// Draw Player.
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}