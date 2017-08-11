package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class SendMes{ 

	public void send(String message, Socket socket) {
		System.out.println("send message:"+message);
		  PrintWriter printWriter=null;
		  OutputStream outputStream=null;
		 try {
			outputStream = socket.getOutputStream();// 获取一个输出流，向服务端发送信息
			  printWriter =new PrintWriter(outputStream);//将输出流包装成打印流
			  printWriter.print(message);
			  printWriter.flush();
			  socket.shutdownOutput();//关闭输出流
			  //关闭相对应的资源
		} 
		 catch (Exception e) {
			 e.printStackTrace();
			}
		 finally{
			 try {
				 System.out.println("client end");
					Thread.currentThread().stop(); 
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 }
	}

}
