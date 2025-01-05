package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SingleConnection {

	private static Connection cn = null;
	
	private SingleConnection() {
		String databaseName="dreamtrap";
		String url="jdbc:mysql://localhost/"+databaseName+"?serverTimezone=UTC";
		String login="root"; // dans l'idal un login de connexion pour l'application, et non root...
		String password="root"; // mot de passe avec xampp
		
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		
		try{
		cn=mysqlDS.getConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static Connection getInstance() {
		if (cn==null) {
			new SingleConnection();
		}
		return cn;
		
		
	}
	
	public static void close( Connection cn) {
		try
		{cn.close();}
		catch(SQLException e ) {
			System.err.println("erreur");
			e.getStackTrace();
		}
	}
}
