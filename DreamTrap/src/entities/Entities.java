package entities;

public abstract class Entities {
	protected int posX = 0;
	protected int posY = 0;
	
	public Entities() {
		importEntity();
	}
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Puts all the frame of character animation in each character array
	 */
	abstract void importEntity();
	
	/**
	 * Where the animation of the character are handled
	 */
	abstract void update();
}
