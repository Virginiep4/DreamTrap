package entities;

import java.awt.image.BufferedImage;

public class door extends Entities implements Interagiseable {
	
	private BufferedImage[][] door;
	private int aniTick, aniIndex = 0, aniSpeed = 60;
	private int currentAnimation ;
	private int sizeX=50;
	private int sizeY=200;
	private int placeX;
	private int placeY;
	
	public door(int placeX, int placeY, int currentAnimation) {
		super();
		this.placeX=placeX;
		this.placeY=placeY;
		this.currentAnimation=currentAnimation;
	}
	
	public boolean isWellPlaced() {
		boolean rep=false;
		int posXChara=Character.getPosX();
		int posYChara=Character.getPosY();
		if((posXChara>(this.placeX-150)) && (posXChara<(this.placeX-150+sizeX)) && (posYChara<(placeY-600+sizeY)) && (posYChara>placeY-600)) {
			rep=true;
		}
			return rep;
		}

		
		public void change() {
				if(backgroundd.getCurrentAnimation()==1) {
					if (dreamTrap.Screen.getDoor0().isWellPlaced()) {
					backgroundd.setCurrentAnimation(3);
					Character.setClicked(false);
					Character.setPosX(0);
					}
				}
				
				if(backgroundd.getCurrentAnimation()==3) {
					
					if(Character.getPosX()<600) {
						if (dreamTrap.Screen.getDoor1().isWellPlaced()) {
						backgroundd.setCurrentAnimation(4);
						Character.setClicked(false);
					}}
					if(Character.getPosX()<1100 && Character.getPosX()>400) {
						if (dreamTrap.Screen.getDoor2().isWellPlaced()) {
						backgroundd.setCurrentAnimation(5);
						Character.setClicked(false);
						}}
					}
				
//				if(backgroundd.getCurrentAnimation()==4) {
//					if (this.isWellPlaced()) {
//						backgroundd.setCurrentAnimation(1);
//						Character.setClicked(false);
//						}}
//				
//				if(backgroundd.getCurrentAnimation()==5) {
//					if (this.isWellPlaced()) {
//					backgroundd.setCurrentAnimation(1);
//					Character.setClicked(false);
//					}}
//				
//				if(backgroundd.getCurrentAnimation()==6) {
//					if (this.isWellPlaced()) {
//					backgroundd.setCurrentAnimation(1);
//					Character.setClicked(false);
//					}}
				}
			
			
		
		void importEntity() {
			door= new BufferedImage[2][]; // amount of different animations

			door[0] = new BufferedImage[2];
			door[0][0] = importImg("/door.png");
			door[0][1]=importImg("/door 2.png");
			
			door[1] = new BufferedImage[2];
			door[1][0] = importImg("/Portal1.png");
			door[1][1]=importImg("/Portal2.png");
			
			
		
		}


		public void updateAnimationTick() {
			aniTick++;
			if (aniTick >= aniSpeed) {
				aniIndex = ++aniIndex % door[currentAnimation].length;
				aniTick = 0;
			}
			if(Character.isClicked()) {
				this.change();
			}
			
		}

		public BufferedImage[][] getDoor() {
			return door;
		}
		public int getCurrentAnimation() {
			return currentAnimation;
		}
		public int getAniIndex() {
			return aniIndex;
		}
		
		public int getPlaceX() {
			return placeX;
		}
		public int getPlaceY() {
			return placeY;
		}
		
		

	}



