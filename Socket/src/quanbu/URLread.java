package quanbu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLread {

	public static void main(String[] args) {

		try {
			URL url=new URL("http://www.baidu.com");
			//通过URL的openstream方法获取URL对象表示的资源字节输入流
			InputStream iStream=url.openStream();
			//将字节输入流转化为字符输入流
			InputStreamReader isr =new InputStreamReader(iStream,"utf-8");//要指定正确的编码格式不然会出现乱码
			//将字符输入流添加缓冲
			BufferedReader br =new BufferedReader(isr);
			//读取数据
			String data=br.readLine();
			while(data!=null) {
				System.out.println(data);
				data=br.readLine();
			}
			br.close();
			isr.close();
			iStream.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
