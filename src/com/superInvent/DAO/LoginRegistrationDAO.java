package com.superInvent.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.superInvent.POJO.Users;

public class LoginRegistrationDAO extends JDBCConnection{
	String query;
	Users user;
	public Users getUserDetails(String pass, String email){

		if(con instanceof Connection) {
			query = "Select * from users where u_password = '" + pass + "' and u_email = '" + email + "'";
			try {
				ResultSet rs = this.createStatement(query);
					if(rs.isBeforeFirst()){
						user = new Users();
						while(rs.next()) {
							user.setEmail(rs.getString("u_email"));
							user.setIs_admin(rs.getBoolean("is_admin"));
							user.setMobile(rs.getString("mobile"));
							user.setName(rs.getString("name"));
							user.setLast_login(rs.getTimestamp("last_login"));
							user.setCreatedAt(rs.getTimestamp("created_at"));
							user.setAddress(rs.getString("address"));
							user.setPassword(rs.getString("u_password"));
						}
					  return user;
					}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return null;
	}
	public void updateLastLogin(String email) {
		query = "UPDATE users SET `last_login` = now() where u_email = '" + email +"'";
		try {
			this.executeUpdate(query);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public String insert(Users user) {
		if(isEmailExist(user.getEmail()))
			return "EMAIL_EXIST";
		query = "INSERT INTO users"
				+ "(name, u_email, u_password, mobile, address, is_admin, last_login, created_at) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getMobile());
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setBoolean(6, user.getIs_admin());
			preparedStatement.setTimestamp(7,user.getLast_login());
			preparedStatement.setTimestamp(8, user.getCreatedAt());
			// execute insert SQL stetement
			preparedStatement .executeUpdate();
		} catch (Exception e) {
			System.out.println("error form LoginRegistrationDAO insert function");
			System.out.println(e);
			return e.getMessage();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				return e.getMessage();
			}
		}
	  return "success";
	}
	public boolean isEmailExist(String email) {
		query = "select id from users where u_email = '" + email + "'";
		try {
			ResultSet rs = this.createStatement(query);
				if(rs.isBeforeFirst()){
					return true;
				}
		}catch(Exception e) {
			return true;
		}
		return false;
	}
}
