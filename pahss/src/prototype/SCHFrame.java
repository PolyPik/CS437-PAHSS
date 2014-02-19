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

public class SCHFrame {

	private JFrame frmPahss;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SCHFrame window = new SCHFrame();
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
	public SCHFrame() {
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
		
		
		JPanel SCHPanel = new JPanel();
		frmPahss.getContentPane().add(SCHPanel);
		
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
		
		JButton startScheduleButton = new JButton("Start Schedule");
		JButton stopScheduleButton = new JButton("Stop Schedule");
		JButton editScheduleButton = new JButton("Edit Schedule");
		
		SCHButtonPanel.add(startScheduleButton);
		SCHButtonPanel.add(stopScheduleButton);
		SCHButtonPanel.add(editScheduleButton);
		
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
	}

}

