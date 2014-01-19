package core;

import java.io.IOException;
import java.net.ServerSocket;

public abstract class PAHSSAbstractServer {
	ServerSocket s_socket;

	public PAHSSAbstractServer(ServerSocket s_socket) {
		super();
		this.s_socket = s_socket;
	}
	
	public PAHSSAbstractServer(int port) {
		super();
		try {
			s_socket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract void readFromClient();
	
	public abstract void writeToClient();
}
