package entities;

import java.awt.image.BufferedImage;

import dreamTrap.Game;
import levels.HelpMethods;
import levels.Loadsave;
import java.awt.geom.Rectangle2D;

public class Character extends Entities {

	private BufferedImage[][] character;
	private int currentAnimation = 0;

	// aniTick is current tick, aniIndex is the current sub-animation, aniSpeed is
	// amount of Game.updates(ticks) before changing animation, walkSpeed is amount
	// of tick between movings IT SHOULD BE A DIVISOR OF aniSpeed !!!
	private int aniTick, aniIndex = 0, aniSpeed = 40, walkSpeed = 5; // animation speed egal 120 fps / 2 animations
	private boolean jumping = false; // true if the character is jumping
	private int jumpingPhase = 0;
	private int MAX_JUMP_PHASE = 103;
	private double parablePos = -1; // makes the jump parabolic
	private boolean movingRight = false;
	private boolean movingLeft = false;
	private boolean movingUp = false;
	private boolean movingDown = false;
	private int[][] lvlData;
	private int[][] lvlStarsData;
	private float airSpeed = 0f; // Vitesse verticale(vitesse monter et descente)
	private final float GRAVITY = 0.04f * 1f; // Force de gravité
	private final static int JUMP_DELAY = 2; // how many phase before changing position
	private final static int JUMP_HEIGHT_FACTOR = 15; // makes character jumps higher

	private final int RIGHT = 0;
	private final int LEFT = 1;
	private String nom = "";
	private int niv = 0;
	private int etoiles = 0;
	private int id;

	public Character(int id, String nom, int niveau, int etoiles) {
		super();
		this.id = id;
		this.nom = nom;
		this.niv = niveau;
		this.etoiles = etoiles;

	}

	public Character(String nom, int niveau, int etoiles) {
		super();
		this.nom = nom;
		this.niv = niveau;
		this.etoiles = etoiles;

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

	public int[][] getLvlData() {
		return lvlData;
	}

	/**
	 * Puts all the frame of character animation in each character array
	 */
	public void importEntity() {
		character = new BufferedImage[2][]; // amount of different animations

		character[0] = new BufferedImage[2];
		character[0][0] = Loadsave.importImg("/powder.png");
		character[0][1] = Loadsave.importImg("/powderPlaned.png");

		character[1] = new BufferedImage[2];
		character[1][0] = Loadsave.importImg("/powderLeft.png");
		character[1][1] = Loadsave.importImg("/powderPlanedLeft.png");
	}

	public void loadlvlData(int[][] lvldata) {

		this.lvlData = lvldata;

		// Boucle sur les lignes
//		for (int i = 0; i < lvldata.length; i++) {
//			// Boucle sur les colonnes
//			for (int j = 0; j < lvldata[i].length; j++) {
//				System.out.print(lvldata[i][j] + " "); // Affiche l'élément
//			}
//			System.out.println(); // Passe à la ligne suivante après avoir affiché une ligne
//		}

	}
	
	public void loadlvlStarsData(int[][] lvlStarsData) {
		this.lvlStarsData = lvlStarsData;
	}

	/**
	 * Where the animation of the character are handled
	 */
	public void updateCharacAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			// two cases : character is jumping or basic animation

			if (!movingUp && !movingDown) {
				aniIndex = ++aniIndex % character.length; // Iterates through all the images in screen.character
				aniTick = 0;
				// if (!HelpMethods.IsEntityOnFloor(hitbox, lvlData)) {jumping=true;}
			} // else {
				// if (HelpMethods.CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width,
				// hitbox.height, lvlData)) {
				// hitbox.y += airSpeed;
				// airSpeed += GRAVITY;
				// jumpAnimation();
				// }

		}

