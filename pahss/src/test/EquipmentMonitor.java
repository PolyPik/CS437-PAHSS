package test;

import java.util.Scanner;
import java.util.ArrayList;

public class EquipmentMonitor {
	ArrayList<EquipmentCheck> equipmentCheckList = new ArrayList<EquipmentCheck>();

	public ArrayList<EquipmentCheck> getCheckList() {
		return equipmentCheckList;
	}

	// replaces monitor.getCheckList.get(i);
	public EquipmentCheck getCheckListValue(int i) {
		return equipmentCheckList.get(i);
	}

	// replaces monitor.getCheckList.add(i);
	public void addToCheckList(EquipmentCheck e) {
		equipmentCheckList.add(e);
	}
}

class EquipmentCheck extends Thread {
	String equipmentName = "Unknown Equipment";//perhaps to store name of equipment from database later?
	String errorNotice = "Equipment is broken! Fix immediately!";//perhaps to store error notice from database later
	int tank_id;
	int equipment_id;
	boolean isOperational = true;
	boolean isChecked = false;

	EquipmentCheck(int tank_id, int equipment_id) {
		this.tank_id = tank_id;
		this.equipment_id = equipment_id;
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
			 */

			if (!getOperational() && !isChecked) {
				System.out.println("ERROR: Tank " + tank_id + "'s " + equipmentName + " MALFUNCTION: "
						+ errorNotice);
				setChecked(true);
			}
			if (getOperational() && isChecked) {
				setChecked(false);
				System.out.println("Error has been fixed on Tank " + tank_id + "'s " + equipmentName);
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
		 * The EquipmentMonitor class has an ArrayList equipmentCheckList. The
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

		EquipmentMonitor monitor = new EquipmentMonitor();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of tanks (preferably 1-3):");
		int numberOfTanks = input.nextInt();

		for (int t = 1; t <= numberOfTanks; t++) {
			for (int e = 1; e <= 19; e++)
				monitor.addToCheckList(new EquipmentCheck(t, e));
		}

		for (int i = 0; i < monitor.getCheckList().size(); i++)
			monitor.getCheckListValue(i).start();

		/*
		 * This is a way to test the true/false case. Need to be able to
		 * consider information of all equipment for every tank. Perhaps the
		 * modules in charge of the equipment can specify which tank is causing
		 * the error when errors occur. They will then send the tank id and
		 * equipment id, and based on those id's, the corresponding tank will
		 * have its boolean set to false.
		 */

		System.out.println("All equipment currently working.");
		System.out.println("Input numbers 0 through "
				+ (numberOfTanks * 19 - 1)
				+ " to break/fix equipment and display error/fix message:");
		while (true) {
			int boolSwitch = input.nextInt();
			if (boolSwitch >= 0 && boolSwitch < monitor.getCheckList().size())
				monitor.getCheckListValue(boolSwitch)
						.setOperational(
								!monitor.getCheckListValue(boolSwitch)
										.getOperational());
		}

		/*
		 * The logic for this is nearly complete. This program needs to handle
		 * the case for when new tanks/equipment are added. For each new tank,
		 * 19 new equipment checks are added.
		 */
	}
}
