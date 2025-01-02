package entities;

import java.time.LocalDateTime;

public class Progression {

	    private String nom;
	    private String tmp; 
	    private int win; // Indicateur de victoire (1 pour gagné, 0 pour perdu)
	    private static Progression progression;
	    
	    public Progression(String nom , String tmp ,int win) {
	        
	    	this.tmp = tmp;
	        this.nom = nom;
	        this.win = win;
	        progression=this;
	    }

	    // Getters
	    public static Progression getInstance() {
	        return progression; 
	    }

	    public int getWin() {
	        return win;
	    }

		public String getNom() {
			return nom;
		}

		public String getTmp() {
			return tmp;
		}

		
		public void setWin(int win) {
			this.win = win;
		}
		

		public void setTmp(String tmp) {
			this.tmp = tmp;
		}

		@Override
		public String toString() {
			return "Progression [nom=" + nom + ", tmp=" + tmp + ", win=" + win + "]";
		}
		
	
}
