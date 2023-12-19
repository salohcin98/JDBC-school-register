/*
 * SQLCommands.java
 * Nicholas Franck
 * 
 * This program contains the methods and actions that the user can select by 
 * running the driver program.
 * 
 * 
 */

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class SQLCommands {
	
	
////////////////////////////////////////////////////////////
// NAME:        add_course
//
// BEHAVIOR:    Asks the user to input the course code and title
//				the method checks the Course table to make sure 
//				no such course exists and if it does not then
//				the course code and title are added to the Course table.
//
// Parameters:  conn - the connection to the database server
//              key - the keyboard input of the scanner library 
//                                 
////////////////////////////////////////////////////////////
	public void add_course(Connection conn, Scanner key) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		
		System.out.println("Add a course");
		
		System.out.print("Please input course code: ");
		String courseCode = key.nextLine().toUpperCase().trim();
		
		System.out.println();
		
		System.out.print("Please input course title: ");
		String courseTitle = key.nextLine().trim();
		
		System.out.println();
		
		String query = "select code from Course Where code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		
		if (rs.next()) 
		{  // checks to see if the course already exists
			System.out.println("Course already exists");
			return;
		}
		query = "Insert into Course (code, title) values ('" + courseCode + "', '" + courseTitle + "')";
		try 
		{// creates a new entry in Course table with the input code and title
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		st.close();
		
		System.out.println("A new course is added.");
	} // end of add_course
	
	
////////////////////////////////////////////////////////////
//NAME:        delete_course
//
//BEHAVIOR:    Asks the user to input the course code of 
//				the course to be deleted. The code checks
//				to see if the course exists in both the 
//				registered table and the Courses table,
//				if so the course is deleted from them as necessary.
//				Otherwise an error message is printed that 
//				the course does not exist.
//
//Parameters:  conn - the connection to the database server
//			   key - the keyboard input of the scanner library 
//
////////////////////////////////////////////////////////////
	public void delete_course(Connection conn, Scanner key) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		
		System.out.println("Delete a course");
		
		System.out.print("Please enter course code: ");
		String code = key.nextLine().toUpperCase().trim();
		
		System.out.println();
		
		String query = "select code from Course Where code = '" + code + "'";
		String query1 = "select code from Registered where code = '" + code + "'";
	
		
		ResultSet rs = st.executeQuery(query);
		ResultSet rl = st.executeQuery(query1);
	
		// checks to see if the code exists in the course table
		if (rs.next()) 
		{
			System.out.println("Code does not exist");
			return;
		}
		
		query = "Delete from Course where code = '" + code + "'" ;
		
		try 
		{
			if(rl.next()) {
				st.executeUpdate("Delete from Registered where code = '" + code + "'");
			}
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		
		rs.close();
		rl.close();
		st.close();
		System.out.println("A course is deleted.");
		
	} // end of delete_course
	
	
////////////////////////////////////////////////////////////
//NAME:        show_courses
//
//BEHAVIOR:    Displays all courses stored in the Course table
//
//Parameters:  conn - the connection to the database server
//
////////////////////////////////////////////////////////////
	public void show_courses(Connection conn) throws SQLException, IOException
	{
		
		try (
			Statement st = conn.createStatement(); )
		
		{
			String query = "select * from Course";
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) 
			{
				 String code = rs.getString("code");
				 String title = rs.getString("title");
			     System.out.println("Code: " + code + "\tTitle: " + title);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
	} // end of show_courses
	
	
////////////////////////////////////////////////////////////
//NAME:        add_student
//
//BEHAVIOR:    Creates a new entry in the Student table
//				based on input from keyboard, as long as
//				the input SSN does not already exist in 
//				the table.
//
//Parameters:  conn - the connection to the database server
//			   key - the keyboard input of the scanner library 
//
////////////////////////////////////////////////////////////
	public void add_student(Connection conn, Scanner key) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		
		System.out.println("Add a Student");
		
		System.out.print("Student ssn: ");
		int studentSSN = Integer.parseInt(key.nextLine().trim());
		
		System.out.println();
		
		System.out.print("Please input student name: ");
		String studentName = key.nextLine().trim();
		
		System.out.println();
		
		System.out.print("Please input student address: ");
		String studentAddress = key.nextLine().trim();
		
		System.out.println();
		
		System.out.print("Please input student major: ");
		String studentMajor = key.nextLine().trim();
		
		System.out.println();
		
		// checks to see if ssn already exists
		String query = "select ssn from Student Where ssn = '" + studentSSN + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) 
		{
			System.out.println("SSN already exists");
			return;
		}
		query = "Insert into Student (ssn, name, address, major) values (" + studentSSN + ", '" + studentName +
				"', '" + studentAddress + "', '" + studentMajor + "')";
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		}
		
		rs.close();
		st.close();
		
		System.out.println("A new Student is added.");
	} // end of add_student
	
	
