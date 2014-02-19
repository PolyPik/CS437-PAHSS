package filtrationModule;

public class WaterPump {

	double pumpRate;
	boolean active;
	//0-100  100 being used up, 0 is new filter
	double condition;
	Tank input;
	Tank output;
	
	
	public WaterPump(Tank in, Tank out,double defaultPumpRate,double condition){
		this.input=in;
		this.output=out;
		this.pumpRate=defaultPumpRate;
		this.condition=condition;
		this.active=false;
		
	}
	
	public boolean startPump(){
		if(this.active)
			return false;
		else
		{
			this.active=true;
	
			return true;
			
		}
		
	}
	public boolean stopPump(){
		
		if(this.active){
			this.active=false;
		
			return true;
			
		}
		else{
			return false;
		}
		
		
	}
	
	
}
