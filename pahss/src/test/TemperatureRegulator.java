package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;


public class TemperatureRegulator{
	
	static String url = "jdbc:mysql://173.247.244.100:3306/asauce5_aquarium";
	static String username = "asauce5_cs437";
	static String password = "cs437pahss";
	private ArrayList<Fish> tankStock;
	private Heater heater = new Heater();
	private Chiller chiller = new Chiller();
	private float opTemp = 50;
	private float curTemp = 55;
	
	public TemperatureRegulator()
	{
		
	}
	
	public ArrayList<Fish> getSpeciesData()
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
			System.out.println("\nMySQL error.");
			System.out.println(s);
		}
		
		return tStock;
	}
	public ArrayList<Fish> getTankStock()
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
			System.out.println("\nMySQL error.");
			System.out.println(s);
		}
		
		return tStock;
	}
	public void update()
	{
		if((int)curTemp != (int)opTemp)
		{
			if ((int)curTemp < (int)opTemp){
				if(!heater.isOn()){
					setHeater(true);
					setChiller(false);
				}
			}
			else{
				if(!chiller.isOn()){
					setChiller(true);
					setHeater(false);
				}
			}
			if(chiller.isOn() || heater.isOn()){
				adjustTemperature();
			}
		}
		else
		{
			if(heater.isOn())
				heater.setOn(false);
			if(chiller.isOn())
				chiller.setOn(false);
		}
		
	}
	public void getTemperature()
	{
		//access database to get current tank temperature
	}
	public void sendActions(String action)
	{
		java.util.Date now = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(now.getTime());
		Calendar c = Calendar.getInstance();
		
		try
		{
			Connection connection = null;
			System.out.println("Connecting to tank_stock...");
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			String insert = "insert into action_log (module_id, time_stamp, action_taken) values ("
				+ 4 + "," +"'" + sqlDate
				+ " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) 
				+ "'" + "," + "'" + action + "'"  +")";
			stmt.executeUpdate(insert);
			
			connection.close();
			System.out.println("Action logged.");
			System.out.println("Connection closed.");
		}
		catch(SQLException s)
		{
			System.out.println("\nMySQL error.");
			System.out.println(s);
		}
	}
	public void calculateOptimalTemperature()
	{
		//calculate op temp and save it to database
	}
	public void adjustTemperature()
	{
		if (curTemp < opTemp){
			if (heater.isOn())
				curTemp+= .1;
		}
		else if (curTemp > opTemp){
			if (chiller.isOn())
				curTemp -= .1;
		}
		System.out.printf("Adjusting Temp: %.1f%n", curTemp);
	}
	public Chiller getChiller()
	{
		return chiller;
	}
	public void setChiller(boolean onOrOff)
	{
		chiller.setOn(onOrOff);
		sendActions("Chiller turned " + (onOrOff ? "on" : "off"));
	}
	public Heater getHeater()
	{
		return heater;
	}
	public void setHeater(boolean onOrOff)
	{
		heater.setOn(onOrOff);
		sendActions("Heater turned " + (onOrOff ? "on" : "off"));
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
