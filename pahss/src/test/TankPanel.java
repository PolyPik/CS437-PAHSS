package test;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class TankPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel controlPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JTabbedPane jtp = new JTabbedPane();
	private TemperatureRegulator tr = new TemperatureRegulator();
	
	public TankPanel()
	{
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		mainPanel.setFocusable(true);
		
		JButton b1 = new JButton("+");
		JButton b2 = new JButton("-");
		controlPanel.add(b1);
		controlPanel.add(b2);
		
		jtp.addTab("Main", mainPanel);
		jtp.addTab("Control", controlPanel);
		jtp.addTab("Temperature Regulator", tr);
	}
	
	public JTabbedPane getMainPanel()
	{
		return jtp;
	}
}
