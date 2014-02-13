package test;

import java.sql.*;
import java.util.ArrayList;


public class TemperatureRegulator{
	
	static String url = "jdbc:mysql://173.247.244.100:3306/asauce5_aquarium";
	static String username = "asauce5_cs437";
	static String password = "cs437pahss";
	private static ArrayList<Fish> tankStock;
	private Heater heater = new Heater();
	private Chiller chiller = new Chiller();
	private int opTemp = 50;
	private int curTemp = 55;
	
	public TemperatureRegulator()
	{
		
	}
	
	public static ArrayList<Fish> getSpeciesData()
	{
		ArrayList<Fish> tStock = new ArrayList<Fish>();
		
		try
		{
			Fish fish;
			Connection connection = null;
			System.out.println("Connecting to Species_Database...");
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			String query = "select * from Species_Database";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				fish = new Fish(rs.getInt("id"), rs.getString("name"), rs.getDouble("ammonia"),
						rs.getDouble("nitrate"),rs.getDouble("nitrite"),
						rs.getDouble("oxygen"), rs.getDouble("pH"),
						rs.getDouble("salinity"), rs.getDouble("temperature"),
						rs.getDouble("water_hardness")); 
				
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
		ArrayList<Fish> tStock = new ArrayList<Fish>();
		
		try
		{
			Fish fish;
			Connection connection = null;
			System.out.println("Connecting to tank_stock...");
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			String query = "select * from tank_stock";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				fish = new Fish(rs.getInt("id"), rs.getString("name"), rs.getDouble("ammonia"),
						rs.getDouble("nitrate"),rs.getDouble("nitrite"),
						rs.getDouble("oxygen"), rs.getDouble("pH"),
						rs.getDouble("salinity"), rs.getDouble("temperature"),
						rs.getDouble("water_hardness")); 
				
				tStock.add(fish);
			}
			connection.close();
			System.out.println("Tank Stock Acquired");
			System.out.println("Connection closed.");
		}
		catch(SQLException s)
		{
			System.out.println("\nNo Connection.");
			System.out.println(s);
		}
		
		return tStock;
	}
	public void update()
	{
		while(chiller.isOn() || heater.isOn()){
			adjustTemperature();
		}
		
	}
	public double getTemperature()
	{
		return 0;
	}
	public void sendCommands()
	{
		
	}
	public void sendActions()
	{
		
	}
	public double calculateOptimalTemperature()
	{
		return 1;
	}
	public void adjustTemperature()
	{
		if (curTemp < opTemp)
			setHeater(true);
		else
			setChiller(true);
	}
	public Chiller getChiller()
	{
		return chiller;
	}
	public void setChiller(boolean onOrOff)
	{
		chiller.setOn(onOrOff);
	}
	public Heater getHeater()
	{
		return heater;
	}
	public void setHeater(boolean onOrOff)
	{
		heater.setOn(onOrOff);
	}
	public void sendDataToMI()
	{
		
	}
	public void sendSettingsToMI()
	{
		
	}
	public void sendNoticesToMI()
	{
		
	}
	public void testPrintCurrentStock()
	{
		tankStock = getSpeciesData();
		
		System.out.println("\nPrinting Current Tank Stock: ");
		for(int i = 0; i < tankStock.size(); i++)
		{
			System.out.println(tankStock.get(i).toString());
		}
		
	}
}
