package lab1;
import java.util.*;
public class weekend2 {

	public static void main(String[] args) {
		
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 

		switch (day) {
		    case Calendar.SUNDAY:
		    	System.out.println("mamy weekend!");
		        break;
		    case Calendar.MONDAY:
		    	System.out.println("poniedzialek, do weekendu zostaly 4 dni");
		        break;
		    case Calendar.TUESDAY:
		    	System.out.println("wtorek, do weekendu zostały 3 dni");
		        break;
		    case Calendar.THURSDAY:
		    	System.out.println("czwartek, do weekendu został 1 dzień");
		        break;
		    case Calendar.SATURDAY:
		    	System.out.println("mamy weekend!");
		        break;
		    case Calendar.WEDNESDAY:
		        System.out.println("sroda, do weekendu zostały 2 dni");
		        break;
		    case Calendar.FRIDAY:
		    	System.out.println("piatek, do weekendu pozostało 0 dni");
		        break;
		}

	}

	

}
