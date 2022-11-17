package lab1;
import java.io.*;

public class WordCounter {
	
	public static void main(String[] args) throws IOException {
		try {
			
			File inFile = null;
			for(int k = 0; k<args.length;k++) {
				if (0 < args.length && !args[k].startsWith("-") ) {
					inFile = new File(args[k]);			
				}							
			}        
            String sCurrentLine;
            int lines = 0;
            int letters = 0;                   
            int words = 0;
            BufferedReader br = new BufferedReader(new FileReader(inFile));            
            while ((sCurrentLine = br.readLine()) != null) { 
            	String NumWords[] = sCurrentLine.split(" ");
            	letters+=sCurrentLine.length();
            	words += NumWords.length;               
                lines++;                
            }
            if(args.length ==1) {
				System.out.println("linii: " + lines);
    			System.out.println("slow: " + words);
    			System.out.println("liter: : " + letters);
			}
            for(int v = 0; v<args.length; v++) {
            	switch(args[v]) {
            		case "-lw":case "-wl":
            			System.out.println("linii: " + lines);
            			System.out.println("slow: " + words);
            			break;
            		case "-lc":case "-cl":
            			System.out.println("linii: " + lines);
            			System.out.println("liter: " + letters);
            			break;
            		case "-wc":case "-cw":
            			System.out.println("liter: " + letters);
            			System.out.println("slow: " + words);
            			break;
            		case "-lwc":case "-wlc":case "-clw":case "-cwl":case "-wcl":case "-lcw":
            			System.out.println("linii: " + lines);
            			System.out.println("slow: " + words);
            			System.out.println("liter: : " + letters);
            			break;
            		case "-l":
            			System.out.println("linii: " + lines);
            			break;
            		case "-w":
            			System.out.println("slow: " + words);
            			break;
            		case "-c":
            			System.out.println("liter: " + letters);
            			break;
            	}
            }
		}
		catch(NullPointerException e) {
			System.out.print("no args");
		}              
    }
	
}
