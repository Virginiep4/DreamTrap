package entities;

import java.awt.image.BufferedImage;

import DAO.JoueurDAO;
import dreamTrap.Screen;
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

	public door(int placeX, int placeY, int currentAnimation, Screen screen) {
		super();
		this.placeX = placeX;
		this.placeY = placeY;
		this.currentAnimation = currentAnimation;

		this.character = screen.getCharacter();
		this.backgroundd = screen.getBackgroundd();
		this.joueurDAO = screen.getJoueur();
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
			}
		}

		if (backgroundd.getCurrentAnimation() == 5) {

			if (character.getPosX() < 600) {
				if (Screen.getDoor1().isWellPlaced()) {
					backgroundd.setCurrentAnimation(6);
					
					Screen.levelManager = new LevelOne(Screen.getInstance());
					character.setLevelManager(Screen.levelManager);

					character.setClicked(false);
				}
			}
			if (character.getPosX() < 1100 && character.getPosX() > 400) {
				if (Screen.getDoor2().isWellPlaced()) {
					backgroundd.setCurrentAnimation(7);
					character.setClicked(false);
					character.setPosX(0);
					character.setPosY(0);
				}
			}
		}

		if (backgroundd.getCurrentAnimation() == 6) {
			if (this.isWellPlaced()) {
				backgroundd.setCurrentAnimation(3);
				character.setNiv(aniIndex);
				joueurDAO.update(character);
				character.setClicked(false);
			}
		}
	}

	void importEntity() {
		door = new BufferedImage[2][]; // amount of different animations

		door[0] = new BufferedImage[2];
		door[0][0] = importImg("/door.png");
		door[0][1] = importImg("/door 2.png");

		door[1] = new BufferedImage[2];
		door[1][0] = importImg("/Portal1.png");
		door[1][1] = importImg("/Portal2.png");

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
