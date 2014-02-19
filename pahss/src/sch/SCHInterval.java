package sch;

public class SCHInterval implements Comparable<SCHInterval>{
	private int start_hour;
	private int start_minute;
	private int stop_hour;
	private int stop_minute;
	
	public SCHInterval() {
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
	
	public int getStart_hour() {
		return start_hour;
	}

	public int getStart_minute() {
		return start_minute;
	}

	public int getStop_hour() {
		return stop_hour;
	}

	public int getStop_minute() {
		return stop_minute;
	}

	@Override
	public int compareTo(SCHInterval o) {
		int start_val1 = (this.start_hour*60)+this.start_minute;
		int start_val2 = (o.start_hour*60)+o.start_minute;
		
		return start_val1-start_val2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + start_hour;
		result = 17 * result + start_minute;
		result = 43 * result + stop_hour;
		result = 5 * result + stop_minute;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SCHInterval))
			return false;
		SCHInterval other = (SCHInterval) obj;
		if (start_hour != other.start_hour)
			return false;
		if (start_minute != other.start_minute)
			return false;
		if (stop_hour != other.stop_hour)
			return false;
		if (stop_minute != other.stop_minute)
			return false;
		return true;
	}

}
