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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import sch.*;
import sch.light.LightEntry;

public class SCHFrame {

	private JFrame frmPahss;
	private SCHModel currentSCHModel;
	private SCHPanel schpanel;
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
		currentSCHModel = new SCHModel();
		initialize();
		LightEntry entry1 = new LightEntry("Light 1", "L923334");
		//SCHEntry entry2 = new FeederEntry("Entry 2");
		//entry1.addInterval(3, 0, 3, 30,10.5);
		currentSCHModel.addEntry(entry1);
		//currentSCHModel.addEntry(entry2);
		schpanel.setSCHModel(currentSCHModel);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPahss = new JFrame();
		frmPahss.setTitle("PAHSS");
		frmPahss.setBounds(100, 100, 709, 480);
		frmPahss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		schpanel = new SCHPanel(frmPahss);
		frmPahss.getContentPane().add(schpanel);
		JPanel notificationArea = new JPanel();
		notificationArea.setLayout(new BorderLayout(0, 0));
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
		frmPahss.pack();
	}

}

