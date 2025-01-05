package entities;

import java.awt.image.BufferedImage;

import javax.swing.Timer;

import dreamTrap.Screen;
import static utils.ImageImporter.importImg;

public class Item extends Entities {
	private BufferedImage[][] item;
	private int aniTick = 0, aniIndex = 1;
	private int currentAnimation;
	private String nom;
	private float prix;
	private String description;
	private int acquis;
	private int id;
	private static Character character;
	
	private static int texte=0;

	private static boolean clicking = false;

	public Item(int id, String nom, float prix, String description, int acquis, int currentAnimation, Character character) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.acquis = acquis;
		this.currentAnimation = currentAnimation;
		Item.character = character;
	}
	
	public Item(int id, String nom, float prix, String description, int acquis) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.acquis = acquis;
	}

	public Item(String nom, float prix, String description, int acquis, int currentAnimation, Character character) {

		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.acquis = acquis;
		this.currentAnimation = currentAnimation;
		Item.character = character;
	}

	public static boolean acheterItem(Item Item) {
		int etoile = character.getEtoiles();
		if (character.getEtoiles() >= Item.prix) {
			etoile -= Item.prix; // Déduire le prix des étoiles
			character.setEtoiles(etoile);
			Item.acquis = 1;
			return true;
		} else {
			return false;
		}
	}

	public boolean isAcquis() {
		if (acquis == 0) {
			return false;
		} else {
			return true;
		}

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
		item = new BufferedImage[2][];

		item[0] = new BufferedImage[2];
		item[0][0] = importImg("/Ailes.png");
		item[0][1] = importImg("/Ailes 2.png");

		item[1] = new BufferedImage[2];
		item[1][0] = importImg("/Sci.png");
		item[1][1] = importImg("/Sci 2.png");

	}

	@Override
	public void update() {
		aniTick++;
		if (this.isAcquis()) {
			this.aniIndex = 0;
		}
	}

	public BufferedImage[][] getItem() {
		return item;
	}

	public int getCurrentAnimation() {
		return currentAnimation;
	}

	public int getAniIndex() {
		return aniIndex;
	}

	public void setAniIndex(int aniIndex) {
		this.aniIndex = aniIndex;
	}

	public static  void click() {
		
		if(Screen.getInstance().getBackgroundd().getCurrentAnimation()==4 && (ShopInt.getPlace()==1 || ShopInt.getPlace()==2)){
			
			texte=1;
			Timer time=new Timer(3000, e->texte=0);
			time.start();		
		}	
	}

	public static String achat() {
		String ans = "";
		if (ShopInt.place == 1 && acheterItem(Screen.getAiles())) {
			if(Screen.getAiles().isAcquis()) {
				ans = "Vous possédez déjà cet objet...";
			}

			ans = "Acheté!";
			Screen.getAiles().setAniIndex(0);
		}
		if (ShopInt.place == 2 && acheterItem(Screen.getPince())) {
			if(Screen.getPince().isAcquis()) {
				ans = "Vous possédez déjà cet objet...";
			}

			ans = "Acheté!";
			Screen.getPince().setAniIndex(0);
		} else {
			ans = "Vous n'avez pas assez d'étoiles...";
		}
		return ans;
	}

	public static boolean isClicked() {
		return clicking;
	}

	public static void setClicked(boolean b) {
		clicking = b;
	}

	public static int getTexte() {
		return texte;
	}
}
