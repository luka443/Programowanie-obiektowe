package lab3;

import java.util.Scanner;

public class DrawTriangle {

	public static void main(String[] args) {
		System.out.println("podaj wysokosc: ");
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		for(int i = 1; i <= size; i++){
		    for(int j = 1; j <= size-i; j++){
		    System.out.print(" ");
		    }
		    for(int k = 1; k<= 2*i-1; k++){
		        System.out.print("#");
		    }
		System.out.print("\n");
		}

	}

}
