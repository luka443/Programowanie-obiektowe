package lab1;

import java.io.*;


public class LineCounter {

     public static void main(String[] args) {
      File inFile = null;
      if (0 < args.length) {
    	  inFile = new File(args[0]);
      }
        try {
            String sCurrentLine;
            int lines = 0;
            BufferedReader br = new BufferedReader(new FileReader(inFile));

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                lines++;
            }
            System.out.println(lines);

        } 

        catch (IOException e) {
            e.printStackTrace();
        } 
        

    }
}
