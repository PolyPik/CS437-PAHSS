package prototype;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import sch.FeederEntry;
import sch.LightDialog;
import sch.LightEntry;
import sch.LightTM;
import sch.SCHEntry;
import sch.SCHInterval;
import sch.SCHModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractListModel;


public class SCHPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9216093653080287746L;
	private JTable intervalTable;
	private LightDialog testdialog1;
	private SCHModel currentSCHModel;
	JList<SCHEntry> entryList;
	JTable IntervalTable2;
	DefaultListModel<SCHEntry> EntryLM;
	HashMap<SCHEntry,DefaultListModel<SCHInterval>> EntryIntervalMap;
	HashMap<LightEntry,LightTM> LightIntervalMap;
	HashMap<FeederEntry,DefaultTableModel> FeederIntervalMap;

	/**
	 * Create the panel.
	 */
	public SCHPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JScrollPane EntryScrollPane = new JScrollPane();
		add(EntryScrollPane);
		
		EntryLM = new DefaultListModel<SCHEntry>();
		entryList = new JList<SCHEntry>(EntryLM);
		EntryIntervalMap = new HashMap<SCHEntry,DefaultListModel<SCHInterval>>();
		LightIntervalMap = new HashMap<LightEntry,LightTM>();
		EntryScrollPane.setViewportView(entryList);
		
		JScrollPane TableScrollPane = new JScrollPane();
		add(TableScrollPane);
		
		intervalTable = new JTable();
		TableScrollPane.setViewportView(intervalTable);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton startButton = new JButton("Start Schedule");
		
		JButton stopButton = new JButton("Stop Schedule");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton addButton = new JButton("Add Interval");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton removeButton = new JButton("Remove Interval");
		
		JButton editButton = new JButton("Edit Interval");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(startButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
						.addComponent(stopButton, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
						.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(editButton, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stopButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(178, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

	}
	
	public void setSCHModel(SCHModel o){
		currentSCHModel = o;
		fillListModels();
		entryList.setSelectedIndex(0);
		//System.out.println(LightIntervalMap.get((LightEntry)entryList.getSelectedValue()).toString());
		intervalTable.setModel(LightIntervalMap.get((LightEntry)entryList.getSelectedValue()));
		//IntervalTable.setModel(EntryIntervalMap.get(EntryList.getSelectedValue()));
		//testdialog1.setTableModel(LightIntervalMap.get((LightEntry)EntryList.getSelectedValue()));
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
