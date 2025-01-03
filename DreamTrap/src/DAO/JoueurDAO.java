package DAO;

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

	public Character find(String nom) {
	    Character character = null;
	    String req = "SELECT * FROM joueur WHERE nom = ?";
	    this.open(req);
	    try {
	        this.stm.setString(1, nom);
	        ResultSet rs = this.stm.executeQuery();
	        if (rs.next()) {
	            int id = rs.getInt("idnom"); 
	            int niveau = rs.getInt("niveau");
	            int etoiles = rs.getInt("etoiles");
	            character = new Character(id, nom, niveau, etoiles); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return character;
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