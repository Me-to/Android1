package duoxiancheng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     try {
		ServerSocket serverSocket=new ServerSocket(8887);
		Socket socket=null;
		int count=0;
		System.out.println("服务器即将启动");
	//	Socket socket=serverSocket.accept();
		while (true) {
			 socket= serverSocket.accept();
			 ServerThread serverThread=new ServerThread(socket);
			 serverThread.start();
			 count++;
			 System.out.println(count);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
