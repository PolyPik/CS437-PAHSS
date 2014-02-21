package sch;

import java.util.HashMap;

public class LightEntry extends SCHEntry {
	HashMap<SCHInterval,Double> intensity_table;
	public LightEntry(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void setIntervalLumen(int i,double lum){
		SCHInterval interval = interval_list.get(i);
		intensity_table.put(interval, new Double(lum));
	}
	
	public Double getIntervalLumen(int i,double lum){
		SCHInterval interval = interval_list.get(i);
		return intensity_table.get(interval);
	}
}
