package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostName = "localhost";
		int portNumber = 1603;
		try{
			try (
			    Socket socket = new Socket(hostName, portNumber);
				DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			){
				String userInput;
				while ((userInput = stdIn.readLine()) != null) {
				    out.writeUTF(userInput);
				    out.flush();
				    System.out.println(in.readUTF());
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
