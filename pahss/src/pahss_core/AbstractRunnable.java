package pahss_core;

import java.io.IOException;

public abstract class AbstractRunnable implements Runnable {
	protected boolean isRunning = true;

	public abstract void run();
	
	public void terminate(){
		isRunning = false;
	}
	
	protected abstract void readFromClient() throws IOException;
	
	protected abstract void writeToClient() throws IOException;
	
	

}
