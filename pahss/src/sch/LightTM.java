package sch;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class LightTM extends AbstractTableModel {
	LightEntry entry;
	
	public LightTM() {
		// TODO Auto-generated constructor stub
	}
	
	public LightTM(LightEntry entry) {
		// TODO Auto-generated constructor stub
		super();
		this.entry = entry;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return entry.getIntervalList().size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		SCHInterval interval = entry.getIntervalList().get(arg0);
		if(arg1==0){
			return String.format("%1$02d:%2$02d",interval.getStart_hour(),interval.getStart_minute());
		} else{
			return entry.getIntervalLumen(arg0);
		}
	}

}
