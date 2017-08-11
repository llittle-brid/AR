package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread {
	Service service=new Service();
	private ServerSocket serverSocket;
	public void run()
	{
		while(true)
		{
			  try {
				    Socket clientSocket = serverSocket.accept();
					MultiServerThread clientService=new MultiServerThread(clientSocket);	
					clientService.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Server() 
	{
		 try {
			serverSocket=new ServerSocket(1233);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Server server=new Server();
		server.start();
		System.out.println("server.start");
	}
}
