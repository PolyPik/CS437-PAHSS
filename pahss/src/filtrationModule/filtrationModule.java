package filtrationModule;

import java.util.List;

public class filtrationModule {

	List<Tank> tanks;
	Tank reservoir;
	CRInput inputs;
	
	public void checkSalinity(int tank){
		
		double salinity = inputs.currentSalinityLevel;
		Tank t = this.tanks.get(tank);
		// if above tolerance
		if(salinity > inputs.salinityLevel[3])
		{
			
						
		}
		/* if below tolerance
		else if(salinity < inputs.salinityLevel[2])
		{
			
		}
		*/
	}
	
	
public void checkWaterHardness(){
		
		double waterHardness = inputs.currentWaterHardnessLevel;
		
		// if above tolerance
		if(waterHardness > inputs.waterHardnessLevel[3])
		{
		
			
			
		}
		// if below tolerance
		else if(waterHardness < inputs.waterHardnessLevel[2])
		{
			
		}
		
	}

public void checkNitrateLevel(){
	
	double nitrateLevel = inputs.currentNitrateLevel;
	
	// if above tolerance
	if(nitrateLevel > inputs.nitrateLevel[3])
	{
		
		
		
	}
	// if below tolerance
	else if(nitrateLevel < inputs.nitrateLevel[2])
	{
		
	}
	
}

	public filtrationModule (Tank res){
	
		this.reservoir=res;
	}
	
	public void addTank(Tank t){
		this.tanks.add(t);
	}
	
	public boolean startWaterPump(int tank,int pump,double rate){
		
		Tank startTank = tanks.get(tank);
		WaterPump startPump = startTank.pumps.get(pump);
		
		return startPump.startPump();
		
	
		
	}
	public boolean stopWaterPump(int tank,int pump,double rate){
		
		Tank startTank = tanks.get(tank);
		WaterPump stopPump = startTank.pumps.get(pump);
		
		return stopPump.stopPump();
		
	
	}
	
	
	
	
	
	
}
