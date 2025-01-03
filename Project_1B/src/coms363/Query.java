package coms363;

import java.sql.*;

public class Query {
	private static Connection connect = null;

	public static void main(String[] args) {
		try {
//Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost/project1";
			// Set up connection
			connect = DriverManager.getConnection(dbServer, userName, password);
		} catch (Exception e) {
		}
		// initiate sql statement
		Statement stmt = null;
		try {
			stmt = connect.createStatement();

			String query_1 = "SELECT c.number, c.name, AVG(r.grade) AS avg_grade\r\n"
					+ "FROM courses c  \r\n"
					+ "JOIN register r ON c.number = r.course_number \r\n"
					+ "GROUP BY c.number, c.name;";
			ResultSet rs = stmt.executeQuery(query_1);
			System.out.println("result for query");
			System.out.println("cnum|cname|avg");
			while (rs.next()) {
//Display values
				System.out.println(rs.getInt("c.number") + "|" + rs.getString("c.name") + "|" + rs.getInt("avg_grade"));
			}
			
			String query_2 = "SELECT COUNT(DISTINCT s.snum) AS female_count\r\n"
					+ "FROM students s\r\n"
					+ "JOIN major m ON s.snum = m.snum \r\n"
					+ "JOIN minor mn ON s.snum = mn.snum \r\n"
					+ "JOIN degrees d ON m.name = d.name OR mn.name = d.name \r\n"
					+ "JOIN departments dep ON d.department_code = dep.code \r\n"
					+ "WHERE s.gender = 'F' AND dep.college = 'LAS';";
			rs = stmt.executeQuery(query_2);
			System.out.println("result for query");
			System.out.println("female count");
			while (rs.next()) {
//Display values
				System.out.println(rs.getInt("female_count"));
			}
			
			String query_3 = "SELECT d.name, d.level \r\n"
					+ "FROM degrees d \r\n"
					+ "LEFT JOIN major ma ON d.name = ma.name AND d.level = ma.level \r\n"
					+ "LEFT JOIN minor mi ON d.name = mi.name AND d.level = mi.level \r\n"
					+ "LEFT JOIN students s ON (ma.snum = s.snum OR mi.snum = s.snum) \r\n"
					+ "GROUP BY d.name, d.level\r\n"
					+ "HAVING SUM(CASE WHEN s.gender = 'M' THEN 1 ELSE 0 END) > SUM(CASE WHEN s.gender = 'F' THEN 1 ELSE 0 END);";
			rs = stmt.executeQuery(query_3);
			System.out.println("result for query");
			System.out.println("name|level");
			while (rs.next()) {
//Display values
				System.out.println(rs.getString("name") + "|" + rs.getString("level"));
			}

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
