package test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

/*
 * This won't be part of the final project. It's just a class I'm using to experiment with JFrame for the GUI for this project.
 * Additionally, this, when finalized (it's not yet), may become integrated with EquipmentMonitor
 * */

public class GuiTester2 {

	public static void createWindow() {
		ArrayList<JLabel> textLabelList = new ArrayList<JLabel>();
		ArrayList<JTextArea> textAreaList = new ArrayList<JTextArea>();
		
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
		
		JLabel labelT1 = new JLabel("Tank 1");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		labelT1.setFont(new Font("Serif", Font.BOLD, 20));
		myFrame.add(labelT1, c);

		JLabel labelT2 = new JLabel("Tank 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		labelT2.setFont(new Font("Serif", Font.BOLD, 20));
		myFrame.add(labelT2, c);

		JLabel labelT3 = new JLabel("Tank 3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		labelT3.setFont(new Font("Serif", Font.BOLD, 20));
		myFrame.add(labelT3, c);

		JTextArea textArea1 = new JTextArea("Tank 1 data\n", 10, 10);
		JScrollPane scrollPane1 = new JScrollPane(textArea1);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,5);
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		textArea1.setEditable(true);
		textArea1.setRows(20);
		textArea1.setColumns(20);
		myFrame.getContentPane().add(scrollPane1,c);
		//textArea1.append("new text"); //this will be the basis for updating the text
		
		JTextArea textArea2 = new JTextArea("Tank 2 data\n", 10, 10);
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		textArea2.setEditable(true);
		textArea2.setRows(20);
		textArea2.setColumns(20);
		myFrame.getContentPane().add(scrollPane2,c);
		
		JTextArea textArea3 = new JTextArea("Tank 3 data\n", 10, 10);
		JScrollPane scrollPane3 = new JScrollPane(textArea3);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 2;
		textArea3.setEditable(true);
		textArea3.setRows(20);
		textArea3.setColumns(20);
		myFrame.getContentPane().add(scrollPane3,c);

		JLabel labelEnd = new JLabel("End of window");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 3;       //third row
		myFrame.add(labelEnd, c);
		
		myFrame.setSize(800, 500);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setLocationRelativeTo(null);
		myFrame.pack();
		myFrame.setVisible(true);

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Input how many tanks are in aquarium: ");
		int numTanks = input.nextInt();
		//this will determine how many headings and how many text boxes there are
		//remember to implement an array list
		
		createWindow();
	}

}
