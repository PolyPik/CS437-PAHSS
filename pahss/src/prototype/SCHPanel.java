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

import sch.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractListModel;


public class SCHPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9216093653080287746L;
	private LightEditDialog editdialog1;
	private JTable intervalTable;
	private SCHModel currentSCHModel;
	JList<SCHEntry> entryList;
	DefaultListModel<SCHEntry> EntryLM;
	HashMap<SCHEntry,DefaultListModel<SCHInterval>> EntryIntervalMap;
	HashMap<LightEntry,LightTM> LightIntervalMap;
	HashMap<FeederEntry,DefaultTableModel> FeederIntervalMap;

	/**
	 * Create the panel.
	 */
	public SCHPanel(JFrame frame) {
		editdialog1 = new LightEditDialog(frame,true);
		editdialog1.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosed(e);
				//System.out.println("Refresh");
				((LightTM)intervalTable.getModel()).resyncEntry();
				((LightTM)intervalTable.getModel()).fireTableDataChanged();
			}
		});
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
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel);
		
		JButton startButton = new JButton("Start Schedule");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				entryList.getSelectedValue().startSchedulers();
			}
		});
		
		JButton stopButton = new JButton("Stop Schedule");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entryList.getSelectedValue().stopSchedulers();
			}
		});
		
		JButton editButton = new JButton("Edit Schedule");
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editdialog1.loadEntry(LightIntervalMap.get((LightEntry)entryList.getSelectedValue()));
				editdialog1.setVisible(true);
			}
		});
		GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
		gl_buttonPanel.setHorizontalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(editButton, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
						.addComponent(stopButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
						.addComponent(startButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_buttonPanel.setVerticalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addGap(159)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stopButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(161, Short.MAX_VALUE))
		);
		buttonPanel.setLayout(gl_buttonPanel);

	}
	
	public void setSCHModel(SCHModel o){
		currentSCHModel = o;
		fillListModels();
		entryList.setSelectedIndex(0);
		intervalTable.setModel(LightIntervalMap.get((LightEntry)entryList.getSelectedValue()));
		//editdialog1.setTableModel(LightIntervalMap.get((LightEntry)entryList.getSelectedValue()));
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
