package sch;

import java.util.Collections;

public class FeederEntry extends SCHEntry {
	public FeederEntry(String name, String id) {
		// TODO Auto-generated constructor stub
		super(name, id);
	}
	
	public void addInterval(int hour1, int minute1, int hour2, int minute2){
		SCHInterval o = new SCHInterval();
		o.setStarttime(hour1, minute1);
		o.setStoptime(hour2, minute2);
		//interval_list.add(o);
		//Collections.sort(interval_list);
	}

	@Override
	public void removeInterval(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyCalendar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startSchedulers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopSchedulers() {
		// TODO Auto-generated method stub
		
	}
}
