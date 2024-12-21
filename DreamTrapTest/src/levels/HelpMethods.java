package levels;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.lang.Math;

import dreamTrap.Game;
import dreamTrap.Screen;
import levels.LevelManager;

public class HelpMethods {

	// Checks if the entity can move to the specified position (x, y) with given
	// width and height
	public static boolean CanMoveHere(entities.Character character, float xMove, float yMove) {
		// Check all four corners of the entity's bounding box
		if (!IsSolid(character, xMove, yMove))
			return true;
		return false;

	}

	// Checks if the position (x, y) is solid (i.e., has an obstacle)
	private static boolean IsSolid(entities.Character character, float xMove, float yMove) {

		// Check if the position is out of bounds
		if (character.getPosY() + yMove - 2 * Game.TILES_SIZE< -Game.GAME_HEIGHT)
			return true; // Out of top/bottom bounds

		// Calculate the tile index based on the position
		int i = LevelManager.getLevelHeight() - 2 ; // Index de colonne
		int j = character.getPosX() / Game.TILES_SIZE + character.getPosX() % Game.TILES_SIZE; // Index de ligne
		
		// Get the value of the tile at the calculated index
		int valueXMin = character.getLvlData()[i + (int) (character.getPosY()) / Game.TILES_SIZE]
				[(int) (Math.ceil(xMove) / Game.TILES_SIZE) + j];
		int valueXMax = character.getLvlData()[i + (int) (character.getPosY()) / Game.TILES_SIZE]
				[(int) Math.floor((character.getWidth() + xMove) / Game.TILES_SIZE) + j];
		int valueYMin = character.getLvlData()
				[i + (int) Math.ceil((character.getPosY() + character.getHeight() + yMove) / Game.TILES_SIZE)][(int) j];
		int valueYMax = character.getLvlData()
				[i + (int) Math.floor((character.getPosY() + yMove) / Game.TILES_SIZE)][(int) j];

		// Determine if the tile is solid based on its value

		if ((valueXMin != -1) || (valueXMax != -1) || (valueYMin != -1) || (valueYMax != -1)) {
			System.out.println("Le joueur est sur un bloc solide.");
			return true; // The tile is solid
		}

		return false; // The tile is not solid

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
