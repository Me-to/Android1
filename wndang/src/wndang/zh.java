package wndang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;


public class zh {
	private static boolean k =true,l = false;
	private static String b;
	private static String a;

	
	public static void jdbcconnection() throws Exception  {
   // public static void main(String[] args) throws Exception  {
		 if (k) {
			 System.out.println("�������˺�");
				Scanner in = new Scanner(System.in);
			     a = in.next();
			     System.out.println("����������:");	
				Scanner in1 = new Scanner(System.in);
				 b = in1.next();
				 k = false;
		}
		
		 
		 
    	Class.forName("com.mysql.jdbc.Driver");//��������
    	
    	
    	//���ݿ�����
    	//url���ݿ��ַ
    	
    	String url = "jdbc:mysql://localhost:3306/qian";
    	String user = "root";
    	String password = "123";
		Connection conn = DriverManager.getConnection(url, user, password);
		//��ʾ���ӳɹ�
		
	
		//while(true){
		//��������趨
		//r1 PM2.5 
		//r2 �¶�
		//r3 ʪ��
		Random r1 = new Random();
		Random r2 = new Random();
		Random r3 = new Random();
		
		//A=r1=PM2.5
		//B=r2=�¶�
		//C=r2=ʪ��
		double A = r1.nextInt(100);
	    double B = r2.nextInt(100);
	    double C = r3.nextInt(100);
	    
		//�����ݿ���в����Ĵ���
		String sql = "INSERT INTO shujv VALUES("+A+","+B+","+C+")";
		
		//Statement st = conn.createStatement();
		
		//ִ�����
		//float row = st.executeLargeUpdate(sql);
		
		if(b.equals("123")||(a.equals("root"))) {
			if (!l) {
				System.out.println("���ӳɹ�");
				l = true;
			}
		
		System.out.println("���PM2.5�����" + A);
		System.out.println("����¶������" + B);
		System.out.println("���ʪ�������" + C);

		//�����ֵ�Ƿ񳬹��޶�ֵ������������
		if (A>25) {
			System.out.println("-------------------------");
			System.out.println("Warning  PM2.5 �¶ȹ���");
		
		};
		if (B>30) {
			System.out.println("-------------------------");
			System.out.println("Warning  �¶ȹ���");
		};
		if (C>40) {
			System.out.println("-------------------------");
		    System.out.println("Warning  ʪ�ȹ���");
		}
		
		else {}
		}
		

		else {
			System.out.println("������˺Ŵ���");
			k = true;
			l = false;
	}
		
	}
}

		
	





		
		
		
		
//		
//		}
//	 
//	 
//	 else {
//		 System.out.print("�˺Ż������������ʧ��");
//	 }
//	 
		
		
	// public static void jiance() {
		// if 
	 
	

    
	//�ͷ���Դ
	//st.close();
	//conn.close();

    
//}
