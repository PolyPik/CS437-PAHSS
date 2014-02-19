package sch;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.ArrayList;
import java.util.Collections;

public abstract class SCHEntry {
	Scheduler starter;
	Scheduler stopper;
	ArrayList<SCHInterval> interval_list;
	
	public SCHEntry(){
		interval_list = new ArrayList<SCHInterval>();
		starter = new Scheduler();
		stopper = new Scheduler();
	}
	
	public void addInterval(int hour1, int minute1, int hour2, int minute2){
		SCHInterval o = new SCHInterval();
		o.setStarttime(hour1, minute1);
		o.setStoptime(hour2, minute2);
		interval_list.add(o);
		Collections.sort(interval_list);
	}
	
	public void removeInterval(int i){
		interval_list.remove(i);
		Collections.sort(interval_list);
	}
	
	public void editInterval(int i, int hour1, int minute1, int hour2, int minute2){
		SCHInterval interv = interval_list.get(i);
		interv.setStarttime(hour1, minute1);
		interv.setStoptime(hour2, minute2);
		Collections.sort(interval_list);
	}
	
	public void startSchedulers(){
		starter.start();
		stopper.start();
	}
	
	public void stopSchedulers(){
		starter.stop();
		stopper.stop();
	}
}
