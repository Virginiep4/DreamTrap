package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import entities.Character;
import entities.Item;
import level.Welcome2;

public class JoueurDAO extends DAO {
	@Override
	public Object create(Object obj) {
		Character character = (Character) obj;

		String nom = character.getNom();
//		int niveau = character.getNiv();
//		int etoiles = character.getEtoiles();

		String req = "INSERT INTO progression(nom_joueur) VALUES(?)";
		this.open(req);
		try {
			this.stm.setString(1, Welcome2.nom);
			this.stm.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		req = "INSERT INTO joueur(idprogression, nom) VALUES(?,?)";
		int idProgression = getLinkedProgression();
		this.open(req);
		try {
			this.stm.setInt(1, idProgression);
			this.stm.setString(2, Welcome2.nom);
			this.stm.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return obj;
	}

	private int getLinkedProgression() {
		int idProgression = 0;
		
		String req = "SELECT idprogression FROM progression P WHERE nom_joueur='" + Welcome2.nom + "' AND win=1";
		this.open(req);
		try {
			ResultSet rs = this.stm.executeQuery();
			if (rs.next()) {
				idProgression = rs.getInt("idnom");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idProgression;
	}

	public Character find() {
		Character character = null;
		String req = "SELECT * FROM joueur J INNER JOIN progression P ON P.idprogression = J.idprogression WHERE nom='"
				+ Welcome2.nom + "' AND win=1";
		this.open(req);
		try {
			// this.stm.setString(1, nom);
			ResultSet rs = this.stm.executeQuery();
			int id = 1;
			if (rs.next()) {
				id = rs.getInt("idnom");
				String nom = rs.getString("nom");
				int niveau = rs.getInt("niveau");
				int etoiles = rs.getInt("etoiles");
				character = new Character(id, nom, niveau, etoiles);
			} else if (character == null) {
				character = (Character) create(new Character(id, Welcome2.nom, 0, 0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return character;
	}

	public Character loadsave() {
		// faudrais recuperer le nom joeur

		return find();

	}

	// update niveau
	@Override
	public void update(Object obj) {

		Character character = (Character) obj;
		int niveau = character.getNiv();
		int id = character.getId();

		String req = "UPDATE item SET niveau=? WHERE idnom = ?";
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

		Character character = (Character) obj;
		int star = character.getEtoiles();
		int id = character.getId();

		String req = "UPDATE item SET etoiles=? WHERE idnom = ?";
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