////////////////////////////////////////////////////////////
//NAME:        delete_student
//
//BEHAVIOR:    Based on user input the method will delete
//				from the Student table and Registration table
//				the entry associated with the input SSN, as
//				long as the SSN exists in the tables.
//
//Parameters:  conn - the connection to the database server
//			   key - the keyboard input of the scanner library 
//
////////////////////////////////////////////////////////////
	public void delete_student(Connection conn, Scanner key) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		
		System.out.println("Delete a Student");
		
		System.out.print("Enter Student ssn: ");
		int studentSSN = Integer.parseInt(key.nextLine().trim());
		
		System.out.println();
		
		String query = "select ssn from Student Where ssn = '" + studentSSN + "'";
		String query1 = "select ssn from Registered where ssn = '" + studentSSN + "'";
	
		ResultSet rs = st.executeQuery(query);
		ResultSet rl = st.executeQuery(query1);
	
		// checks to see if student exists
		if (rs.next()) 
		{
			System.out.println("SSN does not exist");
			return;
		}
		
		query = "Delete from Student where ssn = '" + studentSSN + "'" ;
		try 
		{
			// if student is registered, this will delete student from registered and then deletes from student tables
			if(rl.next()) {
				st.executeUpdate("Delete from Registered where ssn = '" + studentSSN + "'");
			}
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		rl.close();
		st.close();
		
		System.out.println("A Student is deleted.");
	} // end of delete_student
	
	
////////////////////////////////////////////////////////////
//NAME:        register_course
//
//BEHAVIOR:    Asks the user for 4 inputs and as long as
//				inputs for Course code and student SSN exist
//				in the respective tables the entry will be 
//				created in the Registered table.
//
//Parameters:  conn - the connection to the database server
//			   key - the keyboard input of the scanner library 
//
////////////////////////////////////////////////////////////
	public void register_course(Connection conn, Scanner key) throws SQLException, IOException 
	{
		Statement st = conn.createStatement();
		
		System.out.println("Register student for Course");
		
		System.out.print("Enter course code: ");
		String code = key.nextLine().toUpperCase().trim();
		
		System.out.println();
		
		System.out.print("Enter student ssn: ");
		int ssn = Integer.parseInt(key.nextLine().trim());
		
		System.out.println();
		
		System.out.print("Enter year: ");
		int year = Integer.parseInt(key.nextLine().trim());
		
		System.out.println();
		
		System.out.print("Enter semester: ");
		String semester = key.nextLine().trim();
		semester = semester.substring(0,1).toUpperCase() + semester.substring(1).toLowerCase(); 
		
		System.out.println();
		
		// check to see if student exists
		String query = "select ssn from Student where ssn = " + ssn;
		ResultSet rs = st.executeQuery(query);
		if(!rs.next()) {
			System.out.println("Student does not exist");
			return;
		}
		
		// check to see if course exists
		String query1 = "select code from Course where code = '" + code + "'";
		ResultSet rl = st.executeQuery(query1);
		if(!rl.next()) {
			System.out.println("Course does not exist");
			return;
		}
		
		// makes sure student is not already registered for course
		String query2 = "select * from Registered where code = '" + code + "'" + " AND year = " + year + " AND  semester = '" + semester + "' AND ssn = " + ssn;
		ResultSet rt = st.executeQuery(query2);
		if(rt.next()) {
			System.out.println("Student already registered for course");
			return;
		}
		
		
		query = "Insert into Registered (ssn, code, year, semester) values (" + ssn + ", '" 
				+ code + "', " + year + ", '" + semester + "')" ;
		
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		rt.close();
		rs.close();
		rl.close();
		st.close();
		
		System.out.println("A student is registered.");
		
	} // end of register_course
	
