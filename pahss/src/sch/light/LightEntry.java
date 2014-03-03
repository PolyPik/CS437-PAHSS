package sch.light;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import sch.SCHEntry;

public class LightEntry extends SCHEntry {
	private ArrayList<LightInterval> interval_list;
	private int nextLumIndex;
	private LightRunnable light_runnable;
	
	public LightEntry(String name, String id) {
		super(name,id);
		interval_list = new ArrayList<LightInterval>();
		light_runnable = new LightRunnable();
		// TODO Auto-generated constructor stub
	}
	
	public void addInterval(int hour1, int minute1, double lum) {
		// TODO Auto-generated method stub
		LightInterval interv = new LightInterval(hour1, minute1, lum);
		interval_list.add(interv);
		Collections.sort(interval_list);
	}
	
	public void editInterval(int i, int hour1, int minute1, double lum){
		LightInterval interv = interval_list.get(i);
		interv.setStarttime(hour1, minute1);
		interv.setBrightness(lum);
		Collections.sort(interval_list);
	}
	
	public void removeInterval(int i){
		interval_list.remove(i);
		Collections.sort(interval_list);
	}

	public List<LightInterval> getIntervalList() {
		// TODO Auto-generated method stub
		return interval_list;
	}
	
	public void setIntervalList(List<LightInterval> list){
		this.interval_list = (ArrayList<LightInterval>) list;
	}

	@Override
	public void applyCalendar(){
		if(start_calendar.length()>0){
			start_calendar.delete(0, start_calendar.length());
		}
		
		for(int i = 0; i < interval_list.size(); i++){
			start_calendar.append(interval_list.get(i).getStart_minute()+" ");
			start_calendar.append(interval_list.get(i).getStart_hour());
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
			
			start_taskid = starter.schedule(start_calendar.toString(), light_runnable);
		}
	}

	@Override
	public void startSchedulers(){
		nextLumIndex = getNextBrightnessIndex();
		starter.start();
	}

	@Override
	public void stopSchedulers() {
		// TODO Auto-generated method stub
		starter.stop();
	}
	
	private int getNextBrightnessIndex(){
		int i;
		Calendar now = Calendar.getInstance();
		for(i = 0; i < interval_list.size(); i++){
			if(now.get(Calendar.HOUR_OF_DAY)<interval_list.get(i).getStart_hour()){
				return i;
			} else if(now.get(Calendar.HOUR_OF_DAY)==interval_list.get(i).getStart_hour()){
				if(now.get(Calendar.MINUTE)<interval_list.get(i).getStart_minute()){
					return i;
				}
			}
		}
		
		return 0;
	}
	
	private class LightRunnable implements Runnable{
		public LightRunnable() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//System.out.println(name+" has been set to "+interval_list.get(nextLumIndex).getBrightness()+" lumens");
			try {
				SCHEntry.notifications.write(name+" has been set to "+interval_list.get(nextLumIndex).getBrightness()+" lumens");
				//SCHEntry.notifications.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nextLumIndex = ((nextLumIndex+1)%interval_list.size());
		}
	}
}
