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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class EquipmentMonitor {
	EquipmentCheck[][] equipmentCheckList;
	
	public EquipmentMonitor(int numTanks, int numChecks) {
		equipmentCheckList = new EquipmentCheck[numTanks][numChecks];
	}
	
	//returns entire array
	public EquipmentCheck[][] getChecks() {
		return equipmentCheckList;
	}

	//returns the value in the array of index [t][e]
	public EquipmentCheck getChecksValue(int t, int e) {
		return equipmentCheckList[t][e];
	}

	//set the value of the array index [t][e] to c
	public void addToChecks(EquipmentCheck c, int t, int e) {
		equipmentCheckList[t][e] = c;
	}
}

class MonitorGUI {
	static ArrayList<JLabel> textLabelList = new ArrayList<JLabel>();
	static ArrayList<JTextArea> textAreaList = new ArrayList<JTextArea>();

	public static void createWindow(int numTanks) {
		JFrame myFrame = new JFrame("Equipment Monitor");

		JLabel label;
		myFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;

		label = new JLabel("Equipment Monitor");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		label.setFont(new Font("Serif", Font.BOLD, 30));
		myFrame.add(label, c);

		for (int i = 1; i <= numTanks; i++) {
			JLabel labelT = new JLabel("Tank " + i);
			c.weightx = 0.5;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = i - 1;
			c.gridy = 1;
			labelT.setFont(new Font("Serif", Font.BOLD, 20));
			textLabelList.add(labelT);
			myFrame.add(labelT, c);
		}

		c.insets = new Insets(0, 5, 0, 5);

		for (int i = 1; i <= numTanks; i++) {
			JTextArea textArea = new JTextArea("Tank " + i
					+ " data. All equipment working.\n", 10, 10);
			JScrollPane scrollPane = new JScrollPane(textArea);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridwidth = 1;
			c.gridx = i - 1;
			c.gridy = 2;
			textArea.setEditable(false);
			textArea.setRows(20);
			textArea.setColumns(20);
			textAreaList.add(textArea);
			myFrame.getContentPane().add(scrollPane, c);
		}

		JLabel labelEnd = new JLabel("End of window");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 2; // 2 columns wide
		c.gridy = 3; // third row
		myFrame.add(labelEnd, c);

		myFrame.setSize(800, 500);
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
		/*
		 * instead of all these if statements, equipmentName will take the
		 * equipment id, look for it on the table equipment_checks in the
		 * database, and assigns itself to whatever name it finds there. This is
		 * an example of the query it will use:
		 * 
		 * select equipment_name from equipment_checks where equipment_id = 1;
		 */
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
		/*
		 * The same process that equipmentName went through will also occur for
		 * errorNotice, except it will use the value found in the error_message
		 * field. This is an example of the query it will use:
		 * 
		 * select error_message from equipment_checks where equipment_id = 1;
		 */
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
			 * The following code simulates how the Equipment Monitor would keep
			 * track of the boolean value. If we had a table in the database
			 * with booleans, this is what would be added: When you declare an
			 * object in the main method, you would specify which equipment from
			 * which tank is being read. This is done by passing in the specific
			 * tank_id and equipment_id. Then, this method will use those ID's
			 * to figure out which boolean value in the database to constantly
			 * read. Whenever that value is set to false, an error message will
			 * be sent to MI. Otherwise, print out that everything is fine. In
			 * order to keep the internal value of the boolean update, we might
			 * have to continuously keep reading from the database. That means
			 * we'll either have to create a variable right before the checks
			 * below that will keep reading based on id's, or just use the code
			 * that would access the database inside the booleans of the checks
			 * below. More work on this is needed.
			 * 
			 * Basically, instead of using getOperational(), which is a boolean
			 * that is toggled by user input, we will instead use the tank_id
			 * and equipment_id of this object to find the corresponding boolean
			 * value in the operational_status column of the equipment_status
			 * table in the database. Alternatively, while the program is
			 * running, we could set getOperational = boolean found in
			 * operational_status of the equipment_status table. The
			 * error/resolution messages also need to be sent to the MI, and
			 * when the MI is done resolving the issue, they must change the
			 * boolean value to true and let EM know that the value has been
			 * changed. This would be the query used to retrieve the value:
			 * 
			 * select operational_status from equipment_status where tank_id = 1
			 * and equipment_id = 1;
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

			//*
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
		 * The EquipmentMonitor class has an ArrayList EquipmentCheckList. The
		 * main method of EquipmenentCheck will create an object of type
		 * EquipmentMonitor, and proceed to add elements to it of type
		 * EquipmentCheck. When we implement the database, these objects will
		 * also take in the tank_id and the equipment_id of the equipment they
		 * represent. These values will correspond to a boolean value that will
		 * have a value of either true or false. This value will be changed by
		 * the other modules whenever equipment malfunctions, by referencing the
		 * table based on the specific tank_id and equipment_id of the equipment
		 * and tank that malfunctioned. During the object's run method, whenever
		 * a value is false, an error message will be printed and sent to the
		 * MI.
		 */

		/*
		 * Here, we are allowing the user to determine the number of tanks.
		 * Instead, we can automatically detect how many tanks there are using
		 * this query:
		 * 
		 * select count(id) from tank_database;
		 */

		int numEquipmentChecks = 19;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of default tanks:");
		int numberOfTanks = input.nextInt();
		EquipmentMonitor mon = new EquipmentMonitor(numberOfTanks, numEquipmentChecks);
		
		for (int t = 1; t <= numberOfTanks; t++)
			for (int e = 1; e <= numEquipmentChecks; e++)
				mon.addToChecks(new EquipmentCheck(t,e), t-1, e-1);
		
		for (int t = 1; t <= numberOfTanks; t++)
			for (int e = 1; e <= numEquipmentChecks; e++)
				mon.getChecksValue(t-1,e-1).start();

		MonitorGUI.createWindow(numberOfTanks);
		

		/*
		 * This is a way to test the true/false case. Need to be able to
		 * consider information of all equipment for every tank. Perhaps the
		 * modules in charge of the equipment can specify which tank is causing
		 * the error when errors occur. They will then send the tank id and
		 * equipment id, and based on those id's, the corresponding tank will
		 * have its boolean set to false. Here are examples of queries that
		 * would be used to modify the boolean values in the database:
		 * 
		 * update equipment_status set operational_status = 0 where tank_id = 1
		 * and equipment_id = 1;
		 * 
		 * update equipment_status set operational_status = 1 where tank_id = 1
		 * and equipment_id = 1;
		 */

		System.out.println("All equipment currently working.");
		System.out.println("You'll be inputting numbers to break/fix equipment and display error/fix messages.");
		while(true){
			System.out.println("Input a valid tank number from 1 to " + numberOfTanks + ": ");
			int tankNo = input.nextInt();
			if (tankNo >= 1 && tankNo <= numberOfTanks){
				System.out.println("Input a valid equipment check number from 1 to " + numEquipmentChecks + ":");
				int checkNo = input.nextInt();
				if (checkNo >= 1 && checkNo <= numEquipmentChecks)
				{
					mon.getChecksValue(tankNo-1, checkNo-1).setOperational(!mon.getChecksValue(tankNo-1,checkNo-1).getOperational());
				}
				else{
					System.out.println("This equipment check does not exist. Please start again.");
				}
			}
			else{
				System.out.println("This tank does not exist. Please start again.");
			}
		}		

		/*
		 * The logic for this is nearly complete. This program needs to handle
		 * the case for when new tanks/equipment are added. For each new tank,
		 * 19 new equipment checks are added to the database, which would mean
		 * 19 new values must be added to the array list, and they must all be
		 * filled. Not sure how to detect that, but here's what I think:
		 * 
		 * Have this program constantly check the state of the equipment_status
		 * table. If, at any time, new tanks are added, then for each new tank,
		 * 19 entries are added. Then this program will use the new entries'
		 * tank_id and equipment_id to create a new EquipmentCheck object and
		 * add it to the array list.
		 * 
		 * ALTERNATIVELY, if this program is going to be turned off whenever
		 * tanks are added, then it would be better to recalculate the number of
		 * tanks. In that case, this program needs to be changed such that
		 * instead of asking the user how many tanks there are, it will instead
		 * check the tank_database and count how many tanks there are, and then
		 * just build the array list all over again.
		 */
	}
}
