package lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DrawTriangle {

	public static void main(String[] args) {
	  try {
		System.out.println("podaj wysokosc: ");
		Scanner scan = new Scanner(System.in);
		int c = scan.nextInt();
		if(c<2) {
			System.out.println("rozmiar mniejszy od 2");
		}
		else {			
			for(int i = 1; i <= c; i++){
		    	for(int j = 1; j <= c-i; j++){
		    	System.out.print(" ");
		    	}
		    	for(int k = 1; k<= 2*i-1; k++){
		        	System.out.print("#");
		    	}
		    System.out.print("\n");
			}

		}
	  }
	  catch(InputMismatchException e) {
		  System.out.println("blad! podaj liczbe calkowita ");
	  }
	  
	}

}