package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Character;
import entities.Item;
import entities.Progression;



public class JoueurDAO extends DAO {
	private progressionDAO  progress = new progressionDAO();
	private static Progression progression;
	
	@Override
	public Character create(Object obj) {
		Character character=null;
		String nom = (String)obj;
		int niveau = 1;
		int etoiles=0;
		
		String req="INSERT INTO joueur(nom,niveau,etoiles) VALUES(?,?,?)";
		this.open(req);
		try {
			this.stm.setString(1, nom);
			this.stm.setInt(2, niveau);
			this.stm.setInt(3, etoiles);
			this.stm.executeUpdate();
			
			 String getIdQuery = "SELECT LAST_INSERT_ID()"; 
             this.open(getIdQuery);
             ResultSet rs = this.stm.executeQuery();
             int userId = 0;
             if (rs.next()) {
                userId = rs.getInt(1);
                 character= new Character(userId,nom,niveau,etoiles);
                System.out.println("ici 1 "+character);
             }
            
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		 System.out.println("ici 2 "+character);
		
		return character;
	}

	public Character find(String playerName ) {
	    Character character = null;
	    
	    String req = "SELECT * FROM joueur J JOIN avoir A ON J.idnom = A.idnom INNER JOIN progression P ON A.idprogression = P.idprogression WHERE J.nom LIKE ? AND P.win=1";
	    this.open(req);
	    try {
	        this.stm.setString(1, playerName);
	        ResultSet rs = this.stm.executeQuery();
	        
		        if (rs.next()) {
		            int id = rs.getInt("idnom"); 
		            String nom =rs.getString("nom");
		            int niveau = rs.getInt("niveau");
		            int etoiles = rs.getInt("etoiles");
		            character = new Character(id, nom, niveau, etoiles); 
		           progression=progress.getProgressionByJoueurId(id);
		           // System.out.println(character);
		        }
	        
		        
	        else {
	        	 // check if we have a load already and so if we got a select * from joueur where OIN  avoir A ON J.idnom = A.idnom INNER JOIN progression P  ON A.idprogression = P.idprogression WHERE win=1
	        	character=create(playerName);
	        	 System.out.println("ici 3 "+character);
	        	progression=new Progression(playerName,"0",1);
	        	progress.create(progression);
	        	
	        }
	        	
	   
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return character;
	}
	
	public Character loadsave(String name) {
		// faudrais recuperer le nom joeur 
		
		return find(name);
		
	}
	
	//update niveau
	@Override
	public void update(Object obj) {
		
		Character character= (Character)obj;
		 int niveau=character.getNiv();
		 int id=character.getId();

		
		String req="UPDATE item SET niveau=? WHERE idnom = ?";
		this.open(req);
		try {
			this.stm.setInt(1, niveau);
			this.stm.setInt(2, id);
			
			this.stm.executeUpdate();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		

	}

public Object updateStar(Object obj) {
		
	Character character= (Character)obj;
	 int star=character.getEtoiles();
	 int id=character.getId();

	
	String req="UPDATE item SET etoiles=? WHERE idnom = ?";
	this.open(req);
	try {
		this.stm.setInt(1, star);
		this.stm.setInt(2, id);
		
		this.stm.executeUpdate();
		
		
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	
	return obj;
	}
	
	

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}
	

}
