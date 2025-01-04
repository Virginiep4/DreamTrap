package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import dreamTrap.Game;

public abstract class Entities {
	protected int posX=0;
	protected int posY=0;
	protected Rectangle2D.Float hitbox ;
	protected int width = 49;
	protected int height = 72;
	public final static int BLOCK_PER_WIDTH = 20;
	public final static int BLOCK_PER_HEIGHT = 11;
	public final static int BLOCK_SIZE = 64;
	private int currentAnimation = 0;

	
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
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public void setPosX(int posX) {
		this.posX=posX;
	}
	public void setPosY(int posY) {
		 this.posY=posY;
	}
	public void setCurrentAnimation(int currentAnimation) {this.currentAnimation=currentAnimation;}
	public int getCurrentAnimation() {return currentAnimation;}
	
	public void drawHitbox(Graphics g) {
		g.setColor(Color.red);
		//g.drawRect((int)150 ,(int)( (BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE+ hitbox.y-40),(int) hitbox.width, (int)hitbox.height);
		g.drawRect(4 * Game.TILES_SIZE ,(int) hitbox.y,(int) hitbox.width, (int)hitbox.height);
		
		// de base c'est 150+ hitbox.x si j veux que le carrer se deplacent
	}
	
	protected void initHitbox() {
		
		hitbox= new Rectangle2D.Float(4 * BLOCK_SIZE, (BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE,width,height);
	}
	
	public void updateHitbox() {
		hitbox.x=posX / Game.TILES_SIZE + posX % Game.TILES_SIZE;
		hitbox.y=posY + (BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE;
	}
	
	public Rectangle2D.Float getHitbox() {
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
