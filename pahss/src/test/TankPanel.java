package test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.ArrayList;

public class TankPanel extends JPanel{
	protected final int SCREEN_HEIGHT = 400;
	protected final int SCREEN_WIDTH = 600;
	private final int SLEEP_TIME = 19;
	private Thread thread;
	private ArrayList<Fish> tankObjects;
	private TankJPanel tankPanel = new TankJPanel();
	private JPanel controlPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	
	public TankPanel()
	{
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		mainPanel.setFocusable(true);
		
		JButton b1 = new JButton("+");
		JButton b2 = new JButton("-");
		controlPanel.add(b1);
		controlPanel.add(b2);
		
		mainPanel.add(tankPanel, BorderLayout.NORTH);
		mainPanel.add(controlPanel, BorderLayout.EAST);
		
		tankObjects = new ArrayList<Fish>();
		Fish f = new Fish();
		tankObjects.add(f);
	}
	
	public JPanel getMainPanel()
	{
		return mainPanel;
	}
	private class TankJPanel extends JPanel implements Runnable{
		
		public TankJPanel()
		{
			
			setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
			setBackground(Color.GREEN);
			setDoubleBuffered(true);
		}
		public void paint(Graphics g)
		{
			super.paint(g);
			
			Graphics2D g2D = (Graphics2D)g;
			
			for(int i = 0; i < tankObjects.size(); i++)
			{
				Fish f = (Fish)tankObjects.get(i);
				if (f.getAlive())
					f.Draw(g2D);
			}
			
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
		
		public void Update(double dt)
		{
			for(int i = 0; i < tankObjects.size(); i++)
			{
				Fish f = (Fish)tankObjects.get(i);
				if (f.getAlive())
					f.Update(dt);
			}
			repaint();
		}
		
		public void addNotify()
	    {
	        super.addNotify();
	        thread = new Thread(this);
	        thread.start();
	    }
		public void run()
		{
			double dt = 1.f/60.f;
			while (true){
				Update(dt);
				repaint();
				
				try{
					Thread.sleep(SLEEP_TIME);
				}
				catch(InterruptedException e){
					System.out.println("Interrupted");
					System.out.println(e);
				}
			}
		}
	}
	
}
