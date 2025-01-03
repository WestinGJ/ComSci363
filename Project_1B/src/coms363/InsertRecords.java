package coms363;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRecords {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			// Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost/project1?allowLoadLocalInfile=true";
			// Set up connection
			con = DriverManager.getConnection(dbServer, userName, password);
		} catch (Exception e) {
		}
		try {
			st = con.createStatement();

			String students = "LOAD DATA LOCAL INFILE 'students.csv'\r\n"
					+ "INTO TABLE students\r\n"
					+ "FIELDS TERMINATED BY ','\r\n"
					+ "ENCLOSED BY '\"'\r\n"
					+ "LINES TERMINATED BY '\\r\\n'\r\n"
					+ "IGNORE 1 ROWS\r\n"
					+ "(snum,ssn,name,gender,dob,c_addr,c_phone,p_addr,p_phone);";
			st.execute(students);
			System.out.println("Added students");
			
			String departments = "LOAD DATA LOCAL INFILE 'departments.csv'\r\n"
					+ "INTO TABLE departments\r\n"
					+ "FIELDS TERMINATED BY ','\r\n"
					+ "ENCLOSED BY '\"'\r\n"
					+ "LINES TERMINATED BY '\\r\\n'\r\n"
					+ "IGNORE 1 ROWS\r\n"
					+ "(code,name,phone,college);";
			st.execute(departments);
			System.out.println("Added departments");
			
			String courses = "LOAD DATA LOCAL INFILE 'courses.csv'\r\n"
					+ "INTO TABLE courses\r\n"
					+ "FIELDS TERMINATED BY ','\r\n"
					+ "ENCLOSED BY '\"'\r\n"
					+ "LINES TERMINATED BY '\\r\\n'\r\n"
					+ "IGNORE 1 ROWS\r\n"
					+ "(number,name,description,credithours,level,department_code);";
			st.execute(courses);
			System.out.println("Added courses");
			
			String degrees = "LOAD DATA LOCAL INFILE 'degrees.csv'\r\n"
					+ "INTO TABLE degrees\r\n"
					+ "FIELDS TERMINATED BY ','\r\n"
					+ "ENCLOSED BY '\"'\r\n"
					+ "LINES TERMINATED BY '\\r\\n'\r\n"
					+ "IGNORE 1 ROWS\r\n"
					+ "(name,level,department_code);";
			st.execute(degrees);
			System.out.println("Added degrees");
			
			String register = "LOAD DATA LOCAL INFILE 'register.csv'\r\n"
					+ "INTO TABLE register\r\n"
					+ "FIELDS TERMINATED BY ','\r\n"
					+ "ENCLOSED BY '\"'\r\n"
					+ "LINES TERMINATED BY '\\r\\n'\r\n"
					+ "IGNORE 1 ROWS\r\n"
					+ "(snum,course_number,regtime,grade);";
			st.execute(register);
			System.out.println("Added register");
			
			String major = "LOAD DATA LOCAL INFILE 'major.csv'\r\n"
					+ "INTO TABLE major\r\n"
					+ "FIELDS TERMINATED BY ','\r\n"
					+ "ENCLOSED BY '\"'\r\n"
					+ "LINES TERMINATED BY '\\r\\n'\r\n"
					+ "IGNORE 1 ROWS\r\n"
					+ "(snum,name,level);";
			st.execute(major);
			System.out.println("Added major");
			
			String minor = "LOAD DATA LOCAL INFILE 'minor.csv'\r\n"
					+ "INTO TABLE minor\r\n"
					+ "FIELDS TERMINATED BY ','\r\n"
					+ "ENCLOSED BY '\"'\r\n"
					+ "LINES TERMINATED BY '\\r\\n'\r\n"
					+ "IGNORE 1 ROWS\r\n"
					+ "(snum,name,level);";
			st.execute(minor);
			System.out.println("Added minor");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.executeBatch();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}finally {
			try {
				// Close connection
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
