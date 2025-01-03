package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Item;
import entities.Progression;

public class ItemDAO extends DAO{

	public Object create(Object obj) {
		
		Item item= (Item)obj;
		
		String nom = item.getNom();
		float prix = item.getPrix();
		String description = item.getDesciption();
		int Acquis = item.getAcquis();
		
		
		String req="INSERT INTO item(nom,prix,description,Acquis) VALUES(?,?,?,?)";
		this.open(req);
		try {
			this.stm.setString(1, nom);
			this.stm.setFloat(2, prix);
			this.stm.setString(3, description);
			this.stm.setInt(4, Acquis);
			
			this.stm.executeUpdate();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
		return obj;
	}

	
	// update Acquis (savoir si l'items est posseder par le joueur ou non)
	// si tu veux utiliser avec acquis sinon tu utilise la table posseder 
	
	public void update(Object obj) {
		
		Item item= (Item)obj;
		item.setAcquis(1);
		int Acquis = item.getAcquis();
		System.out.println(Acquis);
		int id=item.getId();
		String req="UPDATE item SET Acquis=? WHERE idItem = ?";
		this.open(req);
		try {
			this.stm.setInt(1, Acquis);
			this.stm.setInt(2, id);
			
			this.stm.executeUpdate();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	
	}


	public void addInventory(int pseudo, int itemId) {
        String req = "INSERT INTO posseder (idnom, idItem) VALUES (?, ?)";
        this.open(req);
        try {
            this.stm.setInt(1, pseudo);
            this.stm.setInt(2, itemId);
            this.stm.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
        
	public Item getById(int itemId) {
		String nom = "";
		float prix ;
		String description = "";
		int Acquis ;
		Item item = null;
        String req = "SELECT * FROM item WHERE idItem = ?";
        this.open(req);
        try {
            stm.setInt(1, itemId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
            	 nom = rs.getString("nom"); 
                prix = rs.getInt("prix"); 
                description= rs.getString("description"); 
                Acquis= rs.getInt("Acquis"); 

              
                 item = new Item(itemId,nom, prix, description,Acquis);
               
               
            }
        }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
        return item;
		}
	public void delete(Object obj) {
		//null
	}

	
}
