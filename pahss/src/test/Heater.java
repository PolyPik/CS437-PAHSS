package test;

public class Heater {
	private boolean isOn;
	
	public Heater()
	{
		isOn = false;
	}
	
	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

}
