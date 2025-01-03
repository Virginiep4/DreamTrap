package entities;

import java.awt.image.BufferedImage;

public class Character extends Entities {
	private BufferedImage[][] character;
	private int currentAnimation = 0;

	// aniTick is current tick, aniIndex is the current sub-animation, aniSpeed is
	// amount of Game.updates(ticks) before changing animation, walkSpeed is amount
	// of tick between movings IT SHOULD BE A DIVISOR OF aniSpeed !!!
	private int aniTick, aniIndex = 0, aniSpeed = 30, walkSpeed = 5;
	private boolean jumping = false; // true if the character is jumping
	private int jumpingPhase = 0;
	private final int MAX_JUMP_PHASE = 66;
	private double parablePos = -1; // makes the jump parabolic
	private boolean movingRight = false;
	private boolean movingLeft = false;
	private static boolean clicking=false;

	private final int RIGHT = 0;
	private final int LEFT = 1;
	private int id;
	private String nom;
	private int niv;
	private static int etoiles;
	
	public Character(int id, String nom, int niveau,int etoiles) {
		this.id=id;
		this.nom =nom;
		this.niv =niveau;
		this.etoiles=etoiles;
		
	}
	public Character( String nom, int niveau,int etoiles) {
		
		this.nom =nom;
		this.niv =niveau;
		this.etoiles=etoiles;
		
	}

	public Character() {
		super();
	}

	public BufferedImage[][] getCharacter() {
		return character;
	}
	public int getCurrentAnimation() {
		return currentAnimation;
	}

	/**
	 * Puts all the frame of character animation in each character array
	 */
	public void importEntity() {
		character = new BufferedImage[2][]; // amount of different animations

		character[0] = new BufferedImage[2];
		character[0][0] = importImg("/Powpow.png");
		character[0][1] = importImg("/Powpow2.png");

		character[1] = new BufferedImage[2];
		character[1][0] = importImg("/Left-Powpow (1).png");
		character[1][1] = importImg("/Left-Powpow 2 (1).png");
	}

	/**
	 * Where the animation of the character are handled
	 */
	public void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			// two cases : character is jumping or basic animation
			if (!jumping) {
				aniIndex = ++aniIndex % character.length; // Iterates through all the images in screen.character
				aniTick = 0;
			} else {
				jumpAnimation();
			}
		}

		if (aniTick % walkSpeed == 0) {
			if (movingRight) {
				xMovement(20);
			}
			if (movingLeft) {
				xMovement(-20);
			}
		}
	}

	/**
	 * Where the jump of the character is handled
	 */
	public void jumpAnimation() {
		// 16 phases for jumping : 8 going up and 8 going down
		// this will probably change to make the jump more smooth

		// ascending phase
		if ((jumpingPhase < (MAX_JUMP_PHASE / 2)) && (jumpingPhase % 3 == 0)) { // mod 3 to have more delayed updates
			jumpingPhase++;
			posY -= parablePos * parablePos * 20; //
			parablePos += 2 / MAX_JUMP_PHASE;
		}

		else if (jumpingPhase == MAX_JUMP_PHASE) { // jump is over
			jumpingPhase = 0;
			parablePos = -1;
			jumping = false;
		}

		// descending phase
		else if (jumpingPhase % 3 == 0) {
			jumpingPhase++;
			posY += parablePos * parablePos * 20;
			parablePos += 2 / MAX_JUMP_PHASE;
		}

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
		this.clicking=false;
		if (move < 0)
			currentAnimation = LEFT;
		else
			currentAnimation = RIGHT;
	}

	/**
	 * Allow to enter the jumping animation in updateCharacAnimationTick()
	 */
	public void jump() {
		jumping = true;
	}
	
	public void click() {
		clicking = true;
	}
	
	public static boolean isClicked() {
		return clicking;
	}
	
	public static void setClicked(boolean b) {
		clicking=b;
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
	
	public int getAniIndex() {
		return aniIndex;
	}
	
	public static void setPosX(int x) {
		posX=x;
	}
	public static int getEtoiles() {
		return etoiles;
	}
	public static void setEtoiles(int e) {
		etoiles = e;
	}
	public String getNom() {
		return nom;
	}
	public int getNiv() {
		return niv;
	}
	public int getId() {
		return id;
	}
}