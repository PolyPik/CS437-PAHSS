package test;

import java.io.*;
import java.net.*;


public class HBTask extends Thread{
	Socket socket;
	PrintWriter out;
	
	public HBTask(Socket c_socket) {
		try {
			socket = c_socket;
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void run() {
		while(true){
			out.println("HB");
			System.out.println("HB");
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}