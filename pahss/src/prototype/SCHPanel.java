package prototype;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import sch.*;
import sch.feeder.FeederEntry;
import sch.light.LightEditDialog;
import sch.light.LightEntry;
import sch.light.LightTM;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractListModel;

import java.awt.Component;

import javax.swing.SwingConstants;

import java.awt.ComponentOrientation;

import javax.swing.Box;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


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
		EntryScrollPane.setPreferredSize(new Dimension(200, 450));
		EntryScrollPane.setMaximumSize(new Dimension(600, 32767));
		add(EntryScrollPane);
		
		EntryLM = new DefaultListModel<SCHEntry>();
		entryList = new JList<SCHEntry>(EntryLM);
		EntryIntervalMap = new HashMap<SCHEntry,DefaultListModel<SCHInterval>>();
		LightIntervalMap = new HashMap<LightEntry,LightTM>();
		EntryScrollPane.setViewportView(entryList);
		entryList.setCellRenderer(new EntryRenderer());
		
		JScrollPane TableScrollPane = new JScrollPane();
		TableScrollPane.setPreferredSize(new Dimension(200, 50));
		add(TableScrollPane);
		
		intervalTable = new JTable();
		TableScrollPane.setViewportView(intervalTable);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(180, 32767));
		buttonPanel.setPreferredSize(new Dimension(180, 10));
		add(buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] {120};
		gbl_buttonPanel.rowHeights = new int[] {60, 0, 120, 23};
		gbl_buttonPanel.columnWeights = new double[]{0.0};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		JButton editButton = new JButton("Edit Schedule");
		editButton.setPreferredSize(new Dimension(120, 40));
		editButton.setMinimumSize(new Dimension(120, 40));
		editButton.setMaximumSize(new Dimension(120, 40));
		editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		editButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		editButton.setHorizontalTextPosition(SwingConstants.CENTER);
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editdialog1.loadEntry(LightIntervalMap.get((LightEntry)entryList.getSelectedValue()));
				editdialog1.setVisible(true);
			}
		});
		
		JButton startButton = new JButton("Start Schedule");
		startButton.setPreferredSize(new Dimension(120, 40));
		startButton.setMinimumSize(new Dimension(120, 40));
		startButton.setMaximumSize(new Dimension(120, 40));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_startButton = new GridBagConstraints();
		gbc_startButton.anchor = GridBagConstraints.SOUTH;
		gbc_startButton.insets = new Insets(0, 0, 5, 0);
		gbc_startButton.gridx = 0;
		gbc_startButton.gridy = 0;
		buttonPanel.add(startButton, gbc_startButton);
		startButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		startButton.setHorizontalTextPosition(SwingConstants.CENTER);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				entryList.getSelectedValue().startSchedulers();
			}
		});
		
		JButton stopButton = new JButton("Stop Schedule");
		stopButton.setPreferredSize(new Dimension(120, 40));
		stopButton.setMaximumSize(new Dimension(120, 40));
		stopButton.setMinimumSize(new Dimension(120, 40));
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		stopButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entryList.getSelectedValue().stopSchedulers();
			}
		});
		GridBagConstraints gbc_stopButton = new GridBagConstraints();
		gbc_stopButton.insets = new Insets(0, 0, 5, 0);
		gbc_stopButton.gridx = 0;
		gbc_stopButton.gridy = 1;
		buttonPanel.add(stopButton, gbc_stopButton);
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.gridx = 0;
		gbc_editButton.gridy = 2;
		buttonPanel.add(editButton, gbc_editButton);

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
