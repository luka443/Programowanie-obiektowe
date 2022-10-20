package lab3;
import java.util.Date;
import java.text.SimpleDateFormat;

public class zad5 {

	public static void main(String[] args) {
		System.out.println("Witaj! dzisiaj jest : " + (new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")).format(new Date()));
		

	}

}
