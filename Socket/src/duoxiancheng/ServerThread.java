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
 * �������̴߳�����
 */
public class ServerThread extends Thread {
	//�ͱ��߳���ص�Socket
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
	}//�ֽ�������
	InputStreamReader isr=new InputStreamReader(is);//���ֽ���ת��Ϊ�ַ���
	BufferedReader br=new BufferedReader(isr);//Ϊ��������ӻ���
	
	try {
		while((info=br.readLine())!=null) {//ѭ����ȡ�ͻ��˵���Ϣ
			System.out.println("���Ƿ��������ͻ���˵-------��"+info);
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
	}//�ر�������
	
	//4. ��Ӧ�ͻ��˵Ĳ���

	try {
		os = socket.getOutputStream();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Scanner scanner=new Scanner(System.in);
	System.out.println("��������ͻ��˷��͵���Ϣ");
	PrintWriter pw = new PrintWriter(os);
	pw.write(scanner.nextLine());
	pw.flush();
	
	//5. �ر���Դ
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
