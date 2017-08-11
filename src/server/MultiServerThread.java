package server;

import java.net.Socket;


public class MultiServerThread extends Thread {

	private Socket socket;
	private Service service;
	SendMes sendMes=new SendMes();

	public MultiServerThread(Socket clientSocket) {
		
		this.socket=clientSocket;
		System.out.println("new client");
	}
	
	public void run()
	{
		service=new Service();
		service.getMethod(socket);

	}

	
	

}
