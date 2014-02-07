package test;

import java.util.Scanner;

public class EquipmentMonitorThread extends Thread {

	String equipment;
	String error;

	boolean broken = false;

	EquipmentMonitorThread(String equipmentName, String errorNotice) {
		equipment = equipmentName;
		error = errorNotice;
	}

	public void setBroken(boolean b) {
		broken = b;
	}

	public boolean getBroken() {
		return broken;
	}

	public void run() {
		while (true) {
			/*
			 * The following code simulates how the Equipment Monitor would keep
			 * track of the boolean value. If we had a table in the database
			 * with booleans instead, this is what would be added: When you
			 * declare an object in the main method, you would specify which
			 * boolean value in the database that this object keeps track of.
			 * Whenever that boolean becomes false, output an error message.
			 * otherwise, just keep outputting that everything is working fine.
			 * Not sure if that's exactly how it would work. I also don't really
			 * know how to pull information from a database.
			 */
			if (!broken)
				System.out.println(equipment + " is working fine. ");
			else
				System.out.println("ERROR: " + equipment + " malfunction: "
						+ error);

			try {
				sleep(5000);
			} catch (InterruptedException e) {
			}

		}
	}

	public static void main(String[] args) {

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
		System.out.println("Enter either 1 or 0");

		/*
		 * This is a way to test the true/false case. Need to be able to
		 * consider information of all equipment for every tank in the aquarium
		 * Will require a for-loop for each aquarium tank. Might also need a
		 * single loop for all equipment, or alternatively, list out all
		 * equipment individually instead of having a single loop for all
		 * equipment.
		 */
		while (true) {
			System.out.println("Please input a 1 or a 0: ");
			int oneOrZero = input.nextInt();
			if (oneOrZero == 1)
				feederMonitor.setBroken(false);
			else if (oneOrZero == 0)
				feederMonitor.setBroken(true);
			else if (oneOrZero == 3)
				feeder2Monitor.setBroken(false);
			else if (oneOrZero == 2)
				feeder2Monitor.setBroken(true);
			else
				System.out.println("Invalid input.");

		}

	}

}
