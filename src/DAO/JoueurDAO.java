package DAO;  // Le package DAO contient des classes liées à l'accès aux données dans la base de données.

import java.sql.ResultSet;  // Importation de la classe ResultSet pour manipuler les résultats d'une requête SQL.
import java.sql.SQLException;  // Importation de la classe SQLException pour gérer les erreurs liées aux bases de données.

import entities.Character;  // Importation de la classe Character représentant un joueur dans le jeu.
import entities.Progression;  // Importation de la classe Progression représentant les progrès du joueur dans le jeu.

public class JoueurDAO extends DAO<Object> {  // Classe qui étend la classe DAO générique pour gérer les objets Character (joueur) dans la base de données.
    
    private progressionDAO progress = new progressionDAO();  // Instance de la classe progressionDAO pour gérer la progression d'un joueur.
    private static Progression progression;  // Variable statique pour stocker l'objet Progression du joueur.

    /* 
     * Crée un nouvel objet Character (joueur) dans la base de données.
     * @param obj Le nom du joueur à créer dans la base de données.
     * @return L'objet Character (joueur) créé.
     */
    @Override
    public Character create(Object obj) {
        Character character = null;
        String nom = (String) obj;  // Le nom du joueur est récupéré à partir de l'objet passé en paramètre.
        int niveau = 1;  // Le niveau initial du joueur est fixé à 1.
        int etoiles = 0;  // Les étoiles du joueur sont initialisées à 0.
        
        // Requête SQL pour insérer un joueur dans la base de données.
        String req = "INSERT INTO joueur(nom,niveau,etoiles) VALUES(?,?,?)";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            // Paramètre de la requête
            this.stm.setString(1, nom);
            this.stm.setInt(2, niveau);
            this.stm.setInt(3, etoiles);
            this.stm.executeUpdate();  // Exécute la mise à jour dans la base de données.
            
            // Récupère l'ID du joueur nouvellement créé.
            String getIdQuery = "SELECT LAST_INSERT_ID()"; 
            this.open(getIdQuery);  // Ouvre la connexion à la base de données pour récupérer l'ID.
            ResultSet rs = this.stm.executeQuery();  // Exécute la requête.
            int userId = 0;
            if (rs.next()) {  // Si un ID est retourné, on crée un objet Character.
                userId = rs.getInt(1);
                character = new Character(userId, nom, niveau, etoiles);
                System.out.println("ici 1 " + character);
            }
        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }
        System.out.println("ici 2 " + character);
        
        return character;  // Retourne l'objet Character créé.
    }

    /* 
     * Recherche un joueur par son nom dans la base de données.
     * Si le joueur existe et a gagné une partie, on retourne l'objet Character du joueur.
     * Sinon, on crée un nouveau joueur.
     * @param playerName Le nom du joueur à rechercher.
     * @return L'objet Character représentant le joueur trouvé ou créé.
     */
    public Character find(String playerName) {
        Character character = null;
        
        // Requête SQL pour trouver un joueur avec un nom spécifique et une victoire dans la table "progression".
        String req = "SELECT * FROM joueur J JOIN avoir A ON J.idnom = A.idnom INNER JOIN progression P ON A.idprogression = P.idprogression WHERE J.nom LIKE ? AND P.win=1";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            this.stm.setString(1, playerName);  // Paramètre de la requête (nom du joueur).
            ResultSet rs = this.stm.executeQuery();  // Exécute la requête.
            
            if (rs.next()) {  // Si un joueur est trouvé avec une victoire, on le retourne.
                int id = rs.getInt("idnom");  // Récupère l'ID du joueur.
                String nom = rs.getString("nom");  // Récupère le nom du joueur.
                int niveau = rs.getInt("niveau");  // Récupère le niveau du joueur.
                int etoiles = rs.getInt("etoiles");  // Récupère les étoiles du joueur.
                character = new Character(id, nom, niveau, etoiles);  // Crée un objet Character avec les données récupérées.
                progression = progress.getProgressionByJoueurId(id);  // Récupère la progression du joueur.
            } else {
                // Si aucun joueur n'est trouvé, crée un nouveau joueur.
                character = create(playerName);
                System.out.println("ici 3 " + character);
                progression = new Progression(playerName, "0", 1);  // Crée un objet Progression initialisé à "0".
                progress.create(progression);  // Crée la progression du joueur dans la base de données.
            }
        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }
        return character;  // Retourne l'objet Character trouvé ou créé.
    }

    /* 
     * Charge les données du joueur à partir de la base de données (utilise la méthode find).
     * @param name Le nom du joueur à charger.
     * @return L'objet Character représentant le joueur.
     */
    public Character loadsave(String name) {
        // Utilise la méthode find pour récupérer le joueur par son nom.
        return find(name);
    }

    /* 
     * Met à jour les données d'un joueur dans la base de données (niveau et étoiles).
     * @param obj L'objet Character à mettre à jour.
     */
    @Override
    public void update(Object obj) {
        Character character = (Character) obj;  // Conversion de l'objet en Character.
        int niveau = character.getNiv();  // Récupère le niveau du joueur.
        int etoiles = character.getEtoiles();  // Récupère le nombre d'étoiles du joueur.
        int id = character.getId();  // Récupère l'ID du joueur.

        // Requête SQL pour mettre à jour les informations du joueur.
        String req = "UPDATE joueur SET niveau=?, etoiles=? WHERE idnom = ?";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            // Paramètre de la requête
            this.stm.setInt(1, niveau);
            this.stm.setInt(2, etoiles);
            this.stm.setInt(3, id);
            this.stm.executeUpdate();  // Exécute la mise à jour dans la base de données.
        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }
    }

    /* 
     * Met à jour les informations d'un joueur dans la table "joueur".
     * @param character L'objet Character contenant les nouvelles informations du joueur.
     */
    public void updateTable(Character character) {
        int niveau = character.getNiv();  // Récupère le niveau du joueur.
        int etoiles = character.getEtoiles();  // Récupère le nombre d'étoiles du joueur.
        int id = character.getId();  // Récupère l'ID du joueur.

        // Requête SQL pour mettre à jour les informations du joueur dans la table "joueur".
        String req = "UPDATE joueur SET niveau = ?, etoiles = ? WHERE idnom = ?";
        this.open(req);  // Ouvre la connexion à la base de données et prépare la requête.
        try {
            // Paramètre de la requête
            this.stm.setInt(1, niveau);
            this.stm.setInt(2, etoiles);
            this.stm.setInt(3, id);
            this.stm.executeUpdate();  // Exécute la mise à jour dans la base de données.
        } catch (SQLException e) {  // Gestion des erreurs SQL.
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object obj) {
    }
}
