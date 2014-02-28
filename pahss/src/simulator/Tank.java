package simulator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tank implements Runnable {

	double size;
	double waterTemp; // equilibrium level

	double luminosity; // equilibrium level
	double nitrateLevel;
	double salnity;

	TempEquipment heater;
	TempEquipment chiller;

	public Tank(double size, double temp, TempEquipment heater,TempEquipment chiller) {
		this.size = size;
		this.waterTemp = temp;
		this.heater = heater;
		this.chiller=chiller;
		

	}

	public void update(double temp) {
		this.waterTemp = temp;

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		TempEquipment heater1 = new TempEquipment((long)100,(long).001,1,"heater",false,5000,500);
		TempEquipment chiller1 = new TempEquipment((long)100,(long).001,1,"chiller",false,-5000,500);

		Tank Sim1 = new Tank(500, 60.00,heater1,chiller1);

		JFrame status = new JFrame("Tank Simulator");
		status.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Sim1.run();
		JLabel temp = new JLabel(String.valueOf(Sim1.waterTemp));
		JLabel size = new JLabel(String.valueOf(Sim1.size));

		JPanel current = new JPanel();
		
		current.add(temp);
		current.add(size);
		
		status.getContentPane().add(current);
		status.pack();
		status.setVisible(true);
		
		status.repaint();
		
		
		
		

		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			
			
			
			
			this.waterTemp--;
			
			if(waterTemp== 5)
				break;
			
		}

	}

	

}
