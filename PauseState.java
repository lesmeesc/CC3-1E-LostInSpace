// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).

package GameState;

import java.awt.Graphics2D;

import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;

public class PauseState extends GameState {
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		//draws the string in the pause menu
		Content.drawString(g, "paused", 40, 30);
		
		Content.drawString(g, "arrow", 12, 76);
		Content.drawString(g, "keys", 16, 84);
		Content.drawString(g, ": move", 52, 80);
		
		Content.drawString(g, "space", 12, 96);
		Content.drawString(g, ": action", 52, 96);
		
		Content.drawString(g, "ESC:", 36, 112);
		Content.drawString(g, "return", 68, 108);
		Content.drawString(g, "to menu", 68, 116);
		
	}
	public void handleInput() {
		
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(false); //if pressed escape it will unpause 
			gsm.setState(GameStateManager.MENU); //and go to the menu again
		}
                if (Keys.isPressed(Keys.SPACE)){
                    gsm.setPaused(false); //if pressed space it will unpause the game
                    
                }
	}
	
}
