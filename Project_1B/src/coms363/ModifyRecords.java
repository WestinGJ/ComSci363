package coms363;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyRecords {
	private static Connection connect = null;

	public static void main(String[] args) {
		try {
			// Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost/project1";
			// Set up connection
			connect = DriverManager.getConnection(dbServer, userName, password);
		} catch (Exception e) {
		}
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			
			String update1 = "UPDATE students\r\n"
					+ "SET name = 'Scott'\r\n"
					+ "WHERE ssn = 144673371;";
			stmt.executeUpdate(update1);
			
			String update2 = "UPDATE major\r\n"
					+ "SET name = 'Computer Science', level = 'MS'\r\n"
					+ "WHERE snum = (\r\n"
					+ "    SELECT snum\r\n"
					+ "    FROM students\r\n"
					+ "    WHERE ssn = 144673371\r\n"
					+ ");";
			stmt.executeUpdate(update2);
			
			String update3 = "DELETE FROM register\r\n"
					+ "WHERE regtime = 'Summer2024';";
			stmt.executeUpdate(update3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			stmt.executeBatch();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}finally {
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
