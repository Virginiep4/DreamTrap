package entities;

public class Progression {

	    private String nom;
	    private int tmp; 
	    private int win; // Indicateur de victoire (1 pour gagné, 0 pour perdu)

	    public Progression(String nom , int tmp ,int win) {
	        this.tmp = tmp;
	        this.nom = nom;
	        this.win = win;
	    }

	    // Getters
	   

	    public int getWin() {
	        return win;
	    }

		public String getNom() {
			return nom;
		}

		public int getTmp() {
			return tmp;
		}
	
}
