package sch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;


public abstract class EntryEditDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -5954386544326168721L;
	protected JPanel contentPanel;
	protected JButton addButton;
	protected JButton removeButton;
	protected JButton editButton;
	protected JTable intervalTable;
	/**
	 * Create the dialog.
	 */
	public EntryEditDialog(JFrame owner, boolean modal) {
		super(owner,modal);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
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
		tableScrollPane.setBorder(new TitledBorder(null, "Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
	
	public abstract class IntervalDialog extends JDialog implements ActionListener{

		public static final int NEW = 0;
		public static final int EDIT = 1;
		protected static final long serialVersionUID = -1719412600118554199L;
		protected final JPanel contentPanel;
		protected JTextField startHField;
		protected JTextField startMField;
		protected JLabel startHLabel = new JLabel("Start Hour");
		protected JLabel startMLabel = new JLabel("Start Minute");
		protected int mode;
		/**
		 * Create the dialog.
		 */
		public IntervalDialog(JDialog owner, boolean modal) {
			super(owner,modal);
			setBounds(100, 100, 346, 156);
			setResizable(false);
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setVgap(5);
			borderLayout.setHgap(5);
			getContentPane().setLayout(borderLayout);
			contentPanel = new JPanel();
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			
			startHField = new JTextField();
			startHField.setColumns(10);
			
			startMField = new JTextField();
			startMField.setColumns(10);
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
		}
		
		public abstract void setMode(int mode);
	}
	
	public void loadEntry(SCHTableModel model) {
		// TODO Auto-generated method stub
		try {
			intervalTable.setModel((TableModel) model.clone());
			if(!(((SCHTableModel)intervalTable.getModel()).isEmpty())){
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
	
}
