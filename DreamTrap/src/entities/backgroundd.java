package entities;

import java.awt.image.BufferedImage;
import static utils.ImageImporter.importImg;

public class backgroundd extends Entities{
	private BufferedImage[][] backgroundd;
	private int currentAnimation = 1;
	private int aniTick, aniIndex = 0, aniSpeed = 60;
//	private int accueil=0; 
//	private int loby=1;
//	private int zoomShop =2;
//	private int sas=3;
//	private int niv_1=4;
//	private int niv_2=5;
//	private int niv_3=6;
//	private int end=7;
	
	
	public backgroundd() {
		super();
	}
	
	
	void importEntity() {
		backgroundd= new BufferedImage[10][]; // amount of different animations

		backgroundd[0] = new BufferedImage[2];
		backgroundd[0][0] = importImg("/PageAccueil R.png");
		backgroundd[0][1]=importImg("/PageAccueil Rb.png");
		
		backgroundd[1] = new BufferedImage[2];
		backgroundd[1][0] = importImg("/PageAccueil 4.png");
		backgroundd[1][1]=importImg("/PageAccueil 4b.png");
		
		backgroundd[2] = new BufferedImage[2];
		backgroundd[2][0] = importImg("/Boutique int.png");
		backgroundd[2][1]=importImg("/Boutique int.png");
		
		backgroundd[3] = new BufferedImage[2];
		backgroundd[3][0] = importImg("/PageAccueil R.png");
		backgroundd[3][1]=importImg("/PageAccueil Rb.png");
		
		backgroundd[4] = new BufferedImage[2];
		backgroundd[4][0] = importImg("/PageAccueil 4.png");
		backgroundd[4][1]=importImg("/PageAccueil 4b.png");
		
		backgroundd[5] = new BufferedImage[2];
		backgroundd[5][0] = importImg("/PageAccueil 4.png");
		backgroundd[5][1]=importImg("/PageAccueil 4b.png");
		
		backgroundd[6] = new BufferedImage[2];
		backgroundd[6][0] = importImg("/PageAccueil 4.png");
		backgroundd[6][1]=importImg("/PageAccueil 4b.png");
		
		backgroundd[9] = new BufferedImage[2];
		backgroundd[9][0] = importImg("/PageAccueil 4.png");
		backgroundd[9][1]=importImg("/PageAccueil 4b.png");
		
		// Background FinalLevel
		
		backgroundd[8] = new BufferedImage[2];
		backgroundd[8][0] = importImg("/finalLevel_background.png");
		backgroundd[8][1]= importImg("/finalLevelB_background.png");
	}
	
	@Override
	public void update() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniIndex = ++aniIndex % backgroundd[currentAnimation].length;
			aniTick = 0;}
		
	}

	public BufferedImage[][] getBackgroundd() {
		return backgroundd;
	}
	public int getCurrentAnimation() {
		return currentAnimation;
	}
	
	public void setCurrentAnimation(int a) {
		currentAnimation=a;
	}
	public int getAniIndex() {
		return aniIndex;
	}
}
