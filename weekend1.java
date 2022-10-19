package lab1;
import java.util.*;
public class weekend1 {

	public static void main(String[] args) {
		
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 

		switch (day) {
		    case Calendar.SUNDAY:
		    	System.out.println("weekend");
		        break;
		    case Calendar.MONDAY:
		    	System.out.println("poniedzialek");
		        break;
		    case Calendar.TUESDAY:
		    	System.out.println("wtorek");
		        break;
		    case Calendar.THURSDAY:
		    	System.out.println("czwartek");
		        break;
		    case Calendar.SATURDAY:
		    	System.out.println("weekend");
		        break;
		    case Calendar.WEDNESDAY:
		        System.out.println("sroda");
		        break;
		    case Calendar.FRIDAY:
		    	System.out.println("piatek");
		        break;
		}

	}

	

}

