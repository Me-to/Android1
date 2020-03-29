package sock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {

		try {
			//1. �����ͻ���Socket��ָ����������ַ�Ͷ˿�
			Socket socket=new Socket("localhost",8887);
			//2. ��ȡ��������������������Ϣ
			OutputStream os=socket.getOutputStream();//�ֽ������
			PrintWriter pw=new PrintWriter(os);//���������װΪ��ӡ��
			pw.write("�û�����admin; ���� ��123");
			pw.flush();
			socket.shutdownOutput();//�ر������
			//3. ��ȡ����������ȡ
			InputStream is =socket.getInputStream();
		    InputStreamReader isr =new InputStreamReader(is);
		    BufferedReader br=new BufferedReader(isr);
		    String info1=null;
		    while ((info1=br.readLine())!=null) {
				System.out.println("���ǿͻ��ˣ�������˵��"+info1);
			}
			//4. �ر���Դ
		    br.close();
		    isr.close();
		    is.close();
		    
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
