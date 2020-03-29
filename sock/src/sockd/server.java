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
			//创建一个服务端，并绑定这个接口
			ServerSocket serverSocket=new ServerSocket(6666);
			//创建一个socket来监听这个的状态
			Socket socket=serverSocket.accept();
			
			//获取输入流来读取客户端信息
			InputStream is=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			
			String info=null;
			while((info=br.readLine())!=null) {
				System.out.println("客户端说："+info);
			}
			//关闭输入流
			socket.shutdownInput();
			
			
			//开始进行往客户端发消息
			OutputStream outputStream=new Output
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
