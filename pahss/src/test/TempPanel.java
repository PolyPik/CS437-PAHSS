 package test;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TempPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DecimalFormat df = new DecimalFormat("#.##");
	private JLabel chillerStatus = new JLabel();
	private JLabel heaterStatus = new JLabel();
	private JLabel desiredTempLabel = new JLabel("Desired Temperature: ");
	private JLabel desiredTempValue = new JLabel();
	private JLabel optimalTempLabel = new JLabel("Optimal Temperature: ");
	private JLabel optimalTempValue = new JLabel();
	private JLabel tempLabel = new JLabel("Current Temperature: ");
	private JLabel tempValue = new JLabel();
	private JButton tempUpButton = new JButton("Up");
	private JButton tempDownButton = new JButton("Down");
	private JButton tempSetButton = new JButton("Set Temperature");
	private JRadioButton heaterOn = new JRadioButton("Heater On");
	private JRadioButton heaterOff = new JRadioButton("Heater Off");
	private JRadioButton chillerOn = new JRadioButton("Chiller On");
	private JRadioButton chillerOff = new JRadioButton("Chiller Off");
	private ButtonGroup heaterGroup = new ButtonGroup();
	private ButtonGroup chillerGroup = new ButtonGroup();
	private JCheckBox manualOverride = new JCheckBox();
	private JPanel tempPanel = new JPanel();
	private JPanel controlPanel = new JPanel();
	private JPanel manualOverridePanel = new JPanel();
	private JPanel statusPanel = new JPanel();
	private TemperatureRegulator tr;
	private double desiredTemp = 75;
	
	public TempPanel(TemperatureRegulator tempReg)
	{
		tr = tempReg;
		setLayout(new GridLayout(4, 2, 0, 0));
		setPreferredSize(new Dimension(100, 100));
		setFocusable(true);
		
		
		String s = df.format(tr.getCurTemp());
		tempValue.setText(s);
		s = df.format(tr.getOpTemp());
		optimalTempValue.setText(s);
		
		desiredTempValue.setText(String.valueOf(desiredTemp));
		
		heaterOn.setEnabled(false);
		heaterOff.setEnabled(false);
		chillerOn.setEnabled(false);
		chillerOff.setEnabled(false);
		tempUpButton.setEnabled(false);
		tempDownButton.setEnabled(false);
		tempSetButton.setEnabled(false);
		
		manualOverride.setSelected(false);
		
		
		OptionListener ol = new OptionListener();
		tempUpButton.addActionListener(ol);
		tempDownButton.addActionListener(ol);
		tempSetButton.addActionListener(ol);
		heaterOn.addActionListener(ol);
		heaterOff.addActionListener(ol);
		chillerOn.addActionListener(ol);
		chillerOff.addActionListener(ol);
		manualOverride.addActionListener(ol);
		
		
		heaterOff.setSelected(true);
		chillerOff.setSelected(true);
		heaterGroup.add(heaterOn);
		heaterGroup.add(heaterOff);
		chillerGroup.add(chillerOn);
		chillerGroup.add(chillerOff);
		
		JPanel tpA = new JPanel();
		tpA.add(desiredTempLabel);
		tpA.add(desiredTempValue);
		JPanel tpB = new JPanel();
		tpB.add(tempUpButton);
		tpB.add(tempDownButton);
		tpB.add(tempSetButton);
		
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
		tempPanel.setBorder(BorderFactory.createTitledBorder("Temperature Controls"));
		tempPanel.add(tpA);
		tempPanel.add(tpB);
		
		
		controlPanel.setBorder(BorderFactory.createTitledBorder("Equipment Controls"));
		controlPanel.add(heaterOn);
		controlPanel.add(heaterOff);
		controlPanel.add(chillerOn);
		controlPanel.add(chillerOff);
		
		manualOverridePanel.setBorder(BorderFactory.createTitledBorder("Manual Mode"));
		manualOverridePanel.add(new JLabel("Enable Manual Override"));
		manualOverridePanel.add(manualOverride);
		
		JPanel statusPanelA = new JPanel();
		statusPanelA.add(optimalTempLabel); statusPanelA.add(optimalTempValue);
		statusPanelA.add(new JLabel(" - "));
		statusPanelA.add(tempLabel); statusPanelA.add(tempValue);
		
		JPanel statusPanelB = new JPanel();
		statusPanelB.add(heaterStatus);
		statusPanelB.add(new JLabel(" - "));
		statusPanelB.add(chillerStatus);
		
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
		statusPanel.setBorder(BorderFactory.createTitledBorder("TR Module Status"));
		statusPanel.add(statusPanelA);
		statusPanel.add(statusPanelB);
		
		add(statusPanel);
		add(tempPanel);
		add(controlPanel);
		add(manualOverridePanel);
	}
	void update()
	{
		String s = df.format(tr.getCurTemp());
		tempValue.setText(s);

		chillerStatus.setText("Chiller is " + (tr.getChiller().isOn() ? "On" : "Off"));
		heaterStatus.setText("Heater is " + (tr.getHeater().isOn() ? "On" : "Off"));
	}
	void PopUp(String message)
	{
		JOptionPane.showMessageDialog(this, message, "Temperature Out of Bounds", JOptionPane.ERROR_MESSAGE);
	}
	 private class OptionListener implements ActionListener
	    {
	        public void actionPerformed(ActionEvent event)
	        {
	            Object source = event.getSource();
	            if (source.equals(tempUpButton))
	            {
	            	if(tr.getMaxtemp() == desiredTemp)
	            	{
	            		PopUp("Temperature value exceeds current tank's\nmaximum temperature allowance.");
	            	}
	            	else
	            	{
		            	desiredTemp += 1;
		            	desiredTempValue.setText(String.valueOf(desiredTemp));
	            	}
	            }
	            if(source.equals(tempDownButton))
	            {
	            	if(tr.getMinTemp() == desiredTemp)
	            	{
	            		PopUp("Temperature value exceeds current tank's\nminimum temperature allowance.");
	            	}
	            	else
	            	{
		            	desiredTemp -= 1;
		            	desiredTempValue.setText(String.valueOf(desiredTemp));
	            	}
	            }
	            if(source.equals(tempSetButton))
	            {
	            	heaterOn.setSelected(false);
	            	chillerOn.setSelected(false);
	            	tr.setScheduled(desiredTemp);
	            }
	            if(source.equals(heaterOn))
	            {
	            	if(!tr.getHeater().isOn())
	            	{
	            		tr.setHeater(true);
	            		tr.turnOffScheduled();
	            	}
	            }
	            if(source.equals(heaterOff))
	            {
	            	if(tr.getHeater().isOn())
	            		tr.setHeater(false);
	            }
	            if(source.equals(chillerOn))
	            {
	            	if(!tr.getChiller().isOn())
	            	{
	            		tr.setChiller(true);
	            		tr.turnOffScheduled();
	            	}
	            }
	            if(source.equals(chillerOff))
	            {
	            	if(tr.getChiller().isOn())
	            		tr.setChiller(false);
	            }
	            if(source.equals(manualOverride))
	            {
	            	if (manualOverride.isSelected())
	            	{
	            		if(tr.isAutomated())
	            			tr.setAutomated(false);
	            		
	            		heaterOn.setEnabled(true);
	            		heaterOff.setEnabled(true);
	            		chillerOn.setEnabled(true);
	            		chillerOff.setEnabled(true);
	            		tempUpButton.setEnabled(true);
	            		tempDownButton.setEnabled(true);
	            		tempSetButton.setEnabled(true);
	            	}
	            	else
	            	{
	            		if(!tr.isAutomated())
	            			tr.setAutomated(true);
	            		
	            		heaterOff.setSelected(true);
	            		chillerOff.setSelected(true);
	            		heaterOn.setEnabled(false);
	            		heaterOff.setEnabled(false);
	            		chillerOn.setEnabled(false);
	            		chillerOff.setEnabled(false);
	            		tempUpButton.setEnabled(false);
	            		tempDownButton.setEnabled(false);
	            		tempSetButton.setEnabled(false);
	            	}
	            }
	        }
	    }
}
