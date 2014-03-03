package sch;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.List;

import javax.swing.JTextArea;

import pahss_core.NotificationWriter;

public abstract class SCHEntry {
	protected String name;
	protected String id;
	protected Scheduler starter;
	protected Scheduler stopper;
	protected StringBuilder start_calendar;
	protected StringBuilder stop_calendar;
	protected boolean hasStarted= false;
	protected String start_taskid;
	protected String stop_taskid;
	protected static NotificationWriter notifications;
	
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
	
	public abstract List<? extends SCHInterval> getIntervalList();
	
	public boolean isStarted(){
		return starter.isStarted();
	}
	
	public String getName(){
		return name;
	}
	
	public static void setNotifications(NotificationWriter notificationWriter) {
		notifications = notificationWriter;
	}
}
