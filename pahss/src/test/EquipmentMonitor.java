package test;

import java.util.Scanner;

/*
 * The Equipment Monitor is responsible for keeping track of the operational statuses of all aquarium equipment 
 * for each managed aquarium. If any malfunction has occurred, the Equipment Monitor will immediately send a 
 * notification to the Management Interface to display with all the available information regarding the nature 
 * of the malfunction and recommended course of action. 
 */

/*
 * This is a test class to try to get the basic idea on how the Equipment Monitor works.
 * So far, this class gets data from some equipment (with its own class) and then prints out that data.
 * Instead of printing out, we want to send that data to the MI.
 */
public class EquipmentMonitor {

	// collects information and then prints it out.
	// should all equipment have its own method? or should there only be one
	// method that
	// can handle any equipment?
	public void sendInfo(String error) {
		System.out.println("Send to MI: " + error);
	}

	public static void main(String[] args) {
		Feeder feeder = new Feeder();
		EquipmentMonitor monitor = new EquipmentMonitor();

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
			if (oneOrZero == 1) {
				feeder.setFoodSupplyStatus(true);
			} else if (oneOrZero == 0) {
				feeder.setFoodSupplyStatus(false);
			} else
				System.out.println("Invalid input.");
			if (oneOrZero == 0 || oneOrZero == 1) {
				if (feeder.getFoodSupplyStatus())
					System.out.println("Feeder is working.");
				else {
					System.out.println("Faulty Equipment Detected;");
					monitor.sendInfo(feeder.getLowSupplyErrorNotice());
				}
			}
		}

	}
}
