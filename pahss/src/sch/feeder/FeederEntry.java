package sch.feeder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import sch.SCHEntry;

public class FeederEntry extends SCHEntry {
	private List<FeederInterval> interval_list;
	private FeederRunnable feeder_runnable;
	private double rate = 0;
	

	private boolean isSendStart;

	public FeederEntry(String name, String id) {
		// TODO Auto-generated constructor stub
		super(name, id);
		interval_list = new ArrayList<FeederInterval>();
		feeder_runnable = new FeederRunnable();
	}
	
	public void addInterval(int hour1, int minute1, int hour2, int minute2){
		FeederInterval o = new FeederInterval();
		o.setStarttime(hour1, minute1);
		o.setStoptime(hour2, minute2);
		interval_list.add(o);
		Collections.sort(interval_list);
	}
	
	public void removeInterval(int i){
		interval_list.remove(i);
		Collections.sort(interval_list);
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate){
		this.rate = rate;
	}

	public List<FeederInterval> getIntervalList() {
		// TODO Auto-generated method stub
		return interval_list;
	}
	
	public void setIntervalList(List<FeederInterval> list){
		this.interval_list = (ArrayList<FeederInterval>) list;
	}

	@Override
	public void applyCalendar(){
		if(start_calendar.length()>0){
			start_calendar.delete(0, start_calendar.length());
		}
		
		for(int i = 0; i < interval_list.size(); i++){
			start_calendar.append(interval_list.get(i).getStart_minute()+" ");
			start_calendar.append(interval_list.get(i).getStart_hour());
			start_calendar.append(" * * *|");
			start_calendar.append(interval_list.get(i).getStop_minute()+" ");
			start_calendar.append(interval_list.get(i).getStop_hour());
			start_calendar.append(" * * *");
			if(i!=(interval_list.size()-1)){
				start_calendar.append("|");
			}
		}
		
		if(starter.isStarted()){
			starter.reschedule(start_taskid, start_calendar.toString());
		} else{
			if(start_taskid!=null){
				starter.deschedule(start_taskid);
			}
			start_taskid = starter.schedule(start_calendar.toString(), feeder_runnable);
		}
	}
	
	private boolean checkTime(){
		Calendar now = Calendar.getInstance();
		int timeval = (now.get(Calendar.HOUR_OF_DAY)*60)+now.get(Calendar.MINUTE);
		int startval, stopval;
		for(int i = 0; i < interval_list.size(); i++){
			startval = (interval_list.get(i).getStart_hour()*60)+interval_list.get(i).getStart_minute();
			stopval = (interval_list.get(i).getStop_hour()*60)+interval_list.get(i).getStop_minute();
			
			if((stopval < startval) && (timeval > startval)){
				return false;
			} else{
				if((startval > timeval) || (stopval > timeval)){
					if(startval > timeval){
						return true;
					} else{
						return false;
					}
				}
			}
		}
		
		return true;
		
		
	}

	@Override
	public void startSchedulers(){
		isSendStart = checkTime();
		starter.start();
	}

	@Override
	public void stopSchedulers() {
		// TODO Auto-generated method stub
		starter.stop();
	}
	
	private class FeederRunnable implements Runnable{
		public FeederRunnable() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(isSendStart){
				//System.out.println(name+" is now dispensing fish feed at a rate of "+rate+" kg/min.");
				try {
					SCHEntry.notifications.write(name+" is now dispensing fish feed at a rate of "+rate+" kg/min.");
					//SCHEntry.notifications.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isSendStart = false;
			} else{
				//System.out.println(name+" has stopped dispensing fish feed");
				try {
					SCHEntry.notifications.write(name+" has stopped dispensing fish feed");
					//SCHEntry.notifications.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isSendStart = true;
			}
			
		}
	}
	

}
