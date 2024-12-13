package entities;

import java.awt.image.BufferedImage;

public class door extends Entities implements Interagiseable {
	
	private BufferedImage[][] door;
	private int aniTick, aniIndex = 0, aniSpeed = 60;
	private int currentAnimation ;
	private int sizeX;
	private int sizeY;
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
		if((posXChara>(placeX)) && (posXChara<(placeX+sizeX)) && (posYChara<(placeY)) && (posYChara>(placeY+sizeY))) {
			rep=true;
		}
			return rep;
		}

		
		public void change() {
			if (this.isWellPlaced()) {
				if(backgroundd.getCurrentAnimation()==1) {
					
				backgroundd.setCurrentAnimation(3);
				Character.setClicked(false);
				}
				if(backgroundd.getCurrentAnimation()==3) {
					if(posX==) {
					
					backgroundd.setCurrentAnimation(4);
					Character.setClicked(false);
					}
					if(posX==) {
						
						backgroundd.setCurrentAnimation(5);
						Character.setClicked(false);
						}
					if(posX==) {
						
						backgroundd.setCurrentAnimation(6);
						Character.setClicked(false);
						}}
				if(backgroundd.getCurrentAnimation()==4) {
						
						backgroundd.setCurrentAnimation(1);
						Character.setClicked(false);
						}
				if(backgroundd.getCurrentAnimation()==5) {
					
					backgroundd.setCurrentAnimation(1);
					Character.setClicked(false);
					}
				if(backgroundd.getCurrentAnimation()==6) {
					
					backgroundd.setCurrentAnimation(1);
					Character.setClicked(false);
					}
				}
			else {
				Character.setClicked(false);
			}
			}
		
		void importEntity() {
			door= new BufferedImage[2][]; // amount of different animations

			door[0] = new BufferedImage[2];
			door[0][0] = importImg("/Boutique 4.png");
			door[0][1]=importImg("/Boutique 6.png");
			
			door[1] = new BufferedImage[2];
			door[1][0] = importImg("/Boutique 4.png");
			door[1][1]=importImg("/Boutique 6.png");
			
			
			//size shop : 406 345
			//shop place : 500 339
			//interagiseable between: 
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

	}



