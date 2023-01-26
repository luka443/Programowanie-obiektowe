
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

public class SatBeams extends WebPage {

    protected SatBeams(String url) {
        super(url);
        this.url = url;
    }

    public ArrayList<Satellite> getSatellites() {
        List<Satellite> satellites = Collections.synchronizedList(new ArrayList<>());
        Document doc;
        try {
            doc = Jsoup.connect(this.url).timeout(0).get();
        } catch (IOException e) {
            System.out.println(e);
            return new ArrayList<>(satellites);
        }
        Element satellites_html = doc.getElementsByAttributeValue("id", "sat_grid").first();
        Elements satellites_tables = satellites_html.getElementsByClass("class_tr");

        ExecutorService pool = Executors.newFixedThreadPool(40);
        List<Callable<Object>> todo = new ArrayList<>(Integer.parseInt(satellites_tables.last().getElementsByTag("td").first().text()));
        for (Element satellite_data : satellites_tables) {
            todo.add(Executors.callable(new SatelliteInfo(satellite_data, this.url, satellites)));
        }
        try {
            List<Future<Object>> answers = pool.invokeAll(todo);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        pool.shutdown();
        return new ArrayList<>(satellites);
    }
}
class SatelliteInfo implements Runnable{
    private final Element satellite_data;
    private final String url;
    private final List<Satellite> satellites;

    public SatelliteInfo(Element satellite_data, String url, List<Satellite> satellites){
        this.url = url;
        this.satellite_data = satellite_data;
        this.satellites = satellites;
    }

    public void run(){


        // Names
        String tempNames = satellite_data.getElementsByClass("link").first().text();
        Satellite newSatellite = new Satellite(tempNames.split("\\(")[0].strip());

        // Additional names
        if (tempNames.split("\\(").length > 1) {
            for (String additionalName : tempNames.split("\\(")[1].replace(")", "").split(", ")) {
                newSatellite.addName(additionalName);
            }
        }

        // Loading page with additional data
        String link = satellite_data.getElementsByClass("link").first().attributes().get("href");
        Document docDetailed = null;
        int tries = 0;
        while (docDetailed == null && tries < 10)
            try {
                System.out.println("Trying to connect to: " + this.url + link.replace("/satellites",""));
                docDetailed = Jsoup.connect(this.url + link.replace("/satellites","")).timeout(0).get();
            } catch (IOException e) {
                try {
                    System.out.println("Failed connection to: " + this.url + link.replace("/satellites","") + "\nRetrying in 200ms");
                    sleep(200);
                    tries++;
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        if (docDetailed == null) {
            throw new RuntimeException("Couldn't connect to: " + this.url + link.replace("/satellites",""));
        }

        // Detailed information about a satellite
        Element detailedTable = docDetailed.getElementById("sat_grid1");
        Element info = detailedTable.getElementsByTag("tr").get(1);
        ArrayList<String> infoArray = new ArrayList<>(List.of(info.getElementsByTag("td").first().toString().replaceAll("<td>", "").replaceAll("<b>", "").replaceAll("</b>", "").split("<br>")));

        // NORAD
        String tempNorad = infoArray.get(3).split(": ")[1];
        if (tempNorad.equals("&nbsp;")) {
            tempNorad = link.split("=")[1];
        } else {
            tempNorad = Jsoup.parse(tempNorad).getAllElements().first().text();
        }
        newSatellite.setNorad(Integer.parseInt(tempNorad));

        // Positions
        String tempPosition = infoArray.get(2).split(": ")[1];
        String[] positions = tempPosition.replace("(", "").replace(")", "").replace("°", "").split(" ");
        newSatellite.setOrbitalPosition(Float.parseFloat(positions[0]));
        newSatellite.setOrbitalPositionDirection(positions[1].charAt(0));
        newSatellite.setSatellitePosition(Float.parseFloat(positions[2]));
        newSatellite.setSatellitePositionDirection(positions[3].charAt(0));

        // Date
        Date launchDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            newSatellite.setLaunchDate(formatter.parse(infoArray.get(6).split(": ")[1].split(" ")[0]));
        } catch (java.text.ParseException ignored) {}

        // Launch Mass
        String tempLaunchMass = infoArray.get(9).split(": ")[1];
        if (!tempLaunchMass.equals("&nbsp;")) {
            newSatellite.setLaunchMass(Integer.parseInt(tempLaunchMass));
        }
        satellites.add(newSatellite);
        System.out.println("Satellite "+newSatellite.getNames()+" added successfully");
    }
}
