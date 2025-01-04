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
	private BufferedImage BackgroundLevel1 = importImg("/PageAccueil 4.png");
	private BufferedImage BackgroundLevel1b = importImg("/PageAccueil 4b.png");
	private BufferedImage BackgroundLevel2 = importImg("/PageAccueil gris.png");
	private BufferedImage BackgroundLevel2b = importImg("/PageAccueil grisB.png");
	private BufferedImage BackgroundLevel3 = importImg("/PageAccueil R.png");
	private BufferedImage BackgroundLevel3b = importImg("/PageAccueil Rb.png");
	
	public backgroundd() {
		super();
	}
	
	
	void importEntity() {
		backgroundd= new BufferedImage[7][]; // amount of different animations

		backgroundd[0] = new BufferedImage[2];
		backgroundd[0][0] = importImg("/PageAccueil R.png");
		backgroundd[0][1]=importImg("/PageAccueil Rb.png");
		
		backgroundd[1] = new BufferedImage[2];
		backgroundd[1][0] = importImg("/PageAccueil 4.png");
		backgroundd[1][1]=importImg("/PageAccueil 4b.png");
		
		backgroundd[2] = new BufferedImage[2];
		backgroundd[2][0] = importImg("/Boutique int.png");
		backgroundd[2][1]= importImg("/Boutique int.png");
		
		backgroundd[4] = new BufferedImage[2];
		backgroundd[4][0] = importImg("/Boutique int.png");
		backgroundd[4][1] = importImg("/Boutique int.png");
		
		// Changer selon le niveau pour le hub avec le shop, pour les trois portes
		
		BackgroundLevel1 = importImg("/PageAccueil 4.png");
		BackgroundLevel1b = importImg("/PageAccueil 4b.png");
		BackgroundLevel2 = importImg("/PageAccueil gris.png");
		BackgroundLevel2b = importImg("/PageAccueil grisB.png");
		BackgroundLevel3 = importImg("/PageAccueil R.png");
		BackgroundLevel3b = importImg("/PageAccueil Rb.png");
		
		backgroundd[3] = new BufferedImage[2];
		backgroundd[3][0] = BackgroundLevel1;
		backgroundd[3][1]= BackgroundLevel1b;
		
		backgroundd[5] = new BufferedImage[2];
		backgroundd[5][0] = BackgroundLevel1;
		backgroundd[5][1]= BackgroundLevel1b;
		
		
		// Premier level (ne change pas)
		
		
		backgroundd[6] = new BufferedImage[2];
		backgroundd[6][0] = importImg("/PageAccueil 4.png");
		backgroundd[6][1]=importImg("/PageAccueil 4b.png");
		
	}
	
	@Override
	public void update() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniIndex = ++aniIndex % backgroundd[currentAnimation].length;
			aniTick = 0;}
		
		if (Character.getInstance().getNiv()==1) {
			backgroundd[3][0] = BackgroundLevel1;
			backgroundd[3][1]= BackgroundLevel1b;
			backgroundd[5][0] = BackgroundLevel1b;
			backgroundd[5][1]= BackgroundLevel1b;
		}
		if (Character.getInstance().getNiv()==2) {
			backgroundd[3][0] = BackgroundLevel2;
			backgroundd[3][1]= BackgroundLevel2b;
			backgroundd[5][0] = BackgroundLevel2;
			backgroundd[5][1]= BackgroundLevel2b;
			
		}
		if (Character.getInstance().getNiv()==3) {
			backgroundd[3][0] = BackgroundLevel3;
			backgroundd[3][1]= BackgroundLevel3b;
			backgroundd[5][0] = BackgroundLevel3;
			backgroundd[5][1]= BackgroundLevel3b;
		}
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
