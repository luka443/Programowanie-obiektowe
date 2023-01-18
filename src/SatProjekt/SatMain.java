package SatProjekt;
import java.lang.*;
import java.util.*;

public class SatMain {

    public static void main(String[] args) throws Exception {

        flysat object = new flysat("https://flysat.com/en/satellitelist");
        List<Satellite> sat_l = object.getSatellites();

        for (Satellite sat : sat_l){

            System.out.println(" name: " +sat.getNames()+ "   band: " +sat.getBand()

                   + " SatellitePosition: " + sat.getSatellitePosition());


            sat.toString();

        }


    }
}
