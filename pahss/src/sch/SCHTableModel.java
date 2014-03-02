package sch;

import javax.swing.table.AbstractTableModel;

public abstract class SCHTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7331906733209274266L;

	public abstract void removeInterval(int i);
	
	public abstract void sortIntervals();
	
	public abstract void applySchedule();
	
	public abstract void resyncEntry();
	
	public abstract boolean isEmpty();
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
