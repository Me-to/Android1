package quanbu;

import java.net.MalformedURLException;
import java.net.URL;

public class URLLL {

	public static void main(String[] args) {

		try {
			URL zhihu=new URL("https://www.zhihu.com/");
			URL url =new URL(zhihu,"/idex.html?username=tom#test");
			System.out.println("协议："+url.getProtocol());
			System.out.println("主机："+url.getHost());
			//没指定端口则使用默认的端口号，此时端口号返回的值为-1
			System.out.println("端口："+url.getPort());
			System.out.println("文件路径："+url.getPath());
			System.out.println("相对路径："+url.getRef());
			System.out.println("查询字符串："+url.getQuery());
			
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
