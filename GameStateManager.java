// The GameStateManager does exactly what its
// name says. It contains a list of GameStates.
// It decides which GameState to update() and
// draw() and handles switching between different
// GameStates.

package Manager;

import java.awt.Graphics2D;

import GameState.GameOverState;
import GameState.GameState;
import GameState.IntroState;
import GameState.MenuState;
import GameState.PauseState;
import GameState.PlayState;


public class GameStateManager {
	
	private boolean paused; // boolean when game is paused
	private PauseState pauseState;
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	public static final int NUM_STATES = 4;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	public static final int GAMEOVER = 3;
	
	public GameStateManager() { //declares the game state at the start of the game		
		paused = false; //paused is false
		pauseState = new PauseState(this);
		
		gameStates = new GameState[NUM_STATES];
		setState(INTRO);
		
	}
	
	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if(i == INTRO) { //if the state is at the intro, into is played
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		else if(i == MENU) { // if state is menu, menu is disiplyed
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		}
		else if(i == PLAY) { //if the game is played, game is displayed
			gameStates[i] = new PlayState(this);
			gameStates[i].init();
		}
		else if(i == GAMEOVER) { // if the game is over
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		}
	}
	
	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	
	public void update() { //updates the gamestate if paused
		if(paused) {
			pauseState.update();
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}
	
	public void draw(Graphics2D g) {
            // draws the game state
		if(paused) {
			pauseState.draw(g);
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}
	
}
