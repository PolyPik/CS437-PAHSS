package test;

import java.awt.Graphics;

import javax.swing.JFrame;

public class ProtoSimulator extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isRunning = true;
	private int fps = 20;
	int x = 0;
	TemperatureRegulator tr = new TemperatureRegulator();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProtoSimulator ps = new ProtoSimulator();
		ps.run();
		System.exit(0);
	}
    
	public void run()
	{
		initialize();
		while (isRunning){
			long time = System.currentTimeMillis(); 
	        
	        update(); 
	        draw(); 
	        
	        //  delay for each frame  -   time it took for one frame 
	        time = (1000 / fps) - (System.currentTimeMillis() - time); 
	        
	        if (time > 0) 
            { 
                try 
                { 
                        Thread.sleep(time); 
                } 
                catch(Exception e){} 
            } 
		}
		setVisible(false);
	}
	void initialize()
	{
		setTitle("Simulator"); 
        setSize(600, 400); 
        setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setVisible(true); 
        
	}
	void update()
	{
		tr.update();
	}
	void draw()
	{
		 Graphics g = getGraphics(); 
        
         g.drawRect(100, 100, 10, 10);
	}

}
