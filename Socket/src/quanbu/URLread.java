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
			//ͨ��URL��openstream������ȡURL�����ʾ����Դ�ֽ�������
			InputStream iStream=url.openStream();
			//���ֽ�������ת��Ϊ�ַ�������
			InputStreamReader isr =new InputStreamReader(iStream,"utf-8");//Ҫָ����ȷ�ı����ʽ��Ȼ���������
			//���ַ���������ӻ���
			BufferedReader br =new BufferedReader(isr);
			//��ȡ����
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
