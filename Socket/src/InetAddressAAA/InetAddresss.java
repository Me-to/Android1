package InetAddressAAA;

import java.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
public class InetAddresss {

	public static void main(String[] args) {
		
      InetAddress address = null;
	try {
		address = InetAddress.getLocalHost();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	byte[] by=address.getAddress();
        
        System.out.println("���������"+address.getHostName());
        System.out.println("IP ��ַ"+address.getHostAddress());
        System.out.println("�ֽ������IP"+Arrays.toString(by));
	}



}
