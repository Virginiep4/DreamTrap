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
	private BufferedImage character;
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int x) {
		posX = x;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int y) {
		posY = y;
	}
	
	public Screen() {
		importImg();
		
		setScreenSize();
		addKeyListener(new KeyboardInputs(this));
	}
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/powder.png");
		
		try {
			character = ImageIO.read(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	* The function that will allow to draw on the created screen
	* It is never called on the code because it is automatically done
	*
	* @param g  you don't have to care about this argument because this is 
	* 			created by the extensions used
	*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clean and allow to draw on the screen
		
		// could be optimized by loading all sprite on same image and use getSubimage()
		g.drawImage(character, 150 + posX, 600 + posY, null);
	}
	
	private void setScreenSize() {
		Dimension size = new Dimension(1280, 720); // Screen resolution
		setPreferredSize(size);
	}
	
	public void xMovement(int move) {
		posX += move;
	}
	
	public void yMovement(int move) {
		posY += move;
	}
	
	public void jump() throws InterruptedException {
		yMovement(-20);
		Thread.sleep(100);
		yMovement(20);
	}
}
