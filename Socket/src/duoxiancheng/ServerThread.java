package duoxiancheng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/*
 * 服务器线程处理类
 */
public class ServerThread extends Thread {
	//和本线程相关的Socket
	String Server_send;
	Socket socket=null;
  public ServerThread(Socket socket) {
		this.socket=socket;
	}
public void run() {
	InputStream is = null;
	OutputStream os = null;
	String info=null;
	try {
		is = socket.getInputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//字节输入流
	InputStreamReader isr=new InputStreamReader(is);//将字节流转化为字符流
	BufferedReader br=new BufferedReader(isr);//为输入流添加缓冲
	
	try {
		while((info=br.readLine())!=null) {//循环读取客户端的信息
			System.out.println("我是服务器，客户端说-------》"+info);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		socket.shutdownInput();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//关闭输入流
	
	//4. 响应客户端的操作

	try {
		os = socket.getOutputStream();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Scanner scanner=new Scanner(System.in);
	System.out.println("请输入向客户端发送的信息");
	PrintWriter pw = new PrintWriter(os);
	pw.write(scanner.nextLine());
	pw.flush();
	
	//5. 关闭资源
	if (pw!=null) {
		pw.close();
	}
	if (os!=null) {
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if (br!=null) {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if (isr!=null) {
		try {
			isr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if (is!=null) {
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if (socket!=null) {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
