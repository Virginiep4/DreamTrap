package entities;

public abstract class Entities {
	protected int posX=0;
	protected int posY=0;
	protected int width = 49;
	protected int height = 72;
	public final static int BLOCK_PER_WIDTH = 20;
	public final static int BLOCK_PER_HEIGHT = 11;
	public final static int BLOCK_SIZE = 64;
	protected int currentAnimation = 0;

	
	public Entities() {
		importEntity();
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
	
	/**
	 * Puts all the frame of character animation in each character array
	 */
	abstract void importEntity();
	
	/**
	 * Where the animation of the character are handled
	 */
	abstract void update();
}