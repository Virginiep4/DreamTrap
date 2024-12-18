package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utils.ImageImporter.importImg;
import mouvement.*;

public class Character extends Entities {
	private BufferedImage[][] character;

	private Rectangle hitbox; // volume that trigger an action when they detect an interaction

	// aniTick is current tick, aniIndex is the current sub-animation, aniSpeed is
	// amount of Game.updates(ticks) before changing animation
	private int aniTick, aniIndex = 0, aniSpeed = 30;
	public Mouvement moving;

	public Character() {
		super();
		this.moving = new MouvementAiles(this);
	}

	public BufferedImage[][] getCharacter() {return character;}
	public int getAniIndex() {return aniIndex;}

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
			if (posX > 63) // évite les outOfBounds
				moving.xMovement(-2);
		}
	}
}
