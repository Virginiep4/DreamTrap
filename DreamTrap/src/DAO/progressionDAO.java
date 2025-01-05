package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Progression;
import entities.Character;

public class progressionDAO extends DAO {

	private static Character charactere;

	@Override
	public Object create(Object obj) {
		charactere = Character.getInstance();
		Progression progression = (Progression) obj;
		String nom = progression.getNom();
		String tmp = progression.getTmp();
		int win = progression.getWin();

		String req = "INSERT INTO progression(nom_joueur, temps, win) VALUES(?, ?, ?)";
		this.open(req);
		try {
			this.stm.setString(1, nom);
			this.stm.setString(2, tmp);
			this.stm.setInt(3, win);
			this.stm.executeUpdate();

			String getIdQuery = "SELECT LAST_INSERT_ID()";
			this.open(getIdQuery);
			ResultSet rs = this.stm.executeQuery();
			int progressionId = 0;
			if (rs.next()) {
				progressionId = rs.getInt(1);
				progression.setId(progressionId);
				// System.out.println(progressionId);
			}

			String insertAvoirQuery = "INSERT INTO avoir(idnom, idprogression) VALUES(?, ?)";
			this.open(insertAvoirQuery);
			System.out.println("ici 4 " + charactere);
			this.stm.setInt(1, charactere.getId());
			this.stm.setInt(2, progressionId);
			this.stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Progression> getProgression() {
		List<Progression> progressions = new ArrayList<>();
		String req = "SELECT * FROM progression P JOIN  avoir A ON P.idprogression = A.idprogression JOIN joueur J ON A.idnom = J.idnom ";
		// WHERE J.idnom = ?
		this.open(req);
		try {
			// this.stm.setInt(1, joueurId);
			ResultSet rs = this.stm.executeQuery();

			while (rs.next()) {
				String nom = rs.getString("nom_joueur");
				String temps = rs.getString("temps");
				int win = rs.getInt("win");

				Progression progression = new Progression(nom, temps, win);
				progressions.add(progression);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return progressions; // Retourner la liste des progressions
	}

	public int getTimeInSeconds(int progressionId) {
		String req = "SELECT temps FROM progression WHERE idprogression = ?";
		int totalTimeInSeconds = 0;

		this.open(req);
		try {
			this.stm.setInt(1, progressionId);
			ResultSet rs = this.stm.executeQuery();

			if (rs.next()) {
				String timeString = rs.getString("temps"); // get time in HH:mm:ss format
				totalTimeInSeconds = convertTimeToSeconds(timeString);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalTimeInSeconds;
	}

	private int convertTimeToSeconds(String timeString) {
		String[] parts = timeString.split(":");
		int hours = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);
		int seconds = Integer.parseInt(parts[2]);

		// convert in seconds
		return hours * 3600 + minutes * 60 + seconds;
	}

	public void updateTime(int progressionId, String newTime) {
		String req = "UPDATE progression SET temps = ? WHERE idprogression = ?";

		this.open(req);
		try {
			this.stm.setString(1, newTime); // Le nouveau temps au format HH:mm:ss
			this.stm.setInt(2, progressionId);
			this.stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Progression getProgressionByJoueurId(int joueurId) {
		Progression progression = null;
		String req = "SELECT * FROM progression P " + "JOIN avoir A ON P.idprogression = A.idprogression "
				+ "JOIN joueur J ON A.idnom = J.idnom " + "WHERE J.idnom = ?";

		this.open(req);
		try {
			this.stm.setInt(1, joueurId); // Définir l'ID du joueur dans la requête
			ResultSet rs = this.stm.executeQuery();

			if (rs.next()) { // Vérifier s'il y a au moins une progression
				String nom = rs.getString("nom_joueur");
				String temps = rs.getString("temps");
				int win = rs.getInt("win");
				int id = rs.getInt("idprogression");
				progression = new Progression(nom, temps, win);
				progression.setId(id);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return progression; // Retourner la progression trouvée ou null si aucune
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

}
