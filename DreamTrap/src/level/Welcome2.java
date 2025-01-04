package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dreamTrap.Game;
import dreamTrap.Screen;
import entities.Progression;
import entities.Character;

public class Welcome2 {


			public final static int BLOCK_SIZE = 64;
			public final static int BLOCK_PER_WIDTH = 24;
			public final static int BLOCK_PER_HEIGHT = 13;
			private final static float SCALE = 1f;
			private boolean ShowZ=false;
			private boolean ShowS=false;
			
			
			
			public Welcome2() {}
			
			public void draw(Graphics g) {
			
				
					
					g.setColor(new Color(0,0,0,250));
					g.fillRect(0, 0, (int)(BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),(int)(BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE));
					int x ; int y ;String text;
					g.setFont(g.getFont().deriveFont(Font.BOLD,40f));
					text="Vous ouvrez les yeux… mais vous rêvez encore.";
					g.setColor(Color.white);
					x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 5);
					y= 250;
				
				    g.drawString(text, x, y);
				  
				  
					g.setFont(g.getFont().deriveFont(30f));
					text="Entrez votre nom de rêveur ";
					g.setColor(Color.white);
					x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 2.75);
					y= 375;
				
				    g.drawString(text, x, y);
				   
				   // a faire 
				    /*
				     -create la saisie de texte et actionlistener 
				     qui recup le nom 
				     
				     -changer la methode de loadjoeur que ca load si et seulement si il ya une progression ou win=1; sinon sa cree le joueur
				     
				    - ducoup pour ce fair tu change la methode createnew dans scoreScreen  qui doit maintenant cree une progression a temp null et win=1 
				     puis apres tu crée une nouvelle methode update dans scroreScreen qui appel update de ProgressionDAO
				     qui doit modifie/update  temps avec le nouveau tmp et win a 0;
				     
				     - gerer ce qui ce passe quand on appuies sur les bouttons  les passages entre different ecran voir code virgini sur git
				     
				     si fini 
				     -gerer le bouttons setting  
				     
				     */

				    
		
				    
				    
				}
			
			
			public void ShowZ(boolean showZ) {
				ShowZ = showZ;
			}
			
			
			public void ShowS(boolean showS) {
				ShowS = showS;
			}
			
			



}
