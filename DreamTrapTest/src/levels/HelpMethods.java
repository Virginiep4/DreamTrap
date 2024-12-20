package levels;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import dreamTrap.Game;
import levels.LevelManager;

public class HelpMethods {


	
		 // Checks if the entity can move to the specified position (x, y) with given width and height
	    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
	        // Check all four corners of the entity's bounding box
	    	if (!IsSolid(x, y, lvlData))
				if (!IsSolid(x + width, y + height, lvlData))
					if (!IsSolid(x + width, y, lvlData))
						if (!IsSolid(x, y + height, lvlData))
							return true;
			return false;	    
			
	    }

	    // Checks if the position (x, y) is solid (i.e., has an obstacle)
	    private static boolean IsSolid(float x, float y, int[][] lvlData) {
	    	 
	    		
	    	// Check if the position is out of bounds
	        if (x < 0) return true; // Out of left/right bounds
	        if (y < 0 || y >= Game.GAME_HEIGHT) return true; // Out of top/bottom bounds
	        //|| x >= Game.GAME_WIDTH
	        
	        // Calculate the tile index based on the position
	        float i = y / Game.TILES_SIZE; // Index de ligne
	        float j = x /Game.TILES_SIZE; // Index de colonne
	        // Get the value of the tile at the calculated index
	        int value = lvlData[(int)i][(int)j];

	        System.out.println("i: "+i+"j: "+j+"value : " +value);
	        System.out.println("x:"+x+""+"y: "+y);
	        // Determine if the tile is solid based on its value
	       
	        if ((value!=0)) {
	        	System.out.println("Le joueur est sur un bloc solide.");
	            return true; // The tile is solid
	            	        }
	      
	        System.out.println("Le joueur est dans un espace vide.");
	        return false; // The tile is not solid
	        
	    }

	    // Gets the X position of the entity next to a wall
	    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
	        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
	        if (xSpeed > 0) {
	            // Moving right
	            int tileXPos = currentTile * Game.TILES_SIZE;
	            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
	            return tileXPos + xOffset - 1; // Position just before the wall
	        } else {
	            // Moving left
	            return currentTile * Game.TILES_SIZE; // Position at the wall
	        }
	    }

	    // Gets the Y position of the entity under a roof or above the floor
	    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
	        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
	        if (airSpeed > 0) {
	            // Falling - touching the floor
	            int tileYPos = currentTile * Game.TILES_SIZE;
	            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
	            return tileYPos + yOffset - 1; // Position just above the floor
	        } else {
	            // Jumping
	            return currentTile * Game.TILES_SIZE; // Position at the ceiling
	        }
	    }

	    // Checks if the entity is on the floor
	    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
	        // Check the pixel below the bottom left and bottom right of the hitbox
	        if (!IsSolid((int)hitbox.x,(int) hitbox.y + (int)hitbox.height + 1, lvlData) && 
	            !IsSolid((int)hitbox.x + (int)hitbox.width, (int)hitbox.y + (int)hitbox.height + 1, lvlData)) {
	            return false; // Not on the floor if both positions are not solid
	        }
	        return true; // On the floor if at least one position is solid
	    }
}
