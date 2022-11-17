package lab1;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.Random;

public class MultipicationTable {
		
	public static void main(String[] args) throws IOException {
				
	
		Properties Props = new Properties();
		File f = new File("/javaaa/lab1/config901122.properties");
		if(f.exists()) { 
		    System.out.println("istnieje");
		}
		if(!f.exists()) {			
			Props.store(new FileOutputStream("config901122.properties"),null);	
			System.out.println("tworze plik");
		}		
		Props.setProperty("wartoscMinimum", "1");
		Props.setProperty("wartoscMaximum", "10");
		Props.setProperty("Procent", "0.7");
		Props.setProperty("powtorzenMinimum", "10");
		Props.setProperty("powtorzenMaximum", "25");
		int Min = ToInt(Props.getProperty("wartoscMinimum"));
		int Max = ToInt(Props.getProperty("wartoscMaximum"));
		int powMin = ToInt(Props.getProperty("powtorzenMinimum"));
		int powMax = ToInt(Props.getProperty("powtorzenMaximum"));							
		Float procent =Float.parseFloat(Props.getProperty("Procent"));
		double i = 0;
		int y= 1;
		Random rand = new Random();
		double points = 0;
		while(i<powMax) {
			if(i>0) {
				if((((points/i)>procent) && i>powMin)) break;
			}
			int n = rand.nextInt(Max)+Min;
			int m = rand.nextInt(Max)+Min;
			System.out.println(y+") "+n + "*" + m+ "= ");
			Scanner scan = new Scanner(System.in); 
			int answer = scan.nextInt();
			if(answer ==(n*m)) {
				points++;
			}
			i++;
			y++;
		}
		System.out.println("procent dobrych odpowiedzi" + (points)/(i));
		
	}
	static int ToInt(String val) {
		Integer val1 = Integer.parseInt(val);
		return val1;
	}

}