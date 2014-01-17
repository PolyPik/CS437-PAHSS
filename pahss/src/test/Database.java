package test;
import java.sql.*;

public class Database {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/Aquarium";
		String username = "root";
		String password = "";
		
		try
		{
			Connection connection = null;
			System.out.println("Connecting to database...\n");
			connection = DriverManager.getConnection(url, username,
					password);
			Statement stmt = connection.createStatement();
			String query = "select * from Aquarium";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				System.out.println(rs.getString("name") + " " + rs.getString("id"));
			}
			connection.close();
			System.out.println("\nConnection closed.");
		}
		catch(SQLException s)
		{
			System.out.println("\nNo Connection.");
			System.out.println(s);
		}
	}

}
