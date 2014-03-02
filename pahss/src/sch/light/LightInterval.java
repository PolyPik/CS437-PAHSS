package sch.light;

import sch.SCHInterval;



public class LightInterval extends SCHInterval{
	private double lum;

	public LightInterval() {
		super();
	}

	public LightInterval(int start_hour, int start_minute, double lum) {
		super(start_hour, start_minute);
		this.lum = lum;
	}
	
	public double getBrightness() {
		return lum;
	}

	public void setBrightness(double lum) {
		this.lum = lum;
	}
	
	protected LightInterval clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		LightInterval clone = new LightInterval(start_hour, start_minute, lum);
		return clone;
	}
}
