package dreamTrap;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;

public class Screen extends JPanel {
	private double posX = 0;
	private double posY = 0;

	private BufferedImage[][] character;
	private int currentAnimation = 0;

	// determines where we change, which img is displayed and how fast to change the
	// animations
	private int aniTick, aniIndex = 0, aniSpeed = 30; // aniSpeed = amount of updates per changes
	private boolean jumping = false; // true if the character is jumping
	private int jumpingPhase = 0;
	private final int MAX_JUMP_PHASE = 66;
	private double parablePos = -1; // makes the jump parabolic

	public Screen() {
		importCharac();

		setScreenSize();
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		updateCharacAnimationTick();
	}

	/**
	 * The function that will allow to draw on the created screen It is never called
	 * on the code because it is automatically done
	 *
	 * @param g you don't have to care about this argument because this is created
	 *          by the extensions used
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clean and allow to draw on the screen

		// could be optimized by loading all sprite on same image and use getSubimage()
		g.drawImage(character[currentAnimation][aniIndex], 150 + (int) posX, 600 + (int) posY, null);
	}

	/**
	 * Puts all the frame of character animation in each character array
	 */
	private void importCharac() {
		character = new BufferedImage[2][]; // amount of different animations

		character[0] = new BufferedImage[2];
		character[0][0] = importImg("/powder.png");
		character[0][1] = importImg("/powderPlaned.png");

		character[1] = new BufferedImage[2];
		character[1][0] = importImg("/powderLeft.png");
		character[1][1] = importImg("/powderPlanedLeft.png");
	}

	/**
	 * Load an image on stream and return it if is gotten
	 *
	 * @param path is the path of the image to load
	 */
	private BufferedImage importImg(String path) {
		InputStream is = getClass().getResourceAsStream(path);

		try {
			BufferedImage img = ImageIO.read(is);
			is.close();
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension(1280, 720); // Screen resolution
		setPreferredSize(size);
	}

	/**
	 * Where the jump of the character is handled
	 */
	public void jumpAnimation() {
		// 16 phases for jumping : 8 going up and 8 going down
		// this will probably change to make the jump more smooth

		// ascending phase
		if ((jumpingPhase < (MAX_JUMP_PHASE / 2)) && (jumpingPhase % 1 == 0)) { // mod 3 to have more delayed updates
			jumpingPhase++;
			yMovement(-(parablePos * parablePos) * 20); // 
			parablePos += 2 / MAX_JUMP_PHASE;
		}

		else if (jumpingPhase == MAX_JUMP_PHASE) { // jump is over
			jumpingPhase = 0;
			parablePos = -1;
			jumping = false;
		}

		// descending phase
		else if (jumpingPhase % 1 == 0) {
			jumpingPhase++;
			yMovement((parablePos * parablePos) * 20);
			parablePos += 2 / MAX_JUMP_PHASE;
		}

		else {
			jumpingPhase++;
			parablePos += 2 / MAX_JUMP_PHASE;
		}
	}

	/**
	 * Where the animation of the character are handled
	 */
	public void updateCharacAnimationTick() {
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
	}

	/**
	 * Make the character move
	 *
	 * @param move is the amount of pixels that move will be done and the sign
	 *             determine the direction
	 */
	public void xMovement(double move) {
		posX += move;
		if (move < 0)
			currentAnimation = 1;
		else
			currentAnimation = 0;

	}

	public void yMovement(double move) {
		posY += move;
	}

	/**
	 * Allow to enter the jumping animation in updateCharacAnimationTick()
	 */
	public void jump() {
		jumping = true;
	}
}
