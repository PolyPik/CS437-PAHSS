package sch;

public class SCHInterval {
	private int start_hour;
	private int start_minute;
	private int stop_hour;
	private int stop_minute;
	
	public SCHInterval() {
		// TODO Auto-generated constructor stub
	}
	
	public void setStarttime(int hour, int minute){
		start_hour = hour;
		start_minute = minute;
	}
	
	public void setStoptime(int hour, int minute){
		stop_hour = hour;
		stop_minute = minute;
	}
	
	public boolean checkOverlap(SCHInterval o){
		float start_val1 = (float) (this.start_hour+(this.start_minute/60.0));
		float stop_val1 = (float) (this.stop_hour+(this.stop_minute/60.0));
		float start_val2 = (float) (o.start_hour+(o.start_minute/60.0));
		float stop_val2 = (float) (o.stop_hour+(o.stop_minute/60.0));
		
		if(stop_val1 < start_val1){
			if(stop_val2 < start_val2){
				return true;
			}else if((start_val2<=stop_val1)||(stop_val2>=start_val1)){
				return true;
			} else{
				return false;
			}
		} else{
			if((start_val2<=stop_val1)||(stop_val2>=start_val1)){
				return true;
			} else{
				return false;
			}
		}
	}
}
