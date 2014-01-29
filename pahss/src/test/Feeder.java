package test;

/*
 * This is a test class representing an idea of how the Equipment Monitor will collect data from equipment.
 * Depending on how we decide to implement aquarium equipment, we might have to make classes like these
 * into subclasses, with a class called Equipment as a superclass.
 */
public class Feeder {

	private boolean isOperational = true;
	private boolean isFull = true;
	private String errorMessageIfEmpty = "Feeder malfunction! Out of food! Refill immediately!";
	private String errorMessageIfBroken = "Feeder malfunction! Equipment is broken! Fix immediately!";

	// real-time readings?
	public void realTimeReadings() {
		System.out.println("Food level at 100%. Machine working.");
	}

	// check whether this equipment is working
	public boolean getOperationalStatus() {
		return this.isOperational;
	}

	public void setOperationalStatus(boolean isOperational) {
		this.isOperational = isOperational;
	}

	// check whether feeder has food
	boolean getIsFull() {
		return this.isFull;
	}

	public void setIsFull(boolean isFull) {
		this.isFull = isFull;
	}

	// Error message that will be sent to MI
	public String getErrorMessageIfEmpty() {
		return this.errorMessageIfEmpty;
	}

	// Error message that will be sent to MI
	public String getErrorMessageIfBroken() {
		return this.errorMessageIfBroken;
	}
}
