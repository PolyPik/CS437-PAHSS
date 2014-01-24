package test;

public class Fish {
	private double o2Level; private double nitriteLevel; private double nitrateLevel; 
	private double salinity; private double phLevel; private double ammoniaLevel; 
	private double waterHardness; private double temperature; private String name;
	

	public Fish(String n, double o2, double niLevel, double naLevel, double sal, double ph, double ammonia,
			double water, double temp)
	{
		name = n; o2Level = o2; nitriteLevel = niLevel; nitrateLevel = naLevel; salinity = sal;
		phLevel = ph; ammoniaLevel = ammonia; waterHardness = water; temperature = temp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getO2Level() {
		return o2Level;
	}
	public void setO2Level(double o2_level) {
		this.o2Level = o2_level;
	}
	public double getNitriteLevel() {
		return nitriteLevel;
	}
	public void setNitriteLevel(double nitriteLevel) {
		this.nitriteLevel = nitriteLevel;
	}
	public double getNitrateLevel() {
		return nitrateLevel;
	}
	public void setNitrateLevel(double nitrateLevel) {
		this.nitrateLevel = nitrateLevel;
	}
	public double getSalinity() {
		return salinity;
	}
	public void setSalinity(double salinity) {
		this.salinity = salinity;
	}
	public double getPhLevel() {
		return phLevel;
	}
	public void setPhLevel(double phLevel) {
		this.phLevel = phLevel;
	}
	public double getAmmoniaLevel() {
		return ammoniaLevel;
	}
	public void setAmmoniaLevel(double ammoniaLevel) {
		this.ammoniaLevel = ammoniaLevel;
	}
	public double getWaterHardness() {
		return waterHardness;
	}
	public void setWaterHardness(double waterHardness) {
		this.waterHardness = waterHardness;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	@Override public String toString(){
		return (name+ " " + o2Level + " " + nitriteLevel + " " + nitrateLevel + " "
				+ salinity + " " + phLevel + " " + ammoniaLevel + " " + waterHardness + " "
				+ temperature); 
	}
}
