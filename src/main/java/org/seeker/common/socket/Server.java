package org.seeker.common.socket;

import java.net.*;
import java.io.*;

public class Server {
	private ServerSocket ss;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Server() throws IOException {
		try {
			ss = new ServerSocket(10000);
			while (true) {
				socket = ss.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				String line = in.readLine();
				System.out.println(line);
				out.println("you input is :" + line);
				out.close();
				in.close();
				socket.close();
			}
		} catch (IOException e) {
			ss.close();
		}
	}

	public static void main(String[] args) throws IOException {
		new Server();
	}
}