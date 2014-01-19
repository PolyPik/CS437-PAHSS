package pahss_core;

public abstract class AbstractRunnable implements Runnable {

	public abstract void run();
	
	protected abstract void readFromClient();
	
	protected abstract void writeToClient();

}
