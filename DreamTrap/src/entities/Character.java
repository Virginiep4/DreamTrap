package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utils.ImageImporter.importImg;

public class Character extends Entities {
	private BufferedImage[][] character;
	private int currentAnimation = 0;

	private Rectangle hitbox;

	// aniTick is current tick, aniIndex is the current sub-animation, aniSpeed is
	// amount of Game.updates(ticks) before changing animation
	private int aniTick, aniIndex = 0, aniSpeed = 30;
	private boolean jumping = false; // true if the character is jumping
	private int jumpingPhase = -1;
	private int MAX_JUMP_PHASE = 103;
	private final static int JUMP_DELAY = 2; // how many phase before changing position
	private final static int JUMP_HEIGHT_FACTOR = 15; // makes character jumps higher
	private double parablePos = -1; // parable position between -1 and 1
	private boolean movingRight = false;
	private boolean movingLeft = false;

	private final static int RIGHT = 0;
	private final static int LEFT = 1;

	public Character() {
		super();
	}
	
	// getters + setters
	public BufferedImage[][] getCharacter() {
		return character;
	}
	public int getCurrentAnimation() {
		return currentAnimation;
	}
	public int getAniIndex() {
		return aniIndex;
	}

	/**
	 * Puts all the frame of character animation in each character array
	 */
	public void importEntity() {
		character = new BufferedImage[2][]; // amount of different animations

		character[0] = new BufferedImage[2];
		character[0][0] = importImg("/powder.png");
		character[0][1] = importImg("/powderPlaned.png");

		character[1] = new BufferedImage[2];
		character[1][0] = importImg("/powderLeft.png");
		character[1][1] = importImg("/powderPlanedLeft.png");
	}

	/**
	 * Where the animation of the character are handled
	 */
	public void update() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			// two cases : character is jumping or basic animation
			if (!jumping && (jumpingPhase == -1)) {
				aniIndex = ++aniIndex % character.length; // Iterates through all the images in screen.character
				aniTick = 0;
			} else {
				jumpAnimation();
			}
		}

		if (movingRight) {
			// i hope we never reach  2048 * 64...
			xMovement(2);
		}
		if (movingLeft) {
			if (posX > 63) // prevent outOfBounds
				xMovement(-2);
		}
	}

	/**
	 * Where the jump of the character is handled
	 */
	public void jumpAnimation() {
		// ascending phase for the first half of jump phases
		if ((jumpingPhase < (MAX_JUMP_PHASE / 2)) && (jumpingPhase % JUMP_DELAY == 0)) {
			jumpingPhase++;
			posY -= parablePos * parablePos * JUMP_HEIGHT_FACTOR;
			parablePos += 2 / MAX_JUMP_PHASE; // 2 because range between -1 and 1
		}
		
		// last phase : jump is over
		else if (jumpingPhase == MAX_JUMP_PHASE) { // jump is over
			jumpingPhase = -1;
			parablePos = -1;
			MAX_JUMP_PHASE = 103;
		}

		// descending phase 
		else if (jumpingPhase % JUMP_DELAY == 0) {
			jumpingPhase++;
			posY += parablePos * parablePos * JUMP_HEIGHT_FACTOR;
			parablePos += 2 / MAX_JUMP_PHASE; // 2 because range between -1 and 1
		}
		
		// when jumpingPhase is not equal to 0 with mod JUMP_DELAY
		else {
			jumpingPhase++;
			parablePos += 2 / MAX_JUMP_PHASE;
		}
	}

	/**
	 * Make the character move
	 *
	 * @param move is the amount of pixels that move will be done and the sign
	 *             determine the direction
	 */
	public void xMovement(double move) {
		posX += move;
		if (move < 0)
			currentAnimation = LEFT;
		else
			currentAnimation = RIGHT;
	}

	/**
	 * Allow to enter the jumping animation in updateCharacAnimationTick()
	 */
	public void jump(boolean b) {
		jumping = b;
		if (b && jumpingPhase == -1)
			jumpingPhase++;
	}
	
	public void releaseJump() {
		if (jumpingPhase * 2 < 51)
			MAX_JUMP_PHASE = 51;
	}

	/**
	 * Allow to enter the right moving animation in updateCharacAnimationTick()
	 */
	public void right(boolean b) {
		movingRight = b;
	}

	/**
	 * Allow to enter the left moving animation in updateCharacAnimationTick()
	 */
	public void left(boolean b) {
		movingLeft = b;
	}
}
