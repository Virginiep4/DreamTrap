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
	private int posX = 0;
	private int posY = 0;
	
	private BufferedImage[][] character;
	private int currentAnimation = 0;
	
	// determines where we change, which img is displayed and how fast to change the
	// animations
	private int aniTick, aniIndex = 0, aniSpeed = 15; // aniSpeed = changes per second SO need to be change according to
														// fps
	private boolean jumping = false; // true if the character is jumping
	private int jumpingPhase = 0;

	public Screen() {
		importCharac();

		setScreenSize();
		addKeyListener(new KeyboardInputs(this));
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

		updateCharacAnimationTick();

		// could be optimized by loading all sprite on same image and use getSubimage()
		g.drawImage(character[currentAnimation][aniIndex], 150 + posX, 600 + posY, null);
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
				// 16 phases for jumping : 8 going up and 8 going down 
				// this will probably change to make the jump more smooth
				if (jumpingPhase < 11) {
					jumpingPhase++;
					yMovement(-25);
				} else if (jumpingPhase == 22) {
					jumpingPhase = 0;
					jumping = false;
				} else {
					jumpingPhase++;
					yMovement(25);
				}
			}
		}
	}
	
	/**
	 * Make the character move
	 *
	 * @param move is the amount of pixels that move will be done and the sign determine the direction
	 */
	public void xMovement(int move) {
		posX += move;
		if (move < 0)
			currentAnimation = 1;
		else
			currentAnimation = 0;

	}

	public void yMovement(int move) {
		posY += move;
	}

	/**
	 * Allow to enter the jumping animation in updateCharacAnimationTick()
	 */
	public void jump() {
		jumping = true;
	}
}
