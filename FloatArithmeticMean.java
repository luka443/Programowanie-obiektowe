package lab3;
import java.util.Scanner;

public class FloatArithmeticMean {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner;
		scanner = new Scanner(System.in);
		int i;
		float ilosc;
		float sum = 0;
		System.out.println("podaj ile liczb chcesz wpisac: ");
		ilosc = scanner.nextFloat();
		for (i = 1; i < ilosc+1; i++) {
			System.out.println("podaj: "+ i + " liczbe: \n");
			sum += scanner.nextFloat();
          
        }
		float mean;
		mean = sum/ilosc;
		System.out.println("srednia: ");
		System.out.println(String.format("%.4f", mean));
	}
	
}