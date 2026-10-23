package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;


import entities.Progression;
import entities.Character;


public class ScoreScreen extends ScoreList {

	private List<Progression> TopfiveList;
	private ScoreList scorelist;
	private Character character;
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 24;
	public final static int BLOCK_PER_HEIGHT = 13;
	private final static float SCALE = 1f;
	public ScoreScreen(Character character) {
		 super(); // scrolist constructor
	     this.character = character; 
	     scorelist=ScoreList.getInstance(); // get all the progression
	     TopfiveList=scorelist.getTopfive();
	     //timer=game.getTimer();
	        
	    }


	
	public void drawScroreScreen(Graphics g) {

		
		g.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, (int)(BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),(int)(BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE));
		int x ; int y ;String text;
		g.setFont(g.getFont().deriveFont(Font.BOLD,110f));
		text="Score";
		x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 3);
		y= 200;
		g.setColor(Color.white);
	    g.drawString(text, x, y);
	    
	   
	    y = 250;
	    int i=1;
	    g.setFont(g.getFont().deriveFont(20f));
	    for(Progression progression : TopfiveList) {
	    	text = i + ". " + progression.getNom() + " - " + progression.getTmp();
            g.drawString(text, x, y); 
            y += 30; 
            i++; 
	    }
	    
	    
	}
}
