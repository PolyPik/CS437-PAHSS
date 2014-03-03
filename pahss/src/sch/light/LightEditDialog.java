package sch.light;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import sch.EntryEditDialog;


public class LightEditDialog extends EntryEditDialog implements ActionListener{
	private static final long serialVersionUID = -5954386544326168721L;
	private LIntervalDialog lidialog;
	/**
	 * Create the dialog.
	 */
	public LightEditDialog(JFrame owner, boolean modal) {
		super(owner,modal);
		setBounds(100, 100, 450, 300);
		lidialog = new LIntervalDialog(this,true);
		lidialog.setVisible(false);
	}
	
	private class LIntervalDialog extends EntryEditDialog.IntervalDialog implements ActionListener{
		private static final long serialVersionUID = -1719412600118554199L;
		private JTextField lumField;
		/**
		 * Create the dialog.
		 */
		public LIntervalDialog(JDialog owner, boolean modal) {
			super(owner,modal);
			
			JLabel lumLabel = new JLabel("Luminosity");
			
			lumField = new JTextField();
			lumField.setColumns(10);
			GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
			gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(startMLabel)
							.addComponent(startHLabel)
							.addComponent(lumLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(startHField, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
							.addComponent(startMField, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
							.addComponent(lumField, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(startHLabel)
							.addComponent(startHField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(startMLabel)
							.addComponent(startMField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lumLabel)
							.addComponent(lumField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			contentPanel.setLayout(gl_contentPanel);
			pack();
		}
		
		public void setMode(int mode){
			if(mode == NEW){
				startHField.setText("");
				startMField.setText("");
				lumField.setText("");
			} else{
				LightTM tm = (LightTM)intervalTable.getModel();
				startHField.setText(String.valueOf(tm.getInterval(intervalTable.getSelectedRow()).getStart_hour()));
				startMField.setText(String.valueOf(tm.getInterval(intervalTable.getSelectedRow()).getStart_minute()));
				lumField.setText(String.valueOf(tm.getInterval(intervalTable.getSelectedRow()).getBrightness()));
			}
			this.mode = mode;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("OK")){
				LightTM tm = (LightTM)intervalTable.getModel();
				if(mode == NEW){
					tm.addInterval(
							Integer.parseInt(startHField.getText()),
							Integer.parseInt(startMField.getText()),
							Double.parseDouble(lumField.getText()));
					tm.fireTableDataChanged();
					editButton.setEnabled(true);
					removeButton.setEnabled(true);
					intervalTable.setRowSelectionInterval(0, 0);
				} else{
					tm.getInterval(intervalTable.getSelectedRow()).setStarttime(Integer.parseInt(startHField.getText()), Integer.parseInt(startMField.getText()));
					tm.getInterval(intervalTable.getSelectedRow()).setBrightness(Double.parseDouble(lumField.getText()));
					tm.fireTableDataChanged();
				}
				//((LightTM)intervalTable.getModel()).fireTableDataChanged();
				setVisible(false);
			} else if(e.getActionCommand().equals("Cancel")){
				setVisible(false);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Add Interval")){
			lidialog.setMode(LIntervalDialog.NEW);
			lidialog.setVisible(true);
		} else if(e.getActionCommand().equals("Remove Interval")){
			int last_select = intervalTable.getSelectedRow();
			((LightTM)intervalTable.getModel()).removeInterval(last_select);
			((LightTM)intervalTable.getModel()).fireTableDataChanged();
			if((last_select-1)>-1){
				intervalTable.setRowSelectionInterval(last_select-1, last_select-1);
			}
			if(((LightTM)intervalTable.getModel()).isEmpty()){
				editButton.setEnabled(false);
				removeButton.setEnabled(false);
			}
		} else if(e.getActionCommand().equals("Edit Interval")){
			lidialog.setMode(LIntervalDialog.EDIT);
			lidialog.setVisible(true);
		} else if(e.getActionCommand().equals("OK")){
			((LightTM)intervalTable.getModel()).applySchedule();
			dispose();
		} else if(e.getActionCommand().equals("Cancel")){
			setVisible(false);
		} else if(e.getActionCommand().equals("Apply")){
			((LightTM)intervalTable.getModel()).applySchedule();
		}
	}

	
}
