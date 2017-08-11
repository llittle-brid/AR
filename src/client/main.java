package client;

import java.io.*;
import java.net.Socket;

import dao.UserDao;
import daoImp.UserDaoImp;

public class main {

	
	
	public static void main(String args[])
	{
//		System.out.println(SendToServer.send("login test 123456"));
		System.out.println(SendToServer.send("register test 1 123456 5 1570000 mail@qq.com"));
	}
	
}
