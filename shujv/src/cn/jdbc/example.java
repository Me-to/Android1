package jdbc.connect;

import java.sql.Connection;

import java.sql.DriverManager;

public Connectionclass{
    public static void main(String[] args) throws Exception  {
    	Class.forName("com.mysql.jdbc.Driver");//��������
    	
    	//url���ݿ��ַ
    	String url = "jdbc:mysql://localhost:3306/qian";
    	String user = "root";
    	String password = "123";
		Connection conn = DriverManager.getConnection(url, user, password);
    }
}
