package DAO;  // Le package DAO contient des classes liées à l'accès aux données dans la base de données.

import java.sql.Connection;  // Importation de la classe Connection pour gérer les connexions à la base de données.
import java.sql.PreparedStatement;  // Importation de la classe PreparedStatement pour exécuter des requêtes SQL.
import java.sql.SQLException;  // Importation de la classe SQLException pour gérer les erreurs liées aux bases de données.

public abstract class DAO<T> {  // Déclaration de la classe DAO générique, qui sera utilisée comme classe de base pour gérer les objets de type T dans la base de données.

    protected Connection cn;  // Variable membre pour stocker la connexion à la base de données.
    protected PreparedStatement stm;  // Variable membre pour préparer et exécuter des requêtes SQL.

    /* 
     * Ouvre une connexion à la base de données et prépare une requête SQL.
     * @param req La requête SQL à préparer.
     */
    public void open(String req) {  
        cn = SingleConnection.getInstance();  // Obtient une instance de connexion à la base de données via un singleton.
        
        try {
            stm = cn.prepareStatement(req);  // Prépare la requête SQL en utilisant la connexion.
        } catch (SQLException e) {  // Gère l'exception SQL si la préparation échoue.
            e.printStackTrace();  // Affiche l'erreur dans la console.
        }
    }

    /* 
     * Méthode abstraite pour créer un objet dans la base de données.
     * @param obj L'objet de type T à créer dans la base de données.
     * @return L'objet créé (de type T).
     */
    public abstract T create(T obj);  // Méthode abstraite à implémenter dans les sous-classes pour créer un objet dans la base de données.

    /* 
     * Méthode abstraite pour mettre à jour un objet dans la base de données.
     * @param obj L'objet de type T à mettre à jour dans la base de données.
     */
    public abstract void update(T obj);  // Méthode abstraite à implémenter dans les sous-classes pour mettre à jour un objet dans la base de données.

    /* 
     * Méthode abstraite pour supprimer un objet de la base de données.
     * @param obj L'objet de type T à supprimer de la base de données.
     */
    public abstract void delete(T obj);  // Méthode abstraite à implémenter dans les sous-classes pour supprimer un objet de la base de données.

    /* 
     * Ferme la connexion à la base de données.
     */
    public void close() {  
        SingleConnection.close(cn);  // Ferme la connexion à la base de données via une méthode de la classe SingleConnection.
    }
}
