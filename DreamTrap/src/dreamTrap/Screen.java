package dreamTrap;

import java.awt.Dimension;

public class Screen extends JPanel {
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 20;
	public final static int BLOCK_PER_HEIGHT = 11;
	private final static float SCALE = 1f;
	
	private Character character;
	private Boss boss;
	private LevelManager levelManager;
	
	//getters and setters
	public Character getCharacter() {
		return character;
	}
	
	public Boss getBoss() {
	    return boss;

	public Screen() {
		setScreenSize();
		character = new Character();
		boss = new Boss(character);
		levelManager = new LevelManager(this);
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		character.update();
		boss.update();
	}

	
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
		
		//levelmanager.draw(g);
		g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()], 150 + (int) character.getPosX(), 600 + (int) character.getPosY(), null);
		character.drawHitbox(g);
		

	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension((int) (BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),
				(int) (BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE)); // Screen resolution
		setPreferredSize(size);
		
	}
	
	public Character getCharacter() {
		return character;
	}
}
