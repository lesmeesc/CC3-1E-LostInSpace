// GameState that shows intro.

package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Manager.GameStateManager;
import Manager.Keys;

public class IntroState extends GameState {
	
	private BufferedImage intro; //declares image to intro
	
	private int alpha;
	private int ticks;
	
	private final int FADE_IN = 60;
	private final int LENGTH = 60;
	private final int FADE_OUT = 60;
	
	public IntroState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		ticks = 0;
		try { //gets the image from the resources 
			intro = ImageIO.read(getClass().getResourceAsStream("/Resources/Logo/logo.gif"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		handleInput();
		ticks++;//adds to ticks
		if(ticks < FADE_IN) { //it will fade in
			alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
		if(ticks > FADE_IN + LENGTH) {//will fade in and pic largen
			alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
			if(alpha > 255) alpha = 255;
		}
		if(ticks > FADE_IN + LENGTH + FADE_OUT) { //will fade in, largen and fade out 
			gsm.setState(GameStateManager.MENU); //then will go to the menu
		}
	}
	
	public void draw(Graphics2D g) { //draws the game panel
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
		g.drawImage(intro, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2, null);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) { //if enter is pressed will go to the menu
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}