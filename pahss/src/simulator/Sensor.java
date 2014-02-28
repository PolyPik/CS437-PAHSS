package simulator;

public class Sensor extends Equipment {

	long sensorValue;
	public Sensor(long c, long d, int dI, String n, boolean b) {
		super(c, d, dI, n, b);
		// TODO Auto-generated constructor stub
	}
	public void setSensorValue(long value){
		
		this.sensorValue=value;
	}

}
