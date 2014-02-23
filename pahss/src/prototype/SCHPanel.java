package prototype;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sch.*;

public class SCHPanel extends JPanel{
	private JFrame frame;
	private LightDialog testdialog1;
	private SCHModel currentSCHModel;
	JList<SCHEntry> EntryList;
	JTable IntervalTable;
	JTable IntervalTable2;
	DefaultListModel<SCHEntry> EntryLM;
	HashMap<SCHEntry,DefaultListModel<SCHInterval>> EntryIntervalMap;
	HashMap<LightEntry,LightTM> LightIntervalMap;
	HashMap<FeederEntry,DefaultTableModel> FeederIntervalMap;
	/**
	 * 
	 */
	private static final long serialVersionUID = 629536423038624531L;
	
	public SCHPanel(JFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		IntervalTable2 = new JTable();
		
		EntryLM = new DefaultListModel<SCHEntry>();
		EntryIntervalMap = new HashMap<SCHEntry,DefaultListModel<SCHInterval>>();
		LightIntervalMap = new HashMap<LightEntry,LightTM>();
		
		EntryList = new JList<SCHEntry>(EntryLM);
		EntryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane entryScrollPane = new JScrollPane(EntryList);
		
		this.add(entryScrollPane);
		
		IntervalTable = new JTable();
		IntervalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane intervalScrollPane = new JScrollPane(IntervalTable);
		
		this.add(intervalScrollPane);
		
		JPanel SCHButtonPanel = new JPanel();
		SCHButtonPanel.setLayout(new BoxLayout(SCHButtonPanel, BoxLayout.Y_AXIS));
		
		JButton startScheduleButton = new JButton("Start Schedule");
		JButton stopScheduleButton = new JButton("Stop Schedule");
		JButton editScheduleButton = new JButton("Edit Schedule");
		
		SCHButtonPanel.add(startScheduleButton);
		SCHButtonPanel.add(stopScheduleButton);
		SCHButtonPanel.add(editScheduleButton);
		
		this.add(SCHButtonPanel);
		
		EntryList.setCellRenderer(new EntryRenderer());
		//IntervalTable.setCellRenderer(new IntervalRenderer());
		
		EntryList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				//int index = e.getFirstIndex();
				//IntervalTable.setModel(EntryIntervalMap.get(EntryList.getSelectedValue()));
			}
		});
		
		
		testdialog1 = new LightDialog(frame);
		editScheduleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				testdialog1.setVisible(true);
			}
		});
	}
	
	public void setSCHModel(SCHModel o){
		currentSCHModel = o;
		fillListModels();
		EntryList.setSelectedIndex(0);
		IntervalTable.setModel(LightIntervalMap.get((LightEntry)EntryList.getSelectedValue()));
		//IntervalTable.setModel(EntryIntervalMap.get(EntryList.getSelectedValue()));
		testdialog1.setTableModel(LightIntervalMap.get((LightEntry)EntryList.getSelectedValue()));
	}
	
	private void fillListModels(){
		EntryLM.clear();
		List<SCHEntry> el = currentSCHModel.getEntryList();
		for(SCHEntry i: el){
			EntryLM.addElement(i);
			if(i instanceof LightEntry){
				LightIntervalMap.put((LightEntry) i, new LightTM((LightEntry)i));
			} else{
				
			}
		}
	}

}

