package entities;

import java.awt.image.BufferedImage;

import dreamTrap.Screen;

public class Item extends Entities {
	private BufferedImage[][] item;
	private int aniTick, aniIndex = 1, aniSpeed = 60;
	private int currentAnimation;
	 private String nom;
	 private float prix;
	 private String description;
	 private int acquis;
	 private int id;
	
	 private static boolean clicking=false;

	 public Item(int id,String nom,float prix,String description,int acquis,int currentAnimation) {
		 this.id=id;
		 this.nom=nom;
		 this.prix=prix;
		 this.description=description;
		 this.acquis=acquis;
		 this.currentAnimation= currentAnimation;
		 
	 }
	 public Item(String nom,float prix,String description,int acquis,int currentAnimation) {
		
		 this.nom=nom;
		 this.prix=prix;
		 this.description=description;
		 this.acquis=acquis;
		 this.currentAnimation= currentAnimation;
		 
	 }
	 
	 public static boolean acheterItem(Item Item) {
		 int etoile=Character.getEtoiles();
	     if (Character.getEtoiles() >= Item.prix) {
	    	 etoile-= Item.prix; // Déduire le prix des étoiles
	    	 Character.setEtoiles(etoile);
	    	 Item.acquis=1;
	         return true;
	     }
	     else{return false; }
	 }

	 public boolean isAcquis() {
		 if (acquis==0) { return false;}
		 else {return true;}
	    
	 }
	 

	public int getId() {
		return id;
	}


	public String getDesciption() {
		return description;
	}
	public void setDesciption(String desciption) {
		this.description = desciption;
	}
	public int getAcquis() {
		return acquis;
	}
	public void setAcquis(int acquis) {
		this.acquis = acquis;
	}
	public String getNom() {
		return nom;
	}

	public float getPrix() {
		return prix;
	}

	@Override
	public String toString() {
		return "Item [nom=" + nom + ", prix=" + prix + "]";
	}
	@Override
	void importEntity() {
		item= new BufferedImage[2][];
		
		item[0] = new BufferedImage[2];
		item[0][0] = importImg("/Ailes.png");
		item[0][1]=importImg("/Ailes 2.png");
		
		item[1] = new BufferedImage[2];
		item[1][0] = importImg("/Sci.png");
		item[1][1]=importImg("/Sci 2.png");
		
	}
	@Override
	public
	void updateAnimationTick() {
		aniTick++;
		if (this.isAcquis()) {
			this.aniIndex=0;
		}	
	}

	public BufferedImage[][] getItem(){
		return item;
	}
	public int getCurrentAnimation() {
		return currentAnimation;
	}
	public int getAniIndex() {
		return aniIndex;
	}
	public void setAniIndex(int aniIndex) {
		this.aniIndex=aniIndex;
	}
	
	public static void click() {
		achat();
	}
	
	public static String achat() {
		String ans="";
		if(ShopInt.place==1 && acheterItem(Screen.getAiles())) {
			
			ans="Acheté!";
			Screen.getAiles().setAniIndex(0);
		}
		if(ShopInt.place==2 && acheterItem(Screen.getPince())) {
			ans="Acheté!";
			Screen.getPince().setAniIndex(0);
		}
		else {
			ans="Pas possible d'acheté";
		}
		return ans;
	}
	
	public static boolean isClicked() {
		return clicking;
	}
	
	public static void setClicked(boolean b) {
		clicking=b;
	}
	
}
