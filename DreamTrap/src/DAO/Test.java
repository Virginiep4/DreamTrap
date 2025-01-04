package DAO;

import DAO.ItemDAO;
import entities.Item;
import entities.Character;
import entities.Progression;

import java.util.ArrayList;
import java.util.List;
public class Test {

	public static void main(String[] args) {
		
		        ItemDAO itemDAO = new ItemDAO();
		       
		        // Créer un nouvel item
		        Item newItem = new Item(2,"poing", 20, "Une poing tranchante", 0);
		        itemDAO.create(newItem);
		       // System.out.println("Item créé : " + newItem.getNom());
		       
		        itemDAO.addInventory(1, 1);
		        // Récupérer l'item par ID
		        
		        /*List<Item> items = new ArrayList<Item>();
		        		items.add(itemDAO.getById(3));
		        if (!items.isEmpty()) {
		            System.out.println("Item récupéré : " + items.get(0).getNom());
		        }
				*/
		        
		        // Mettre à jour la valeur Acquis de l'item
		        //itemDAO.update(newItem); // Marquer l'item comme acquis
		        
		        JoueurDAO joueurDAO = new JoueurDAO();
		        
		        Character newJoeur = new Character("dede",1,0);
		        
		        joueurDAO.create(newJoeur);
	

}
}
