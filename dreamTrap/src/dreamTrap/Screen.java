package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import level.LevelManager;
import entities.Character;
import entities.door;

public class Screen extends JPanel {
	public final static int BLOCK_SIZE = 32;
	public final static int BLOCK_PER_WIDTH = 40;
	public final static int BLOCK_PER_HEIGHT = 22;
	private final static float SCALE = 1f;
	
	private LevelManager levelManager;
	private Character character;
	private entities.shop shop;
	private entities.backgroundd backgroundd;
	private static entities.door door0;
	private static entities.door door1;
	private static entities.door door2;
	private static entities.door door3;

	public Screen() {
		setScreenSize();
		levelManager = new LevelManager(this);
		character = new Character();
		shop=new entities.shop();
		backgroundd=new entities.backgroundd();
		door0=new entities.door(1400, 570,1 );
		door1=new entities.door(300, 570,0 );
		door2=new entities.door(700, 200,0 );
		door3=new entities.door(1100, 570,0 );
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		character.updateAnimationTick();
		shop.updateAnimationTick();
		backgroundd.updateAnimationTick();
		getDoor0().updateAnimationTick();
		door1.updateAnimationTick();
		door2.updateAnimationTick();
		door3.updateAnimationTick();
		
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
		if (backgroundd.getCurrentAnimation()==1) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0 , 10 , null);
			g.drawImage(getDoor0().getDoor()[getDoor0().getCurrentAnimation()][getDoor0().getAniIndex()], getDoor0().getPlaceX(), getDoor0().getPlaceY() , null);
			g.drawImage(shop.getShop()[shop.getCurrentAnimation()][shop.getAniIndex()], 500 , 339 , null);
			g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()], 150 + (int) character.getPosX(), 600 + (int) character.getPosY(), null);
		}
		if (backgroundd.getCurrentAnimation()==2) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0 , 10 , null);
		}
		
		if (backgroundd.getCurrentAnimation()==3) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0 , 10 , null);
			g.drawImage(door1.getDoor()[door1.getCurrentAnimation()][door1.getAniIndex()], door1.getPlaceX(), door1.getPlaceY() , null);
			g.drawImage(door2.getDoor()[door2.getCurrentAnimation()][door2.getAniIndex()], door2.getPlaceX(), door2.getPlaceY() , null);
			g.drawImage(door3.getDoor()[door3.getCurrentAnimation()][door3.getAniIndex()], door3.getPlaceX(), door3.getPlaceY() , null);
			g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()], 150 + (int) character.getPosX(), 600 + (int) character.getPosY(), null);
		}
		if (backgroundd.getCurrentAnimation()==4) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0 , 10 , null);
			levelManager.draw(g);
			g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
					150, (BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE + character.getPosY() - 40, null);
		}
		if (backgroundd.getCurrentAnimation()==5) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0 , 10 , null);
			levelManager.draw(g);
			g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
					150, (BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE + character.getPosY() - 40, null);
		}
		if (backgroundd.getCurrentAnimation()==6) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0 , 10 , null);
			levelManager.draw(g);
			g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
					150, (BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE + character.getPosY() - 40, null);
		}
		
	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension(1920, 1080); // Screen resolution
		setPreferredSize(size);
	}

	public static entities.door getDoor0() {
		return door0;
	}

	public static door getDoor1() {
		return door1;
	}  
	
	public static door getDoor2() {
		return door2;
	} 
	public static door getDoor3() {
		return door3;
	} 
	
}