package sch.feeder;

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
import sch.SCHTableModel;
import sch.light.LightTM;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;


public class FeederEditDialog extends EntryEditDialog implements ActionListener{
	private static final long serialVersionUID = -5954386544326168721L;
	private FIntervalDialog fidialog;
	private JTextField rateField;
	/**
	 * Create the dialog.
	 */
	public FeederEditDialog(JFrame owner, boolean modal) {
		super(owner,modal);
		fidialog = new FIntervalDialog(this,true);
		
		JPanel ratePanel = new JPanel();
		ratePanel.setBorder(new TitledBorder(null, "Rate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) ratePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(ratePanel, BorderLayout.NORTH);
		
		JLabel lblRate = new JLabel("Rate (kg/min):");
		ratePanel.add(lblRate);
		
		rateField = new JTextField();
		ratePanel.add(rateField);
		rateField.setColumns(20);
		fidialog.setVisible(false);
	}
	
	private class FIntervalDialog extends EntryEditDialog.IntervalDialog implements ActionListener{
		private static final long serialVersionUID = -1719412600118554199L;
		private JTextField stopHField;
		private JTextField stopMField;
		private int mode;
		/**
		 * Create the dialog.
		 */
		public FIntervalDialog(JDialog owner, boolean modal) {
			super(owner,modal);
			
			JLabel stopHLabel = new JLabel("Stop Hour");
			
			stopHField = new JTextField();
			stopHField.setColumns(10);
			
			stopMField = new JTextField();
			stopMField.setColumns(10);
			
			JLabel stopMLabel = new JLabel("Stop Minute");
			GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
			gl_contentPanel.setHorizontalGroup(
					gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(startMLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(startHLabel, Alignment.LEADING))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(startHField, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
										.addComponent(startMField, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(stopMLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(stopHLabel, Alignment.LEADING))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(stopMField, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
										.addComponent(stopHField, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))))
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
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(stopHLabel)
								.addComponent(stopHField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(stopMLabel)
								.addComponent(stopMField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
			contentPanel.setLayout(gl_contentPanel);
			{
				JPanel buttonPane = new JPanel();
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					JButton okButton = new JButton("OK");
					okButton.setActionCommand("OK");
					okButton.addActionListener(this);
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					cancelButton.addActionListener(this);
					buttonPane.add(cancelButton);
				}
			}
			pack();
		}
		
		public void setMode(int mode){
			if(mode == NEW){
				startHField.setText("");
				startMField.setText("");
				stopHField.setText("");
				stopMField.setText("");
			} else{
				FeederTM tm = (FeederTM)intervalTable.getModel();
				startHField.setText(String.valueOf(tm.getInterval(intervalTable.getSelectedRow()).getStart_hour()));
				startMField.setText(String.valueOf(tm.getInterval(intervalTable.getSelectedRow()).getStart_minute()));
				stopHField.setText(String.valueOf(tm.getInterval(intervalTable.getSelectedRow()).getStop_hour()));
				stopMField.setText(String.valueOf(tm.getInterval(intervalTable.getSelectedRow()).getStop_minute()));
			}
			this.mode = mode;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("OK")){
				FeederTM tm = (FeederTM)intervalTable.getModel();
				if(mode == NEW){
					tm.addInterval(
							Integer.parseInt(startHField.getText()),
							Integer.parseInt(startMField.getText()),
							Integer.parseInt(stopHField.getText()),
							Integer.parseInt(stopMField.getText()));
					tm.fireTableDataChanged();
					editButton.setEnabled(true);
					removeButton.setEnabled(true);
					intervalTable.setRowSelectionInterval(0, 0);
				} else{
					tm.getInterval(intervalTable.getSelectedRow()).setStarttime(Integer.parseInt(startHField.getText()), Integer.parseInt(startMField.getText()));
					tm.getInterval(intervalTable.getSelectedRow()).setStoptime(Integer.parseInt(stopMField.getText()), Integer.parseInt(stopMField.getText()));
					tm.fireTableDataChanged();
				}
				tm.setRate(Double.parseDouble(rateField.getText()));
				//((LightTM)intervalTable.getModel()).fireTableDataChanged();
				setVisible(false);
			} else if(e.getActionCommand().equals("Cancel")){
				setVisible(false);
			}
		}
	}
	
	@Override
	public void loadEntry(SCHTableModel model) {
		// TODO Auto-generated method stub
		super.loadEntry(model);
		rateField.setText(String.valueOf(((FeederTM)intervalTable.getModel()).getRate()));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Add Interval")){
			fidialog.setMode(FIntervalDialog.NEW);
			fidialog.setVisible(true);
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
			fidialog.setMode(FIntervalDialog.EDIT);
			fidialog.setVisible(true);
		} else if(e.getActionCommand().equals("OK")){
			((FeederTM)intervalTable.getModel()).applySchedule();
			dispose();
		} else if(e.getActionCommand().equals("Cancel")){
			setVisible(false);
		} else if(e.getActionCommand().equals("Apply")){
			((FeederTM)intervalTable.getModel()).applySchedule();
		}
	}

	
}
