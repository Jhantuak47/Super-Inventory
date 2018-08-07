package com.superInvent.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
	private String url = "jdbc:mysql://myinventoryinstance.cpxvt3wvgpau.ap-south-1.rds.amazonaws.com/InventoryManagement";
	private String username = "root";
	private String pass = "98353Jha#";
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
	protected int executeUpdate(String query) throws SQLException {
		st = con.createStatement();
	     return  st.executeUpdate(query);
	}

}
