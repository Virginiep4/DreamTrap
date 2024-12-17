package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entities {
	protected int posX;
	protected int posY;
	protected Rectangle hitbox ;
	protected int width = 49;
	protected int height = 72;
	
	public Entities() {
		importEntity();
		initHitbox();
	}
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	
	public void drawHitbox(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(150 + hitbox.x, 600+ hitbox.y, hitbox.width, hitbox.height);
	}
	
	protected void initHitbox() {
		
		hitbox= new Rectangle(posX,posY,width,height);
	}
	
	public void updateHitbox() {
		
		hitbox.x=posX;
		hitbox.y=posY;
	}
	
	protected Rectangle getHitbox() {
		return hitbox;
	}
	/**
	 * Puts all the frame of character animation in each character array
	 */
	abstract void importEntity();

	/**
	 * Load an image on stream and return it if is gotten
	 *
	 * @param path is the path of the image to load
	 */
	
	
	
	/**
	 * Where the animation of the character are handled
	 */
	abstract void updateCharacAnimationTick();
}
