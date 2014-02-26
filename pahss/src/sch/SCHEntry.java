package sch;

//import it.sauronsoftware.cron4j.Scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SCHEntry {
	String name;
	//Scheduler starter;
	//Scheduler stopper;
	//StringBuilder start_calendar;
	//StringBuilder stop_calendar;
	ArrayList<SCHInterval> interval_list;
	boolean hasStarted= false;
	String start_taskid;
	String stop_taskid;
	
	public SCHEntry(String name){
		interval_list = new ArrayList<SCHInterval>();
		this.name = name;
		//starter = new Scheduler();
		//stopper = new Scheduler();
		//start_calendar = new StringBuilder();
		//stop_calendar = new StringBuilder();
	}
	
	public abstract void removeInterval(int i);
	
	public void editInterval(int i, int hour1, int minute1, int hour2, int minute2){
		SCHInterval interv = interval_list.get(i);
		interv.setStarttime(hour1, minute1);
		interv.setStoptime(hour2, minute2);
		Collections.sort(interval_list);
	}

//	public void applyCalendar(){
//		if(start_calendar.length()>0){
//			start_calendar.delete(0, start_calendar.length());
//		}
//		
//		if(stop_calendar.length()>0){
//			stop_calendar.delete(0, stop_calendar.length());
//		}
//		
//		for(int i = 0; i < interval_list.size(); i++){
//			start_calendar.append(interval_list.get(i).getStart_minute());
//			start_calendar.append(interval_list.get(i).getStart_hour());
//			start_calendar.append(" * * *");
//			stop_calendar.append(interval_list.get(i).getStop_minute());
//			stop_calendar.append(interval_list.get(i).getStop_hour());
//			stop_calendar.append(" * * *");
//			if(i!=(interval_list.size()-1)){
//				start_calendar.append("|");
//				stop_calendar.append("|");
//			}
//		}
//	}
//	
//	public void startSchedulers(){
//		starter.start();
//		stopper.start();
//	}
//	
//	public void stopSchedulers(){
//		starter.stop();
//		stopper.stop();
//	}
	
	public List<SCHInterval> getIntervalList(){
		return interval_list;
	}
	
	public String getName(){
		return name;
	}
}
