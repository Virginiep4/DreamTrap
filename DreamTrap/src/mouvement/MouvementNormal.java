package mouvement;

import entities.Entities;

public class MouvementNormal implements Mouvement {

	private Entities main;

	private boolean jumping = false; // true if the character is jumping
	private boolean right = false;
	private boolean left = false;
	private final static int RIGHT = 0;
	private final static int LEFT = 1;
	
	private int jumpingPhase = -1;
	private int MAX_JUMP_PHASE = 103;
	private final static int JUMP_DELAY = 2; // how many phase before changing position
	private final static int JUMP_HEIGHT_FACTOR = 15; // makes character jumps higher
	private double parablePos = -1; // parable position between -1 and 1
	
	// Getters
	
	public boolean isRight() {return this.right;}
	public boolean isLeft() {return this.left;}
	public boolean isJumping() {return this.jumping;}
	public int isInJumpingPhase() {return this.jumpingPhase;}
	
	// Setters
	
	public void right(boolean b) {this.right = b;}
	public void left(boolean b) {this.left = b;}
	public void jumping(boolean b) {
		jumping = b;
		if (b && jumpingPhase == -1)
			jumpingPhase++;
	}
	public void flying(boolean b) {}
	
	// Constructeur
	
	public MouvementNormal(Entities main) {
		this.main=main;
	}
	
	// Méthodes

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
	
	public void yMovement(int move) {
		
	}

	public void releaseJump() {
		if (jumpingPhase * 2 < 51)
			MAX_JUMP_PHASE = 51;
	}
	
	@Override
	public boolean isUp() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isDown() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isFlying() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void down(boolean b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void up(boolean b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean gotWings() {
		return false;
	}
	@Override
	public void falling(boolean b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isFalling() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
