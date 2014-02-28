package sch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

public class LightTM extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1312050698164038630L;

	private LightEntry entry;
	private ArrayList<LightInterval> intervals;
	
	private static final String[] columnNames = {"Start Time", "Luminosity"};
	
	public LightTM(LightEntry entry) {
		// TODO Auto-generated constructor stub
		super();
		this.entry = entry;
		this.intervals = new ArrayList<LightInterval>();
		for(LightInterval i: entry.getIntervalList()){
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
		LightInterval interval = intervals.get(arg0);
		if(arg1==0){
			return String.format("%1$02d:%2$02d",interval.getStart_hour(),interval.getStart_minute());
		} else{
			return interval.getBrightness();
		}
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	
	public LightInterval getInterval(int i){
		return intervals.get(i);
	}
	
	public void addInterval(int hour, int minute, double d){
		intervals.add(new LightInterval(hour, minute, hour, minute, d));
		Collections.sort(intervals);
	}
	
	public void removeInterval(int i){
		intervals.remove(i);
		Collections.sort(intervals);
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
		for(LightInterval i: entry.getIntervalList()){
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
	protected LightTM clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		LightTM clone = new LightTM(entry);
		return clone;
	}
}
