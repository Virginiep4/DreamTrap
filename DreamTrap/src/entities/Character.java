package entities;

import java.awt.image.BufferedImage;

import static utils.ImageImporter.importImg;
import mouvement.*;

public class Character extends Entities {
	private BufferedImage[][] character;

	// aniTick is current tick, aniIndex is the current sub-animation, aniSpeed is
	// amount of Game.updates(ticks) before changing animation
	private int aniTick, aniIndex = 0, aniSpeed = 30;
	public Mouvement moving;

	public Character() {
		super();
		this.moving = new MouvementNormal(this);
	}

	public Character(int id, String nom, int niveau, int etoiles) {
		// TODO Auto-generated constructor stub
	}

	public Character(String string, int i, int j) {
		// TODO Auto-generated constructor stub
	}

	public BufferedImage[][] getCharacter() {return character;}
	public int getAniIndex() {return aniIndex;}
	
	public int getEtoiles() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNiv() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getNom() {
		// TODO Auto-generated method stub
		return null;
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

	public void update() {
		aniTick++;

		if (moving instanceof MouvementNormal) {
			if (aniTick >= aniSpeed) {
				// deux cas : le personnage saute ou animation
				if (!moving.isJumping() && (moving.isInJumpingPhase() == -1)) {
					aniIndex = ++aniIndex % character.length; // Iterates through all the images in screen.character
					aniTick = 0;
				} else {
					this.moving.jumpAnimation();
				}
			}
		}
		
		if (moving.isUp()) {
			moving.yMovement(-2);
		}
		if (moving.isDown()) {
			moving.yMovement(2);
		}
		
		if (moving.isRight()) {
			// On ne rejoint jamais  2048 * 64...
			moving.xMovement(2);
		}
		if (moving.isLeft()) {
			if (posX > 0) // évite les outOfBounds
				moving.xMovement(-2);
		}
	}

	public void setEtoiles(int etoile) {
		// TODO Auto-generated method stub
		
	}
}
