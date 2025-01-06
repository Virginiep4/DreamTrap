package entities;

import java.awt.image.BufferedImage;

import DAO.ItemDAO;
import DAO.JoueurDAO;
import dreamTrap.Screen;
import level.FinalLevel;
import level.HubLevel;
import level.LevelManager;
import level.LevelOne;
import level.LevelTwo;

import static utils.ImageImporter.importImg;

public class door extends Entities implements Interagiseable {

	private BufferedImage[][] door;
	private int aniTick, aniIndex = 0, aniSpeed = 60;
	private int currentAnimation;
	private int sizeX = 50;
	private int sizeY = 200;
	private int placeX;
	private int placeY;
	private Character character;
	private backgroundd backgroundd;
	private JoueurDAO joueurDAO;
	private ItemDAO itemDAO;

	public door(int placeX, int placeY, int currentAnimation, Screen screen) {
		super();
		this.placeX = placeX;
		this.placeY = placeY;
		this.currentAnimation = currentAnimation;

		this.character = screen.getCharacter();
		this.backgroundd = screen.getBackgroundd();
		joueurDAO = new JoueurDAO();
		itemDAO = new ItemDAO();
	}

	public boolean isWellPlaced() {
		boolean rep = false;
		int posXChara = character.getPosX();
		int posYChara = character.getPosY();
		if ((posXChara > this.placeX) && (posXChara < (this.placeX + sizeX))
				&& (posYChara < (placeY - character.getLevelManager().getyCharacterSpawn() + sizeY))
				&& (posYChara > placeY - character.getLevelManager().getyCharacterSpawn())) {
			rep = true;
		}
		return rep;
	}

	public void change() {
		if (backgroundd.getCurrentAnimation() == 3) {
			if (Screen.getDoor0().isWellPlaced()) {
				backgroundd.setCurrentAnimation(5);
				character.setClicked(false);
				character.setPosX(character.getLevelManager().getxCharacterSpawn());
				character.setPosY(0);
			}
		}

		if (backgroundd.getCurrentAnimation() == 5) {

			if (Screen.getDoor1().isWellPlaced()) {
				backgroundd.setCurrentAnimation(6);

				Screen.levelManager = new LevelOne(Screen.getInstance());
				character.setLevelManager(Screen.levelManager);

				character.setClicked(false);
			}

			if (Screen.getDoor2().isWellPlaced()) {
				backgroundd.setCurrentAnimation(7);

				Screen.levelManager = new LevelTwo(Screen.getInstance());
				character.setLevelManager(Screen.levelManager);
				character.setPosY(0);
				character.setClicked(false);
			}

			if (this.isWellPlaced()) {
				if (itemDAO.gotItem(character, 2)) {
					if (this.getCurrentAnimation() == 2) {
						this.setCurrentAnimation(0);
						character.setClicked(false);
					} else {
						backgroundd.setCurrentAnimation(8);
						Screen.levelManager = new FinalLevel(Screen.getInstance());
						character.setLevelManager(Screen.levelManager);

						character.setClicked(false);
					}
				}
			}

		}

		if (backgroundd.getCurrentAnimation() == 6) {
			if (character.getPosX() < 10430 && character.getPosX() > 10340) {
				if (dreamTrap.Screen.getdoorEndLevelOne().isWellPlaced()) {
					backgroundd.setCurrentAnimation(3);
					character.setEtoiles(character.getEtoiles() + character.getLocalEtoiles());
					character.setClicked(false);
					if (character.getNiv() < 2) {
						character.setNiv(2);
					}
					joueurDAO.updateTable(character);
					Screen.levelManager = new HubLevel(Screen.getInstance());
					character.setLevelManager(Screen.levelManager);
					character.setPosX(Screen.levelManager.getxCharacterSpawn());
					character.setPosY(0);
				}
			}
		}

		if (backgroundd.getCurrentAnimation() == 7) {
			if (character.getPosX() < 8500 && character.getPosX() > 8200) {
				if (dreamTrap.Screen.getdoorEndLevelTwo().isWellPlaced()) {
					backgroundd.setCurrentAnimation(3);
					character.setEtoiles(character.getEtoiles() + character.getLocalEtoiles());
					character.setClicked(false);
					if (character.getNiv() < 3) {
						character.setNiv(3);
					}
					joueurDAO.updateTable(character);
					Screen.levelManager = new HubLevel(Screen.getInstance());
					character.setLevelManager(Screen.levelManager);
					character.setPosX(Screen.levelManager.getxCharacterSpawn());
					character.setPosY(0);
				}
			}
		}

		if (backgroundd.getCurrentAnimation() == 8) {
			if (FinalLevel.getInstance().isBossOver()) {
				if (character.getPosX() < 1400 && character.getPosX() > 1000) {
					if (this.isWellPlaced()) {
						if (itemDAO.gotItem(character, 2)) {
							if (this.getCurrentAnimation() == 2) {
								this.setCurrentAnimation(0);
								character.setClicked(false);
							} else {
								Progression.getInstance().setWin(0);
								// Mettre à jour la DAO
								character.setClicked(false);
							}
						}
					}
				}
			}
		}
	}

	void importEntity() {
		door = new BufferedImage[3][]; // amount of different animations

		door[0] = new BufferedImage[2];
		door[0][0] = importImg("/door.png");
		door[0][1] = importImg("/door 2.png");

		door[1] = new BufferedImage[2];
		door[1][0] = importImg("/Portal1.png");
		door[1][1] = importImg("/Portal2.png");

		door[2] = new BufferedImage[2];
		door[2][0] = importImg("/portechainee.png");
		door[2][1] = importImg("/portechainee.png");

	}

	public void update() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniIndex = ++aniIndex % door[currentAnimation].length;
			aniTick = 0;
		}
		if (Character.isClicked()) {
			this.change();
		}

	}

	public BufferedImage[][] getDoor() {
		return door;
	}

	public int getCurrentAnimation() {
		return currentAnimation;
	}

	public void setCurrentAnimation(int currentAnimation) {
		this.currentAnimation = currentAnimation;
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
