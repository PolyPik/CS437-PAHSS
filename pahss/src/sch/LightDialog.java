package sch;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.TableModel;

public class LightDialog extends JDialog {
	JTable table;
	JScrollPane tablescrollpane;
	JPanel centerPanel;
	JPanel buttonsPanel;
	JPanel confirmPanel;
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
		
		JButton OkButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		confirmPanel.add(OkButton);
		confirmPanel.add(cancelButton);
	}
	
	public void setTableModel(TableModel tm){
		table.setModel(tm);
	}
}
