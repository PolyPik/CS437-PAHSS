package pahss_core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public abstract class PAHSSAbstractServer {
	private ServerSocket s_socket;
	private ArrayList<Thread> threadlist = new ArrayList<Thread>();
	private ArrayList<AbstractRunnable> runnablelist = new ArrayList<AbstractRunnable>();

	public PAHSSAbstractServer(ServerSocket s_socket) {
		super();
		this.s_socket = s_socket;
	}
	
	public PAHSSAbstractServer(int port) throws IOException {
		super();
		s_socket = new ServerSocket(port);
	}
	
	protected void acceptClient() throws IOException{
		runnablelist.add(new EchoRunnable(s_socket.accept()));
		System.out.println("Server accepted client");
		Thread newThread = new Thread(runnablelist.get(runnablelist.size()-1));
		threadlist.add(newThread);
		threadlist.get(threadlist.size()-1).start();
	}
	
	public void closeServer(){
		try {
			for(int i = 0; i < threadlist.size(); i++){
				runnablelist.get(i).terminate();
				threadlist.get(i).join();
			}
			s_socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
