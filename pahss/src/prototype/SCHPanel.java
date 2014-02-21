package prototype;

import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sch.*;

public class SCHPanel extends JPanel{
	private SCHModel currentSCHModel;
	JList<SCHEntry> EntryList;
	JList<SCHInterval> IntervalList;
	DefaultListModel<SCHEntry> EntryLM;
	HashMap<SCHEntry,DefaultListModel<SCHInterval>> EntryIntervalMap; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 629536423038624531L;
	
	public SCHPanel() {
		// TODO Auto-generated constructor stub
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		EntryLM = new DefaultListModel<SCHEntry>();
		EntryIntervalMap = new HashMap<SCHEntry,DefaultListModel<SCHInterval>>();
		
		EntryList = new JList<SCHEntry>(EntryLM);
		EntryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane entryScrollPane = new JScrollPane(EntryList);
		
		this.add(entryScrollPane);
		
		IntervalList = new JList<SCHInterval>();
		IntervalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane intervalScrollPane = new JScrollPane(IntervalList);
		
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
		
		EntryList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				//int index = e.getFirstIndex();
				IntervalList.setModel(EntryIntervalMap.get(EntryList.getSelectedValue()));
			}
		});
	}
	
	public void setSCHModel(SCHModel o){
		currentSCHModel = o;
		fillListModels();
		EntryList.setSelectedIndex(0);
		IntervalList.setModel(EntryIntervalMap.get(EntryList.getSelectedValue()));
		
	}
	
	private void fillListModels(){
		EntryLM.clear();
		List<SCHEntry> EntryList = currentSCHModel.getEntryList();
		for(SCHEntry i: EntryList){
			EntryLM.addElement(i);
			List<SCHInterval> IntervalList = i.getIntervalList();
			DefaultListModel<SCHInterval> IntervalLM = new DefaultListModel<SCHInterval>();
			for(SCHInterval j: IntervalList){
				IntervalLM.addElement(j);
			}
			EntryIntervalMap.put(i, IntervalLM);
		}
	}

}

