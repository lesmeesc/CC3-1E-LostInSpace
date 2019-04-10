// Congratulations for finishing the game.
// Gives you a rank based on how long it took
// you to beat the game.

// Under two minutes = Speed Demon
// Under three minutes = Spaceman
// Under four minutes = Beginner
// Four minutes or greater = Space Idiot

package GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import Manager.Content;
import Manager.Data;
import Manager.GameStateManager;
import Manager.Keys;

public class GameOverState extends GameState {
	
	private Color color; //declares the variable color
	
	private int rank; // declares int rank
	private long ticks; // declares the tick or time
	
	public GameOverState(GameStateManager gsm) {
		super(gsm); //gets variable from the gamestate class
	}
	
	public void init() {
		color = Color.black; //declares color to black
		ticks = Data.getTime(); // gets the time
		if(ticks < 3600) rank = 1; //if the time is less than 3600 then rank is one
		else if(ticks < 5400) rank = 2;//if the time is less than 5400 then rank is 2
		else if(ticks < 7200) rank = 3;//if the time is less than 7200 then rank is 3
		else rank = 4;//if the time is more than 7200 then rank is 4
	}
	
	public void update() {handleInput();}
	
	public void draw(Graphics2D g) {
		
		g.setColor(color);//sets color to black
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2); //fills the gamepanel with black rect
		
		Content.drawString(g, "finish time", 20, 36);//draws the strinf
		
		int minutes = (int) (ticks / 1800); //sets the tick to convert to minutes
		int seconds = (int) ((ticks / 30) % 60); // convert the tick to secondes
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 44, 48);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 44, 48);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 44, 48);
			else Content.drawString(g, minutes + ":" + seconds, 44, 48);
		}
		
		Content.drawString(g, "rank", 48, 66);//draws the ranks as said above
		if(rank == 1) Content.drawString(g, "speed demon", 20, 78);
		else if(rank == 2) Content.drawString(g, "spaceman", 24, 78);
		else if(rank == 3) Content.drawString(g, "beginner", 32, 78);
		else if(rank == 4) Content.drawString(g, "space idiot", 20, 78);
		
		Content.drawString(g, "press any key", 12, 110); //draws string if player want to start again
		
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {//if pressed enter then return to the menu
			gsm.setState(GameStateManager.MENU);
                }
	}
	
}