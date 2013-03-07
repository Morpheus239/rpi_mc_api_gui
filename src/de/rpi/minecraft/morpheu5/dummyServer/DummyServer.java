package de.rpi.minecraft.morpheu5.dummyServer;

import java.io.*;
import java.net.*;

public class DummyServer {

	static ServerSocket myServerSocket;

	public static void main(String[] args) throws IOException {

		myServerSocket = new ServerSocket(4711);
		DummyServer server = new DummyServer();

	}

	public DummyServer() throws IOException {

		while (true) {

			Socket client = myServerSocket.accept();

			write(client, "Bitte Eingabe übertragen");
			String eingabe = read(client);
			write(client, "Ihre Eingabe ist: " + eingabe);

		}

	}

	private void write(Socket client, String text) throws IOException {

		PrintWriter w = new PrintWriter(new OutputStreamWriter(
				client.getOutputStream()));
		w.write(text);
		w.close();

	}

	private String read(Socket client) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(client.getInputStream()));
		char[] buffer = new char[200];
		int anzahlZeichen = bufferedReader.read(buffer, 0, 200);
		String nachricht = new String(buffer, 0, anzahlZeichen);
		bufferedReader.close();
		return nachricht;

	}
}
