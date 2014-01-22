package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import pahss_core.PAHSSAbstractServer;

public class EchoServer extends PAHSSAbstractServer{

	public EchoServer(int port) throws IOException {
		super(port);
		// TODO Auto-generated constructor stub
	}

	public EchoServer(ServerSocket s_socket) {
		super(s_socket);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostName = "localhost";
		int portNumber = 1603;
		Scanner input = new Scanner(System.in);
		String in_message = "";
		
		try {
			EchoServer testserv = new EchoServer(portNumber);
			System.out.println("Server ready to accept");
			testserv.acceptClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
