package test;

import java.util.Scanner;

public class EquipmentMonitorThread extends Thread {

	String equipment;
	String error;

	boolean isOperational = true;

	EquipmentMonitorThread(String equipmentName, String errorNotice) {
		equipment = equipmentName;
		error = errorNotice;
	}

	public void setOperational(boolean o) {
		isOperational = o;
	}

	public boolean getOperational() {
		return isOperational;
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
			if (getOperational())
				System.out.println(equipment + " is working fine. ");
			else
				System.out.println("ERROR: " + equipment + " malfunction: "
						+ error);

			try {
				sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("sleep(5000) did not work");
			}
		}
	}

	public static void main(String[] args) {

		/*
		 * Each new EquipmentMonitorThread represents a piece of equipment, and
		 * each one will correspond to a different entry in the database to be
		 * created. When we implement the database, these objects will also take
		 * in the tank_id and the equipment_id of the equipment they represent.
		 * These values will correspond to a boolean value that will have a
		 * value of either true or false. This value will be changed by the
		 * other modules whenever equipment malfunctions, by referencing the
		 * table based on the specific tank_id and equipment_id of the equipment
		 * and tank that malfunctioned. During the object's run method, whenever
		 * a value is false, an error message will be printed and sent to the
		 * MI.
		 */
		EquipmentMonitorThread feederMonitor = new EquipmentMonitorThread(
				"Feeder 1", "Out of food! Refill immediately!");
		EquipmentMonitorThread feeder2Monitor = new EquipmentMonitorThread(
				"Feeder 2", "Equipment is broken! Fix immediately!");

		feederMonitor.start();
		feeder2Monitor.start();

		/*
		 * if we take the database approach, this might be where the code would
		 * end, as the rest of the code is just designed to switch booleans.
		 */

		Scanner input = new Scanner(System.in);

		/*
		 * This is a way to test the true/false case. Need to be able to
		 * consider information of all equipment for every tank. Perhaps the
		 * modules in charge of the equipment can specify which tank is causing
		 * the error when errors occur. They will then send the tank id and
		 * equipment id, and based on those id's, the corresponding tank will
		 * have its boolean set to false.
		 */
		while (true) {
			System.out.println("Enter an integer 0-3: ");
			int zeroThroughFour = input.nextInt();
			if (zeroThroughFour == 1)
				feederMonitor.setOperational(false);
			if (zeroThroughFour == 0)
				feederMonitor.setOperational(true);
			if (zeroThroughFour == 3)
				feeder2Monitor.setOperational(false);
			if (zeroThroughFour == 2)
				feeder2Monitor.setOperational(true);
		}
	}
}
