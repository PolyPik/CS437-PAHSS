package sch;

public abstract class SCHInterval implements Comparable<SCHInterval>{
	protected int start_hour;
	protected int start_minute;
	protected int stop_hour;
	protected int stop_minute;
	
	public SCHInterval() {
	}
	
	public SCHInterval(int start_hour, int start_minute) {
		super();
		this.start_hour = start_hour;
		this.start_minute = start_minute;
	}

	public void setStarttime(int hour, int minute){
		start_hour = hour;
		start_minute = minute;
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
	
	public int getStart_hour() {
		return start_hour;
	}

	public int getStart_minute() {
		return start_minute;
	}

	@Override
	public int compareTo(SCHInterval o) {
		int start_val1 = (this.start_hour*60)+this.start_minute;
		int start_val2 = (o.start_hour*60)+o.start_minute;
		
		return start_val1-start_val2;
	}

}
