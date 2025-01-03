package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import dao.progressionDAO;
import dreamTrap.Game;
import dreamTrap.Screen;
import dreamTrap.Time;
import entities.Progression;
import entities.Character;
import level.ScoreList;
import mouvement.Mouvement;
import mouvement.MouvementNormal;


public class ScoreScreen extends ScoreList {

	private List<Progression> TopfiveList;
	private ScoreList scorelist;
	private Game game= Game.getInstance();
	private Time timer;
	private progressionDAO progression;
	private Progression progress;
	private LocalDateTime timeStamp;
	private Screen screen;
	private Character character;
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 24;
	public final static int BLOCK_PER_HEIGHT = 13;
	private final static float SCALE = 1f;
	private boolean ShowZ=false;
	private boolean ShowS=false;
	private int TotalTimeElapsed;

	
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
	    
	    if (character.moving.isUp()) {ShowZ(true); ShowS(false);}
	    // Play Again 
	    g.setFont(g.getFont().deriveFont(40f));
		text="New Partie";
		x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 3);
		y= 500;
		g.drawString(text, x, y);
		if (ShowZ) {
			g.drawString(">", x-40, y);
			// set currentanimation to a number 
			
		}
		
		if(character.moving.isDown()){ShowZ(false); ShowS(true);}
		//Exit 
		g.setFont(g.getFont().deriveFont(40f));
		text="Exit";
		x=(int)((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 3);
		y= 600;
		g.drawString(text, x, y);
		
		if (ShowS) {
			g.drawString(">", x-40, y);
			//set currentanimation to a number
		}
	    
	    
	}
	
	//create new progression 
	public void updateTime() {
		
		progression= new progressionDAO();
		 progress= progress.getInstance();
		
		
		int hour = TotalTimeElapsed/ 3600; 
        int minute = (TotalTimeElapsed% 3600) / 60; 
        int second =  TotalTimeElapsed% 60;
        
        String tmp=String.format("%02d:%02d:%02d", hour, minute, second);
      
       
        progress.setTmp(tmp);
       
		
	}
	
	


	


	public void ShowZ(boolean showZ) {
		ShowZ = showZ;
	}


	public void ShowS(boolean showS) {
		ShowS = showS;
	}



	public void setTotalTimeElapsed(int totalTimeElapsed) {
		TotalTimeElapsed = totalTimeElapsed;
	}
	
	
	
	
}
