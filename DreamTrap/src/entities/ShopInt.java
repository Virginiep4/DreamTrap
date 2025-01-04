package entities;

import java.awt.image.BufferedImage;

import dreamTrap.Screen;

import static utils.ImageImporter.importImg;

public class ShopInt extends Entities {
	private BufferedImage[][] fleche;
	private int aniTick, aniIndex = 0, aniSpeed = 60;
	private int currentAnimation ;
	static int place=1;
	boolean left=false;
	boolean right=false;
	boolean up=false;
	boolean down=false;
	int posX=700;
	int posY=400;
	private static boolean clicking=false;
	
	private static Character character;
	private static backgroundd backgroundd;
	
	public ShopInt(int currentAnimation, Screen screen) {
		super();
		this.currentAnimation=currentAnimation;
		
		character = screen.getCharacter();
		backgroundd = screen.getBackgroundd();
	}
	
	@Override
	void importEntity() {
		fleche= new BufferedImage[2][]; 
		
		fleche[0] = new BufferedImage[2];
		fleche[0][0] = importImg("/Fleche.png");
		fleche[0][1] = importImg("/Fleche2.png");
		
		fleche[1] = new BufferedImage[2];
		fleche[1][0] = importImg("/Souris.png");
		fleche[1][1] = importImg("/Souris2.png");
		
	}
	
	@Override
	public void update() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniIndex = ++aniIndex % fleche[currentAnimation].length;
			aniTick = 0;
		}
		
	}
	
	public static void left() {
		if(place==2) {
			place=1;
		}
	}
	
	public static void right() {
		if(place==1) {
			place=2;
		}
	}
	
	public static void up() {
		if(place==3) {
			place=1;
		}
	}
	
	public static void down() {
		if(place==1||place==2) {
			place=3;
		}
	}
	
	public int bougerX(){
		int X=posX;
		if(place==1) {
			X= 550;
		}
		if(place==2) {
			X= 1050;
		}
		if(place==3) {
			X= 815;
		}
		return X;
	}
	
	public int bougerY(){
		int Y=posY;
		if(place==1||place==2) {
			Y=400;
		}
		if(place==3) {
			Y=700;
		}
		return Y;
	}
	
	public BufferedImage[][] getFleche(){
		return fleche;
	}

	public int getCurrentAnimation() {
		return this.currentAnimation;
	}

	public int getAniIndex() {
		return this.aniIndex;
	}
	
	public static void click() {
		if(place==3) {
			backgroundd.setCurrentAnimation(3);
			character.setPosX(0);
			place=1;
		}
	}
	
	public static boolean isClicked() {
		return clicking;
	}
	
	public static void setClicked(boolean b) {
		clicking=b;
	}

}
