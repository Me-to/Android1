package sock;

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

		try {
			//1. ����һ����������Socket����SeverSocket,ָ���󶨵Ķ˿ڣ�������
			ServerSocket serverSocket=new ServerSocket(12345);
			//2. ����accept()������ʼ�������ȴ��ͻ��˵�����
			System.out.println("******�����������������ȴ��ͻ�����*******");
			Socket socket=serverSocket.accept();
			//3. ��ȡ������������ȡ�ͻ�����Ϣ
			InputStream is=socket.getInputStream();//�ֽ�������
			InputStreamReader isr=new InputStreamReader(is);//���ֽ���ת��Ϊ�ַ���
			BufferedReader br=new BufferedReader(isr);//Ϊ��������ӻ���
			String info=null;
			while((info=br.readLine())!=null) {//ѭ����ȡ�ͻ��˵���Ϣ
				System.out.println("���Ƿ��������ͻ���˵"+info);
			}
			socket.shutdownInput();//�ر�������
			
			//4. ��Ӧ�ͻ��˵Ĳ���
			OutputStream os =socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("��ӭ����");
			pw.flush();
			
			//5. �ر���Դ
			
			pw.close();
			os.close();
			
			br.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
