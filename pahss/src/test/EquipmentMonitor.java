package test;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class EquipmentMonitor {
	EquipmentCheck[][] equipmentCheckList;

	public EquipmentMonitor(int numTanks, int numChecks) {
		equipmentCheckList = new EquipmentCheck[numTanks][numChecks];
	}

	// returns entire array
	public EquipmentCheck[][] getChecks() {
		return equipmentCheckList;
	}

	// returns the value in the array of index [t][e]
	public EquipmentCheck getChecksValue(int t, int e) {
		return equipmentCheckList[t][e];
	}

	// set the value of the array index [t][e] to c
	public void addToChecks(EquipmentCheck c, int t, int e) {
		equipmentCheckList[t][e] = c;
	}
}

class MonitorGUI {
	static ArrayList<JLabel> textLabelList = new ArrayList<JLabel>();
	static ArrayList<JTextArea> textAreaList = new ArrayList<JTextArea>();

	public static void createWindow(int numTanks) {
		JFrame myFrame = new JFrame("Equipment Monitor");
		JPanel myPanel = new JPanel();

		JLabel title;
		myPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;

		title = new JLabel("Equipment Monitor");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		title.setFont(new Font("Serif", Font.BOLD, 30));
		myPanel.add(title, c);

		for (int i = 1; i <= numTanks; i++) {
			JLabel labelT = new JLabel("Tank " + i);
			c.weightx = 0.5;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = i - 1;
			c.gridy = 1;
			labelT.setFont(new Font("Serif", Font.BOLD, 20));
			textLabelList.add(labelT);
			myPanel.add(labelT, c);
		}

		c.insets = new Insets(0, 5, 0, 5);

		for (int i = 1; i <= numTanks; i++) {
			JTextArea textArea = new JTextArea("Tank " + i
					+ " data. All equipment working.\n\n", 10, 10);
			JScrollPane scrollPane = new JScrollPane(textArea);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridwidth = 1;
			c.gridx = i - 1;
			c.gridy = 2;
			textArea.setEditable(false);
			textArea.setRows(35);
			textArea.setColumns(20);
			textAreaList.add(textArea);
			myPanel.add(scrollPane, c);
		}

		JLabel anchor = new JLabel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 2; // 2 columns wide
		c.gridy = 3; // third row
		myPanel.add(anchor, c);

		JScrollPane masterScroll = new JScrollPane(myPanel);
		myFrame.add(masterScroll);
		myFrame.setSize(700, 500);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setLocationRelativeTo(null);
		// myFrame.pack();
		myFrame.setVisible(true);
	}
}

