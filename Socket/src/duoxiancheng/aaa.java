package duoxiancheng;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class aaa {
	static Socket socket;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		try {
			String b="1423313233";
			System.out.println("0.1");
		    socket=new Socket("192.168.1.110",2111);
		    System.out.println("0.2");
			OutputStream os =socket.getOutputStream();
			System.out.println("0.3");
			os.write(b.getBytes());
			System.out.println("0.4");
			os.close();
			System.out.println(b.getBytes());
			System.out.println("1");
		//	os.flush();
			System.out.println("2");
			//os.close();
			System.out.println("3");
		//	OutputStreamWriter osw=new OutputStreamWriter(os);
//			int a=0;
//			byte[] a=new byte[1024];
//			ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//			
//			while (a=os.write(a)) {
			InputStream is=socket.getInputStream();
			int i=0;
			byte[] bs =new byte[1024];
			ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
			while((i=is.read(bs))!=-1) {
				byteArrayOutputStream.write(bs, 0, i);
			}
			System.out.println(byteArrayOutputStream.toString());
			byteArrayOutputStream.close();
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("4");
			if (socket!=null) {
				try {
					socket.close();
					System.out.println("5");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
