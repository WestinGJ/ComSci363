package coms363;

import com.github.javafaker.Faker;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Index {
	private static Connection connect = null;

	public static void main(String[] args) {
		Faker faker = new Faker();
        Random random = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy"); 
		
		try {
//Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost/project1";
//Set up connection
			connect = DriverManager.getConnection(dbServer, userName, password);
		} catch (Exception e) {
		}
//initiate sql statement
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(snum) AS max_snum FROM students;");
            int next_snum = 1000;  
            if (rs.next()) {
                next_snum = rs.getInt("max_snum") + 1;
            }
            for (int i = 0; i < 5000; i++) {
                int snum = next_snum + i;
                int ssn = 100000000 + random.nextInt(899999999);  
                String student_name = escapeSpecialChars(faker.name().firstName() + " " + faker.name().lastName());
                String gender = random.nextBoolean() ? "M" : "F";  
                Date dobDate = faker.date().birthday(18, 25);  
                String dob = dateFormat.format(dobDate);  
                String c_addr = escapeSpecialChars(faker.address().streetAddress());
                String c_phone = faker.phoneNumber().subscriberNumber(10);
                String p_addr = escapeSpecialChars(faker.address().streetAddress());
                String p_phone = faker.phoneNumber().subscriberNumber(10);

                String newStudentRecord = String.format(
                    "INSERT INTO students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) " +
                    "VALUES (%d, %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                    snum, ssn, student_name, gender, dob, c_addr, c_phone, p_addr, p_phone
                );
                stmt.executeUpdate(newStudentRecord);
                
                String query_student = "SELECT name, level \r\n"
                		+ "FROM degrees\r\n"
                		+ "ORDER BY RAND()\r\n"
                		+ "LIMIT 1";
    			ResultSet rs2 = stmt.executeQuery(query_student);
    			String degree_name = null;
    			String degree_level = null;
    			if (rs2.next()) {
                    degree_name = rs2.getString("name");
                    degree_level = rs2.getString("level");
                }
                
                if(snum%7==0) {
                    String newMinorRecord = String.format(
                            "INSERT INTO minor (snum, name, level) " +
                                    "VALUES (%d, '%s', '%s');",
                                    snum, degree_name, degree_level
                                );
                    stmt.executeUpdate(newMinorRecord);
                }
                else {
                	String newMajorRecord = String.format(
                            "INSERT INTO major (snum, name, level) " +
                                    "VALUES (%d, '%s', '%s');",
                                    snum, degree_name, degree_level
                                );
                    stmt.executeUpdate(newMajorRecord);
                }
            }
            System.out.println("5000 new student records inserted");
            
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
	private static String escapeSpecialChars(String input) {
        return input.replace("'", "''");
    }
}