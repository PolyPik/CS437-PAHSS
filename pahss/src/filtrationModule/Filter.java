package filtrationModule;

public class Filter {

	String type;
	//0-100  100 being used up, 0 is new filter
	double condition;
	
	public Filter(String t)
	{
		this.type=t;
		this.condition=100;
		
	}
	
	
}
