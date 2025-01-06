package entities;

import java.awt.image.BufferedImage;

import javax.swing.Timer;

import DAO.ItemDAO;
import DAO.JoueurDAO;
import dreamTrap.Screen;
import mouvement.MouvementAiles;
import mouvement.MouvementNormal;

import static utils.ImageImporter.importImg;

public class Item extends Entities {
	private BufferedImage[][] item;
	private int aniTick = 0, aniIndex = 1;
	private int currentAnimation;
	private String nom;
	private float prix;
	private String description;
	private boolean acquis;
	private int id;
	private static Character character;
	private static ItemDAO itemDAO;
	private static JoueurDAO joueurDAO;

	private static int texte = 0;
	private static int affichage = 0;

	private static boolean clicking = false;

	public Item(int id, String nom, float prix, String description, boolean acquis, int currentAnimation,
			Character character) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.acquis = acquis;
		this.currentAnimation = currentAnimation;
		Item.character = character;
		this.itemDAO = new ItemDAO();
		this.joueurDAO = new JoueurDAO();
	}

	public Item(int id, String nom, float prix, String description, boolean acquis) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.acquis = acquis;
		this.itemDAO = new ItemDAO();
		this.joueurDAO = new JoueurDAO();
	}

	public Item(String nom, float prix, String description, boolean acquis, int currentAnimation, Character character) {

		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.acquis = acquis;
		this.currentAnimation = currentAnimation;
		Item.character = character;
		this.itemDAO = new ItemDAO();
		this.joueurDAO = new JoueurDAO();
	}

	public static boolean acheterItem(Item item) {
		if (!itemDAO.gotItem(character, item.getId())) {
			int etoile = character.getEtoiles();
			if (character.getEtoiles() >= item.prix) {
				etoile -= item.prix; // Déduire le prix des étoiles
				character.setEtoiles(etoile);
				joueurDAO.updateTable(character);
				item.acquis = true;

				itemDAO.addInventory(character.getId(), item.getId());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static boolean possibleAchat(Item item) {
		int etoile = character.getEtoiles();
		boolean rep = false;
			if (character.getEtoiles() >= item.prix) {
				rep = true;
			}
		return rep;
	}

	public boolean isAcquis() {
		return acquis;

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

	public boolean getAcquis() {
		return acquis;
	}

	public void setAcquis(boolean acquis) {
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

	public static void click() {

		if (Screen.getInstance().getBackgroundd().getCurrentAnimation() == 4
				&& (ShopInt.getPlace() == 1 || ShopInt.getPlace() == 2)) {

			texte = 1;
			affichage = 1;
			Timer time = new Timer(3000, e -> affichage = 0);
			time.start();
		}
	}

	public static String achat() {
		String ans = "";
		if (texte == 1) {
			if (ShopInt.place == 1 && possibleAchat(Screen.getAiles())) {
				if (itemDAO.gotItem(character, Screen.getAiles().getId()) && Screen.getAiles().isAcquis()) {
					ans = "Vous possédez déjà cet objet...";
				}
				if (!itemDAO.gotItem(character, Screen.getAiles().getId()) && !Screen.getAiles().isAcquis()) {
					ans = "Ailes achetés!";
					Screen.getAiles().setAniIndex(0);
				}
			}
			if (ShopInt.place == 1 && !(possibleAchat(Screen.getAiles()))) {
				if (itemDAO.gotItem(character, Screen.getAiles().getId()) && Screen.getAiles().isAcquis()) {
					ans = "Vous possédez déjà cet objet...";
				} else {
					ans = "Vous n'avez pas assez d'étoiles...";
				}
			}
			if (ShopInt.place == 2 && possibleAchat(Screen.getPince()) && ans != "Pince achetée!") {
				if (itemDAO.gotItem(character, Screen.getPince().getId()) && Screen.getPince().isAcquis()) {
					ans = "Vous possédez déjà cet objet...";
				}
				if (!itemDAO.gotItem(character, Screen.getPince().getId()) && !Screen.getPince().isAcquis()) {
					ans = "Pince achetée!";
					Screen.getPince().setAniIndex(0);
				}
			}
			if (ShopInt.place == 2 && !(possibleAchat(Screen.getPince()))) {
				if (itemDAO.gotItem(character, Screen.getPince().getId()) && Screen.getPince().isAcquis()) {
					ans = "Vous possédez déjà cet objet...";
				} else {
					ans = "Vous n'avez pas assez d'étoiles...";
				}
			}
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

	public static int getAffichage() {
		return affichage;
	}

	public static void setTexte(int i) {
		texte = i;
	}
}