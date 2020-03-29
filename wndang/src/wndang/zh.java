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
			 System.out.println("请输入账号");
				Scanner in = new Scanner(System.in);
			     a = in.next();
			     System.out.println("请输入密码:");	
				Scanner in1 = new Scanner(System.in);
				 b = in1.next();
				 k = false;
		}
		
		 
		 
    	Class.forName("com.mysql.jdbc.Driver");//加载驱动
    	
    	
    	//数据库连接
    	//url数据库地址
    	
    	String url = "jdbc:mysql://localhost:3306/qian";
    	String user = "root";
    	String password = "123";
		Connection conn = DriverManager.getConnection(url, user, password);
		//提示连接成功
		
	
		//while(true){
		//随机数的设定
		//r1 PM2.5 
		//r2 温度
		//r3 湿度
		Random r1 = new Random();
		Random r2 = new Random();
		Random r3 = new Random();
		
		//A=r1=PM2.5
		//B=r2=温度
		//C=r2=湿度
		double A = r1.nextInt(100);
	    double B = r2.nextInt(100);
	    double C = r3.nextInt(100);
	    
		//对数据库进行操作的代码
		String sql = "INSERT INTO shujv VALUES("+A+","+B+","+C+")";
		
		//Statement st = conn.createStatement();
		
		//执行语句
		//float row = st.executeLargeUpdate(sql);
		
		if(b.equals("123")||(a.equals("root"))) {
			if (!l) {
				System.out.println("连接成功");
				l = true;
			}
		
		System.out.println("输出PM2.5随机数" + A);
		System.out.println("输出温度随机数" + B);
		System.out.println("输出湿度随机数" + C);

		//检测数值是否超过限定值，来进行提醒
		if (A>25) {
			System.out.println("-------------------------");
			System.out.println("Warning  PM2.5 温度过高");
		
		};
		if (B>30) {
			System.out.println("-------------------------");
			System.out.println("Warning  温度过高");
		};
		if (C>40) {
			System.out.println("-------------------------");
		    System.out.println("Warning  湿度过高");
		}
		
		else {}
		}
		

		else {
			System.out.println("密码或账号错误");
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
//		 System.out.print("账号或密码错误连接失败");
//	 }
//	 
		
		
	// public static void jiance() {
		// if 
	 
	

    
	//释放资源
	//st.close();
	//conn.close();

    
//}
