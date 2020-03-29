package duoxiancheng;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
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
				Socket socket=new Socket("192.168.1.115",2111);
				OutputStream os=socket.getOutputStream();
				
			//	 OutputStream fos  = new FileOutputStream("textw.txt");//流的打开
		            
		            String outString = "write 123456写出数据";
		            
		            byte[] output = outString.getBytes();
		         
		            //流的写入，将文本文件获取为字节数组
		            BufferedWriter bf= new BufferedWriter(os);
		            System.out.println(output.length);
		            bf.write(output);//流的读取
		            
		            os.close();
//				int i=0;
//				byte[] bs =new byte[1024];
//				ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
//				byteArrayOutputStream.write(bs, 0,bs.length);
//				byteArrayOutputStream.close();
//			    
//				
//				byte[] a=new byte[1024];
//				Arrs
//				DataOutputStream dos=new DataOutputStream(os);
//				dos.writeUTF("dadfasdf");
//				dos.flush();
//				socket.close();
				System.out.println("---------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
