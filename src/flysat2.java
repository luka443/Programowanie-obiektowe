import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class flysat2 extends WebPage {

    String url;
    protected flysat2(String url) {
        super(url);
        this.url = url;
    }

    @Override
    public ArrayList<Satellite> getSatellites() {
        List<Satellite> satellitesList = new ArrayList<Satellite>();
        String url = this.url;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements media = doc.select("table[border='1'][width='494'][bgcolor='#000099'] tbody tr");
            int j;
            for (Element el : media) {
                if (el.elementSiblingIndex() == 0) {
                    continue;
                }
                if (Objects.requireNonNull(el.children().last()).elementSiblingIndex() == 5) {
                    j = 0;
                } else {
                    j = -1;
                }

                String name = el.child(j + 1).text();
                String pos = el.child(j + 2).text();
                String band = el.child(j + 4).text();

                String[] pos2 = pos.split("°");
                if (pos2[1].contains(" ")) {
                    pos2[1] = pos2[1].replace(" ", "");
                }
                char c = pos2[1].charAt(0);

                String[] names;
                if (name.contains("/") || name.contains("(")) {
                    name = name.replace("(", ",").replace(")", "");
                    names = name.split("/");
                } else {
                    names = new String[1];
                    names[0] = name;
                }

                Satellite sat = new Satellite(names[0]);
                if (names.length > 1) {
                    sat.addName(names[1]);
                }

                sat.setSatellitePosition(Float.parseFloat(pos2[0]));
                sat.setSatellitePositionDirection(c);
                sat.setBand(band);
                satellitesList.add(sat);
            }

        }
        catch (IOException ex1){

        }
        return (ArrayList<Satellite>) satellitesList;
    }
}
