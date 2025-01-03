package entities;

import java.awt.image.BufferedImage;

import level.HelpMethods;
import level.Loadsave;
import mouvement.*;

public class Character extends Entities {

	public Mouvement moving;
	private BufferedImage[][] character;
	// aniTick is current tick, aniIndex is the current sub-animation, aniSpeed is
	// amount of Game.updates(ticks) before changing animation, walkSpeed is amount
	// of tick between movings IT SHOULD BE A DIVISOR OF aniSpeed !!!
	private int aniTick, aniIndex = 0, aniSpeed = 40, walkSpeed = 5; // animation speed egal 120 fps / 2 animations
	private int[][] lvlData;
	private int[][] lvlStarsData;
	private String nom = "";
	private int niv = 0;
	private int etoiles = 0;
	private int id;

	// Système de PV et d'attente avant de pouvoir à nouveau subir des dégâts

	private int nbCoeurs = 3;
	private boolean hurting = false;

	public Character(int id, String nom, int niveau, int etoiles) {
		super();
		this.id = id;
		this.nom = nom;
		this.niv = niveau;
		this.etoiles = etoiles;
		this.moving = new MouvementNormal(this);
		setPosY(-100);
	}

	public Character(String nom, int niveau, int etoiles) {
		super();
		this.nom = nom;
		this.niv = niveau;
		this.etoiles = etoiles;
		this.moving = new MouvementNormal(this);
		setPosY(-100);
	}

	public Character() {
		super();
		this.moving = new MouvementNormal(this);
		setPosY(-100);
	}

	public BufferedImage[][] getCharacter() {
		return character;
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

	public void updatePos() {
		aniTick++;

		float xMove = 0, yMove = 1;

		if (moving instanceof MouvementNormal) {
			if (moving.isInJumpingPhase() == -1) {
				yMove = 5;
			}
			
			if (!moving.isJumping() && (moving.isInJumpingPhase() == -1)) {
				if (aniTick % walkSpeed == 0) {
					if (aniTick >= aniSpeed) {
						// deux cas : le personnage saute ou animation
						aniIndex = ++aniIndex % character.length; // Iterates through all the images in screen.character
						aniTick = 0;
					}

				}
			} else {
				yMove = this.moving.jumpAnimation(yMove);
			}
			
			if (aniTick % walkSpeed == 0) {
				if (moving.isUp()) {
					yMove = -5.0f;
				}
				if (moving.isDown()) {
					yMove = 5.0f;
				}

				if (moving.isRight()) {
					// On ne rejoint jamais 2048 * 64...
					xMove = 5.0f;

				}
				if (moving.isLeft()) {
					xMove = -5.0f;
				}

				if (HelpMethods.CanMoveHere(this, (int) xMove, 0)) {
					moving.xMovement((int) xMove);
				}
			}
			
			if (HelpMethods.CanMoveHere(this, 0, (int) yMove)) {
				moving.yMovement((int) yMove);
			}
		}

		if (moving instanceof MouvementAiles) {

			if (aniTick % walkSpeed == 0) {
				/*
				 * if (moving instanceof MouvementNormal) { if (aniTick >= aniSpeed) { // deux
				 * cas : le personnage saute ou animation if (!moving.isJumping() &&
				 * (moving.isInJumpingPhase() == -1)) { aniIndex = ++aniIndex %
				 * character.length; // Iterates through all the images in screen.character
				 * aniTick = 0; } else { this.moving.jumpAnimation(); } } }
				 */

				if (moving.isUp()) {
					yMove = -5.0f;
				}
				if (moving.isDown()) {
					yMove = 5.0f;
				}

				if (moving.isRight()) {
					// On ne rejoint jamais 2048 * 64...
					xMove = 5.0f;

				}
				if (moving.isLeft()) {
					xMove = -5.0f;
				}

				if (HelpMethods.CanMoveHere(this, (int) xMove, (int) yMove)) {
					moving.xMovement((int) xMove);
					moving.yMovement((int) yMove);
				}
			}

		}
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

	@Override
	public void updateCharacAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			// two cases : character is jumping or basic animation

			if (!moving.isUp() && !moving.isDown()) {
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

	public int getNbCoeurs() {
		return nbCoeurs;
	}

	public void setNbCoeurs(int nbCoeurs) {
		this.nbCoeurs = nbCoeurs;
	}

	public boolean isHurting() {
		return hurting;
	}

	public void setHurting(boolean hurting) {
		this.hurting = hurting;
	}

}

/*
 * Ancien : update aniTick++;
 * 
 * if (moving instanceof MouvementNormal) { if (aniTick >= aniSpeed) { // deux
 * cas : le personnage saute ou animation if (!moving.isJumping() &&
 * (moving.isInJumpingPhase() == -1)) { aniIndex = ++aniIndex %
 * character.length; // Iterates through all the images in screen.character
 * aniTick = 0; } else { this.moving.jumpAnimation(); } } }
 * 
 * if (moving.isUp()) { moving.yMovement(-2); } if (moving.isDown()) {
 * moving.yMovement(2); }
 * 
 * if (moving.isRight()) { // On ne rejoint jamais 2048 * 64...
 * moving.xMovement(2); } if (moving.isLeft()) { if (posX > 63) // évite les
 * outOfBounds moving.xMovement(-2); }
 */
