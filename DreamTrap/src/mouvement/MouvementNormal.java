package mouvement;

import entities.Entities;

public class MouvementNormal implements Mouvement {

	private Entities main;

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
	
	public boolean getMovingRight() {return this.movingRight;}
	public boolean getMovingLeft() {return this.movingLeft;}
	public boolean getJumping() {return this.jumping;}
	public int getJumpingPhase() {return this.jumpingPhase;}
	
	public MouvementNormal(Entities main) {
		this.main=main;
	}

	/**
	 * Where the jump of the character is handled
	 */
	public void jumpAnimation() {
		// ascending phase for the first half of jump phases
		if ((jumpingPhase < (MAX_JUMP_PHASE / 2)) && (jumpingPhase % JUMP_DELAY == 0)) {
			jumpingPhase++;
			main.setPosY(main.getPosY() - (int)(parablePos * parablePos * JUMP_HEIGHT_FACTOR));
			parablePos += 2/ MAX_JUMP_PHASE; // 2 because range between -1 and 1
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
			main.setPosY(main.getPosY() + (int)(parablePos * parablePos * JUMP_HEIGHT_FACTOR));
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
	public void xMovement(int move) {
		main.setPosX(main.getPosX()+move);
		if (move < 0) {
			main.setCurrentAnimation(LEFT);
			left(true);
		}
			
		else {
			main.setCurrentAnimation(RIGHT);
			right(true);
		}
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
	
	public void yMovement() {
		// Pas à faire à priori
		
	}
}