		/*
		 * if (aniTick % walkSpeed == 0) { if (movingRight) {
		 * //if(HelpMethods.CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width,
		 * hitbox.height, lvlData)) { xMovement(20); } if (movingLeft) { xMovement(-20);
		 * } }
		 */

	}

	public void updatePos() {
		float xSpeed = 0, ySpeed = 0;

		if (aniTick % walkSpeed == 0) {

			if (movingRight) {
				xSpeed = 5.0f;

			}
			if (movingLeft) {
				xSpeed = -5.0f;

			}
			if (movingUp) {
				ySpeed = -5.0f;
			}
			if (movingDown) {
				ySpeed = 5.0f;

			}
			if (HelpMethods.CanMoveHere(this, (int) xSpeed, (int) ySpeed)) {
				xMovement((int) xSpeed);
				yMovement((int) ySpeed);
			}
		}
	}

	/**
	 * Where the jump of the character is handled
	 */
	public void jumpAnimation() {
		// 16 phases for jumping : 8 going up and 8 going down
		// this will probably change to make the jump more smooth

		// ascending phase for the first half of jump phases
		if ((jumpingPhase < (MAX_JUMP_PHASE / 2)) && (jumpingPhase % JUMP_DELAY == 0)) {
			jumpingPhase++;
			posY -= parablePos * parablePos * JUMP_HEIGHT_FACTOR;
			parablePos += 2 / MAX_JUMP_PHASE; // 2 because range between -1 and 1
		}

		// last phase : jump is over
		else if (jumpingPhase == MAX_JUMP_PHASE) { // jump is over
			jumpingPhase = 0;
			parablePos = -1;
			jumping = false;

		}

		// descending phase
		else if (jumpingPhase % 3 == 0) {
			jumpingPhase++;
			posY += parablePos * parablePos * JUMP_HEIGHT_FACTOR;
			parablePos += 2 / MAX_JUMP_PHASE; // 2 because range between -1 and 1
		}

		// when jumpingPhase is not equal to 0 with mod JUMP_DELAY
		else {
			jumpingPhase++;
			parablePos += 2 / MAX_JUMP_PHASE;
		}

		/*
		 * // Appliquer la gravité if (jumpingPhase < MAX_JUMP_PHASE) { // Phase de
		 * montée if (jumpingPhase < (MAX_JUMP_PHASE / 2)) { this.posY -= airSpeed; //
		 * Déplacer vers le haut airSpeed -= GRAVITY; // Appliquer la gravité } else {
		 * // Phase de descente this.posY += airSpeed; // Déplacer vers le bas airSpeed
		 * += GRAVITY; // Appliquer la gravité } jumpingPhase++; } else { // Fin du saut
		 * jumpingPhase = 0; jumping = false; airSpeed = 0; // Réinitialiser la vitesse
		 * verticale }
		 */

	}

	/**
	 * Make the character move
	 *
	 * @param move is the amount of pixels that move will be done and the sign
	 *             determine the direction
	 */
	public void xMovement(int move) {
		this.posX += move;

		if (move < 0)
			currentAnimation = LEFT;
		else
			currentAnimation = RIGHT;
	}

	public void yMovement(int move) {
		this.posY += move;

	}

	/**
	 * Allow to enter the jumping animation in updateCharacAnimationTick()
	 */
	public void jump() {
		jumping = true;
	}

//	public void updateVerticalMovement() {
//		// Vérifier si le personnage est en train de sauter
//		if (jumping) {
//			// Appliquer la vitesse verticale pour le saut
//			this.posY -= airSpeed; // Déplacer le personnage vers le haut
//			airSpeed -= GRAVITY; // Appliquer la gravité pour ralentir le saut
//
//			// Vérifier si le personnage a atteint le plafond ou la hauteur maximale du saut
//			if (airSpeed <= 0 || jumpingPhase >= MAX_JUMP_PHASE) {
//				jumping = false; // Fin du saut
//				jumpingPhase = 0; // Réinitialiser la phase de saut
//				airSpeed = 0; // Réinitialiser la vitesse verticale
//			}
//		} else {
//			// Si le personnage n'est pas en train de sauter, appliquer la gravité
//			airSpeed += GRAVITY; // Augmenter la vitesse vers le bas
//			this.posY += airSpeed; // Déplacer le personnage vers le bas
//
//			// Vérifier la collision avec le sol
//			int tileYPos = (int) HelpMethods
//					.GetEntityYPosUnderRoofOrAboveFloor(new Rectangle2D.Float(posX, posY, width, height), airSpeed);
//			if (posY >= tileYPos) {
//				posY = tileYPos; // Ajuster la position Y pour qu'elle soit juste au-dessus du sol
//				airSpeed = 0; // Réinitialiser la vitesse verticale
//			}
//		}
//	}

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

	public void up(boolean b) {
		movingUp = b;
	}

	public void down(boolean b) {
		movingDown = b;
	}

	public int getAniIndex() {
		return aniIndex;
	}

	public String getNom() {
		return nom;
	}

	public int getNiv() {
		return niv;
	}

	public int getEtoiles() {
		return etoiles;
	}

	public void setEtoiles(int etoiles) {
		this.etoiles = etoiles;
	}

	public int getId() {
		return id;
	}

	public int[][] getLvlStarsData() {
		return lvlStarsData;
	}

	public void setLvlStarsData(int[][] lvlStarsData) {
		this.lvlStarsData = lvlStarsData;
	}

}
