package pahss_core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoRunnable extends AbstractRunnable {
	private Socket c_socket;
	private DataOutputStream out;
	private DataInputStream in;
	private String message;
	
	public EchoRunnable(Socket c_socket) {
		super();
		this.c_socket = c_socket;
	}

	@Override
	public void run() {
		try{
				out = new DataOutputStream(new BufferedOutputStream(c_socket.getOutputStream()));
				in = new DataInputStream(new BufferedInputStream(c_socket.getInputStream()));
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
		message = in.readUTF();
	}

	@Override
	protected void writeToClient() throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF("Echo: "+message);
		out.flush();
	}

}
