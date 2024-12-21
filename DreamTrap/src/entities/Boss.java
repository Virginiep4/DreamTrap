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
	private int xBlock= 50;
	private int yBlock= 20;
	private int movingXBlock;
	private int movingYBlock;

	public Boss(Character main) {
		super();
		setPosX(50*Screen.BLOCK_SIZE);
		setPosY(20*Screen.BLOCK_SIZE);
		this.moving = new MouvementAiles(this);
		this.main=main;
	}

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
	
	public void aimCharacter() {
		int characX = main.getPosX();
		int bossX = this.getPosX();
		int characY = main.getPosY();
		int bossY = this.getPosY();
		if (characX < bossX) {moving.left(true); moving.right(false);}
		if (characX > bossX) {moving.right(true); moving.left(false);}
		if (characX==bossX) {moving.right(false); moving.left(false);}
		if (characY > bossY) {moving.down(true); moving.up(false);}
		if (characY < bossY) {moving.up(true); moving.down(false);}
		if (characY==bossY) {moving.up(false); moving.down(false);}
	}
	
	public void update() {
		
		if (movingYBlock < Screen.BLOCK_SIZE) {
			movingYBlock+=Screen.BLOCK_SIZE;
			yBlock-=1;}
		if (movingYBlock > Screen.BLOCK_SIZE) {
			movingYBlock-=Screen.BLOCK_SIZE;
			yBlock+=1;}
		if (movingXBlock < Screen.BLOCK_SIZE) {
			movingXBlock+=Screen.BLOCK_SIZE;
			xBlock-=1;}
		if (movingXBlock > Screen.BLOCK_SIZE) {
			movingXBlock-=Screen.BLOCK_SIZE;
			xBlock+=1;
		}
		
		aimCharacter();
		if (moving.isUp()) {
			moving.yMovement(1);
			movingYBlock+= 1;
		}
		if (moving.isDown()) {
			moving.yMovement(-1);
			movingYBlock-= 1;
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
		return xBlock;
	}
	public int getmovingXBlock() {
		return movingXBlock;
	}

	public void setxBlock(int xBlock) {
		this.xBlock = xBlock;
	}

	public int getyBlock() {
		return yBlock;
	}
	public int getmovingYBlock() {
		return movingYBlock;
	}
	public void setyBlock(int yBlock) {
		this.yBlock = yBlock;
	}
}
