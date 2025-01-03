package coms363;

import java.sql.*;

public class DropTables {
	private static Connection connect = null;

	public static void main(String[] args) {
		try {
//Set up connection parameters
			String userName = "test";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost/project1_del_data";
//Set up connection
			connect = DriverManager.getConnection(dbServer, userName, password);
		} catch (Exception e) {
		}
//initiate sql statement
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			String dropTables = "DROP TABLE IF EXISTS `students`, `departments`, `degrees`, `courses`, `register`, `major`, `minor`;";
			stmt.executeUpdate(dropTables);
			System.out.println("Table deleted in given database...");

		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
// Close connection
				if (stmt != null) {
					stmt.close();
				}
				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}