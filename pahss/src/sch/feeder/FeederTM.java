package sch.feeder;

import java.util.ArrayList;
import java.util.Collections;

import sch.SCHTableModel;

public class FeederTM extends SCHTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1312050698164038630L;

	private FeederEntry entry;
	private ArrayList<FeederInterval> intervals;
	
	private static final String[] columnNames = {"Start Time", "Stop Time"};
	
	public FeederTM(FeederEntry entry) {
		// TODO Auto-generated constructor stub
		super();
		this.entry = entry;
		this.intervals = new ArrayList<FeederInterval>();
		for(FeederInterval i: entry.getIntervalList()){
			try {
				this.intervals.add(i.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return intervals.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		FeederInterval interval = intervals.get(arg0);
		if(arg1==0){
			return String.format("%1$02d:%2$02d",interval.getStart_hour(),interval.getStart_minute());
		} else{
			return String.format("%1$02d:%2$02d",interval.getStop_hour(),interval.getStop_minute());
		}
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	
	public FeederInterval getInterval(int i){
		return intervals.get(i);
	}
	
	public void addInterval(int start_h, int start_m, int stop_h, int stop_m){
		intervals.add(new FeederInterval(start_h, start_m, stop_h, stop_m));
		Collections.sort(intervals);
	}
	
	public void removeInterval(int i){
		intervals.remove(i);
		Collections.sort(intervals);
	}
	
	public double getRate(){
		return entry.getRate();
	}
	
	public void setRate(double rate){
		entry.setRate(rate);
	}
	
	public void sortIntervals(){
		Collections.sort(intervals);
	}
	
	public void applySchedule(){
		entry.setIntervalList(intervals);
		if(!(entry.getIntervalList().isEmpty())){
			entry.applyCalendar();
		}
	}
	
	public void resyncEntry(){
		this.intervals.clear();
		for(FeederInterval i: entry.getIntervalList()){
			try {
				this.intervals.add(i.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean isEmpty(){
		return intervals.isEmpty();
	}
	
	@Override
	protected FeederTM clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		FeederTM clone = new FeederTM(entry);
		return clone;
	}
}
