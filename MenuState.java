// The main menu GameState.
package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;

public class MenuState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage spaceshipparts;
	
       
	private int currentOption = 0;
	private String[] options = { //loads the options showed
		"START",
		"QUIT"       
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.MENUBG[0][0]; //gets the menu background image
		spaceshipparts = Content.PARTS[0][0]; // gets the spaceship parts image
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		//draws the image
		g.drawImage(bg, 0, 0, null);
		
                //draws the string options
		Content.drawString(g, options[0], 44, 75);
		Content.drawString(g, options[1], 44, 90);
		
		if(currentOption == 0) g.drawImage(spaceshipparts, 25, 71, null); 
                //if 1st option the spaceship parts will go to the first one
		else if(currentOption == 1) g.drawImage(spaceshipparts, 25, 86, null);
		   //if 2nd option the spaceship parts will go to the 2nd one
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			currentOption++;//if pressed down the option will add one
		}
		if(Keys.isPressed(Keys.UP) && currentOption > 0) {
			currentOption--; // if pressed up the option will minus one
		}
		if(Keys.isPressed(Keys.ENTER)) {
			selectOption(); //if pressed enter it will select option
		}
	}
	
	private void selectOption() {
		if(currentOption == 0) {
			gsm.setState(GameStateManager.PLAY);//if option one is pressed the game wil play
		}
		if(currentOption == 1) {
			System.exit(0); //if option two is pressed the game will exit
		}
	}
	
}
