package quanbu;

import java.net.MalformedURLException;
import java.net.URL;

public class URLLL {

	public static void main(String[] args) {

		try {
			URL zhihu=new URL("https://www.zhihu.com/");
			URL url =new URL(zhihu,"/idex.html?username=tom#test");
			System.out.println("Э�飺"+url.getProtocol());
			System.out.println("������"+url.getHost());
			//ûָ���˿���ʹ��Ĭ�ϵĶ˿ںţ���ʱ�˿ںŷ��ص�ֵΪ-1
			System.out.println("�˿ڣ�"+url.getPort());
			System.out.println("�ļ�·����"+url.getPath());
			System.out.println("���·����"+url.getRef());
			System.out.println("��ѯ�ַ�����"+url.getQuery());
			
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
