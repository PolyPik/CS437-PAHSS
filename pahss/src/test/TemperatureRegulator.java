package test;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Semaphore;

class TankID{
	public int speciesID;
	public int tankID;
	public int amount;
	public TankID(int s, int t, int a){
		speciesID = s; tankID = t; amount = a;
	}
};
public class TemperatureRegulator extends Thread{
	
	private final static double EPSILON = .0000001;
	static String url = "jdbc:mysql://173.247.244.100:3306/asauce5_aquarium";
	static String username = "asauce5_cs437";
	static String password = "cs437pahss";
	private static int MODULE = 4;
	private ArrayList<Fish> speciesData;
	private ArrayList<TankID> tankIDS;
	private ArrayList<String> notifications = new ArrayList<String>();
	private Heater heater = new Heater();
	private Chiller chiller = new Chiller();
	private boolean isRunning = true;
	private boolean isAutomated = true;
	private boolean isScheduled = false;
	private int fps = 20;
	long lastLoopTime = 0;
	long refreshTime = 0;
	private double minTemp = 1000; 
	private double maxTemp = 0;
	private double opTemp = 0;
	private double curTemp = 78; 
	private double desiredTemp = 0;
	DecimalFormat df = new DecimalFormat("#.##");
	Semaphore s;
	
	public TemperatureRegulator(Semaphore s)
	{
		this.s = s;
	}

	@Override
	public void run() {
		initialize();
		while (isRunning){
			long time = System.currentTimeMillis(); 
	        long delta = time - lastLoopTime;
	        lastLoopTime = System.currentTimeMillis();
	        
	        update(delta);
	        
	        //  delay for each frame  -   time it took for one frame 
	        time = (1000 / fps) - (System.currentTimeMillis() - time); 
	        if (time > 0) 
            { 
                try 
                { 
                        Thread.sleep(time); 
                } 
                catch(Exception e){} 
            } 
		}
	}
	void initialize()
	{
		notifications.add("Initializing Temperature Regulator");
		tankIDS = getTankStock();
		speciesData = getSpeciesData();
		opTemp = calculateOptimalTemperature();
		notifications.add("Temperature Regulator initialized");
		s.release();
	}
	public ArrayList<Fish> getSpeciesData()
	{
		ArrayList<Fish> tStock = new ArrayList<Fish>();
		
		try
		{
			Fish fish;
			Connection connection = null;
			notifications.add("Connecting to species_database...");
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			for(int i = 0; i < tankIDS.size(); i++)
			{
				String query = "select * from species_database where id=" + tankIDS.get(i).speciesID;
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
			}
			connection.close();
			notifications.add("Species Information Acquired...Connection closed.");
		}
		catch(SQLException s)
		{
			notifications.add("\nMySQL error.");
			notifications.add(s.getLocalizedMessage());
		}
		
		return tStock;
	}
	public ArrayList<TankID> getTankStock()
	{
		ArrayList<TankID> tStock = new ArrayList<TankID>();
		
		try
		{
			TankID id;
			Connection connection = null;
			notifications.add("Connecting to tank_stock database...");
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			String query = "select * from tank_stock";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				id = new TankID(rs.getInt("species_id"), rs.getInt("tank_id"),
						rs.getInt("amount"));
				
				tStock.add(id);
			}
			connection.close();
			notifications.add("Tank Stock Acquired...Connection closed.");
		}
		catch(SQLException s)
		{
			notifications.add("\nMySQL error.");
			notifications.add(s.getLocalizedMessage());
		}
		
