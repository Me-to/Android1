package duoxiancheng;


	import java.io.*;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.util.Scanner;

	public class zza {

	    public static void main(String[] args) {
	        try {
	            ServerSocket serverSocket =new ServerSocket(9998);
	            System.out.println("=============�򿪳ɹ�=========");
	            Socket socket =serverSocket.accept();
	            System.out.println("=============���ӳɹ�=========");
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
	                                    System.out.println("�¶ȣ�"+data[0]+"ʪ�ȣ�"+data[1]);
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
	                  System.out.println("������");
	                  String data =scanner.nextLine();
	                  PrintWriter printWriter =new PrintWriter(outputStream);
	                  printWriter.write(data+"\n");
	                  printWriter.flush();
	                  System.out.println("���ͳɹ�");
	              }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	}


