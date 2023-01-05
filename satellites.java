import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class satellites {

     public String[] satNames;
     public int Norad;
     public int OrbPosition;
     public String model;

     public satellites(URL urlStrony){
         //parsowanie
     }

    public static boolean compare(int OrbitalPos1, int OrbitalPos2){
        return false;
    }
    public static void ToString(){
    }
    public static ArrayList<Integer> getList(ArrayList<Integer> list){

         return list;
    }

    public static void main(String[] args){
        URL url;
        try{
           url = new URL("");
           new satellites(url);
        }
        catch (IOException ex){

        }
    }
}

// zwraca liste satelitow(obiektow?) z kilku url