package entities;

import static utils.ImageImporter.importImg;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dreamTrap.Game;
import dreamTrap.Screen;
import level.LevelManager;
import mouvement.MouvementAiles;

public class Boss extends Entities {

	private BufferedImage[][] boss;
	private int aniTick, aniIndex = 0, aniSpeed = 30;
	private Character main;
	public MouvementAiles moving;
	private int xBlock = 2;
	private int yBlock;
	private int movingXBlock;
	private int movingYBlock;
	private int currentAnimation = 0;
	boolean bossMoving = true;

	public void setCurrentAnimation(int currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	public int getCurrentAnimation() {
		return currentAnimation;
	}

	public Boss(Character main) {
		super();
		
		yBlock = Screen.BLOCK_PER_HEIGHT / 2; 
		setPosX(xBlock * Screen.BLOCK_SIZE);
		int yCharacterSpawn = Screen.BLOCK_PER_HEIGHT - main.getLevelManager().getyCharacterSpawn() / Screen.BLOCK_SIZE;
		setPosY((yCharacterSpawn - yBlock) * Screen.BLOCK_SIZE);
		
		this.moving = new MouvementAiles(this);
		this.main = main;
	}

	// Animations

	public BufferedImage[][] getBoss() {
		return boss;
	}

	public int getAniIndex() {
		return aniIndex;
	}

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
		if (characY > bossY) {
			moving.down(true);
			moving.up(false);
		}
		if (characY < bossY) {
			moving.up(true);
			moving.down(false);
		}
		if (characY == bossY) {
			moving.up(false);
			moving.down(false);
		}

		// Viser le print du main sur x

		float characX = (float) (main.getPosX()) / (float) (Screen.BLOCK_SIZE)
				- (float) (main.getPosX() % Screen.BLOCK_SIZE) / (float) (Screen.BLOCK_SIZE);
		if (this.xBlock - characX > 0) {
			moving.left(true);
			moving.right(false);
		} // 4 * BLOCK_SIZE sur le print de main dans Screen
		if (this.xBlock - characX < 0) {
			moving.right(true);
			moving.left(false);
		}
		if (this.xBlock - characX == 0) {
			moving.left(false);
			moving.right(false);
			;
		}
	}

	@Override
	public void update() {

			if (movingYBlock < Screen.BLOCK_SIZE) {
				movingYBlock += Screen.BLOCK_SIZE;
				yBlock -= 1;
			}
			if (movingYBlock > Screen.BLOCK_SIZE) {
				movingYBlock -= Screen.BLOCK_SIZE;
				yBlock += 1;
			}
			if (movingXBlock < Screen.BLOCK_SIZE) {
				movingXBlock += Screen.BLOCK_SIZE;
				xBlock -= 1;
			}
			if (movingXBlock > Screen.BLOCK_SIZE) {
				movingXBlock -= Screen.BLOCK_SIZE;
				xBlock += 1;
			}

			aimCharacter();
			if (moving.isUp()) {
				moving.yMovement(-1);
				movingYBlock -= 1;
			}
			if (moving.isDown()) {
				moving.yMovement(1);
				movingYBlock += 1;
			}

			if (moving.isRight()) {
				moving.xMovement(2);
				movingXBlock += 2;
			}
			if (moving.isLeft()) {
				moving.xMovement(-2);
				movingXBlock -= 2;
			}

	}

	public int getxBlock() {
		return (int) xBlock;
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
