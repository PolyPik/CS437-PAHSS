package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class PAHSSClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostName = "localhost";
		int portNumber = 1603;
		try {
			(new HBTask(new Socket(hostName,portNumber))).start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}