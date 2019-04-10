// Contains a reference to the Player.
// Draws all relevant information at the
// bottom of the screen.

package HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entity.Spaceshipparts;
import Entity.Player;
import Main.GamePanel;
import Manager.Content;

public class Hud {
	
	private int yoffset;
	
	private BufferedImage bar;
	private BufferedImage spacesparts;
	private BufferedImage boat;
	private BufferedImage axe;
	
	private Player player;
	
	private int numParts;
	
	private Font font;
	private Color textColor; 
	
	public Hud(Player p, ArrayList<Spaceshipparts> d) {
		
		player = p;
		numParts = d.size();
		yoffset = GamePanel.HEIGHT;
		
		bar = Content.BAR[0][0];
		spacesparts = Content.PARTS[0][0];
		boat = Content.ITEMS[0][0];
		axe = Content.ITEMS[0][1];
		
		font = new Font("Arial", Font.PLAIN, 10);
		textColor = new Color(47, 64, 126);
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw hud
		g.drawImage(bar, 0, yoffset, null);
		
		// draw spacesparts bar
		g.setColor(textColor);
		g.fillRect(8, yoffset + 6, (int)(28.0 * player.numParts() / numParts), 4);
		
		// draw spacesparts amount
		g.setColor(textColor);
		g.setFont(font);
		String s = player.numParts() + "/" + numParts;
		Content.drawString(g, s, 40, yoffset + 3);
		if(player.numParts() >= 10) g.drawImage(spacesparts, 80, yoffset, null);
		else g.drawImage(spacesparts, 72, yoffset, null);
		
		// draw items
		if(player.hasBoat()) g.drawImage(boat, 100, yoffset, null);
		if(player.hasAxe()) g.drawImage(axe, 112, yoffset, null);
		
		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 85, 3);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, minutes + ":" + seconds, 85, 3);
		}
		
		
		
	}
	
}
