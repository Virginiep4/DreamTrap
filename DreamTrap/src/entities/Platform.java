package entities;

import java.awt.image.BufferedImage;

import level.Loadsave;

public class Platform extends Entities  {
	private BufferedImage[][] Shop;
	private int currentAnimation = 0;
	
	
	public Platform() {
		super();
	}
	void importEntity() {
		Shop = new BufferedImage[2][];
		Shop[0]=new BufferedImage[1];
		Shop[0][0]=Loadsave.importImg("/shop_1.png");
		
	}


	void updateCharacAnimationTick() {
		// TODO Auto-generated method stub
		
	}
	public BufferedImage[][] getShop() {
		return Shop;
	}
	public int getCurrentAnimation() {
		return currentAnimation;
	}
	

}
