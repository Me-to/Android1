package wndang;

import java.util.Scanner;

public class a {

	
	public static void main(String[] args) {
		char a='A';
		Scanner scanner =new Scanner(System.in);
		int b=scanner.nextInt();
		// TODO Auto-generated method stub
      for(int i=1;i<=b;i++) {
	  
	  for (int j = 1; j<i+2;j++) {
		  System.out.print(a);
		
	}
	  System.out.println();
	  a=(char) (a+Character.valueOf((char) 1));
	 
//	  System.out.printf("%c\n",a+1);
  }
		System.out.println();
	}

}
