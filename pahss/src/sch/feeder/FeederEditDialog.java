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

import sch.light.LightTM;


public class FeederEditDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -5954386544326168721L;
	private final JPanel contentPanel = new JPanel();
	private JButton addButton;
	private JButton removeButton;
	private JButton editButton;
	private JTable intervalTable;
	private FIntervalDialog fidialog;
	/**
	 * Create the dialog.
	 */
	public FeederEditDialog(JFrame owner, boolean modal) {
		super(owner,modal);
		setBounds(100, 100, 450, 300);
		fidialog = new FIntervalDialog(this,true);
		fidialog.setVisible(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel modButtonPanel = new JPanel();
			contentPanel.add(modButtonPanel, BorderLayout.EAST);
			{
				addButton = new JButton("Add Interval");
				addButton.setActionCommand("Add Interval");
				addButton.addActionListener(this);
			}
			
			removeButton = new JButton("Remove Interval");
			removeButton.setActionCommand("Remove Interval");
			removeButton.addActionListener(this);
			
			editButton = new JButton("Edit Interval");
			editButton.setActionCommand("Edit Interval");
			editButton.addActionListener(this);
			GroupLayout gl_modButtonPanel = new GroupLayout(modButtonPanel);
			gl_modButtonPanel.setHorizontalGroup(
				gl_modButtonPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_modButtonPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_modButtonPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addComponent(editButton, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_modButtonPanel.setVerticalGroup(
				gl_modButtonPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_modButtonPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(addButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(removeButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(editButton)
						.addContainerGap(127, Short.MAX_VALUE))
			);
			modButtonPanel.setLayout(gl_modButtonPanel);
		}
		
		JScrollPane tableScrollPane = new JScrollPane();
		contentPanel.add(tableScrollPane, BorderLayout.CENTER);
		
		intervalTable = new JTable();
		intervalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableScrollPane.setViewportView(intervalTable);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
			{
				JButton applyButton = new JButton("Apply");
				applyButton.setActionCommand("Apply");
				applyButton.addActionListener(this);
				buttonPane.add(applyButton);
				
			}
		}
	}
	
	private class FIntervalDialog extends JDialog implements ActionListener{

		public static final int NEW = 0;
		public static final int EDIT = 1;
		private static final long serialVersionUID = -1719412600118554199L;
		private final JPanel contentPanel = new JPanel();
		private JTextField startHField;
		private JTextField startMField;
		private JTextField lumField;
		private int mode;
		/**
		 * Create the dialog.
		 */
		public FIntervalDialog(JDialog owner, boolean modal) {
			super(owner,modal);
			setBounds(100, 100, 346, 156);
			setResizable(false);
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setVgap(5);
			borderLayout.setHgap(5);
			getContentPane().setLayout(borderLayout);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			
			JLabel startHLabel = new JLabel("Start Hour");
			
			startHField = new JTextField();
			startHField.setColumns(10);
			
			JLabel startMLabel = new JLabel("Start Minute");
			
			startMField = new JTextField();
			startMField.setColumns(10);
			
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
						.addContainerGap(21, Short.MAX_VALUE))
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
	public void loadEntry(FeederTM feederTM) {
			// TODO Auto-generated method stub
		try {
			intervalTable.setModel(feederTM.clone());
			if(!(((FeederTM)intervalTable.getModel()).isEmpty())){
				intervalTable.setRowSelectionInterval(0, 0);
			} else{
				editButton.setEnabled(false);
				removeButton.setEnabled(false);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			((LightTM)intervalTable.getModel()).applySchedule();
			dispose();
		} else if(e.getActionCommand().equals("Cancel")){
			setVisible(false);
		} else if(e.getActionCommand().equals("Apply")){
			((LightTM)intervalTable.getModel()).applySchedule();
		}
	}

	
}
