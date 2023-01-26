import java.util.List;

public class Main {


    public static void main(String[] args) {
        System.out.println("Creating a class FlySat");
        WebPage[] sat = new WebPage[3];
        sat[0]=new FlySat("https://www.flysat.com/en/satellitelist");
        sat[1]=new SatBeams("https://satbeams.com/satellites");
        sat[2]=new LyngSat("https://www.lyngsat.com/europe.html");
        
        for(int i =0; i <sat.length;i++){
            System.out.println("Getting all the satellites");
            List<Satellite> Satellites =  sat[i].getSatellites();
            System.out.println("Printing all the satellite's info "+Satellites.size());
            for(Satellite s:Satellites){
                System.out.println(s.toString());
            }
        }
    }
    
}
