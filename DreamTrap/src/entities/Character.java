package entities;

import java.awt.image.BufferedImage;

import dreamTrap.Game;
import dreamTrap.Screen;
import level.HelpMethods;
import level.ScoreScreen;

import static utils.ImageImporter.importImg;
import mouvement.*;

public class Character extends Entities {
	private BufferedImage[][] character;
	private static Character charactere;
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
	private static boolean clicking = false;
	public Mouvement moving;

	private final int RIGHT = 0;
	private final int LEFT = 1;
	private String nom = "";
	private int niv = 0;
	private int etoiles = 0;
	private int id;
	
	private int nbCoeurs = 3;
	private boolean hurting = false;
	private int[][] lvlData;

	public Character(String nom, int niveau, int etoiles) {
		this.nom = nom;
		this.niv = niveau;
		this.etoiles = etoiles;

	}

	public Character(int id, String nom, int niv, int etoiles) {
		// le passage des infos ne marche pas a partir d'ici
		super();
		charactere = this;
		this.moving = new MouvementNormal(this);
		new ScoreScreen(this);
		this.id = id;
		this.nom = nom;
		this.niv = niv;
		this.etoiles = etoiles;

	}

	public BufferedImage[][] getCharacter() {
		return character;
	}
	
	public int[][] getLvlData() {
		return lvlData;
	}

	public int getAniIndex() {
		return aniIndex;
	}

	public int getEtoiles() {
		return etoiles;
	}

	public int getId() {
		return id;
	}

	public int getNiv() {
		return niv;
	}

	public String getNom() {
		return nom;
	}

	public static Character getInstance() {
		return charactere;
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
	
	public void loadlvlData(int[][] lvldata) {
		this.lvlData = lvldata;
	}

	/**
	 * Where the animation of the character are handled
	 */
	public void update() {
		updatePos();
	}
	
	/**
	 * Where the moves of the character are handled
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

	public void setEtoiles(int etoile) {
		// TODO Auto-generated method stub

	}

	public void setNom(String nom) {
		this.nom = nom;

	}

	public void setId(int id) {
		this.id = id;
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

	/**
	 * Make the character move
	 *
	 * @param move is the amount of pixels that move will be done and the sign
	 *             determine the direction
	 */
	public void xMovement(double move) {
		posX += move;
		this.clicking = false;
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

	public void setClicked(boolean b) {
		clicking = b;
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

	public String toString() {
        return "Character{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", niveau=" + niv +
                ", etoiles=" + etoiles +
                '}';
    }
}