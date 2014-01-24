package test;
import java.sql.*;

public class Database {

	public static void main(String[] args) {
		String url = "jdbc:mysql://173.247.244.100:3306/asauce5_aquarium";
		String username = "asauce5_cs437";
		String password = "cs437pahss";
		
		try
		{
			Connection connection = null;
			System.out.println("Connecting to database...\n");
			connection = DriverManager.getConnection(url, username,
					password);
			Statement stmt = connection.createStatement();
			String query = "select * from Species_Database";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				System.out.println(rs.getString("name") + " " + rs.getString("oxygen_level") + " "
						+ rs.getString("nitrite_level") + " " + rs.getString("nitrate_level") + " "
						+ rs.getString("salinity") + " " + rs.getString("ph_level") + " "
						+ rs.getString("ammonia_level") + " " + rs.getString("water_hardness") + " "
						+ rs.getString("temperature")); 
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
