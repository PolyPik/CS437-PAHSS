package sch;

public class SCHInterval {
	private int start_hour;
	private int start_minute;
	private int stop_hour;
	private int stop_minute;
	
	public SCHInterval() {
		// TODO Auto-generated constructor stub
	}
	
	public void setStarttime(int hour, int minute){
		start_hour = hour;
		start_minute = minute;
	}
	
	public void setStoptime(int hour, int minute){
		stop_hour = hour;
		stop_minute = minute;
	}
}
