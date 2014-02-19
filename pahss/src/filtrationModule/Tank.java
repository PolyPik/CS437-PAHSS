package filtrationModule;

import java.util.ArrayList;
import java.util.List;

public class Tank {

	double waterLevel;
		
	List<WaterPump> pumps;
	List<Filter> filters;
	
	public Tank(double wLvl)
	{
		this.waterLevel=wLvl;
		pumps = new ArrayList<WaterPump>();
		filters = new ArrayList<Filter>();
				
	}
	
	public void addFilter(Filter filter)
	{
		
		filters.add(filter);
		
	}
	public void addWaterPump(WaterPump wp)
	{
		pumps.add(wp);
	}
	
	
	
}
