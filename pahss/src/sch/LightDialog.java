package sch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

public class LightDialog extends JDialog {
	JTable table;
	JScrollPane tablescrollpane;
	JPanel centerPanel;
	JPanel buttonsPanel;
	JPanel confirmPanel;
	LIntervalDialog intervalDialog;
	
	public LightDialog(JFrame arg0) {
		// TODO Auto-generated constructor stub
		super(arg0, "Edit Schedule", true);
		setSize(450, 350);
		setLayout(new BorderLayout(5, 5));
		confirmPanel = new JPanel();
		centerPanel = new JPanel();
		confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.X_AXIS));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		add(centerPanel, BorderLayout.CENTER);
		add(confirmPanel, BorderLayout.SOUTH);
		table = new JTable();
		tablescrollpane = new JScrollPane(table);
		tablescrollpane.setPreferredSize(new Dimension(75, 50));
		centerPanel.add(tablescrollpane);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		centerPanel.add(buttonsPanel);
		JButton addButton = new JButton("Add Interval");
		JButton removeButton = new JButton("Remove Interval");
		JButton editButton = new JButton("Edit Interval");
		buttonsPanel.add(addButton);
		buttonsPanel.add(removeButton);
		buttonsPanel.add(editButton);
		
		intervalDialog = new LIntervalDialog(this);
		intervalDialog.setVisible(false);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				intervalDialog.clearInnerDialog();
				intervalDialog.setVisible(true);
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow()!=-1){
					LightTM ltm = (LightTM) table.getModel();
					ltm.removeInterval(table.getSelectedRow());
					ltm.fireTableDataChanged();
				}
			}
		});
		
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		confirmPanel.add(okButton);
		confirmPanel.add(cancelButton);
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}
	
	public void setTableModel(TableModel tm){
		table.setModel(tm);
	}
	
	public class LIntervalDialog extends JDialog {
		
		public static final int EDIT = 1;
		public static final int NEW = 0;
		private int mode;
		private final JPanel contentPanel = new JPanel();
		private JTextField hourField;
		private JTextField minuteField;
		private JTextField lumField;

		public LIntervalDialog(JDialog dialog) {
			super(dialog,true);
			setBounds(100, 100, 346, 156);
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setVgap(5);
			borderLayout.setHgap(5);
			getContentPane().setLayout(borderLayout);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			
			JLabel lblNewLabel = new JLabel("Start Hour");
			
			hourField = new JTextField();
			hourField.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Start Minute");
			
			minuteField = new JTextField();
			minuteField.setColumns(10);
			
			JLabel lblLumenosity = new JLabel("Luminosity");
			
			lumField = new JTextField();
			lumField.setColumns(10);
			GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
			gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addComponent(lblNewLabel)
							.addComponent(lblLumenosity))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(hourField, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
							.addComponent(minuteField, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
							.addComponent(lumField, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(hourField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(minuteField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblLumenosity)
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
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(mode == NEW){
								LightTM ltm = (LightTM) table.getModel();
								ltm.addInterval(Integer.parseInt(hourField.getText()), 
										Integer.parseInt(minuteField.getText()), Integer.parseInt(hourField.getText()), 
										Integer.parseInt(minuteField.getText()), Double.parseDouble(lumField.getText()));
								ltm.fireTableDataChanged();
								setVisible(false);
							} else{
								
							}
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							setVisible(false);
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
		
		public void setMode(int modenum){
			mode = modenum;
		}
		
		public void clearInnerDialog(){
			hourField.setText("");
			minuteField.setText("");
			lumField.setText("");
		}
	}
}
