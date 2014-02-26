package sch;

import java.util.Collections;
import java.util.HashMap;

public class LightEntry extends SCHEntry {
	HashMap<SCHInterval,Double> intensity_table;
	public LightEntry(String name) {
		super(name);
		intensity_table = new HashMap<SCHInterval,Double>();
		// TODO Auto-generated constructor stub
	}
	
	public void addInterval(int hour1, int minute1, int hour2, int minute2, double lum) {
		// TODO Auto-generated method stub
		SCHInterval interv = new SCHInterval(hour1, minute1, hour2, minute2);
		interval_list.add(interv);
		intensity_table.put(interv, new Double(lum));
		Collections.sort(interval_list);
	}
	
	public void setIntervalLumen(int i,double lum){
		SCHInterval interval = interval_list.get(i);
		intensity_table.put(interval, new Double(lum));
	}
	
	public Double getIntervalLumen(int i){
		SCHInterval interval = interval_list.get(i);
		return intensity_table.get(interval);
	}
	
	public void removeInterval(int i){
		intensity_table.remove(interval_list.remove(i));
		Collections.sort(interval_list);
	}
}
