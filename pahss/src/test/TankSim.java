package test;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TankSim extends JFrame{

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocation(400,400);
        frame.setResizable(false);

        TankPanel p = new TankPanel();
        
        frame.getContentPane().add(p.getMainPanel());
        frame.pack();
        frame.setVisible(true);
	}

}
