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
			//1. 创建客户端Socket，指定服务器地址和端口
			Socket socket=new Socket("localhost",8887);
			//2. 获取输出流，向服务器发送信息
			OutputStream os=socket.getOutputStream();//字节输出流
			PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
			pw.write("用户名：admin; 密码 ：123");
			pw.flush();
			socket.shutdownOutput();//关闭输出流
			//3. 获取输入流并读取
			InputStream is =socket.getInputStream();
		    InputStreamReader isr =new InputStreamReader(is);
		    BufferedReader br=new BufferedReader(isr);
		    String info1=null;
		    while ((info1=br.readLine())!=null) {
				System.out.println("我是客户端，服务器说："+info1);
			}
			//4. 关闭资源
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
