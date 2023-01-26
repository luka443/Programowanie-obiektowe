import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LyngSat extends WebPage {

    String url;
    protected LyngSat(String url) {
        super(url);
        this.url = url;
    }

    @Override
    public ArrayList<Satellite> getSatellites() {
        List<Satellite> satellitesList = new ArrayList<Satellite>();
        String url = this.url;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows = getTableRows(doc);

            //Removing elements with wildcard attributes in names (for example "incl.")
            for (int i = 0; i < rows.size(); i++) {
                String found = rows.get(i).select("td > font > font > i").toString();
                if (!found.equals("")) {
                    rows.remove(i);
                }
            }
            int i=0;
            while (i < rows.size()) {
                float position = Float.parseFloat(getNthFieldText(rows.get(i), 1).split("°")[0]);
                String names = String.valueOf(getNthFieldText(rows.get(i), 2).split("/"));
                Satellite sat = new Satellite(names);
                satellitesList.add(sat);
                if (rows.get(i).select("td:nth-child(1)").attr("rowspan") != "") {
                    int rowCount = Integer.parseInt(rows.get(i).select("td:nth-child(1)").attr("rowspan"));

                    //First satellite is parsed normally

                    sat.setSatellitePosition(position);
                    satellitesList.add(sat);
                    i++;

                    //Iterate through the following elements in that rowspan and add satellites with changed names
                    for(int j = 1; j < rowCount; j++) {
                        names = String.valueOf(getNthFieldText(rows.get(i), 1).split("/"));
                        satellitesList.add(sat);
                        i++;
                    }
                } else {
                    sat.setSatellitePosition(position);
                    satellitesList.add(sat);
                    i++;
                }
            }



        }
        catch (IOException ex1){

        }
        return (ArrayList<Satellite>) satellitesList;
    }
    private Elements getTableRows(Document doc) {

        Elements rows = new Elements();

        Elements firstTableRows = doc.select("body > div > table > tbody > tr > td:nth-child(2) > table:nth-child(8) > tbody > tr > td:nth-child(1) > table > tbody > tr");
        Elements secondTableRows = doc.select("body > div > table > tbody > tr > td:nth-child(2) > table:nth-child(8) > tbody > tr > td:nth-child(2) > table > tbody > tr");

        rows.addAll(firstTableRows);
        rows.addAll(secondTableRows);

        return  rows;
    }

    private String getNthFieldText(Element row, Integer index) {
        return row.select("td:nth-child(" + index + ")").text();
    }
}