////////////////////////////////////////////////////////////
//NAME:        check_registration
//
//BEHAVIOR:    Asks the user to input a SSN for a student
//				and prints the courses that student has 
//				registered for as long as that student exists
//				in the Student table.
//
//Parameters:  conn - the connection to the database server
//			   key - the keyboard input of the scanner library 
//
////////////////////////////////////////////////////////////
	public void check_registration(Connection conn, Scanner key) throws SQLException, IOException{
		Statement st = conn.createStatement();
		
		System.out.println("Check Student registration");
		
		System.out.print("Enter student ssn: ");
		int ssn = Integer.parseInt(key.nextLine().trim());
		
		System.out.println();
		
		String query = "select ssn from Student where ssn = " + ssn;
		String query1 = "select ssn from Registered where ssn = " + ssn;
		
		// checks to see if student exists in the student table
		ResultSet rs = st.executeQuery(query);
		if(!rs.next()) {
			System.out.println("Student does not exist");
			return;
		}
		
		// checks to see if student is registered for any classes
		ResultSet rl = st.executeQuery(query1);
		if(!rl.next()) {
			System.out.println("Student is not registered for any clases");
			return;
		}
		
		query = "select code, year, semester from Registered where ssn = " + ssn;
		rs = st.executeQuery(query);
		while (rs.next()) 
		{
			 String code = rs.getString("code");
			 int year = rs.getInt("year");
			 String semester = rs.getString("semester");
			 
		     System.out.println("Code: " + code + "\tYear: " + year + "\tSemester: " + semester);
		}
		
		rs.close();
		rl.close();
		st.close();
		
	} // end of check_registration
	
////////////////////////////////////////////////////////////
//NAME:        input_grade
//
//BEHAVIOR:    The user is asked to input the Course code,
//				the year, and the Semester. The program
//				will loop through students in the registered
// 				course and will ask the grade for each student,
//				as long as the entry exists for the three original
//				inputs.
//
//Parameters:  conn - the connection to the database server
//			   key - the keyboard input of the scanner library 
//
////////////////////////////////////////////////////////////
	public void input_grade(Connection conn, Scanner key) throws SQLException, IOException{
		
		Statement st = conn.createStatement(); // statement for finding the ssn and registered courses
		Statement st1 = conn.createStatement(); // statement for finding names for ssn and updating grades
		
		System.out.println("Upload grades");
		
		System.out.print("Enter course code: ");
		String code = key.nextLine().toUpperCase().trim();
		
		System.out.println();
		
		System.out.print("Enter year: ");
		int year = Integer.parseInt(key.nextLine().trim());
		
		System.out.println();
		
		System.out.print("Enter semester: ");
		String semester = key.nextLine().trim();
		semester = semester.substring(0,1).toUpperCase() + semester.substring(1).toLowerCase(); 
		
		System.out.println();
		
		// checks to see if course exists in course table
		String query = "select * from Course where code = '" + code + "'";
		ResultSet rs = st.executeQuery(query);
		if(!rs.next()) {
			System.out.println("Course does not exist");
			return;
		}
		
		// checks to see if course exists for this term
		query = "select * from Registered where code = '" + code + "' and year = " + year +" and semester = '" + semester + "'";
		rs = st.executeQuery(query);
		if(!rs.next()) {
			System.out.println("This course is not registered for this term");
			return;
		}
		
		// loop to update grades for every person
		while(rs.next()) {
			int ssn = rs.getInt("ssn");
			
			String query1 = "select name from Student where ssn = " + ssn;
			
			ResultSet rl = st1.executeQuery(query1);
			rl.next();
			
			String name = rl.getString("name");
			String grade;
			
			// makes sure user inputs  a letter grade for the grade
			while(true) {
			
				System.out.print("Student name: " + name + "\tStudent ssn: " + ssn + "\nEnter student grade: ");
				grade = key.nextLine().toUpperCase().trim();
				
				if(grade.length() == 1) 
					break;
				else 
					System.out.println("Invalid grade. Input letter grade!");	
				
			}
			
			String query2 = "update Registered set grade = '" + grade + "' where ssn = " + ssn + " and code = '" 
					+ code + "' and year = " + year + " and semester = '" + semester + "'";
			
			try 
			{
				st1.executeUpdate(query2);
			}
			catch (SQLException e) 
			{
				System.out.println("Message: " + e.getMessage());
			}
			
			rl.close();
			System.out.println();
		}
		 
		rs.close();
		st.close(); 
		st1.close();
		
		System.out.println("Grades input!");
	} // end of input_grade

	
} // end of class SQLCommands