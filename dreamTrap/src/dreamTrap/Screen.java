package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;

import entities.Character;

public class Screen extends JPanel {
	private Character character;
	private entities.shop shop;
	private entities.backgroundd backgroundd;

	public Screen() {
		setScreenSize();
		character = new Character();
		shop=new entities.shop();
		backgroundd=new entities.backgroundd();
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		character.updateAnimationTick();
		shop.updateAnimationTick();
		backgroundd.updateAnimationTick();
	}
	
	public Character getCharacter() {
		return character;
	}
	public entities.shop getShop() {
		return shop;
	}
	public entities.backgroundd getBackgroundd() {
		return backgroundd;
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
		g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0 , 10 , null);
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