package prototype;

import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class ProtoPanel {

	private JFrame frmPahss;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProtoPanel window = new ProtoPanel();
					window.frmPahss.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProtoPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPahss = new JFrame();
		frmPahss.setTitle("PAHSS");
		frmPahss.setBounds(100, 100, 720, 480);
		frmPahss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPahss.setResizable(false);
		
		JList<String> TankList = new JList<String>();
		TankList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TankList.setBorder(BorderFactory.createTitledBorder("Tanks"));
		TankList.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Tank 1", "Tank 2", "Tank 3", "Tank 4", "Tank 5", 
					"Tank 6", "Tank 7", "Tank 8", "Tank 9", "Tank 10","Tank 11", "Tank 12", "Tank 13", 
					"Tank 14", "Tank 15", "Tank 16", "Tank 17", "Tank 18", "Tank 19", "Tank 20"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		JScrollPane tankScrollPane = new JScrollPane(TankList);
		tankScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tankScrollPane.setPreferredSize(new Dimension(100, 0));
		frmPahss.getContentPane().add(tankScrollPane, BorderLayout.WEST);
		
		JTabbedPane MainPanel = new JTabbedPane(JTabbedPane.TOP);
		//MainPanel.setSelectedIndex(1);
		frmPahss.getContentPane().add(MainPanel, BorderLayout.CENTER);
		
		JPanel SCHPanel = new JPanel();
		MainPanel.addTab("Scheduler", null, SCHPanel, null);
		
		SCHPanel.setLayout(new BoxLayout(SCHPanel, BoxLayout.X_AXIS));
		
		JList<String> EntryList = new JList<String>();
		EntryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//EntryList.setBorder(BorderFactory.createTitledBorder("Tanks"));
		EntryList.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"SCH 1", "SCH 2", "SCH 3", "SCH 4", "SCH 5", 
					"SCH 6", "SCH 7", "SCH 8", "SCH 9", "SCH 10","SCH 11", "SCH 12", "SCH 13"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollPane entryScrollPane = new JScrollPane(EntryList);
		
		SCHPanel.add(entryScrollPane);
		
		JList<String> IntervalList = new JList<String>();
		IntervalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//EntryList.setBorder(BorderFactory.createTitledBorder("Tanks"));
		IntervalList.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Interval 1", "Interval 2", "Interval 3", "Interval 4", "Interval 5"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollPane intervalScrollPane = new JScrollPane(IntervalList);
		
		SCHPanel.add(intervalScrollPane);
		
		JPanel SCHButtonPanel = new JPanel();
		SCHButtonPanel.setLayout(new BoxLayout(SCHButtonPanel, BoxLayout.Y_AXIS));
		
		JButton startScheduleButton = new JButton("Start");
		JButton stopScheduleButton = new JButton("Stop");
		JButton addIntervalButton = new JButton("Add Interval");
		JButton removeIntervalButton = new JButton("Remove Interval");
		JButton editIntervalButton = new JButton("Edit Interval");
		JButton applyButton = new JButton("Apply Schedule");
		
		SCHButtonPanel.add(startScheduleButton);
		SCHButtonPanel.add(stopScheduleButton);
		SCHButtonPanel.add(addIntervalButton);
		SCHButtonPanel.add(removeIntervalButton);
		SCHButtonPanel.add(editIntervalButton);
		SCHButtonPanel.add(applyButton);
		
		SCHPanel.add(SCHButtonPanel);
		/*JPanel CRPanel = new JPanel();
		MainPanel.addTab("Chemistry", null, CRPanel, null);
		
		JPanel TRPanel = new JPanel();
		MainPanel.addTab("Temperature", null, TRPanel, null);
		
		JPanel FRPanel = new JPanel();
		MainPanel.addTab("Filtration", null, FRPanel, null);
		
		JPanel EMPanel = new JPanel();
		MainPanel.addTab("Equipment", null, EMPanel, null);
		*/
		JMenuBar MenuBar = new JMenuBar();
		frmPahss.setJMenuBar(MenuBar);
		
		JMenu FileMenu = new JMenu("File");
		MenuBar.add(FileMenu);
		
		JMenuItem mntmSaveSettings = new JMenuItem("Save Settings");
		FileMenu.add(mntmSaveSettings);
		
		JMenuItem mntmLoadSettings = new JMenuItem("Load Settings");
		FileMenu.add(mntmLoadSettings);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		FileMenu.add(mntmExit);
		
		JMenu TankMenu = new JMenu("Tank");
		MenuBar.add(TankMenu);
		
		JMenuItem NewTankMI = new JMenuItem("Add New Tank");
		TankMenu.add(NewTankMI);
		
		JMenuItem RemoveTankMI = new JMenuItem("Remove Tank");
		TankMenu.add(RemoveTankMI);
		
		JPanel notificationArea = new JPanel();
		//notificationArea.setPreferredSize(new Dimension(720, 100));
		
		JTextArea textArea = new JTextArea(5,55);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(700, 90));
		
		notificationArea.add(areaScrollPane);
		notificationArea.setBorder(BorderFactory.createTitledBorder("Notifications"));
		frmPahss.getContentPane().add(notificationArea, BorderLayout.SOUTH);
	}

}
