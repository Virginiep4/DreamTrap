package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Character;
import entities.Item;



public class JoueurDAO extends DAO {
	@Override
	public Object create(Object obj) {
		Character character = (Character)obj;
		
		String nom = character.getNom();
		int niveau = character.getNiv();
		int etoiles=character.getEtoiles();
		
		String req="INSERT INTO joueur(nom,niveau,etoiles) VALUES(?,?,?)";
		this.open(req);
		try {
			this.stm.setString(1, nom);
			this.stm.setInt(2, niveau);
			this.stm.setInt(3, etoiles);
			this.stm.executeUpdate();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
		return obj;
	}

	public Character find( String playerName ) {
	    Character character = null;
	    
	    String req = "SELECT * FROM joueur J  JOIN  avoir A ON J.idnom = A.idnom INNER JOIN progression P  ON A.idprogression = P.idprogression WHERE J.nom=? AND win=1";
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
		            System.out.println(character);
		        }
	        
		        
	        else {
	        	character = new Character(0, playerName, 0, 0); 
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
		// TODO Auto-generated method stub
		
	}
	

}
