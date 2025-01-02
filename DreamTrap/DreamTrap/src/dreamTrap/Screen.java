package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.JoueurDAO;
import entities.Character;
import entities.Progression;
import inputs.KeyboardInputs;
import level.GameOverScreen;
import level.LevelManager;
import level.LevelOne;
import level.ScoreScreen;
import level.Welcome;
import level.Welcome2;
import level.WelcomeScreen;

public class Screen extends JPanel {
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 24;
	public final static int BLOCK_PER_HEIGHT = 13;
	private final static float SCALE = 1f;

	private Character character;
	private LevelManager levelManager;
	private JoueurDAO joueur;
	Progression progression;
	private ScoreScreen scoreScreen;
	private GameOverScreen gameOverScreen;
	private Welcome welcomeScreen;
	private Welcome2 welcomeScreen2;
	
	// getters and setters
	public Character getCharacter() {
		return character;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public Screen(Progression prog ) {
		setScreenSize();
		joueur=new JoueurDAO();
		character = joueur.loadsave();
		 progression=prog;
		scoreScreen=new ScoreScreen(character);
		gameOverScreen=new GameOverScreen(character);
		welcomeScreen=new Welcome(character);
		welcomeScreen2=new Welcome2(character);
		//levelManager = new WelcomeScreen(this);
		levelManager = new LevelOne(this);
		addKeyListener(new KeyboardInputs(this));
		getPlayerName();
	}

	public void updateGame() {
		character.update();
		levelManager.update();
	}

	/**
	 * The function that will allow to draw on the created screen It is never called
	 * on the code because it is automatically done
	 *
	 * @param g you don't have to care about this argument because this is created
	 *          by the extensions used
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // constructor of Jpanel clean and allow to draw on the screen

		// paintComponent is called when Jpanel is created
		// could be optimized by loading all sprite on same image and use getSubimage()
		
		
		
		levelManager.draw(g);
		g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
				levelManager.getxCharacterSpawn(), levelManager.getyCharacterSpawn() + (int) character.getPosY(), null);
				
		System.out.println("Screen win: "+progression.getWin());
		/*if (progression.getWin()==0) {

			scoreScreen.drawScroreScreen(g);
		}*/
		
		//gameOverScreen.drawGameOverScreen(g,character,levelManager);
		welcomeScreen2.drawGameOverScreen(g, character, levelManager);
		
		
	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension((int) (BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),
				(int) (BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE)); // Screen resolution
		setPreferredSize(size);

	}
	public void setTime(int tmp) {
		scoreScreen.setTotalTimeElapsed(tmp);
	}
	
	public void getPlayerName() {
		JTextField welcomeText = new JTextField();
		// x, y, width, height
		welcomeText.setPreferredSize(new Dimension(250,40));
		welcomeText.setBounds((int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 4), 250, 200, 100);
		this.add(welcomeText);
			
	}

	
	
}
