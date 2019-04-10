// This class takes in an array of images.
// Calling getImage() gives you the appropriate
// image in the animation cycle.
//this animates the change of the images of the walking
//of character and the change of the color and position
// of the spaceship parts
package Entity;

import java.awt.image.BufferedImage;

public class Animation {
	
        //creates the array for the frames of the images
	private BufferedImage[] frames; 
        //declares an int regarding the currentframe
	private int currentFrame;
        //declares an int regarding the number of frame
	private int numFrames;
	//declares an int regarding  the count of the number of times played
	private int count;
       //declares an int regarding a delay of the animation
	private int delay;
	// declares an int of the num of times played
	private int timesPlayed;
	
	public Animation() {
		timesPlayed = 0;//starts the timesPlayed at 0
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames; //instatiates the frame
		currentFrame = 0; // starts the current frame at 0
		count = 0;// starts the count at 0
		timesPlayed = 0;// starts the timesPlayed at 0
		delay = 2;// starts the delay at 2
		numFrames = frames.length; 
                // declares that the num of frames is equal to the length of frames
	}
	
	public void setDelay(int i) { delay = i; }//method that sets the delay to i
	public void setFrame(int i) { currentFrame = i; }//method that sets the frame to i
	public void setNumFrames(int i) { numFrames = i; }//method that sets the no of frames to i
	
	public void update() { //updates the values
		
		if(delay == -1) return;//if the delay is -1 it returns 
		
		count++; // adds one to the count if delay is -1
		
		if(count == delay) {//if count = delay then 
			currentFrame++;// adds one to currentFrame
			count = 0;//count resets to 0
		}
		if(currentFrame == numFrames) { // if count = numframes
			currentFrame = 0; // current frame resets to 0
			timesPlayed++; // add one to times played
		}
		
	}
	
	public int getFrame() { return currentFrame; } //method getframe that returns current frame
	public int getCount() { return count; }  //method getcount that returns get counts
	public BufferedImage getImage() { return frames[currentFrame]; } 
        //gets the images using buffered reader and returns the  images int the frame based on the current frame
	public boolean hasPlayedOnce() { return timesPlayed > 0; } // boolean if the game is played once, timesplayed is greater than 0
	public boolean hasPlayed(int i) { return timesPlayed == i; } // returns a boolean 
	
}