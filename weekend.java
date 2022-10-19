package lab1;
import java.util.*;
public class weekend {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 

		switch (day) {
		    case Calendar.SUNDAY:
		        break;
		    case Calendar.MONDAY:
		    	System.out.println("liczba dni do weekendu: 4");
		        break;
		    case Calendar.TUESDAY:
		    	System.out.println("liczba dni do weekendu: 3");
		        break;
		    case Calendar.THURSDAY:
		    	System.out.println("liczba dni do weekendu: 1");
		        break;
		    case Calendar.SATURDAY:
		        break;
		    case Calendar.WEDNESDAY:
		        System.out.println("liczba dni do weekendu: 2");
		        break;
		    case Calendar.FRIDAY:
		    	System.out.println("liczba dni do weekendu: 0");
		        break;
		}

	}

	

}
