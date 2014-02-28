package sch;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SCHEntry {
	protected String name;
	protected String id;
	protected Scheduler starter;
	protected Scheduler stopper;
	protected StringBuilder start_calendar;
	protected StringBuilder stop_calendar;
	boolean hasStarted= false;
	protected String start_taskid;
	protected String stop_taskid;
	
	public SCHEntry(String name, String id){
		this.name = name;
		this.id = id;
		starter = new Scheduler();
		stopper = new Scheduler();
		start_calendar = new StringBuilder();
		stop_calendar = new StringBuilder();
	}
	
	public abstract void removeInterval(int i);

	public abstract void applyCalendar();
	
	public abstract void startSchedulers();
	
	public abstract void stopSchedulers();
	
	//public abstract List<? extends SCHInterval> getIntervalList();
	
	public String getName(){
		return name;
	}
}
