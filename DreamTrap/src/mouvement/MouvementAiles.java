package mouvement;

import entities.Entities;

public class MouvementAiles implements Mouvement {
	
	private Entities main;

	private boolean flying = false; // true if the character is jumping
	private boolean right = false;
	private boolean left = false;
	private final static int RIGHT = 0;
	private final static int LEFT = 1;

	private boolean falling = false;
	private boolean up = false;
	private boolean down = false;
	
	// Getters
	
	public boolean isRight() {return this.right;}
	public boolean isLeft() {return this.left;}
	public boolean isJumping() {return false;}
	public boolean isFlying() {return this.flying;}
	public boolean isUp() {return this.up;}
	public boolean isDown() {return this.down;}
	
	// Setters
	
	public void right(boolean b) {this.right = b;}
	public void left(boolean b) {this.left = b;}
	public void up(boolean b) {this.up = b;}
	public void down(boolean b) {this.down = b;}
	public void jumping(boolean b) {}
	public void flying(boolean b) {this.flying=b;}
	
	// Constructeur
	
	public MouvementAiles(Entities main) {
		this.main=main;
	}
	
	// Méthodes
	
	public void xMovement(int move) {
		
		main.setPosX(main.getPosX()+move);
		if (move < 0) {
			main.setCurrentAnimation(LEFT);
		}

		else {
			main.setCurrentAnimation(RIGHT);
		}
	}
	
	public void yMovement(int move) {
		main.setPosY(main.getPosY()+move);
	}
	
	public boolean isFalling() {
	    return this.falling;
	}
	
	public void falling(boolean falling) {
	    this.falling = falling;
	}
	@Override
	public float jumpAnimation(float yMove) {
		return 0;
		
	}
	@Override
	public int isInJumpingPhase() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void releaseJump() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean gotWings() {
		return true;
	}
}
