package server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import dao.*;
import daoImp.*;
import entities.UserProgress;

public class Service {
	// 声明一个输出流
	DataOutputStream output = null;
	// 声明一个输入流
	DataInputStream input = null;

	SendMes sendMes = new SendMes();

	UserDao userDao = new UserDaoImp();

	public void getMethod(Socket socket) {
		String message = "";
		InputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		// InputStreamReader inputStreamReader = null;
		// BufferedReader bufferedReader = null;
		try {
			inputStream = socket.getInputStream();
			bufferedInputStream = new BufferedInputStream(socket.getInputStream());
			byte[] buf = new byte[1024];
			// inputStreamReader = new InputStreamReader(inputStream);
			// bufferedReader = new BufferedReader(inputStreamReader);
			// String temp = null;
			// while ((temp = bufferedReader.readLine()) != null) {
			// message += temp;
			bufferedInputStream.read(buf);
			message = new String(buf, "utf-8").trim();
			System.out.println(message);
		} catch (Exception e) {
		}
		String[] temp = message.split(" ");
		String method = temp[0];
		switch (method.toLowerCase()) {
		case "login":
			login(message, socket);
			break;
		case "register":
			register(message, socket);
			break;
		case "learned":
			learned(message, socket);
			break;
		default:
			error(socket);
			break;
		}
	}

	private void error(Socket socket) {
		sendMes.send("Statement wrong", socket);
	}

	public void login(String message, Socket socket) {
		String[] temp = message.split(" ");
		int user_id;
		try {
			String user_name = temp[1];
			String password = temp[2];
			if ((user_id = userDao.login(user_name, password)) != 0) {
				message = "loginResult success userId{" + user_id + "}";
				UserProgressDao userProgressDao = new UserProgressDaoImp();
				List<UserProgress> userProgress = userProgressDao.getAll(user_id);
				String temp_message = "pageId{";
				if (userProgress != null) {
					for (int i = 0; i < userProgress.size(); i++) {
						temp_message += userProgress.get(i).getPage_id();
						if (i != userProgress.size() - 1)
							temp_message += ",";
					}
				}
				temp_message += "}";
				message+=" "+temp_message;
			} else
				message = "loginResult false";
			sendMes.send(message, socket);
		} catch (Exception e) {
			e.printStackTrace();
			message = "loginResult false";
			sendMes.send(message, socket);
		}
	}

	public void register(String message, Socket socket) {
		String[] temp = message.split(" ");
		try {
			String user_name = temp[1];
			String password = temp[2];
			int gender = temp[3].equals("男") ? 1 : 0;
			int age = Integer.valueOf(temp[4]);
			String tel = temp[5];
			String mail = temp[6];
			userDao.register(user_name, password, gender, age, tel, mail);
			message = "registerResult success";
			sendMes.send(message, socket);
		} catch (Exception e) {
			e.printStackTrace();
			message = "registerResult false";
			sendMes.send(message, socket);
		}
	}

	private void learned(String message, Socket socket) {
		String[] temp = message.split(" ");
		try {
			int user_name = Integer.valueOf(temp[1]);
			int pageId = Integer.valueOf(temp[2]);
			userDao.learned(user_name, pageId);
			message = "learnedResult success";
			sendMes.send(message, socket);
		} catch (Exception e) {
			e.printStackTrace();
			message = "learnedResult false";
			sendMes.send(message, socket);
		}
	}

}
