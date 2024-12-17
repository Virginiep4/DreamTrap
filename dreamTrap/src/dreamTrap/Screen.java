package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import entities.Background;
import entities.Character;
import entities.Shop;

public class Screen extends JPanel {
	private Character character;
	private Shop shop;
	private entities.Background background;
	
	public static final int BLOCK_SIZE = 32;
	public static final int BLOCK_PER_HEIGHT = 32;
	public static final int BLOCK_PER_WIDTH = 32;

	public Screen() {
		setScreenSize();
		character = new Character();
		shop = new Shop(character);
		background = new entities.Background();
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		character.updateAnimationTick();
		shop.updateAnimationTick();
		background.updateAnimationTick();
	}
	
	public Character getCharacter() {
		return character;
	}
	public Shop getShop() {
		return shop;
	}
	

	/**
	 * The function that will allow to draw on the created screen It is never called
	 * on the code because it is automatically done
	 *
	 * @param g you don't have to care about this argument because this is created
	 *          by the extensions used
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clean and allow to draw on the screen

		// could be optimized by loading all sprite on same image and use getSubimage()
		g.drawImage(background.getBackground()[Background.getCurrentAnimation()][background.getAniIndex()], 0 , 10 , null);
		g.drawImage(shop.getShop()[shop.getCurrentAnimation()][shop.getAniIndex()], 500 , 339 , null);
		g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()], 150 + (int) character.getPosX(), 600 + (int) character.getPosY(), null);
		
	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension(1920, 1080); // Screen resolution
		setPreferredSize(size);
	}    
}