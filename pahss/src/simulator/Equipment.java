package simulator;

public class Equipment extends Thread {

	long condition;// a value between 0 and 100 which will degrade
	long degrade; // value to be subtracted from condition every interval
	int degradeInterval; // interval degrade subtracts in seconds for sake of demo
	String name;
	boolean broken;
	
	public Equipment(long c, long d, int dI, String n, boolean b){
		
		this.condition=c;
		this.degrade=d;
		this.degradeInterval=dI;
		this.name=n;
		this.broken=b;
	}
	
	
	
}
