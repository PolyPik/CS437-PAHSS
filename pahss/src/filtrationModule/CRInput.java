package filtrationModule;

public class CRInput {

	// arrays
	// 0- optimal
	// 1- rate of change
	// 2- tolerance low 
	// 3- tolerance high
	double nitrateLevel[];
	double salinityLevel[];
	double waterHardnessLevel[];
	
	double currentNitrateLevel;
	double currentSalinityLevel;
	double currentWaterHardnessLevel;
	
	
	public CRInput(){
		
		this.nitrateLevel = new double[4];
		this.salinityLevel = new double[4];
		this.waterHardnessLevel=new double[4];
				
	}
	
	public void setCurrentNitrateLevel(double nitrate){
		this.currentNitrateLevel=nitrate;
		
	
	}

	public void setCurrentSalinityLevel(double salinity){
		this.currentSalinityLevel=salinity;
	}
	
	public void setCurrentWaterHardnessLevel(double waterHardness){
		this.currentWaterHardnessLevel=waterHardness;
	}
	
	
	
	
}
