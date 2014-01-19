package pahss_core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class PAHSSAbstractServer {
	ServerSocket s_socket;

	public PAHSSAbstractServer(ServerSocket s_socket) {
		super();
		this.s_socket = s_socket;
	}
	
	public PAHSSAbstractServer(int port) throws IOException {
		super();
		s_socket = new ServerSocket(port);
	}
	
	protected Socket acceptClient() throws IOException{
		return s_socket.accept();
	}
	
	public void closeServer(){
		try {
			s_socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
