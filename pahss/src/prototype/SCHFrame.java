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

import sch.*;

public class SCHFrame {

	private JFrame frmPahss;
	private SCHModel currentSCHModel;
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
		SCHPanel schpanel = new SCHPanel(frmPahss);
		frmPahss.add(schpanel);
		LightEntry entry1 = new LightEntry("Entry 1");
		//SCHEntry entry2 = new FeederEntry("Entry 2");
		entry1.addInterval(3, 0, 3, 30);
		entry1.setIntervalLumen(0, 10.5);
		currentSCHModel.addEntry(entry1);
		//currentSCHModel.addEntry(entry2);
		schpanel.setSCHModel(currentSCHModel);
	}

}

