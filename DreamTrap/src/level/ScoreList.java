package level;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dao.JoueurDAO;
import dao.progressionDAO;
import entities.Progression;
import entities.Character;

public class ScoreList {

	private List<Progression> scoreList ;
	private progressionDAO dao = new progressionDAO();
	private JoueurDAO joueur;
	private Character character;
	 private static ScoreList scorelist;
	
	public ScoreList() {
		scorelist=this;
		
		scoreList = dao.getProgressionByJoueurId(); // get all the progression
		 
		
	}
	
	public  List<Progression> getTopfive(){

		List<Progression> topFive = scoreList.stream()
	            .sorted(Comparator.comparing(Progression::getTmp).reversed())
	            .limit(5)
	            .collect(Collectors.toList());

	    
	   

	    return topFive; 
				
	}

	public List<Progression> getScoreList() {
		return scoreList;
	}
	
	public static ScoreList getInstance() {
        return scorelist; 
    }

	@Override
	public String toString() {
		return "ScoreList [scoreList=" + scoreList + "]";
	}

	
	
	// faire appel au info stocker dans class loadbackup soit nom idjoeur ect 
    
	
}
