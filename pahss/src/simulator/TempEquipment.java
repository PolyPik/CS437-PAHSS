package simulator;

import java.util.Timer;

public class TempEquipment extends Equipment implements Runnable {

	boolean status; // true if on , false otherwise
	double btu; // btu per hour.
	double tempChange; // temp change per second

	
	


	public TempEquipment(long c, long d, int dI, String n,boolean b,double btu,int tankSize){
		super(c,d,dI,n,b);
	this.btu = btu;
	this.status = false;

	tempChange = (8.33*tankSize) * (btu / 3600);
	}
	
	public void run(int waterTemp) {
		
		while(status){
			Timer second = new Timer();
		
			timer.schedule(new TimerTask() {

	          
	            public void run() {
	                test.doStuff();
	            }
	        }, 0, test.myLong);
			
		}
		

	}

}
