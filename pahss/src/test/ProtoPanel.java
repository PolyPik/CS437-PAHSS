package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.text.DefaultCaret;

public class ProtoPanel extends Thread{
	
	Semaphore s = new Semaphore(0);
	private JFrame frmPahss;
	private boolean isRunning = true;
	private int fps = 20;
	TemperatureRegulator tr = new TemperatureRegulator(s);
	TempPanel TRPanel; 
	long lastLoopTime = 0;
	long refreshTime = 0;
	JTextArea textArea;
	ArrayList<String> notifications = new ArrayList<String>();
	
	public static void main(String[] args) throws InterruptedException {
		ProtoPanel window = new ProtoPanel();
		window.frmPahss.setVisible(true);
		window.run();
		System.exit(0);
	}

	public void run() {
		try {
			while (isRunning){
				long time = System.currentTimeMillis(); 
		        long delta = time - lastLoopTime;
		        lastLoopTime = System.currentTimeMillis();
		        
		        update(delta);
		        
		        //  delay for each frame  -   time it took for one frame 
		        time = (1000 / fps) - (System.currentTimeMillis() - time); 
		        if (time > 0) 
	            { 
	                try 
	                { 
	                        Thread.sleep(time); 
	                } 
	                catch(Exception e){} 
	            } 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public ProtoPanel() throws InterruptedException {
		initialize();
	}
	public void update(long dt)
	{
		refreshTime += dt;
		if(refreshTime > 1000)
		{
			TRPanel.update();

			notifications = tr.getNotifications();

			if(!notifications.isEmpty())
				for(int i = 0; i < notifications.size(); i++)
				{
					textArea.append(notifications.get(i) + "\n");
				}
			refreshTime = 0;
			tr.clearNotifications();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	private void initialize() throws InterruptedException {
		frmPahss = new JFrame();
		frmPahss.setTitle("PAHSS");
		frmPahss.setBounds(100, 100, 900, 600);
		frmPahss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPahss.setResizable(false);
		tr.start();// start the Temperature Regulator module
		s.acquire();
		
		
		JList<String> TankList = new JList<String>();
		TankList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TankList.setBorder(BorderFactory.createTitledBorder("Tanks"));
		TankList.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Tank 1", "Tank 2", "Tank 3"};
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
		
		frmPahss.getContentPane().add(MainPanel, BorderLayout.CENTER);
		
		JPanel SCHPanel = new JPanel();
		MainPanel.addTab("Scheduler", null, SCHPanel, null);
		
		JPanel CRPanel = new JPanel();
		MainPanel.addTab("Chemistry", null, CRPanel, null);
		
		TRPanel = new TempPanel(tr);
		MainPanel.addTab("Temperature", null, TRPanel, null);
		
		JPanel FRPanel = new JPanel();
		MainPanel.addTab("Filtration", null, FRPanel, null);
		
		JPanel EMPanel = new JPanel();
		MainPanel.addTab("Equipment", null, EMPanel, null);
		
		MainPanel.setSelectedIndex(2);
		
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
		
		textArea = new JTextArea(5,55);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(700, 90));
		
		notificationArea.add(areaScrollPane);
		notificationArea.setBorder(BorderFactory.createTitledBorder("Notifications"));
		frmPahss.getContentPane().add(notificationArea, BorderLayout.SOUTH);
		
	}

}