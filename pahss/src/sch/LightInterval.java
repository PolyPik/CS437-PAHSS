package sch;

public class LightInterval extends SCHInterval{
	private double lum;

	public LightInterval() {
		super();
	}

	public LightInterval(int start_hour, int start_minute, int stop_hour,
			int stop_minute, double lum) {
		super(start_hour, start_minute, stop_hour, stop_minute);
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
		LightInterval clone = new LightInterval(start_hour, start_minute, stop_hour, stop_minute, lum);
		return clone;
	}
}
