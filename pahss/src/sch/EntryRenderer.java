package sch;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class EntryRenderer extends JLabel implements ListCellRenderer<SCHEntry> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2436044270555054989L;

	@Override
	public Component getListCellRendererComponent(
			JList<? extends SCHEntry> list, SCHEntry value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		setText(value.getName());
		setOpaque(true);
		
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            //setBackground(list.getBackground());
            if(value.isStarted()){
            	setBackground(new Color(200, 240, 200));
            } else{
            	setBackground(new Color(240, 200, 200));
            }
            setForeground(list.getForeground());
        }
		return this;
	}

}
