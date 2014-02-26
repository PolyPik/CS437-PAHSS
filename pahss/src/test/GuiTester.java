package test;

import java.awt.*;
import javax.swing.*;

/*
 * This won't be part of the final project. It's just a class I'm using to experiment with JFrame for the GUI for this project.
 * */

public class GuiTester {

	public static void createWindow() {
		JFrame myFrame = new JFrame("Equipment Monitor");
		
		JLabel label;
		myFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;
		
		label = new JLabel("Equipment Monitor");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		label.setFont(new Font("Serif", Font.BOLD, 30));
		myFrame.add(label, c);
		
		label = new JLabel("Tank 1");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		label.setFont(new Font("Serif", Font.BOLD, 20));
		myFrame.add(label, c);

		label = new JLabel("Tank 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		label.setFont(new Font("Serif", Font.BOLD, 20));
		myFrame.add(label, c);

		label = new JLabel("Tank 3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		label.setFont(new Font("Serif", Font.BOLD, 20));
		myFrame.add(label, c);

		JTextArea textArea = new JTextArea("Tank 1 data", 10, 10);
		JScrollPane scrollPane = new JScrollPane(textArea);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,5);
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		textArea.setEditable(true);
		textArea.setRows(20);
		textArea.setColumns(20);
		myFrame.getContentPane().add(scrollPane,c);
		
		textArea = new JTextArea("Tank 2 data", 10, 10);
		scrollPane = new JScrollPane(textArea);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		textArea.setEditable(true);
		textArea.setRows(20);
		textArea.setColumns(20);
		myFrame.getContentPane().add(scrollPane,c);
		
		textArea = new JTextArea("Tank 3 data", 10, 10);
		scrollPane = new JScrollPane(textArea);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 2;
		textArea.setEditable(true);
		textArea.setRows(20);
		textArea.setColumns(20);
		myFrame.getContentPane().add(scrollPane,c);

		label = new JLabel("End of window");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 3;       //third row
		myFrame.add(label, c);
		
		myFrame.setSize(800, 500);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setLocationRelativeTo(null);
		myFrame.pack();
		myFrame.setVisible(true);

	}

	public static void main(String[] args) {
		createWindow();
	}

}
