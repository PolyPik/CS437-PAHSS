package sch.feeder;

import sch.SCHInterval;




public class FeederInterval extends SCHInterval {

	public FeederInterval() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeederInterval(int start_hour, int start_minute, int stop_hour,
			int stop_minute) {
		super(start_hour, start_minute);
		this.stop_hour = stop_hour;
		this.stop_minute = stop_minute;
		// TODO Auto-generated constructor stub
	}
	
	public void setStoptime(int hour, int minute){
		stop_hour = hour;
		stop_minute = minute;
	}
	
	public int getStop_hour() {
		return stop_hour;
	}

	public int getStop_minute() {
		return stop_minute;
	}
	
	@Override
	protected FeederInterval clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		FeederInterval clone = new FeederInterval(start_hour, start_minute, stop_hour, stop_minute);
		return clone;
	}
}
