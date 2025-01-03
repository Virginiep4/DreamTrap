package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dreamTrap.Game;
import dreamTrap.Screen;
import entities.Progression;
import entities.Character;
public class GameOverScreen {


	private Character character;
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 24;
	public final static int BLOCK_PER_HEIGHT = 13;
	private final static float SCALE = 1f;
	private boolean ShowZ=false;
	private boolean ShowS=false;
	private int CommandNum;
	
	
	public GameOverScreen(Character character) {
		        this.character = character; 
		    }
	
	public void drawGameOverScreen(Graphics g,Character character,LevelManager levelManager) {
	
		
			
			g.setColor(new Color(0,0,0,150));
			g.fillRect(0, 0, (int)(BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),(int)(BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE));
			int x ; int y ;String text;
			g.setFont(g.getFont().deriveFont(Font.BOLD,110f));
			text="Game Over";
			
			x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 4);
			y= 200;
			g.setColor(Color.white);
		    g.drawString(text, x, y);
		   
		    g.drawImage(character.getCharacter()[0][0],
		    		((int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 3)),250,200,182, null);
		    

		    
		    if (this.character.moving.isUp()) {ShowZ(true); ShowS(false);}
			
		    // Play Again 
		    g.setFont(g.getFont().deriveFont(40f));
			text="Retry";
			x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 4);
			y= 500;
			g.drawString(text, x, y);
			if (ShowZ) {
				g.drawString(">", x-40, y);
				CommandNum=0;
				
			}
			
			if(this.character.moving.isDown()){ShowZ(false); ShowS(true);}
			//Exit 
			g.setFont(g.getFont().deriveFont(40f));
			text="Exit";
			x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 4);
			y= 600;
			g.drawString(text, x, y);
			
			if (ShowS) {
				g.drawString(">", x-40, y);
				CommandNum=1;
			}
			
		    
		    
		}
	
	
	public void ShowZ(boolean showZ) {
		ShowZ = showZ;
	}
	
	
	public void ShowS(boolean showS) {
		ShowS = showS;
	}
	
	}
