package sockd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
 
	public static void main(String[] args) {
	
		try {
			//����һ������ˣ���������ӿ�
			ServerSocket serverSocket=new ServerSocket(6666);
			//����һ��socket�����������״̬
			Socket socket=serverSocket.accept();
			
			//��ȡ����������ȡ�ͻ�����Ϣ
			InputStream is=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			
			String info=null;
			while((info=br.readLine())!=null) {
				System.out.println("�ͻ���˵��"+info);
			}
			//�ر�������
			socket.shutdownInput();
			
			
			//��ʼ�������ͻ��˷���Ϣ
			OutputStream outputStream=new Output
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
