package level;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.TimerTask;

import javax.swing.Timer;

import dreamTrap.Game;
import dreamTrap.Screen;
import entities.Boss;
import entities.Character;
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

		// Index de colonne
		int i = (int) (LevelManager.getLevelHeight() - Screen.BLOCK_PER_HEIGHT
				+ character.getLevelManager().getyCharacterSpawn() / Screen.BLOCK_SIZE
				+ (character.getPosY() + yMove) / Screen.BLOCK_SIZE);
		if (i + character.getHeight() / Screen.BLOCK_SIZE > 63)
			return false;

		// Index de ligne
		float j = (character.getPosX() + xMove + character.getPosX() % Screen.BLOCK_SIZE) / Screen.BLOCK_SIZE;
		/*
		 * 4 * BLOCK_SIZE / Game.TILES_SIZE BLOCK_SIZE = TILE_SIZE pendant le merge
		 * peut-être uniformiser les noms...
		 */
		int targetedTileUpLeft = LevelManager.level[i][(int) Math.floor(j)];
		int targetedTileUpRight = LevelManager.level[i][(int) Math.ceil(j)];
		int targetedTileDownLeft = LevelManager.level[i + character.getHeight() / Screen.BLOCK_SIZE][(int) Math
				.floor(j)];
		int targetedTileDownRight = LevelManager.level[i + character.getHeight() / Screen.BLOCK_SIZE][(int) Math
				.ceil(j)];

		// Determine if the tile is solid based on its value

		if (targetedTileUpLeft > -1 && targetedTileUpLeft < LevelManager.blocksLength
				|| targetedTileUpRight > -1 && targetedTileUpRight < LevelManager.blocksLength
				|| targetedTileDownLeft > -1 && targetedTileDownLeft < LevelManager.blocksLength
				|| targetedTileDownRight > -1 && targetedTileDownRight < LevelManager.blocksLength) {
			return true; // The tile is solid
		}

		return false; // The tile is not solid

	}

	public static void OnStar(entities.Character character, LevelManager level) {

		// Index de colonne
		int i = (int) (LevelManager.getLevelHeight() - Screen.BLOCK_PER_HEIGHT
				+ character.getLevelManager().getyCharacterSpawn() / Screen.BLOCK_SIZE
				+ (character.getPosY() + (character.getPosY() % Screen.BLOCK_SIZE)) / Screen.BLOCK_SIZE);
		// Index de ligne
		int j = character.getPosX() / Screen.BLOCK_SIZE;
		if (i < 63 && (level.getStars()[i + 1][j] != -1 || level.getStars()[i][j] != -1)) {
			gotStar(character, level, i, j);
		}
	}

	public static void OnSpike(entities.Character character, LevelManager level) {

		// Index de colonne
		int i = (int) (LevelManager.getLevelHeight() - Screen.BLOCK_PER_HEIGHT
				+ character.getLevelManager().getyCharacterSpawn() / Screen.BLOCK_SIZE
				+ (character.getPosY() + (character.getPosY() % Screen.BLOCK_SIZE)) / Screen.BLOCK_SIZE);
		// Index de ligne
		int j = character.getPosX() / Screen.BLOCK_SIZE;
		if (i < 63 && level.getSpikes()[i][j] != -1) {
			characterHurt(character);
		}

	}

	public static void gotStar(entities.Character character, LevelManager level, int i, int j) {
		if (level.stars[i + 1][j] != -1) {
			character.setEtoiles(character.getEtoiles() + 1);
			level.stars[i + 1][j] = -1;
		}
		if (level.stars[i][j] != -1) {
			character.setEtoiles(character.getEtoiles() + 1);
			level.stars[i][j] = -1;
		}
	}
	
	public static void bossHurts(entities.Character character, entities.Boss boss) {
		float characX = (float) (character.getPosX()) / (float) (Screen.BLOCK_SIZE)
				- (float) (character.getPosX() % Screen.BLOCK_SIZE) / (float) (Screen.BLOCK_SIZE);
		if (boss.getxBlock() - characX == 0 && boss.getPosY() == character.getPosY() && !character.isHurting()) {
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
