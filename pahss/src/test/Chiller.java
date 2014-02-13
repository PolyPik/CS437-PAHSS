package test;

public class Chiller {
	private boolean isOn;
	
	public Chiller()
	{
		isOn = false;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
		System.out.println("Chiller is switching " + ( isOn == true ? "on" : "off"));
	}

}
