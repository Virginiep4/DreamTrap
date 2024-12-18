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

	public Boss() {
		super();
		this.moving = new MouvementAiles(this);
		main = Screen.character;
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
	
	public int toCharacterOnX() {
		int characX = main.getPosX();
		int move = (int)Math.sqrt((characX-characX)*(characX-characX));
		return move;
	}
	
	public int toCharacterOnY() {
		int characY = main.getPosY();
		int move = (int)Math.sqrt((characY-characY)*(characY-characY));
		move = move / 4;
		return move;
	}
	
	public void aimCharacter() {
		int characY = main.getPosY();
		int bossY = this.getPosX();
		if (characY > bossY) {moving.down(true); moving.up(false);}
		else if (characY < bossY) {moving.up(true); moving.down(false);}
		else if (characY==bossY) {moving.up(false); moving.down(false);}
	}
	
	public void update() {
		aimCharacter();
		
		if (moving.isUp()) {
			moving.yMovement(-toCharacterOnY());
		}
		if (moving.isDown()) {
			moving.yMovement(toCharacterOnY());
		}
		
		if (moving.isRight()) {
			moving.xMovement(toCharacterOnX());
		}
		if (moving.isLeft()) {
				moving.xMovement(-toCharacterOnX());
		}
	}
}
