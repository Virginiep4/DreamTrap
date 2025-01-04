package entities;

import java.awt.Image;
import java.awt.image.BufferedImage;

import dreamTrap.Screen;

import static utils.ImageImporter.importImg;;

public class shop extends Entities implements Interagiseable {
	private BufferedImage[][] shop;
	private int aniTick, aniIndex = 0, aniSpeed = 60;
	private int currentAnimation = 0;
	private int sizeX=406;
	private int sizeY=345;
	private int placeX=500;
	private int placeY=339;
	private Character character;
	private backgroundd backgroundd;
	
	public shop(Screen screen) {
		super();
		this.backgroundd = screen.getBackgroundd();
		this.character = screen.getCharacter();
	}
	
	public boolean isWellPlaced() {
	boolean rep=false;
	int posXChara=character.getPosX();
	if((posXChara>(placeX-(sizeX/4))) && (posXChara<(placeX+(sizeX/4)))) {
		rep=true;
	}
		return rep;
	}

	
	public void change() {
		if (this.isWellPlaced()) {
			if(backgroundd.getCurrentAnimation()==3){
			backgroundd.setCurrentAnimation(4);
			character.setClicked(false);
			}}
		
		}
	
	void importEntity() {
		shop= new BufferedImage[1][]; 
		
		shop[0] = new BufferedImage[2];
		shop[0][0] = importImg("/Boutique 4.png");
		shop[0][1] = importImg("/Boutique 6.png");
		//size shop : 406 345
		//shop place : 500 339
		//interagiseable between: ((posXChara>(placeX-(sizeX/4))) && (posXChara<(placeX+(sizeX/4)))
	}


	public void update() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniIndex = ++aniIndex % shop[currentAnimation].length;
			aniTick = 0;
		}
		if(Character.isClicked()) {
			this.change();
		}
		
		
		
	}

	public BufferedImage[][] getShop() {
		return shop;
	}
	public int getCurrentAnimation() {
		return currentAnimation;
	}
	public int getAniIndex() {
		return aniIndex;
	}

}
