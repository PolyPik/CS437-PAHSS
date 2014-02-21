package sch;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class IntervalRenderer extends JLabel implements
		ListCellRenderer<SCHInterval> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2822301239075274841L;

	@Override
	public Component getListCellRendererComponent(
			JList<? extends SCHInterval> list, SCHInterval value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		setText(value.getStart_hour()+":"+value.getStart_minute()+" to "+value.getStop_hour()+":"+value.getStop_minute());
		return this;
	}

}
