package level;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.TimerTask;

import javax.swing.Timer;

import dreamTrap.Game;
import dreamTrap.Screen;
import entities.Boss;
import level.LevelManager;

public class HelpMethods {

	public static boolean CanMoveHere(entities.Character character, float xMove, float yMove) {
		// Check all four corners of the entity's bounding box
		if (!IsSolid(character, xMove, yMove))
			return true;
		return false;
	}

	// Checks if the position (x, y) is solid (i.e., has an obstacle)
	private static boolean IsSolid(entities.Character character, float xMove, float yMove) {
		// Check if the position is out of bounds
		if (character.getPosY() + yMove - 2 * Screen.BLOCK_SIZE < -(Screen.BLOCK_SIZE * Screen.BLOCK_PER_HEIGHT))
			return true; // Out of top/bottom bounds

		int i = (int) (LevelManager.getLevelHeight() - 2 + (character.getPosY() + yMove) / Screen.BLOCK_SIZE); // Index de
																												// colonne
		float j = (4 + (character.getPosX() + xMove) / Screen.BLOCK_SIZE); // Index de ligne
		/*
		 * 4 * BLOCK_SIZE / Game.TILES_SIZE BLOCK_SIZE = TILE_SIZE pendant le merge
		 * peut-être uniformiser les noms...
		 */
		int targetedTileUpLeft = character.getLvlData()[i][(int) Math.floor(j)];
		int targetedTileUpRight = character.getLvlData()[i][(int) Math.ceil(j)];
		int targetedTileDownLeft = character.getLvlData()[i + character.getHeight() / Screen.BLOCK_SIZE][(int) Math
				.floor(j)];
		int targetedTileDownRight = character.getLvlData()[i + character.getHeight() / Screen.BLOCK_SIZE][(int) Math
				.ceil(j)];

		// Determine if the tile is solid based on its value

		if (targetedTileUpLeft != -1 || targetedTileUpRight != -1 || targetedTileDownLeft != -1
				|| targetedTileDownRight != -1) {
			return true; // The tile is solid
		}

		return false; // The tile is not solid

	}

	public static void OnStar(entities.Character character, LevelManager level) {

		int i = (int) (LevelManager.getLevelHeight() - 2 + character.getPosY() / Screen.BLOCK_SIZE); // Index de colonne
		int j = (int) (4 + (character.getPosX()) / Screen.BLOCK_SIZE); // Index de ligne
		if (level.getStars()[i][j] != -1) {
			gotStar(character, level);
		}
	}

	public static void OnSpike(entities.Character character, LevelManager level) {

		int i = (int) (LevelManager.getLevelHeight() - 2 + character.getPosY() / Screen.BLOCK_SIZE); // Index de colonne
		int j = (int) (4 + (character.getPosX()) / Screen.BLOCK_SIZE); // Index de ligne
		if (level.getSpikes()[i][j] != -1) {
			characterHurt(character);
		}

	}

	public static void gotStar(entities.Character character, LevelManager level) {
		int i = (int) (LevelManager.getLevelHeight() - 2 + character.getPosY() / Screen.BLOCK_SIZE); // Index de colonne
		int j = (int) (4 + (character.getPosX()) / Screen.BLOCK_SIZE); // Index de ligne

		character.setEtoiles(character.getEtoiles() + 1);
		int[][] newStars = level.getStars();
		newStars[i][j] = -1;
		level.setStars(newStars);
	}

	public static void bossHurts(entities.Character character, entities.Boss boss) {
		float characX = (float) (character.getPosX()) / (float) (Screen.BLOCK_SIZE)
				- (float) (character.getPosX() % Screen.BLOCK_SIZE) / (float) (Screen.BLOCK_SIZE);
		if (boss.getxBlock() - characX == 4.0 && boss.getPosY() == character.getPosY() && !character.isHurting()) {
			characterHurt(character);
		}
	}

	public static void characterHurt(entities.Character character) {
		if (!character.isHurting()) {
			character.setNbCoeurs(character.getNbCoeurs() - 1);
			System.out.println(character.getNbCoeurs());
			character.setHurting(true);
			Timer hurtTimer = new Timer(3000, e -> { // PAS LE MÊME QUE Time !!!!!!!!!
				character.setHurting(false);
			});

			hurtTimer.setRepeats(false);
			hurtTimer.start();
		}
	}
}
