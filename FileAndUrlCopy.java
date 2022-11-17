package lab1;
import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.lang.NullPointerException;

public class FileAndUrlCopy {

    public static void main(String[] args) throws IOException {
    	 File inFile = null;
    	 File inFile1 = null;
    	 URL url;
    	 InputStream fis = null;
    	 OutputStream fos = null;
    	 try {
    		 if(args.length == 0) {
    			 System.out.println("brak argumentów programu");
    		 }
    		 else if(args.length ==1 && args[0].startsWith("http")) {
    			 url = new URL(args[0]);       	  	    
    			 fis = new URL(args[0]).openStream(); 
    			 byte[] buffer = new byte[1024];
    			 int length;
    			 fos = new FileOutputStream("Aplik.txt");                                                 	 
    		 }   	
    		 else if (0 < args.length && args[0].startsWith("http")){
    			 inFile1 = new File(args[1]);    
    			 fos = new FileOutputStream(inFile1);
    			 url = new URL(args[0]);       	  	    
    			 fis = new URL(args[0]).openStream();       	  	       	  	       	  
    		 }
    		 else if(0 < args.length) {
    			 inFile1 = new File(args[1]);    
    			 fos = new FileOutputStream(inFile1);
    			 inFile = new File(args[0]);       	        	  
    			 fis = new FileInputStream(inFile);                   			
    		 }   
    		 
    		 	byte[] buffer = new byte[1024];
    		 	int length;
    		 	while ((length = fis.read(buffer)) > 0) {        	
    		 		fos.write(buffer, 0, length);
    		 	}
    	 }
    	 catch(NullPointerException e) {}
    	 catch (UnknownHostException e) { 
    		    System.out.println("zly adres");
    		}    	 
    	 catch (ConnectException e) {   
 		    System.out.println("odmowa dostepu");
 	 }

    }
}