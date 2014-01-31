package test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.geom.*;

public class TankPanel extends JPanel implements Runnable{
	protected final int SCREEN_HEIGHT = 400;
	protected final int SCREEN_WIDTH = 600;
	private final int SLEEP_TIME = 19;
	private Thread thread;
	private ArrayList tankObjects;
	
	public TankPanel()
	{
		setFocusable(true);
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.GREEN);
		setDoubleBuffered(true);
		
		tankObjects = new ArrayList();
		Fish f = new Fish();
		tankObjects.add(f);
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
