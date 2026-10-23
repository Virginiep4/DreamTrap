package DAO;  // Le package DAO contient des classes liées à l'accès aux données dans la base de données.

import java.sql.ResultSet;  // Importation de la classe ResultSet pour manipuler les résultats d'une requête SQL.
import java.sql.SQLException;  // Importation de la classe SQLException pour gérer les erreurs liées aux bases de données.
import entities.Character;  // Importation de la classe Character, représentant un personnage (probablement d'un jeu).
import entities.Item;  // Importation de la classe Item, représentant un objet ou item dans le jeu.

public class ItemDAO extends DAO<Object> {  // Classe qui étend la classe DAO pour gérer les objets de type Item.

    /* 
     * Crée un nouvel objet Item dans la base de données.
     * @param obj L'objet de type Item à créer dans la base de données.
     * @return L'objet Item créé.
     */
    public Object create(Object obj) {
        Item item = (Item) obj;  // Conversion de l'objet générique en un objet de type Item.

        // Extraction des informations de l'objet Item
        String nom = item.getNom();  // Récupère le nom de l'item.
        float prix = item.getPrix();  // Récupère le prix de l'item.
        String description = item.getDesciption();  // Récupère la description de l'item.
        boolean AcquisB = item.getAcquis();  // Récupère l'état de possession de l'item.
        int Acquis = AcquisB ? 1 : 0;  // Conversion du booléen en entier pour la base de données.

        // Requête SQL pour insérer l'item dans la table "item".
        String req = "INSERT INTO item(nom,prix,description,Acquis) VALUES(?,?,?,?)";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            // Paramètre de la requête
            this.stm.setString(1, nom);
            this.stm.setFloat(2, prix);
            this.stm.setString(3, description);
            this.stm.setInt(4, Acquis);

            this.stm.executeUpdate();  // Exécute la mise à jour dans la base de données.

        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }

        return obj;  // Retourne l'objet créé.
    }

    /* 
     * Met à jour l'état de possession d'un item (acquis ou non).
     * @param obj L'objet Item à mettre à jour.
     */
    public void update(Object obj) {
        Item item = (Item) obj;  // Conversion de l'objet générique en un objet de type Item.
        boolean AcquisB = item.getAcquis();  // Récupère l'état de possession de l'item.
        int Acquis = AcquisB ? 1 : 0;  // Conversion du booléen en entier pour la base de données.
        
        // Récupère l'id de l'item à mettre à jour.
        int id = item.getId();
        
        // Requête SQL pour mettre à jour l'état "Acquis" dans la table "item".
        String req = "UPDATE item SET Acquis=? WHERE idItem = ?";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            // Paramètre de la requête
            this.stm.setInt(1, Acquis);
            this.stm.setInt(2, id);

            this.stm.executeUpdate();  // Exécute la mise à jour dans la base de données.

        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }
    }

    /* 
     * Ajoute un item à l'inventaire d'un personnage.
     * @param pseudo L'identifiant du personnage.
     * @param itemId L'identifiant de l'item à ajouter.
     */
    public void addInventory(int pseudo, int itemId) {
        System.out.println(pseudo + " " + itemId);  // Affiche les identifiants pour débogage.
        
        // Requête SQL pour insérer un enregistrement dans la table "posseder".
        String req = "INSERT INTO posseder (idnom, idItem) VALUES (?, ?)";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            // Paramètre de la requête
            this.stm.setInt(1, pseudo);
            this.stm.setInt(2, itemId);

            this.stm.executeUpdate();  // Exécute l'insertion dans la base de données.

        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }
    }

    /* 
     * Récupère un item par son identifiant.
     * @param itemId L'identifiant de l'item à récupérer.
     * @return L'objet Item correspondant.
     */
    public Item getById(int itemId) {
        String nom = "";
        float prix = 0;
        String description = "";
        int Acquis = 0;
        Item item = null;

        // Requête SQL pour récupérer un item dans la table "item" par son identifiant.
        String req = "SELECT * FROM item WHERE idItem = ?";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            // Paramètre de la requête
            stm.setInt(1, itemId);

            ResultSet rs = stm.executeQuery();  // Exécute la requête et récupère le résultat.
            while (rs.next()) {  // Parcourt les résultats.
                // Récupération des valeurs du résultat.
                nom = rs.getString("nom");
                prix = rs.getFloat("prix");
                description = rs.getString("description");
                Acquis = rs.getInt("Acquis");

                // Conversion de l'état Acquis en booléen.
                boolean AcquisB = (Acquis == 1);

                // Création d'un objet Item avec les données récupérées.
                item = new Item(itemId, nom, prix, description, AcquisB);
            }
        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }

        return item;  // Retourne l'objet Item récupéré.
    }

    /* 
     * Vérifie si un personnage possède un item.
     * @param character Le personnage à vérifier.
     * @param itemId L'identifiant de l'item.
     * @return true si le personnage possède l'item, false sinon.
     */
    public boolean gotItem(Character character, int itemId) {
        int characterId = character.getId();  // Récupère l'identifiant du personnage.
        
        // Requête SQL pour vérifier si l'item est possédé par le personnage.
        String req = "SELECT * FROM posseder WHERE (idnom = ?) AND (idItem = ?)";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            // Paramètre de la requête
            stm.setInt(1, characterId);
            stm.setInt(2, itemId);

            ResultSet rs = stm.executeQuery();  // Exécute la requête et récupère le résultat.
            if (rs.next()) {  // Si une ligne est retournée, cela signifie que le personnage possède l'item.
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }

        return false;  // Retourne false si une erreur s'est produite ou si l'item n'est pas trouvé.
    }

    /* 
     * Supprime un item de la base de données.
     * Cette méthode est vide et n'est pas encore implémentée.
     * @param obj L'objet Item à supprimer.
     */
    public void delete(Object obj) {
   
    }
}
