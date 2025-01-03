package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.JoueurDAO;
import dao.progressionDAO;
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
	private progressionDAO progression;
	
	private ScoreScreen scoreScreen;
	private GameOverScreen gameOverScreen;
	private Welcome welcomeScreen;
	private Welcome2 welcomeScreen2;
	private JTextField welcomeText;
	
	 private boolean isWelcomeTextVisible; 
	// getters and setters
	public Character getCharacter() {
		return character;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public Screen(Game game) {
		setScreenSize();
		character=new Character(0,"",0,0);
		joueur=new JoueurDAO();
	    welcomeScreen = new Welcome(character);
	    welcomeScreen2 = new Welcome2();
	    levelManager = new WelcomeScreen(this);
        //levelManager = new LevelOne(this); 
       
		getPlayerName(game);
		addKeyListener(new KeyboardInputs(this));
		
		
		
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
		
			// if getcurrentanimation== 6 welcomeScreen.draw(g,character,levelManager);
		
			// if getcurrent animation == 7 getplayer welcomeScreen2.draw(g); et  et apres setcurrentanimation a 8 peux importe le chiffre faut juste quon definissent 
		
		
		//levelManager.draw(g);
		//welcomeScreen.draw(g,character,levelManager);
		welcomeScreen2.draw(g);
		
		/*g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
				levelManager.getxCharacterSpawn(), levelManager.getyCharacterSpawn() + (int) character.getPosY(), null);
		*/	
		
		/*if (progression.getWin()==0) {

			scoreScreen.drawScroreScreen(g);
		}*/
		
		//gameOverScreen.drawGameOverScreen(g,character,levelManager);
		
		
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
	
	public void getPlayerName(Game game) {
		 welcomeText = new JTextField();
		// x, y, width, height
		welcomeText.setPreferredSize(new Dimension(250,40));
		welcomeText.setBounds((int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 4), 250, 200, 100);
		this.add(welcomeText);
		this.revalidate();
		this.repaint();
		
	 welcomeText.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            
              String nom = welcomeText.getText();
              character = joueur.loadsave(nom);
             System.out.println(character);
             initializeScreens();
             isWelcomeTextVisible = false;
             welcomeText.setVisible(false);
             repaint();
             
             
         }
         
		
     });
	 isWelcomeTextVisible = true;
     welcomeText.setVisible(true);
	 
	}
	
	private void initializeScreens() {
        scoreScreen = new ScoreScreen(character);
        gameOverScreen = new GameOverScreen(character);
        
       
        
        
    }
	
	
}