		return tStock;
	}
	public void update(long dt)
	{
		refreshTime += dt;
		if(refreshTime > 1000) // update once a second only, not every frame
		{
			if((!isEqual(curTemp, opTemp)) && isAutomated
				 || (!isEqual(curTemp, desiredTemp)) && isScheduled)
			{
				if ((lessThan(curTemp, opTemp) && isAutomated) || (lessThan(curTemp, desiredTemp)) && isScheduled){
					if(!heater.isOn()){
						setHeater(true);
						setChiller(false);
					}
				}
				else if((greaterThan(curTemp, opTemp) && isAutomated) || (greaterThan(curTemp, desiredTemp)) && isScheduled){
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
				if(isAutomated || isScheduled)
				{
					if(heater.isOn())
						setHeater(false);
					if(chiller.isOn())
						setChiller(false);
				}
			}

			if(!isScheduled && !isAutomated && (heater.isOn() || chiller.isOn()))
				adjustTemperature();
			
			refreshTime = 0;
		}
		
	}
	public void sendActions(String action)
	{
		java.util.Date now = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(now.getTime());
		Calendar c = Calendar.getInstance();
		
		try
		{
			Connection connection = null;
			notifications.add("Connecting to action_log database...");
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			String insert = "insert into action_log (module_id, time_stamp, action_taken) values ("
				+ MODULE + "," +"'" + sqlDate
				+ " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" 
				+ c.get(Calendar.SECOND) + "'" + "," + "'" + action + "'"  +")";
			stmt.executeUpdate(insert);
			
			connection.close();
			notifications.add("Action logged...Connection closed.");
		}
		catch(SQLException s)
		{
			notifications.add("\nMySQL error.");
			notifications.add(s.getLocalizedMessage());
		}
	}
	public int calculateOptimalTemperature()
	{
		notifications.add("Calculating Optimal Temperature...");
		int size = speciesData.size();
		if(size != 0)
		{
			int temp = 0;
			double curTemp = 0;
			for(int i = 0; i < size; i++)
			{
				curTemp = speciesData.get(i).getTemperature();
				temp += curTemp;
				
				if (minTemp > curTemp)
					minTemp = curTemp;
				if (maxTemp < curTemp)
					maxTemp = curTemp;
			}
			notifications.add("Optimal Temperature acquired: " + temp / size);
			notifications.add("Min temp: " + minTemp);
			notifications.add("Max temp: " + maxTemp);
			return temp / size; // average temperature
		}
		else
		{
			notifications.add("NO SPECIES DATA AVAILABLE FOR TEMPERATURE CALCULATION!");
			return 100;
		}
		
	}
	public void adjustTemperature()
	{
		if (heater.isOn())
			curTemp+= .1;
	
		if (chiller.isOn())
			curTemp -= .1;
		
		/*StringBuilder sb = new StringBuilder();
		sb.append("Adjust temp: ");
		sb.append(df.format(curTemp));
		notifications.add(sb.toString());	*/
	}
	public void setChiller(boolean onOrOff)
	{
		chiller.setOn(onOrOff);
		notifications.add("Chiller is switching " + ( onOrOff == true ? "on" : "off"));
		sendActions("Chiller turned " + (onOrOff ? "on" : "off"));
	}
	public Chiller getChiller()
	{
		return chiller;
	}
	public void setHeater(boolean onOrOff)
	{
		heater.setOn(onOrOff);
		notifications.add("Heater is switching " + ( onOrOff == true ? "on" : "off"));
		sendActions("Heater turned " + (onOrOff ? "on" : "off"));
	}
	public Heater getHeater()
	{
		return heater;
	}
	public void setAutomated(boolean onOrOff)
	{
		isAutomated = onOrOff;
		if(heater.isOn())
			setHeater(false);
		if(chiller.isOn())
			setChiller(false);
		
		notifications.add("Automation: " + ( onOrOff == true ? "on" : "off"));
	}
	public boolean isAutomated()
	{
		return isAutomated;
	}
	public double getOpTemp()
	{
		return opTemp;
	}
	public double getCurTemp()
	{
		return curTemp;
	}
	public double getMinTemp()
	{
		return minTemp;
	}
	public double getMaxtemp()
	{
		return maxTemp;
	}
	public boolean getScheduled()
	{
		return isScheduled;
	}
	public void setScheduled(double temp)
	{
		desiredTemp = temp;
		if(desiredTemp != curTemp)
		{
			isScheduled = true;
			isAutomated = false;
		}
	}
	public void turnOffScheduled()
	{
		isScheduled = false;
	}
	public ArrayList<String> getNotifications()
	{
		return notifications;
	}
	public void clearNotifications()
	{
		notifications.clear();
	}
	boolean isEqual(double d1, double d2)
	{
		return Math.abs(d1 - d2) < EPSILON * Math.max(Math.abs(d1), Math.abs(d2));
	}
	boolean lessThan(double d1, double d2)
	{
		int c = Double.compare(d1, d2);
		
		if(c < 0)
			return true;
		
		return false;
		
	}
	boolean greaterThan(double d1, double d2)
	{
		int c = Double.compare(d1, d2);
		
		if(c > 0)
			return true;
		
		return false;
	}
}
