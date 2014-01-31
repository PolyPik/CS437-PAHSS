package test;

/*
 * This is a test class representing an idea of how the Equipment Monitor will collect data from equipment.
 * Depending on how we decide to implement aquarium equipment, we might have to make classes like these
 * into subclasses, with a class called Equipment as a superclass.
 */
public class Feeder {

	private boolean isOperational = true;
	private boolean hasFood = true;
	private String lowSupplyErrorNotice = "Feeder malfunction! Out of food! Refill immediately!";
	private String operationalErrorNotice = "Feeder malfunction! Equipment is broken! Fix immediately!";

	// check whether this equipment is working
	public boolean getOperationalStatus() {
		return this.isOperational;
	}

	public void setOperationalStatus(boolean isOperational) {
		this.isOperational = isOperational;
	}

	// check whether feeder has food
	boolean getFoodSupplyStatus() {
		return this.hasFood;
	}

	public void setFoodSupplyStatus(boolean hasFood) {
		this.hasFood = hasFood;
	}

	// Error message that will be sent to MI
	public String getLowSupplyErrorNotice() {
		return this.lowSupplyErrorNotice;
	}

	// Error message that will be sent to MI
	public String getOperationalErrorNotice() {
		return this.operationalErrorNotice;
	}
}
