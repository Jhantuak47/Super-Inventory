package com.superInvent.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
	private String url = "jdbc:mysql://localhost:3306/InventoryManagement";
	private String username = "root";
	private String pass = "";
	protected Connection con;
	private Statement st;
	
	public JDBCConnection() {
		con = (Connection) this.getConnection();
	}
	
	private Object getConnection() {
		try {
			//registering driver...
			Class.forName("com.mysql.jdbc.Driver");
			//creating connection...
			Connection con = DriverManager.getConnection(url,username,pass);
			 return con;
		}catch(Exception obj) {
			System.out.println(obj.getMessage());
		}
		return null;
	}
	
	protected ResultSet createStatement(String query) throws SQLException{
		st = con.createStatement();
		//executing query....
		ResultSet rs = st.executeQuery(query);
		
		return rs;
	}
	protected void executeUpdate(String query) throws SQLException {
		st = con.createStatement();
	      st.executeUpdate(query);
	}

}
