package client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendToServer {

	public static String send(String message)
	{

		Socket socket=null;
		BufferedInputStream bufferedInputStream = null;
		String info="";
		try {
			socket=new Socket("10.63.216.209",1233);
			OutputStream outputStream=socket.getOutputStream();
			 outputStream.write(message.getBytes());
//			PrintWriter printWriter=new PrintWriter(outputStream);
//			 printWriter.print(message);
//			 printWriter.flush();
			socket.shutdownOutput();
			InputStream inputStream=socket.getInputStream();
			bufferedInputStream = new BufferedInputStream(socket.getInputStream());
			byte[] buf = new byte[1024];
			bufferedInputStream.read(buf);
			message=new String(buf,"utf-8").trim();
			System.out.println(message);
			inputStream.close();
			outputStream.close();
//	        socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
		
	}
}
