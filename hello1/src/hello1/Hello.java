package hello1;

import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Word");
		Scanner in = new Scanner(System.in);
//		System.out.println("echo:"+in.nextLine());
		int price;
		price = in.nextInt();
		System.out.println("100-"+price+"="+(100-price));
}
}