package entities;

import static utils.ImageImporter.importImg;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dreamTrap.Screen;
import mouvement.MouvementAiles;
public class Boss extends Entities {
	
	private BufferedImage[][] boss;
	private Rectangle hitbox;
	private int aniTick, aniIndex = 0, aniSpeed = 30;
	private Character main;
	public MouvementAiles moving;
	
	// Stock the block index where the boss is on the screen
	// (could be too far to be visible)
	private int xBlock = 0; // to change for spawn location modification
	private int movingXBlock;

	public Boss(Character main) {
		super();
		setPosX(xBlock * Screen.BLOCK_SIZE);
		setPosY(-(Screen.BLOCK_PER_HEIGHT - 5) * Screen.BLOCK_SIZE);
		this.moving = new MouvementAiles(this);
		this.main=main;
	}
	
	// Animations
	
	public BufferedImage[][] getBoss() {return boss;}
	public int getAniIndex() {return aniIndex;}

	/**
	 * Puts all the frame of character animation in each character array
	 */
	public void importEntity() {
		boss = new BufferedImage[2][]; // amount of different animations

		boss[0] = new BufferedImage[2];
		boss[0][0] = importImg("/boss.png");

		boss[1] = new BufferedImage[2];
		boss[1][0] = importImg("/bossLeft.png");
	}
	
	// Mouvements
	
	public void aimCharacter() {
		
		// Viser position sur y
		
		int characY = main.getPosY();
		int bossY = this.getPosY();
		if (characY - Screen.BLOCK_SIZE > bossY) {moving.down(true); moving.up(false);}
		if (characY - Screen.BLOCK_SIZE < bossY) {moving.up(true); moving.down(false);}
		if (characY - Screen.BLOCK_SIZE == bossY) {moving.up(false); moving.down(false);}
		
		// Viser le print du main sur x
		
		float characX = (float)(main.getPosX()) / (float)(Screen.BLOCK_SIZE) - (float)(main.getPosX() % Screen.BLOCK_SIZE) / (float)(Screen.BLOCK_SIZE);
		if (this.xBlock - characX > 4.0) {moving.left(true); moving.right(false);} // 4 * BLOCK_SIZE sur le print de main dans Screen
		if (this.xBlock - characX < 4.0) {moving.right(true); moving.left(false);}
		if (this.xBlock - characX == 4.0) {moving.left(false); moving.right(false);;}
	}
	
	public void update() {
		
		if (movingXBlock < Screen.BLOCK_SIZE) {
			movingXBlock+=Screen.BLOCK_SIZE;
			xBlock-=1;}
		if (movingXBlock > Screen.BLOCK_SIZE) {
			movingXBlock-=Screen.BLOCK_SIZE;
			xBlock+=1;
		}
		
		aimCharacter();
		if (moving.isUp()) {
			moving.yMovement(-1);
		}
		if (moving.isDown()) {
			moving.yMovement(1);
		}
		
		if (moving.isRight()) {
			moving.xMovement(1);
			movingXBlock+= 1;
		}
		if (moving.isLeft()) {
				moving.xMovement(-1);
				movingXBlock-= 1;
		}
		
		
	
	}

	public int getxBlock() {
		return (int)xBlock;
	}
	public int getmovingXBlock() {
		return movingXBlock;
	}

	public void setxBlock(int xBlock) {
		this.xBlock = xBlock;
	}
}
