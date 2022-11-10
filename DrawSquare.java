package lab3;
import java.util.Scanner;
public class DrawSquare {

	public static void main(String[] args) {
		System.out.println("podaj dlugosc boku: ");
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		char[][] arr = new char[size][size];
		int i,n,m;
		int j;
		for(i = 0; i <size; i++) {
		
			for(j = 0; j<size; j++) {
				if(j ==0 || j==size-1 || i==0 || i==size -1) {
					arr[i][j] = '#';
				}
			}
		}
		
		
		
		
		
		for (n = 0; n < size; n++) {
	           for (m = 0; m < size; m++) {
	               System.out.printf(arr[n][m] + " ");
	           }
	           System.out.println();
	       }

	}

}
