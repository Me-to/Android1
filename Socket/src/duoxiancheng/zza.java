package duoxiancheng;


	import java.io.*;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.util.Scanner;

	public class zza {

	    public static void main(String[] args) {
	        try {
	            ServerSocket serverSocket =new ServerSocket(9998);
	            System.out.println("=============打开成功=========");
	            Socket socket =serverSocket.accept();
	            System.out.println("=============连接成功=========");
	            InputStream inputStream =socket.getInputStream();
	            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
	            OutputStream outputStream =socket.getOutputStream();
//	            ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
//	            byte[] bytes =new byte[1024];

	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    String s = null;
	                    while (true){
	                        while (true){
	                            try {
	                                if (((s=bufferedReader.readLine())!=null)){
	                                    String[] data =s.split("A");
	                                    System.out.println("温度："+data[0]+"湿度："+data[1]);
	                                }
	                            } catch (IOException e) {
	                                e.printStackTrace();
	                            }
	                        }
	                    }
	                }
	            }).start();

	              while (true){
	                  Scanner scanner =new Scanner(System.in);
	                  System.out.println("请输入");
	                  String data =scanner.nextLine();
	                  PrintWriter printWriter =new PrintWriter(outputStream);
	                  printWriter.write(data+"\n");
	                  printWriter.flush();
	                  System.out.println("发送成功");
	              }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	}


