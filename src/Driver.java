/*
 * Driver.java
 * Nicholas Franck
 * 
 * This program runs interface for the registration system and connects
 * to the database server
 * 
 */
import java.io.*;
import java.util.Scanner;
import java.sql.*;

public class Driver {


    public static void main(String[] args) throws SQLException, IOException
	{
    	//connect to database server	
                try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //Load the Oracle JDBC driver
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Could not load the driver");
		}
		try //connect to oracle and validate the user and passwd
		{
			String Oracleuser,Oraclepass;
			String url="jdbc:oracle:thin:@mcs.uscupstate.edu:1521:xe";
			Connection conn = DriverManager.getConnection(url,"c##testg","t123456");//connect oracle

		    //print menu
			Scanner scan = new Scanner(System.in);
			SQLCommands SQL = new SQLCommands();
			int command;
			String input;
	        boolean keepGoing = true;
	        while (keepGoing == true)
	        {
	            printmenu();
	            input = scan.nextLine();
	            command = Integer.parseInt(input);
				
				switch(command)
				{
					case 0: SQL.add_course(conn, scan); break;
					case 1: SQL.delete_course(conn, scan); break;
					case 2: SQL.show_courses(conn); break;
					case 3: SQL.add_student(conn, scan); break;
					case 4: SQL.delete_student(conn, scan); break;
					case 5: SQL.register_course(conn, scan); break;
					case 6: SQL.check_registration(conn, scan); break;
					case 7: SQL.input_grade(conn, scan); break;
					case 8: 
						System.out.println("The Session has been ended, Thank you!");
	               	    keepGoing = false;
	               	    conn.close(); //close database connection   
						break;
				}    		            			
		    }
		}
		catch (SQLException ex)
		{
			System.out.println("An error occurred when connecting to the database server.");
			ex.printStackTrace();
		}				
  }
    
    // Prints the choices available in the registration system
    public static void printmenu()
    {
    		System.out.println();
    		System.out.println("*********************************************************************");
	      	System.out.println("");
	      	System.out.println("***                                                               ***");
	      	System.out.println("");
	      	System.out.println("***              Welcome to Online Registration System            ***");
	      	System.out.println("");
	      	System.out.println("***                                                               ***");
	      	System.out.println("");
	      	System.out.println("*********************************************************************");
	      	System.out.println("0. Add a course");
	      	System.out.println("1. Delete a course");
	      	System.out.println("2. Show all courses");
	      	System.out.println("3. Add a Student");
	      	System.out.println("4. Delete a Student");
	      	System.out.println("5. Register a course");
	      	System.out.println("6. Check a Student registraion");
	      	System.out.println("7. Upload grades");
	      	System.out.println("8. Quit ");
	      	System.out.println();
	      	
	      	System.out.println("Please choose an option");
    }
}