package filtrationModule;

public class WaterPump {

	double pumpRate;
	boolean active;
	//0-100  100 being used up, 0 is new filter
	double condition;
	
	Tank input;
	Tank output;
	
	
	
	
	public boolean startPump(Tank in, Tank out){
		if(this.active)
			return false;
		else
		{
			this.active=true;
			this.input= in;
			this.output=out;
			return true;
			
		}
		
	}
	public boolean stopPump(Tank in, Tank out){
		
		if(this.active){
			this.active=false;
			this.input=null;
			this.output=null;
			return true;
			
		}
		else{
			return false;
		}
		
		
	}
	
	
}
