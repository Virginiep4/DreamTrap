package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dreamTrap.Game;
import dreamTrap.Screen;
import entities.Progression;
import entities.Character;

public class Welcome {






		private Character character;
		public final static int BLOCK_SIZE = 64;
		public final static int BLOCK_PER_WIDTH = 24;
		public final static int BLOCK_PER_HEIGHT = 13;
		private final static float SCALE = 1f;
		private boolean ShowZ=false;
		private boolean ShowS=false;
		private int CommandNum;
		
		
		public Welcome(Character character) {
			        this.character = character; 
			    }
		
		public void draw(Graphics g,Character character,LevelManager levelManager) {
		
			
				
				g.setColor(new Color(0,0,0,250));
				g.fillRect(0, 0, (int)(BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),(int)(BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE));
				int x ; int y ;String text;
				g.setFont(g.getFont().deriveFont(Font.BOLD,110f));
				text="Dream Trap";
				g.setColor(Color.white);
				x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 4);
				y= 200;
			
			    g.drawString(text, x, y);
			   
			    g.drawImage(character.getCharacter()[0][0],
			    		((int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 3)),250,200,182, null);
			    

			    
			    if (this.character.moving.isUp()) {ShowZ(true); ShowS(false);}
				
			    // Play Again 
			    g.setFont(g.getFont().deriveFont(40f));
				text="PlAY";
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
				text="";
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
