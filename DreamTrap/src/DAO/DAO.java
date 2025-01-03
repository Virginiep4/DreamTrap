package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class DAO<T> {

	protected Connection cn;
	protected PreparedStatement stm;
	
	public void open(String req) {
		
		cn = SingleConnection.getInstance();
		
		try {
		stm= cn.prepareStatement(req);
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public abstract T create(T obj);
	public abstract void update(T obj);
	public abstract void delete(T obj);
	
	public void close() {
		SingleConnection.close(cn);
	}
}