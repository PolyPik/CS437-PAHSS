package test;
import java.sql.*;
import java.util.ArrayList;

public class TemperatureRegulator {
	static String url = "jdbc:mysql://173.247.244.100:3306/asauce5_aquarium";
	static String username = "asauce5_cs437";
	static String password = "cs437pahss";
	private static ArrayList<Fish> tankStock;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testPrintCurrentStock();
	}
	
	public static ArrayList<Fish> getSpeciesData()
	{
		ArrayList<Fish> tStock = new ArrayList<Fish>();
		
		try
		{
			Fish fish;
			Connection connection = null;
			System.out.println("Connecting to Species_Database...");
			connection = DriverManager.getConnection(url, username,
					password);
			Statement stmt = connection.createStatement();
			String query = "select * from Species_Database";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				fish = new Fish(rs.getString("name"), rs.getDouble("oxygen_level"),
						rs.getDouble("nitrite_level"),rs.getDouble("nitrate_level"),
						rs.getDouble("salinity"), rs.getDouble("ph_level"),
						rs.getDouble("ammonia_level"), rs.getDouble("water_hardness"),
						rs.getDouble("temperature")); 
				
				tStock.add(fish);
			}
			connection.close();
			System.out.println("Species Information Acquired");
			System.out.println("Connection closed.");
		}
		catch(SQLException s)
		{
			System.out.println("\nNo Connection.");
			System.out.println(s);
		}
		
		return tStock;
	}
	public static ArrayList<Fish> getTankStock()
	{
		return new ArrayList<Fish>();
	}
	public static double getTemperature()
	{
		return 0;
	}
	public static void sendCommands()
	{
		
	}
	public static void sendActions()
	{
		
	}
	
	public static double calculateOptimalTemperature()
	{
		return 1;
	}
	public static void adjustTemperature(double amount)
	{
		
	}
	public static void shutdownEquipment(int which)
	{
		
	}
	public static void startEquipment(int which)
	{
		
	}
	
	public static void sendDataToMI()
	{
		
	}
	public static void sendSettingsToMI()
	{
		
	}
	public static void sendNoticesToMI()
	{
		
	}
	
	public static void testPrintCurrentStock()
	{
		tankStock = getSpeciesData();
		
		System.out.println("\nPrinting Current Tank Stock: ");
		for(int i = 0; i < tankStock.size(); i++)
		{
			System.out.println(tankStock.get(i).toString());
		}
		
	}
}
