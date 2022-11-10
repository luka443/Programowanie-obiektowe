package lab1;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileCopy {

    public static void main(String[] args) throws IOException {
    	 File inFile = null;
    	 File inFile1 = null;
         if (0 < args.length) {
       	  inFile = new File(args[0]);
       	  inFile1 = new File(args[1]);
         }

        var source = new FileReader(inFile);
        var dest = new FileReader(inFile1);

        try (var fis = new FileInputStream(inFile);
             var fos = new FileOutputStream(inFile1)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {

                fos.write(buffer, 0, length);
            }
        
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Nie ma takiego pliku");
        }
        catch (IOException e) {
			System.err.println("IOException");
		}
    }
}