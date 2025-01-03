package coms363;

import java.sql.*;

public class CreateTables {
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

			String dropTables = "DROP TABLE IF EXISTS `students`, `departments`, `degrees`, `courses`, `register`, `major`, `minor`;";
			stmt.executeUpdate(dropTables);
			System.out.println("Table deleted in given database...");

			// students table
			String create_student = "CREATE TABLE `students` (\r\n" + "	`snum` INT,\r\n" + "    `ssn` INT,\r\n"
					+ "    `name` VARCHAR(50),\r\n" + "    `gender` VARCHAR(1),\r\n" + "    `dob` VARCHAR(10),\r\n"
					+ "    `c_addr` VARCHAR(100),\r\n" + "    `c_phone` VARCHAR(10),\r\n"
					+ "    `p_addr` VARCHAR(100),\r\n" + "    `p_phone` VARCHAR(10),\r\n"
					+ "    PRIMARY KEY (`ssn`),\r\n" + "    UNIQUE (`snum`)\r\n" + ");";
			stmt.executeUpdate(create_student);
			System.out.println("Created students table");

			// departments table
			String create_departments = "CREATE TABLE `departments` (\r\n" + "    `code` INT,\r\n"
					+ "    `name` VARCHAR(50),\r\n" + "    `phone` VARCHAR(10),\r\n" + "    `college` VARCHAR(20),\r\n"
					+ "	PRIMARY KEY (`code`),\r\n" + "    UNIQUE (`name`)\r\n" + ");";
			stmt.executeUpdate(create_departments);
			System.out.println("Created departments table");

			// degrees table
			String create_degrees = "CREATE TABLE `degrees` (\r\n" + "    `name` VARCHAR(50),\r\n"
					+ "    `level` VARCHAR(5),\r\n" + "    `department_code` INT,\r\n"
					+ "    PRIMARY KEY (`name`, `level`),\r\n"
					+ "    FOREIGN KEY (`department_code`) REFERENCES `departments`(`code`)\r\n" + ");";
			stmt.executeUpdate(create_degrees);
			System.out.println("Created degrees table");

			// courses table
			String create_courses = "CREATE TABLE `courses` (\r\n" + "    `number` INT,\r\n"
					+ "    `name` VARCHAR(50),\r\n" + "    `description` VARCHAR(50),\r\n"
					+ "    `credithours` INT,\r\n" + "    `level` VARCHAR(20),\r\n" + "    `department_code` INT,\r\n"
					+ "    PRIMARY KEY (`number`),\r\n"
					+ "    FOREIGN KEY (`department_code`) REFERENCES `departments`(`code`)\r\n" + ");";
			stmt.executeUpdate(create_courses);
			System.out.println("Created courses table");

			// register table
			String create_register = "CREATE TABLE `register` (\r\n" + "    `snum` INT,\r\n"
					+ "    `course_number` INT,\r\n" + "    `regtime` VARCHAR(20),\r\n" + "    `grade` INT,\r\n"
					+ "    PRIMARY KEY (`snum`, `course_number`),\r\n"
					+ "    FOREIGN KEY (`snum`) REFERENCES `students`(`snum`),\r\n"
					+ "    FOREIGN KEY (`course_number`) REFERENCES `courses`(`number`)\r\n" + ");";
			stmt.executeUpdate(create_register);
			System.out.println("Created register table");

			// major table
			String create_major = "CREATE TABLE `major` (\r\n" + "    `snum` INT,\r\n" + "    `name` VARCHAR(50),\r\n"
					+ "    `level` VARCHAR(5),\r\n" + "    PRIMARY KEY (`snum`, `name`, `level`),\r\n"
					+ "    FOREIGN KEY (`snum`) REFERENCES `students`(`snum`),\r\n"
					+ "    FOREIGN KEY (`name`, `level`) REFERENCES `degrees`(`name`, `level`)\r\n" + ");";
			stmt.executeUpdate(create_major);
			System.out.println("Created major table");

			// minor table
			String create_minor = "CREATE TABLE `minor` (\r\n" + "    `snum` INT,\r\n" + "    `name` VARCHAR(50),\r\n"
					+ "    `level` VARCHAR(5),\r\n" + "    PRIMARY KEY (`snum`, `name`, `level`),\r\n"
					+ "    FOREIGN KEY (`snum`) REFERENCES `students`(`snum`),\r\n"
					+ "    FOREIGN KEY (`name`, `level`) REFERENCES `degrees`(`name`, `level`)\r\n" + ");";
			stmt.executeUpdate(create_minor);
			System.out.println("Created minor table");

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