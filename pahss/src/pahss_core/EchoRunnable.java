package pahss_core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoRunnable extends AbstractRunnable {
	private Socket c_socket;
	private PrintWriter out;
	private BufferedReader in;
	private String message;
	
	public EchoRunnable(Socket c_socket) {
		super();
		this.c_socket = c_socket;
	}

	@Override
	public void run() {
		try{
				out = new PrintWriter(c_socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(c_socket.getInputStream()));
				while(isRunning){
					readFromClient();
					writeToClient();
					System.out.println("Echoed back: "+message);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void readFromClient() throws IOException {
		// TODO Auto-generated method stub
		message = in.readLine();
	}

	@Override
	protected void writeToClient() throws IOException {
		// TODO Auto-generated method stub
		out.println("Echo: "+ message);
	}

}
