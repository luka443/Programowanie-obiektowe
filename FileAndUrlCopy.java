package lab1;
import java.net.*;
import java.io.*;

public class FileAndUrlCopy {
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("http://www.agh.edu.pl");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        StringBuilder  stringBuilder = new StringBuilder();
        String inputLine = null;
        while ((inputLine = in.readLine()) != null)
        	stringBuilder.append(inputLine);
            System.out.println(inputLine);
        in.close();
        System.out.println(stringBuilder.toString());
        BufferedWriter writer = new BufferedWriter(new FileWriter("requirements.txt"));
        writer.write(stringBuilder.toString());
        
        writer.close();
    }
}