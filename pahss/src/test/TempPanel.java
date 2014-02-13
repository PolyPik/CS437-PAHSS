package test;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TempPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel optimalTempLabel = new JLabel("Optimal Temperature: ");
	private JLabel optimalTempValue = new JLabel("83");
	private JLabel tempLabel = new JLabel("Current Temperature: ");
	private JLabel tempValue = new JLabel("80");
	private JButton tempUpButton = new JButton("Up");
	private JButton tempDownButton = new JButton("Down");
	private JRadioButton heaterOn = new JRadioButton("Heater On");
	private JRadioButton heaterOff = new JRadioButton("Heater Off");
	private JRadioButton chillerOn = new JRadioButton("Chiller On");
	private JRadioButton chillerOff = new JRadioButton("Chiller Off");
	private ButtonGroup heaterGroup = new ButtonGroup();
	private ButtonGroup chillerGroup = new ButtonGroup();
	private JPanel tempPanel = new JPanel();
	private JPanel controlPanel = new JPanel();
	
	public TempPanel()
	{
		setLayout(new GridLayout(4, 2, 0, 0));
		setPreferredSize(new Dimension(100, 100));
		setFocusable(true);
		
		heaterOff.setSelected(true);
		chillerOff.setSelected(true);
		heaterGroup.add(heaterOn);
		heaterGroup.add(heaterOff);
		chillerGroup.add(chillerOn);
		chillerGroup.add(chillerOff);
		
		tempPanel.setBorder(BorderFactory.createTitledBorder("Temperature Controls"));
		tempPanel.add(optimalTempLabel);
		tempPanel.add(optimalTempValue);
		tempPanel.add(tempLabel);
		tempPanel.add(tempValue);
		tempPanel.add(tempUpButton);
		tempPanel.add(tempDownButton);
		
		controlPanel.setBorder(BorderFactory.createTitledBorder("Equipment Controls"));
		controlPanel.add(heaterOn);
		controlPanel.add(heaterOff);
		controlPanel.add(chillerOn);
		controlPanel.add(chillerOff);
		
		add(tempPanel);
		add(controlPanel);
	}
}
