package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Progression;

public class progressionDAO extends DAO{

	@Override
	public Object create(Object obj) {
		
		Progression progression= (Progression)obj;
		String nom=progression.getNom();
		int tmp=progression.getTmp(); 
	    int win=progression.getWin();
		if (progression.getWin() == 1) { 
	            String req = "INSERT INTO progression(nom_joueur, temps, win) VALUES(?, ?, ?)";
	            this.open(req);
	            try {
	                this.stm.setString(1,nom); 
	                this.stm.setInt(2,tmp); 
	                this.stm.setInt(3,win); 
	                this.stm.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Le joueur n'a pas gagné, aucune progression ajoutée.");
	        }
	    
		
		return null;
	}

	public List<Progression> getProgressionByJoueurId(int joueurId) {
        List<Progression> progressions = new ArrayList<>();
        String req = "SELECT * FROM progression P JOIN  avoir A ON P.idprogression = A.idprogression JOIN joueur j ON a.idnom = j.idnom WHERE joueur_id = ?"; 

        this.open(req);
        try {
            this.stm.setInt(1, joueurId); 
            ResultSet rs = this.stm.executeQuery();

            
            while (rs.next()) {
                String nom = rs.getString("nom_joueur"); 
                int temps = rs.getInt("temps"); 
                int win = rs.getInt("win"); 

              
                Progression progression = new Progression(nom, temps, win);
                progressions.add(progression);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progressions; // Retourner la liste des progressions
    }

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

}