class EquipmentCheck extends Thread {
	String equipmentName = "Unknown Equipment";
	String errorNotice = "Equipment is broken! Fix immediately!";
	int tank_id;
	int equipment_id;
	boolean isOperational = true;
	boolean isChecked = false;

	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT,
			Locale.US);
	Date today = new Date();
	String dateOut = dateFormatter.format(today);
	DateFormat timeFormater = DateFormat.getTimeInstance(DateFormat.DEFAULT,
			Locale.US);
	String timeOut = timeFormater.format(today);

	EquipmentCheck(int tank_id, int equipment_id) {
		this.tank_id = tank_id;
		this.equipment_id = equipment_id;

		// Set the name of the equipment based on equipment_id.
		if (equipment_id == 1)
			equipmentName = "Heater";
		else if (equipment_id == 2)
			equipmentName = "Chiller";
		else if (equipment_id == 3)
			equipmentName = "Chemical dispenser";
		else if (equipment_id == 4)
			equipmentName = "Feeder";
		else if (equipment_id == 5)
			equipmentName = "Lighting";
		else if (equipment_id == 6)
			equipmentName = "Air pump";
		else if (equipment_id == 7)
			equipmentName = "Filtration";
		else if (equipment_id == 8)
			equipmentName = "Water pump";
		else if (equipment_id == 9)
			equipmentName = "Temperature sensor";
		else if (equipment_id == 10)
			equipmentName = "Salinity sensor";
		else if (equipment_id == 11)
			equipmentName = "ph sensor";
		else if (equipment_id == 12)
			equipmentName = "Ammonia sensor";
		else if (equipment_id == 13)
			equipmentName = "Nitrite sensor";
		else if (equipment_id == 14)
			equipmentName = "Nitrate sensor";
		else if (equipment_id == 15)
			equipmentName = "Oxygen sensor";
		else if (equipment_id == 16)
			equipmentName = "Water hardness sensor";
		else if (equipment_id == 17)
			equipmentName = "Reservoir water level sensor";
		else if (equipment_id == 18)
			equipmentName = "Feeder food supply";
		else if (equipment_id == 19)
			equipmentName = "Chemical supply dispenser";

		// The error notice is simply the name of the equipment along with a
		// string stating that the equipment is broken.
		errorNotice = equipmentName + " is broken! Please fix immediately!";
	}

	public void setOperational(boolean operational) {
		this.isOperational = operational;
	}

	public boolean getOperational() {
		return isOperational;
	}

	public void setChecked(boolean checked) {
		this.isChecked = checked;
	}

	public boolean getChecked() {
		return isChecked;
	}

	public void run() {
		while (true) {
			dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT,
					Locale.US);
			timeFormater = DateFormat.getTimeInstance(DateFormat.DEFAULT,
					Locale.US);
			today = new Date();
			dateOut = dateFormatter.format(today);
			timeOut = timeFormater.format(today);

			/*
			 * getOperational() returns the boolean of isOperational. If this
			 * boolean is false and it had not been previously checked before,
			 * then indicate that it is being checked and print out an error
			 * message. This message will display on the GUI, and it will say
			 * which tank has a problem and which piece of equipment failed.
			 * When the operational status is true and it was in the process of
			 * being checked, then indicate that this variable is not being
			 * checked anymore, and output an error resolution message to the
			 * GUI.
			 */

			if (!getOperational() && !isChecked) {
				MonitorGUI.textAreaList.get(tank_id - 1).append(
						dateOut + " " + timeOut + "\nERROR: Tank " + tank_id
								+ "'s " + errorNotice + "\n\n");
				System.out.println(dateOut + " " + timeOut + "\nERROR: Tank "
						+ tank_id + "'s " + errorNotice);
				setChecked(true);
			}
			if (getOperational() && isChecked) {
				setChecked(false);
				MonitorGUI.textAreaList.get(tank_id - 1).append(
						dateOut + " " + timeOut
								+ "\nRESOLVED: Error has been fixed on Tank "
								+ tank_id + "'s " + equipmentName + "\n\n");
				System.out.println(dateOut + " " + timeOut
						+ "\nRESOLVED: Error has been fixed on Tank " + tank_id
						+ "'s " + equipmentName);
			}

			// *
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("sleep did not work");
			}
			// */
		}
	}

	public static void main(String[] args) {

		/*
		 * The EquipmentMonitor class has an two-dimensional array
		 * equipmentCheckList. The main method of EquipmenentCheck will create
		 * an object of type EquipmentMonitor, and proceed to add elements to it
		 * of type EquipmentCheck. These objects will take in the tank_id and
		 * the equipment_id of the equipment they represent as parameters. These
		 * values will correspond to a boolean value that will have a value of
		 * either true or false. During the object's run method, whenever a
		 * value is false, an error message will be printed and sent to the GUI.
		 * When the value is true, a resolved message is sent to the GUI.
		 */

		/*
		 * Here, we are allowing the user to determine the number of tanks. The
		 * number of equipment checks is set to 19 because according to the
		 * requirements, that is how many pieces of equipment there are to
		 * check.
		 */
		int numEquipmentChecks = 19;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of default tanks:");
		int numberOfTanks = input.nextInt();
		EquipmentMonitor mon = new EquipmentMonitor(numberOfTanks,
				numEquipmentChecks);

		for (int t = 1; t <= numberOfTanks; t++)
			for (int e = 1; e <= numEquipmentChecks; e++)
				mon.addToChecks(new EquipmentCheck(t, e), t - 1, e - 1);

		for (int t = 1; t <= numberOfTanks; t++)
			for (int e = 1; e <= numEquipmentChecks; e++)
				mon.getChecksValue(t - 1, e - 1).start();

		// This displays the GUI. The amount of tanks shown is based on the
		// number that the user will input.
		MonitorGUI.createWindow(numberOfTanks);

		/*
		 * This is a way to test the true/false case. Enter the tank id number
		 * of the tank you want to check, then enter the equipment id number of
		 * the equipment you want to toggle. If the boolean for that equipment
		 * is true, it sets it to false, and if it's false, then it is set to
		 * true.
		 */

		System.out.println("All equipment currently working.");
		System.out
				.println("Input numbers to break/fix equipment and display error/fix messages.");
		while (true) {
			System.out.println("Input a valid tank number from 1 to "
					+ numberOfTanks + ": ");
			int tankNo = input.nextInt();
			if (tankNo >= 1 && tankNo <= numberOfTanks) {
				System.out
						.println("Input a valid equipment check number from 1 to "
								+ numEquipmentChecks + ":");
				int checkNo = input.nextInt();
				if (checkNo >= 1 && checkNo <= numEquipmentChecks) {
					mon.getChecksValue(tankNo - 1, checkNo - 1).setOperational(
							!mon.getChecksValue(tankNo - 1, checkNo - 1)
									.getOperational());
				} else {
					System.out
							.println("This equipment check does not exist. Please start again.");
				}
			} else {
				System.out
						.println("This tank does not exist. Please start again.");
			}
		}
	}
}
