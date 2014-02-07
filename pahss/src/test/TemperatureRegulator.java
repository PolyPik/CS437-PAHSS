package test;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class TemperatureRegulator extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String url = "jdbc:mysql://173.247.244.100:3306/asauce5_aquarium";
	static String username = "asauce5_cs437";
	static String password = "cs437pahss";
	private static ArrayList<Fish> tankStock;
	private Heater heater = new Heater();
	private Chiller chiller = new Chiller();
	private JLabel optimalTempLabel = new JLabel("Optimal Temperature: ");
	private JLabel optimalTempValue = new JLabel("83");
	private JLabel tempLabel = new JLabel("Current Temperature: ");
	private JLabel tempValue = new JLabel("80");
	private JButton tempUpButton = new JButton("Up");
	private JButton tempDownButton = new JButton("Down");
	private JRadioButton heaterOn = new JRadioButton("Heater On");
	private JRadioButton heaterOff = new JRadioButton("Heater Off");
	private JRadioButton chillerOn = new JRadioButton("Chiller On");
	private JRadioButton chillerOff = new JRadioButton("Chiller Off");
	private ButtonGroup heaterGroup = new ButtonGroup();
	private ButtonGroup chillerGroup = new ButtonGroup();
	private JPanel tempPanel = new JPanel();
	private JPanel controlPanel = new JPanel();
	
	public TemperatureRegulator()
	{
		setLayout(new GridLayout(6, 2, 0, 0));
		setPreferredSize(new Dimension(100, 100));
		setFocusable(true);
		
		heaterOff.setSelected(true);
		chillerOff.setSelected(true);
		heaterGroup.add(heaterOn);
		heaterGroup.add(heaterOff);
		chillerGroup.add(chillerOn);
		chillerGroup.add(chillerOff);
		
		tempPanel.setBorder(BorderFactory.createTitledBorder("Temperature Controls"));
		tempPanel.add(optimalTempLabel);
		tempPanel.add(optimalTempValue);
		tempPanel.add(tempLabel);
		tempPanel.add(tempValue);
		tempPanel.add(tempUpButton);
		tempPanel.add(tempDownButton);
		
		controlPanel.setBorder(BorderFactory.createTitledBorder("Equipment Controls"));
		controlPanel.add(heaterOn);
		controlPanel.add(heaterOff);
		controlPanel.add(chillerOn);
		controlPanel.add(chillerOff);
		
		add(tempPanel);
		add(controlPanel);
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
