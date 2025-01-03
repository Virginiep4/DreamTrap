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
		if (character.getPosY() + yMove - 2 * Game.TILES_SIZE < -Game.GAME_HEIGHT)
			return true; // Out of top/bottom bounds

		int i = (int) (LevelManager.getLevelHeight() - 2 + (character.getPosY() + yMove) / Game.TILES_SIZE); // Index de
																												// colonne
		float j = (4 + (character.getPosX() + xMove) / Game.TILES_SIZE); // Index de ligne
		/*
		 * 4 * BLOCK_SIZE / Game.TILES_SIZE BLOCK_SIZE = TILE_SIZE pendant le merge
		 * peut-être uniformiser les noms...
		 */
		int targetedTileUpLeft = character.getLvlData()[i][(int) Math.floor(j)];
		int targetedTileUpRight = character.getLvlData()[i][(int) Math.ceil(j)];
		int targetedTileDownLeft = character.getLvlData()[i + character.getHeight() / Game.TILES_SIZE][(int) Math
				.floor(j)];
		int targetedTileDownRight = character.getLvlData()[i + character.getHeight() / Game.TILES_SIZE][(int) Math
				.ceil(j)];

		// Determine if the tile is solid based on its value

		if (targetedTileUpLeft != -1 || targetedTileUpRight != -1 || targetedTileDownLeft != -1
				|| targetedTileDownRight != -1) {
			return true; // The tile is solid
		}

		return false; // The tile is not solid

	}

	public static boolean IsStar(entities.Character character) {

		int i = (int) (LevelManager.getLevelHeight() - 2 + character.getPosY() / Game.TILES_SIZE); // Index de colonne
		int j = (int) (4 + (character.getPosX()) / Game.TILES_SIZE); // Index de ligne

		if (character.getLvlStarsData()[i][j] != -1) {
			return true; // The tile is solid
		}

		return false; // The tile is not solid
	}

	public static void gotStar(entities.Character character, LevelManager level) {
		int i = (int) (LevelManager.getLevelHeight() - 2 + character.getPosY() / Game.TILES_SIZE); // Index de colonne
		int j = (int) (4 + (character.getPosX()) / Game.TILES_SIZE); // Index de ligne

		character.setEtoiles(character.getEtoiles() + 1);
		int[][] newStars = character.getLvlStarsData();
		newStars[i][j] = -1;
		character.setLvlStarsData(newStars);
		level.setStars(newStars);
	}

	public static void bossHurts(entities.Character character, entities.Boss boss) {
		float characX = (float) (character.getPosX()) / (float) (Screen.BLOCK_SIZE)
				- (float) (character.getPosX() % Screen.BLOCK_SIZE) / (float) (Screen.BLOCK_SIZE);
		if (boss.getxBlock() - characX == 4.0 && boss.getPosY() == character.getPosY() && !character.isHurting()) {
			characterHurts(character);
			System.out.println("Coeurs :" + character.getNbCoeurs());
		}
	}

	public static void characterHurts(entities.Character character) {
		character.setNbCoeurs(character.getNbCoeurs() - 1);
		character.setHurting(true);
		Timer hurtTimer = new Timer(3000, e -> { // PAS LE MÊME QUE Time !!!!!!!!!
			character.setHurting(false);
		});

		hurtTimer.setRepeats(false);
		hurtTimer.start();
	}

	// Gets the X position of the entity next to a wall
//	    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
//	        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
//	        if (xSpeed > 0) {
//	            // Moving right
//	            int tileXPos = currentTile * Game.TILES_SIZE;
//	            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
//	            return tileXPos + xOffset - 1; // Position just before the wall
//	        } else {
//	            // Moving left
//	            return currentTile * Game.TILES_SIZE; // Position at the wall
//	        }
//	    }
//
//	    // Gets the Y position of the entity under a roof or above the floor
//	    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
//	        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
//	        if (airSpeed > 0) {
//	            // Falling - touching the floor
//	            int tileYPos = currentTile * Game.TILES_SIZE;
//	            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
//	            return tileYPos + yOffset - 1; // Position just above the floor
//	        } else {
//	            // Jumping
//	            return currentTile * Game.TILES_SIZE; // Position at the ceiling
//	        }
//	    }
//
//	    // Checks if the entity is on the floor
//	    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
//	        // Check the pixel below the bottom left and bottom right of the hitbox
//	        if (!IsSolid((int) hitbox.y + (int)hitbox.height + 1, lvlData) && 
//	            !IsSolid((int)hitbox.y + (int)hitbox.height + 1, lvlData)) {
//	            return false; // Not on the floor if both positions are not solid
//	        }
//	        return true; // On the floor if at least one position is solid
//	    }
}
