package mouvement;

public interface Mouvement {
	void xMovement(int move);
	void yMovement(int move);
	void jumping(boolean b);
	boolean isJumping ();
	void flying(boolean b);
	void jumpAnimation();
	boolean isUp();
	boolean isDown();
	boolean isRight();
	boolean isLeft();
	int isInJumpingPhase();
	boolean isFlying();
	void releaseJump();
	void left(boolean b);
	void right(boolean b);
	void down(boolean b);
	void up(boolean b);
	boolean isFalling();
	boolean gotWings();
	void falling(boolean b);
}	
