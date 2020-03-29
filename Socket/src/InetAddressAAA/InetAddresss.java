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
        
        System.out.println("计算机名称"+address.getHostName());
        System.out.println("IP 地址"+address.getHostAddress());
        System.out.println("字节数组的IP"+Arrays.toString(by));
	}



}
