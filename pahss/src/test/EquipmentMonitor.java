package test;

import java.util.Scanner;
import java.util.ArrayList;

public class EquipmentMonitor {
	ArrayList<EquipmentCheck> equipmentCheckList = new ArrayList<EquipmentCheck>();

	public ArrayList<EquipmentCheck> getCheckList() {
		return equipmentCheckList;
	}

	public void addToCheckList(EquipmentCheck e) {
		equipmentCheckList.add(e);
	}
}

class EquipmentCheck extends Thread {
	String equipmentName;
	String errorNotice;
	int tank_id;
	int equipment_id;
	boolean isOperational = true;
	boolean isChecked = false;

	EquipmentCheck(String equipmentName, String errorNotice) {
		this.equipmentName = equipmentName;
		this.errorNotice = errorNotice;
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
			 * be sent to MI. Otherwise, print out that everything is fine. More
			 * work on this is needed.
			 */

			if (!getOperational() && !isChecked) {
				System.out.println("ERROR: " + equipmentName + " malfunction: "
						+ errorNotice);
				setChecked(true);
			}
			if (getOperational() && isChecked) {
				setChecked(false);
				System.out.println("Error has been fixed on " + equipmentName);
			}

			/*
			 * try { sleep(5000); } catch (InterruptedException e) {
			 * System.out.println("sleep(5000) did not work"); }
			 */
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

		for (int t = 1; t <= 3; t++) {
			for (int e = 1; e <= 19; e++)
				monitor.getCheckList().add(
						new EquipmentCheck("Equipment of Tank " + t
								+ " Equipment ID " + e,
								"Equipment is broken! Fix immediately"));
		}

		for (int i = 0; i < monitor.getCheckList().size(); i++)
			monitor.getCheckList().get(i).start();

		/*
		 * This is a way to test the true/false case. Need to be able to
		 * consider information of all equipment for every tank. Perhaps the
		 * modules in charge of the equipment can specify which tank is causing
		 * the error when errors occur. They will then send the tank id and
		 * equipment id, and based on those id's, the corresponding tank will
		 * have its boolean set to false.
		 */
		Scanner input = new Scanner(System.in);

		System.out.println("Input numbers 0-?");
		while (true) {
			int boolSwitch = input.nextInt();
			if (boolSwitch >= 0 && boolSwitch < monitor.getCheckList().size())
				monitor.getCheckList()
						.get(boolSwitch)
						.setOperational(
								!monitor.getCheckList().get(boolSwitch)
										.getOperational());
		}

		/*
		 * The logic for this is nearly complete. This program needs to handle
		 * the case for when new tanks/equipment are added. For each new tank,
		 * 19 new equipment checks are added.
		 */
	}
}
