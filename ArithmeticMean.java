package lab3;
import java.util.Scanner;

public class ArithmeticMean {
	public static void main(String[] args) {
		Scanner scanner;
		scanner = new Scanner(System.in);
		int ilosc, i;
		int sum = 0;
		System.out.println("podaj ile liczb chcesz wpisać: ");
		ilosc = scanner.nextInt();
		for (i = 1; i < ilosc+1; i++) {
			System.out.println("podaj: "+ i + " liczbe: \n");
			sum += scanner.nextInt();
          
        }
		float mean;
		mean = sum/ilosc;
		System.out.println("srednia: "+ mean);
	}
	
}
