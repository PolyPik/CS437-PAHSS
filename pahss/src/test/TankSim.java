package test;
import java.awt.Dimension;

import javax.swing.JFrame;

public class TankSim extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(600,400));
        frame.setLocation(200,200);
        frame.setResizable(false);

        TankPanel p = new TankPanel();
        
        frame.getContentPane().add(p.getMainPanel());
        frame.pack();
        frame.setVisible(true);
	}

}
